package com.group.MCTestProject.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "lastname")
    @NotNull
    private String lastname;

    @Column(name = "age")
    @NotNull
    private Integer age;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "purchase_item", referencedColumnName = "name")
    private Purchase purchase;

    @Column(name = "count")
    @NotNull
    private Integer count;

    @Column(name = "amount")
    @NotNull
    private Double amount;

    @Column(name = "purchase_date")
    @NotNull
    private LocalDateTime purchaseDate;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }


    public String getDateFormat (LocalDateTime localDateTime) {
        String DATE_PATTERN = "yyyy-MM-dd";
        return DateTimeFormatter.ofPattern(DATE_PATTERN).format(localDateTime);
    }


}
