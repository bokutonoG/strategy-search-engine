package com.github.bokutonoG.strategysearchengine.strategy;

import com.github.bokutonoG.strategysearchengine.model.FindCriteria;
import com.github.bokutonoG.strategysearchengine.model.Order;

import java.util.List;

public interface Findable<T> {
    List<T> find(List<Order> orders, FindCriteria findCriteria);
}
