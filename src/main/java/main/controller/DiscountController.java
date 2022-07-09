package main.controller;

import main.model.Discount;
import main.repository.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DiscountController
{
    @Autowired
    DiscountRepository discountRepository;

    @GetMapping("/discount/")
    @ResponseBody
    public List<Discount> list()
    {
        Iterable<Discount> discountIterable = discountRepository.findAll();
        ArrayList<Discount> discountArrayList = new ArrayList<>();
        for(Discount discount : discountIterable)
        {
            discountArrayList.add(discount);
        }
        return discountArrayList;
    }
    @PostMapping("/discount/")
    @ResponseBody
    public void add(Discount discount)
    {
        discountRepository.save(discount);
    }
}
