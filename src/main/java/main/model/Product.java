package main.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product")
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_product")
    private int idProduct;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_category_product")
    private CategoryProduct categoryProduct;
    @Column(name = "title_product")
    private String titleProduct;
    private String color;
    private int size;
    private int price;

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public CategoryProduct getCategoryProduct() {
        return categoryProduct;
    }

    public void setCategoryProduct(CategoryProduct categoryProduct) {
        this.categoryProduct = categoryProduct;
    }

    public String getTitleProduct() {
        return titleProduct;
    }

    public void setTitleProduct(String titleProduct) {
        this.titleProduct = titleProduct;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
