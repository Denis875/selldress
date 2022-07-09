package main.model;

import javax.persistence.*;

@Entity
@Table(name = "discount")
public class Discount
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_discount")
    private int idDiscount;
    @Column(name = "title_discount")
    private String titleDiscount;
    private int percent;
    private String dateStart;
    private String dateFinish;

    public int getIdDiscount() {
        return idDiscount;
    }

    public void setIdDiscount(int idDiscount) {
        this.idDiscount = idDiscount;
    }

    public String getTitleDiscount() {
        return titleDiscount;
    }

    public void setTitleDiscount(String titleDiscount) {
        this.titleDiscount = titleDiscount;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(String dateFinish) {
        this.dateFinish = dateFinish;
    }
}
