package main.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "warehouse")
public class Warehouse
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_warehouse_section")
    private int idWarehouseSection;
    @Column(name = "count_arrive")
    private int countArrive;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "arrive_product", joinColumns = {@JoinColumn(name = "id_warehouse")}, inverseJoinColumns = {@JoinColumn(name = "id_product")})
    private Set<Product> products = new HashSet<>();

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public int getIdWarehouseSection() {
        return idWarehouseSection;
    }

    public void setIdWarehouseSection(int idWarehouseSection) {
        this.idWarehouseSection = idWarehouseSection;
    }

    public int getCountArrive() {
        return countArrive;
    }

    public void setCountArrive(int countArrive) {
        this.countArrive = countArrive;
    }

}
