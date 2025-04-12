package com.github.bokutonoG.strategysearchengine.strategy;

import com.github.bokutonoG.strategysearchengine.model.FindCriteria;
import com.github.bokutonoG.strategysearchengine.model.Order;

import java.util.List;
import java.util.stream.Collectors;

public class FindByClientNameStrategy implements Findable<Order> {
    @Override
    public List<Order> find(List<Order> orders, FindCriteria findCriteria) {
        if (findCriteria == null || findCriteria.getClientName() == null)
            return null;
        return orders.stream()
                .filter(order -> order.getClientName().toLowerCase().contains(findCriteria.getClientName().toLowerCase()))
                .collect(Collectors.toList());
    }
}
