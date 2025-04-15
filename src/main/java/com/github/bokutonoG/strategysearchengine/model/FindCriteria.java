package com.github.bokutonoG.strategysearchengine.model;

import com.github.bokutonoG.strategysearchengine.enums.StrategyType;
import com.github.bokutonoG.strategysearchengine.exceptions.InvalidCriteriaException;

import java.time.LocalDate;
import java.util.Set;

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

    public void validate(Set<StrategyType> strategyTypes) {
        if (strategyTypes.contains(StrategyType.BY_CLIENT_NAME)) {
            if(this.clientName == null || this.clientName.isBlank()) {
                throw new InvalidCriteriaException("Отсутствует имя клиента для поиска");
            }
        }
        if (strategyTypes.contains(StrategyType.BY_CREATED_AT)) {
            if(this.createdAt == null) {
                throw new InvalidCriteriaException("Отсутствует дата создания для поиска");
            }
        }

    }
}
