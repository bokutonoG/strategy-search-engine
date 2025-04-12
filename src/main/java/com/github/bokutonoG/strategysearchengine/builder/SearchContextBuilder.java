package com.github.bokutonoG.strategysearchengine.builder;

import com.github.bokutonoG.strategysearchengine.context.SearchContext;
import com.github.bokutonoG.strategysearchengine.model.FindCriteria;
import com.github.bokutonoG.strategysearchengine.enums.StrategyType;
import com.github.bokutonoG.strategysearchengine.model.Order;
import com.github.bokutonoG.strategysearchengine.strategy.FindByClientNameStrategy;
import com.github.bokutonoG.strategysearchengine.strategy.FindByCreatedAtStrategy;
import com.github.bokutonoG.strategysearchengine.strategy.Findable;
import java.time.LocalDate;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class SearchContextBuilder {

    private final Map<StrategyType, Supplier<Findable<Order>>> strategyTypeMap;
    private Map<StrategyType, BiConsumer<Scanner, FindCriteria>> findCriteriaTypeMap;

    public SearchContextBuilder() {
        // ленивая инициализация мап?
        this.strategyTypeMap = new HashMap<>();
        this.strategyTypeMap.put(StrategyType.BY_CLIENT_NAME, FindByClientNameStrategy::new);
        this.strategyTypeMap.put(StrategyType.BY_CREATED_AT, FindByCreatedAtStrategy::new);
        this.findCriteriaTypeMap = new HashMap<>();
        this.findCriteriaTypeMap.put(StrategyType.BY_CLIENT_NAME, ((scanner, findCriteria) -> {
            System.out.println("Введите имя клиента: ");
            findCriteria.setClientName(scanner.nextLine());
        }));
        this.findCriteriaTypeMap.put(StrategyType.BY_CREATED_AT, ((scanner, findCriteria) -> {
            System.out.println("Введите дату создания заказа: ");
            findCriteria.setCreatedAt(LocalDate.parse(scanner.nextLine()));
        }));

    }

    public SearchContext getSearchContext(List <StrategyType> strategiesTypes, Scanner scanner) {
        List<Findable<Order>> strategiesList = new ArrayList<>();
        FindCriteria findCriteria = new FindCriteria();
        for (StrategyType strategyType : strategiesTypes) {
            if(!strategyTypeMap.containsKey(strategyType) && !findCriteriaTypeMap.containsKey(strategyType)) {
                throw new IllegalArgumentException("Передан некорректный тип стратегии" + strategyType);
            }
            strategiesList.add(strategyTypeMap.get(strategyType).get());
            findCriteriaTypeMap.get(strategyType).accept(scanner, findCriteria);
        }
        return new SearchContext(strategiesList, findCriteria);
    }

}
