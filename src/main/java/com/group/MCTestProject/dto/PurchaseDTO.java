package com.group.MCTestProject.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class PurchaseDTO {
    @NotEmpty(message = "Should not be empty")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
