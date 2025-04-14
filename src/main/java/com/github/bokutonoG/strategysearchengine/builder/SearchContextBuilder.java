package com.github.bokutonoG.strategysearchengine.builder;

import com.github.bokutonoG.strategysearchengine.context.SearchContext;
import com.github.bokutonoG.strategysearchengine.exceptions.StrategyNotFoundException;
import com.github.bokutonoG.strategysearchengine.model.FindCriteria;
import com.github.bokutonoG.strategysearchengine.enums.StrategyType;
import com.github.bokutonoG.strategysearchengine.model.Order;
import com.github.bokutonoG.strategysearchengine.strategy.FindByClientNameStrategy;
import com.github.bokutonoG.strategysearchengine.strategy.FindByCreatedAtStrategy;
import com.github.bokutonoG.strategysearchengine.strategy.Findable;
import java.util.*;
import java.util.function.Supplier;

public class SearchContextBuilder {

    private final Map<StrategyType, Supplier<Findable<Order>>> strategyTypeMap;


    public SearchContextBuilder() {
        // ленивая инициализация мап?
        this.strategyTypeMap = new HashMap<>();
        this.strategyTypeMap.put(StrategyType.BY_CLIENT_NAME, FindByClientNameStrategy::new);
        this.strategyTypeMap.put(StrategyType.BY_CREATED_AT, FindByCreatedAtStrategy::new);
    }

    public SearchContext getSearchContext(List <StrategyType> strategiesTypes, FindCriteria findCriteria) {
        List<Findable<Order>> strategiesList = new ArrayList<>();
        for (StrategyType strategyType : strategiesTypes) {
            if(!strategyTypeMap.containsKey(strategyType)) {
                throw new StrategyNotFoundException("Передан некорректный тип стратегии" + strategyType);
            }
            strategiesList.add(strategyTypeMap.get(strategyType).get());
        }
        return new SearchContext(strategiesList, findCriteria);
    }
}
