package main.controller;

import main.model.Basket;
import main.model.Status;
import main.repository.BasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class BasketController
{
    @Autowired
    BasketRepository basketRepository;

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
            basket.setPrice(basket.getProduct().getPrice() * basket.getCount());
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
}
