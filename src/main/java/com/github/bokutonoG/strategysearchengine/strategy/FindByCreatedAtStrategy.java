package com.github.bokutonoG.strategysearchengine.strategy;

import com.github.bokutonoG.strategysearchengine.model.FindCriteria;
import com.github.bokutonoG.strategysearchengine.model.Order;

import java.util.List;
import java.util.stream.Collectors;

public class FindByCreatedAtStrategy implements Findable<Order> {
    @Override
    public List<Order> find(List<Order> orders, FindCriteria findCriteria)  {
        if (findCriteria == null || findCriteria.getCreatedAt() == null)
            return null;
        return orders.stream()
                .filter(order -> order.getCreatedAt().equals(findCriteria.getCreatedAt()))
                .collect(Collectors.toList());
    }
}
