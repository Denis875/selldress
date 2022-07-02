package main.controller;

import main.DTO.ProductDTO;
import main.DTO.WarehouseDTO;
import main.MapService;
import main.repository.ProductRepository;
import main.repository.WarehouseRepository;
import main.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
public class WarehouseController
{
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    MapService mapService;

    @GetMapping(value = "/warehouse/")
    @ResponseBody
    public List<WarehouseDTO> list()
    {
        List<WarehouseDTO> warehouse = mapService.getAllWarehouse();
        return warehouse;
    }

    @PostMapping(value = "/warehouse/", params = "products")
    @ResponseBody
    public void add(Warehouse warehouse, @RequestParam(value = "products") int id)

    {
        Optional<Product> products = productRepository.findById(id);
        Product product = products.get();
        if(warehouse.getCountArrive() + products.get().getCount() >= 0)
        {
            product.setCount(warehouse.getCountArrive() + products.get().getCount());
            warehouseRepository.save(warehouse);
            productRepository.save(product);
        }

    }
}
