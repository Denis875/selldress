package main.controller;

import main.model.Basket;
import main.model.Discount;
import main.model.Status;
import main.repository.BasketRepository;
import main.repository.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class BasketController
{
    @Autowired
    BasketRepository basketRepository;
    @Autowired
    DiscountRepository discountRepository;

    @GetMapping("/client/{idClient}/order/{idOrder}/basket")
    @ResponseBody
    public List<Basket> basketList(@PathVariable int idClient, @PathVariable int idOrder)
    {
        Iterable<Basket> basketIterable = basketRepository.findAll();
        ArrayList<Basket> basketArrayList = new ArrayList<>();
        for(Basket basket : basketIterable)
        {
            basketArrayList.add(basket);
        }
        for(int i = 0; i < basketArrayList.size();)
        {
            if(basketArrayList.get(i).getOrder().getIdOrder() == idOrder && basketArrayList.get(i).getOrder().getClient().getIdClient() == idClient)
            {
                i++;
                continue;
            }
            basketArrayList.remove(i);
        }
        return basketArrayList;
    }

    @GetMapping("/client/order/basket/")
    @ResponseBody
    public List<Basket> list()
    {
        Iterable<Basket> basketIterable = basketRepository.findAll();
        ArrayList<Basket> basketArrayList = new ArrayList<>();
        for(Basket basket : basketIterable)
        {
            basketArrayList.add(basket);
        }
        return basketArrayList;
    }

    @PostMapping("/client/order/basket/")
    @ResponseBody
    public void add(Basket basket)
    {
        if(basket.getProduct().getCount() - basket.getCount() >= 0 && basket.getOrder().getStatus() != Status.orderCancellation)
        {
            try
            {
                setDiscount(basket);
            } catch (ParseException e)
            {
                throw new RuntimeException(e);
            }
            if(basket.getDiscount() != null)
            {
                int price = basket.getProduct().getPrice() * basket.getCount();
                basket.setPrice(price - price * basket.getDiscount().getPercent() / 100);
            }
            else
            {
                basket.setPrice(basket.getProduct().getPrice() * basket.getCount());
            }
                basket.getOrder().setTotalPrice(basket.getOrder().getTotalPrice() + basket.getPrice());
                basket.getProduct().setCount(basket.getProduct().getCount() - basket.getCount());
                basketRepository.save(basket);

        }
    }

    @DeleteMapping("/client/order/basket/{id}")
    @ResponseBody
    public void delete(@PathVariable int id)
    {
        Optional<Basket> basket = basketRepository.findById(id);
        basket.get().getOrder().setTotalPrice(basket.get().getOrder().getTotalPrice() - basket.get().getPrice());
        basket.get().getProduct().setCount(basket.get().getProduct().getCount() + basket.get().getCount());
        basket.get().setOrder(null);
        basket.get().setProduct(null);
        Basket baskets = basket.get();
        basketRepository.delete(baskets);
    }
    public void setDiscount(Basket basket) throws ParseException {
        Iterable<Discount> discountIterable = discountRepository.findAll();
        ArrayList<Discount> discountArrayList = new ArrayList<>();
        Date dateNow = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat formatBirthday = new SimpleDateFormat("dd.MM");
        for(Discount discount : discountIterable)
        {
            discountArrayList.add(discount);
        }
        for(int i = 0; i < discountArrayList.size(); i++)
        {
            Discount discount = discountArrayList.get(i);
            try
            {
                Date dateStart = format.parse(discount.getDateStart());
                Date dateFinish = format.parse(discount.getDateFinish());
                if (dateNow.after(dateStart) && dateNow.before(dateFinish) || dateNow.equals(dateStart) || dateNow.equals(dateFinish)) {
                    if (discount.getTitleDiscount().equals(basket.getProduct().getCategoryProduct().getTitleCategory())) {
                        if (basket.getDiscount() == null) {
                            basket.setDiscount(discount);
                        } else if (basket.getDiscount().getPercent() < discount.getPercent()) {
                            basket.setDiscount(discount);
                        }
                    }
                    if (discount.getTitleDiscount().equals(basket.getProduct().getTitleProduct())) {
                        if (basket.getDiscount() == null) {
                            basket.setDiscount(discount);
                        } else if (basket.getDiscount().getPercent() < discount.getPercent()) {
                            basket.setDiscount(discount);
                        }
                    }
                }
            }
            catch (Exception e)
            {
                e.getMessage();
            }
            if(discount.getDateStart() == null && discount.getDateFinish() == null
                    && discount.getTitleDiscount().equals("birthday")
                    && formatBirthday.format(dateNow).equals(formatBirthday.format(formatBirthday.parse(basket.getOrder().getClient().getBirthday()))))
            {
                if(basket.getDiscount() == null)
                {
                    basket.setDiscount(discount);
                }
                else if(basket.getDiscount().getPercent() < discount.getPercent())
                {
                    basket.setDiscount(discount);
                }
            }
        }
    }
}
