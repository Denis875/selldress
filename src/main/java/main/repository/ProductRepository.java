package main.repository;

import main.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ProductRepository extends CrudRepository<Product, Integer>
{
}
