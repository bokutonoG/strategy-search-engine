package com.github.bokutonoG.strategysearchengine.model;

import java.time.LocalDate;

public class FindCriteria {
    private String clientName;
    private LocalDate createdAt;
    private String status;


    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientName() {
        return this.clientName;
    }

    public void setCreatedAt(LocalDate createAt) {
        this.createdAt = createAt;
    }

    public LocalDate getCreatedAt() {
        return this.createdAt;
    }
}
