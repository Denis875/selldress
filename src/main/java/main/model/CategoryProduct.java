package main.model;

import javax.persistence.*;

import javax.persistence.Entity;
import java.util.List;

@Entity
@Table(name = "category_product")
public class CategoryProduct
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_category_product")
    private int idCategoryProduct;
    @Column(name = "title_category")
    private String titleCategory;
    @OneToMany(mappedBy = "categoryProduct")
    private List<Product> productList;

    public int getIdCategoryProduct() {
        return idCategoryProduct;
    }

    public void setIdCategoryProduct(int idCategoryProduct) {
        this.idCategoryProduct = idCategoryProduct;
    }
    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
    public String getTitleCategory() {
        return titleCategory;
    }

    public void setTitleCategory(String titleCategory) {
        this.titleCategory = titleCategory;
    }
}
