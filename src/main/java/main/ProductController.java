package main;

import main.model.CategoryProduct;
import main.model.Product;
import main.model.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class ProductController
{
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/warehouse/product/")
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

    @PostMapping("/warehouse/product/")
    public void add(Product product)
    {
        CategoryProduct categoryProduct = new CategoryProduct();

        product.setCategoryProduct(categoryProduct);
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
            if(products.get(i).getTitleProduct().equals(titleProduct) && products.get(i).getCategoryProduct().equals(categoryProduct))
            {
                i++;
                continue;
            }
            products.remove(i);
        }
        return products;
    }

    @DeleteMapping("/warehouse/product/{id}")
    public void delete(@PathVariable int id)
    {
        productRepository.deleteById(id);
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
