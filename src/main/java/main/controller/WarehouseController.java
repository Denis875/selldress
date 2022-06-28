package main.controller;

import main.DTO.WarehouseDTO;
import main.MapService;
import main.repository.WarehouseRepository;
import main.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class WarehouseController
{
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    MapService mapService;

    @GetMapping(value = "/warehouse/")
    @ResponseBody
    public List<WarehouseDTO> list()
    {
        List<WarehouseDTO> warehouse = mapService.getAllWarehouse();
        return warehouse;
    }

    @PostMapping(value = "/warehouse/")
    @ResponseBody
    public void add(Warehouse warehouse)
    {
        warehouseRepository.save(warehouse);
    }
}
