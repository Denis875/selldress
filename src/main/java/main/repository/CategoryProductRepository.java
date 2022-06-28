package main.repository;

import main.model.CategoryProduct;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryProductRepository extends CrudRepository<CategoryProduct, Integer>
{

}
