package main;

import main.model.CategoryProduct;
import main.model.Product;
import main.model.ProductRepository;
import main.model.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class ProductController
{
    @Autowired
    ProductRepository productRepository;

    @GetMapping(value = "/warehouse/product/")
    public List<Product> list()
    {
        Iterable<Product> productsIterable = productRepository.findAll();
        ArrayList<Product> products = new ArrayList<>();
        for(Product product : productsIterable)
        {
            products.add(product);
        }
        return products;
    }

    @PostMapping(value = "/warehouse/product/", params = "categoryProduct")
    @ResponseBody
    public void add(Product product)
    {
        productRepository.save(product);
    }

    @RequestMapping(value = "/warehouse/product", params = {"title product", "category"}, method = RequestMethod.GET)
    @ResponseBody
    public List<Product> search(@RequestParam(value = "title product") String titleProduct,
    @RequestParam(value = "category") String categoryProduct)
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
        return products;
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
    public void updateProduct(@PathVariable Integer id, String titleProduct, String color, int size, int price, CategoryProduct categoryProduct)
    {
        Optional<Product> products = productRepository.findById(id);
        Product product = products.get();
        product.setTitleProduct(titleProduct);
        product.setColor(color);
        product.setSize(size);
        product.setPrice(price);
        product.setCategoryProduct(categoryProduct);
        productRepository.save(product);
    }
}
