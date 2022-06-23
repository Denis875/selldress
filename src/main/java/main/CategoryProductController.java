package main;

import main.model.CategoryProduct;
import main.model.CategoryProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class CategoryProductController
{
    @Autowired
    CategoryProductRepository categoryProductRepository;

    @GetMapping("/warehouse/product/category/")
    public List<CategoryProduct> list()
    {
        Iterable<CategoryProduct> categoryProductsIterable = categoryProductRepository.findAll();
        ArrayList<CategoryProduct> categoryProducts = new ArrayList<>();
        for(CategoryProduct categoryProduct : categoryProductsIterable)
        {
            categoryProducts.add(categoryProduct);
        }
        return categoryProducts;
    }

    @PostMapping("/warehouse/product/category/")
    public void add(CategoryProduct categoryProduct)
    {
        categoryProductRepository.save(categoryProduct);
    }

    @RequestMapping(value = "/warehouse/product/category", params = "title category", method = RequestMethod.GET)
    @ResponseBody
    public List<CategoryProduct> search(@RequestParam(value = "title category") String titleCategory)
    {

        Iterable<CategoryProduct> categoryProductsIterable = categoryProductRepository.findAll();
        ArrayList<CategoryProduct> categoryProducts = new ArrayList<>();
        for(CategoryProduct categoryProduct : categoryProductsIterable)
        {
            categoryProducts.add(categoryProduct);
        }
        for(int i = 0; i < categoryProducts.size(); )
        {
            if(categoryProducts.get(i).getTitleCategory().equals(titleCategory))
            {
                i++;
                continue;
            }
            categoryProducts.remove(i);
        }
        return categoryProducts;
    }

    @DeleteMapping("/warehouse/product/category/{id}")
    public void delete(@PathVariable int id)
    {
        categoryProductRepository.deleteById(id);
    }

    @PutMapping("/warehouse/product/category/{id}")
    public void updateCategoryProduct(@PathVariable Integer id, String titleCategory)
    {
        Optional<CategoryProduct> categoryProducts = categoryProductRepository.findById(id);
        CategoryProduct categoryProduct = categoryProducts.get();
        categoryProduct.setTitleCategory(titleCategory);
        categoryProductRepository.save(categoryProduct);
    }
}
