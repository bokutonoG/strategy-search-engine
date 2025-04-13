package com.github.bokutonoG.strategysearchengine.utils;

import com.github.bokutonoG.strategysearchengine.enums.StrategyType;
import com.github.bokutonoG.strategysearchengine.exceptions.StrategyNotFoundException;

public class StrategyTypeParser {

    public static StrategyType fromDescription(String description) {
        for (StrategyType strategyType : StrategyType.values()) {
            if (description.equalsIgnoreCase(strategyType.getDescription())) {
                return strategyType;
            }
        }
        throw new StrategyNotFoundException("Передан некорректный тип стратегии " + description);
    }
}
