package main.DTO;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import main.model.Product;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

public class WarehouseDTO
{
    private int idWarehouseSection;
    private int countArrive;
    private Set<ProductDTO> products = new HashSet<>();

    public int getIdWarehouseSection() {
        return idWarehouseSection;
    }

    public void setIdWarehouseSection(int idWarehouseSection) {
        this.idWarehouseSection = idWarehouseSection;
    }

    public int getCountArrive() {
        return countArrive;
    }

    public void setCountArrive(int countArrive) {
        this.countArrive = countArrive;
    }

    public Set<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductDTO> products) {
        this.products = products;
    }
}
