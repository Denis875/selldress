package main.controller;

import main.model.Basket;
import main.model.Order;
import main.model.Status;
import main.repository.BasketRepository;
import main.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class OrderController
{
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    BasketRepository basketRepository;

    @GetMapping(value = "/client/order/")
    @ResponseBody
    public List<Order> list()
    {
        Iterable<Order> orderIterable = orderRepository.findAll();
        ArrayList<Order> orders = new ArrayList<>();
        for(Order order : orderIterable)
        {
            orders.add(order);
        }
        return orders;
    }

    @GetMapping(value = "/client/{id}/order/")
    @ResponseBody
    public List<Order> getOrders(@PathVariable int id)
    {
        Iterable<Order> orderIterable = orderRepository.findAll();
        ArrayList<Order> orders = new ArrayList<>();
        for(Order order : orderIterable)
        {
            orders.add(order);
        }
        for(int i = 0; i < orders.size();)
        {
            if(orders.get(i).getClient().getIdClient() == id)
            {
                i++;
                continue;
            }
            orders.remove(i);
        }
        return orders;
    }
    @PostMapping(value = "/client/order")
    @ResponseBody
    public void add(Order order)
    {
        order.setTotalPrice(0);
        orderRepository.save(order);
    }
    @PutMapping("/client/order/{id}")
    public void updateOrders(@PathVariable Integer id, Status status)
    {
        Optional<Order> orders = orderRepository.findById(id);
        Order order = orders.get();
        order.setStatus(status);
        if(order.getStatus() == Status.orderCancellation)
        {
            Iterable<Basket> basketIterable = basketRepository.findAll();
            ArrayList<Basket> basketArrayList = new ArrayList<>();
            for(Basket basket : basketIterable)
            {
                if(basket.getOrder().getIdOrder() == id)
                {
                    basketArrayList.add(basket);
                }
            }
            for(int i = 0; i < basketArrayList.size(); i++)
            {
                Basket basket = basketArrayList.get(i);
                basket.getProduct().setCount(basket.getProduct().getCount() + basket.getCount());
            }
        }
        orderRepository.save(order);
    }
}
