package com.github.bokutonoG.strategysearchengine.context;

import com.github.bokutonoG.strategysearchengine.model.FindCriteria;
import com.github.bokutonoG.strategysearchengine.model.Order;
import com.github.bokutonoG.strategysearchengine.strategy.Findable;
import java.util.*;


public class SearchContext {

    private FindCriteria findCriteria;
    private List<Findable<Order>> strategies;


    public SearchContext(List<Findable<Order>> strategies, FindCriteria findCriteria) {
        this.strategies = strategies;
        this.findCriteria = findCriteria;
    }

    public List<Findable<Order>> getStrategies() {
        return this.strategies;
    }

    public FindCriteria getFindCriteria() {
        return findCriteria;
    }
}
