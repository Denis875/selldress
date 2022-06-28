package main.model;

import javax.persistence.*;
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

    public int getIdCategoryProduct() {
        return idCategoryProduct;
    }

    public void setIdCategoryProduct(int idCategoryProduct) {
        this.idCategoryProduct = idCategoryProduct;
    }
    public String getTitleCategory() {
        return titleCategory;
    }

    public void setTitleCategory(String titleCategory) {
        this.titleCategory = titleCategory;
    }
}
