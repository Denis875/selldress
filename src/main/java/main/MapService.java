package main;

import main.DTO.ProductDTO;
import main.DTO.WarehouseDTO;
import main.model.Product;
import main.model.Warehouse;
import main.repository.ProductRepository;
import main.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MapService
{
    @Autowired
    ProductRepository productRepository;
    @Autowired
    WarehouseRepository warehouseRepository;

    public List<ProductDTO> getAllProduct()
    {
        return ((List<Product>)productRepository.findAll()).stream().map(this::toDto).collect(Collectors.toList());
    }

    public List<WarehouseDTO> getAllWarehouse()
    {
        return ((List<Warehouse>)warehouseRepository.findAll()).stream().map(this::toDto).collect(Collectors.toList());
    }

    public ProductDTO toDto(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setIdProduct(product.getIdProduct());
        productDTO.setTitleProduct(product.getTitleProduct());
        productDTO.setColor(product.getColor());
        productDTO.setSize(product.getSize());
        productDTO.setPrice(product.getPrice());
        productDTO.setCategoryProduct(product.getCategoryProduct());
        productDTO.setCount(product.getCount());
        return productDTO;
    }

    public WarehouseDTO toDto(Warehouse warehouse)
    {
        WarehouseDTO warehouseDTO = new WarehouseDTO();
        Set<ProductDTO> productDTOS = toDtosProduct(warehouse.getProducts());
        warehouseDTO.setIdWarehouseSection(warehouse.getIdWarehouseSection());
        warehouseDTO.setCountArrive(warehouse.getCountArrive());
        warehouseDTO.setProducts(productDTOS);
        return warehouseDTO;
    }
    public List<ProductDTO> toDtoList(List<Product> products)
    {
        List<ProductDTO> productDTO = new ArrayList<>();
        for(int i = 0; i < products.size(); i++)
        {
            productDTO.add(toDto(products.get(i)));
        }
        return productDTO;
    }

    public Set<ProductDTO> toDtosProduct(Set<Product> products)
    {
        return products.stream().map(product -> toDto(product)).collect(Collectors.toSet());
    }
}
