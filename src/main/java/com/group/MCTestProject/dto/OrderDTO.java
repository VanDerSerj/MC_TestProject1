package com.group.MCTestProject.dto;

import javax.validation.constraints.NotNull;


public class OrderDTO {
    @NotNull
    private String name;

    @NotNull
    private String lastname;

    @NotNull
    private Integer age;

    @NotNull
    private String purchase;

    @NotNull
    private Integer count;

    @NotNull
    private Double amount;

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

    public String getPurchase() {
        return purchase;
    }

    public void setPurchase(String purchase) {
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
}