package com.group.MCTestProject.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "Orders")
public class Orders {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "lastname")
    @NotNull
    private String lastname;

    @Column(name = "age")
    @NotNull
    private Integer age;

    @Column(name = "count")
    @NotNull
    private Integer count;

    @Column(name = "amount")
    @NotNull
    private Double amount;

    @Column(name = "orders_date_time")
    @NotNull
    private LocalDateTime ordersDateTime;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "purchase_item", referencedColumnName = "name")
    private Purchase purchase;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getOrdersDateTime() {
        return ordersDateTime;
    }

    public void setOrdersDateTime(LocalDateTime ordersDateTime) {
        this.ordersDateTime = ordersDateTime;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getDateFormat () {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(this.ordersDateTime);

    }
}
