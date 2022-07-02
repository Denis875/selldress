package main.controller;

import main.MapService;
import main.DTO.ProductDTO;
import main.repository.ProductRepository;
import main.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class ProductController
{
    @Autowired
    ProductRepository productRepository;

    @Autowired
    MapService mapService;

    @GetMapping(value = "/warehouse/product/")
    @ResponseBody
    public List<ProductDTO> list()
    {
        List<ProductDTO> product = mapService.getAllProduct();
        return product;
    }

    @PostMapping(value = "/warehouse/product/", params = "categoryProduct")
    @ResponseBody
    public void add(Product product)
    {
        productRepository.save(product);
    }

    @RequestMapping(value = "/warehouse/product", params = {"titleProduct", "categoryProduct"}, method = RequestMethod.GET)
    @ResponseBody
    public List<ProductDTO> search(@RequestParam(value = "titleProduct") String titleProduct,
    @RequestParam(value = "categoryProduct") String categoryProduct)
    {
        Iterable<Product> productsIterable = productRepository.findAll();
        ArrayList<Product> products = new ArrayList<>();
        for(Product product : productsIterable)
        {
            products.add(product);
        }
        for(int i = 0; i < products.size(); )
        {
            if(products.get(i).getTitleProduct().equals(titleProduct) && products.get(i).getCategoryProduct().getTitleCategory().equals(categoryProduct))
            {
                i++;
                continue;
            }
            products.remove(i);
        }

        return  mapService.toDtoList(products);
    }

    @DeleteMapping(value = "/warehouse/product/{id}")
    public void delete(@PathVariable int id)
    {
        Optional<Product> products = productRepository.findById(id);
        Product product = products.get();
        product.setCategoryProduct(null);
        productRepository.delete(product);
    }

    @PutMapping("/warehouse/product/{id}")
    public void updateProduct(@PathVariable Integer id, String titleProduct, String color, int size, int price, int count, CategoryProduct categoryProduct)
    {
        Optional<Product> products = productRepository.findById(id);
        Product product = products.get();
        product.setTitleProduct(titleProduct);
        product.setColor(color);
        product.setSize(size);
        product.setPrice(price);
        product.setCount(count);
        product.setCategoryProduct(categoryProduct);
        productRepository.save(product);
    }

}
