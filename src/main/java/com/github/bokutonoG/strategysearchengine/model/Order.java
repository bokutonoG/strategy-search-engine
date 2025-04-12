package com.github.bokutonoG.strategysearchengine.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Order {
    private String status;
    private LocalDate createdAt;
    private String clientName;


    public Order(String clientName, LocalDate createdAt) {
        this.clientName = clientName;
        this.createdAt = createdAt;
    }
    public String getClientName() {
        return this.clientName;
    }

    public LocalDate getCreatedAt() {
        return this.createdAt;
    }

    @Override
    public String toString() {
        return "Заказ[Имя клиента: " + this.getClientName() + ", Дата создания заказа: " + this.getCreatedAt() + " ]";
    }
}
