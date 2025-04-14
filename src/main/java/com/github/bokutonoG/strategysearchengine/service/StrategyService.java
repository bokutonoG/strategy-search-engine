package com.github.bokutonoG.strategysearchengine.service;

import com.github.bokutonoG.strategysearchengine.context.SearchContext;
import com.github.bokutonoG.strategysearchengine.model.Order;
import com.github.bokutonoG.strategysearchengine.strategy.Findable;
import java.util.List;

public class StrategyService {

    private SearchContext searchContext;


    public StrategyService(SearchContext searchContext) {
        this.searchContext = searchContext;
    }

    public void setStrategy(SearchContext searchContext) {
        this.searchContext = searchContext;
    }

    public List<Order> findByStrategy(List<Order> orders) {
        List<Order> result = orders;
        for (Findable<Order> strategy : searchContext.getStrategies()) {
            result = strategy.find(result, searchContext.getFindCriteria());
        }
        return result;
    }

}
