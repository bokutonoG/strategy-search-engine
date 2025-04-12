package com.github.bokutonoG.strategysearchengine.enums;

public enum StrategyType {
    BY_CLIENT_NAME("по имени клиента"),
    BY_STATUS("по статусу заказа"),
    BY_CREATED_AT("по дате заказа");

    private String description;


    StrategyType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public static StrategyType fromDescription(String description) {

        for (StrategyType strategyType : values()) {
            if (description.equalsIgnoreCase(strategyType.getDescription())) {
                return strategyType;
            }
        }
        throw new IllegalArgumentException("Нет стратегии с описанием: " + description);
    }
}


