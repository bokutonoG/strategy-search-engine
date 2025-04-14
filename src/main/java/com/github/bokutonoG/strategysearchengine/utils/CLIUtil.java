package com.github.bokutonoG.strategysearchengine.utils;

import com.github.bokutonoG.strategysearchengine.enums.StrategyType;
import com.github.bokutonoG.strategysearchengine.exceptions.StrategyNotFoundException;
import com.github.bokutonoG.strategysearchengine.model.FindCriteria;
import com.github.bokutonoG.strategysearchengine.model.Order;

import java.time.LocalDate;
import java.util.*;
import java.util.function.BiConsumer;


public class CLIUtil {

    public static void init(){};

    public static StrategyType fromDescription(String description) {
        for (StrategyType strategyType : StrategyType.values()) {
            if (description.equalsIgnoreCase(strategyType.getDescription())) {
                return strategyType;
            }
        }
        throw new StrategyNotFoundException("Передан некорректный тип стратегии " + description);
    }

    public static List<Order> getOrderList() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("Петя", LocalDate.of(2025, 4, 11)));
        orders.add(new Order("Вася", LocalDate.of(2025, 4, 12)));
        orders.add(new Order("Вася", LocalDate.of(2025, 4, 11)));

        return orders;
    }

    public static void resultPrinter(List<Order> orders) {
        orders.forEach(System.out::println);
    }

    public static FindCriteria inputSearchCriteria(Scanner scanner, List<StrategyType> strategiesTypes) {
        FindCriteria findCriteria = new FindCriteria();
        Map<StrategyType, BiConsumer<Scanner, FindCriteria>> findCriteriaTypeMap = new HashMap<>();
        findCriteriaTypeMap.put(StrategyType.BY_CLIENT_NAME, ((cliScanner, criteria) -> {
            System.out.println("Введите имя клиента: ");
            criteria.setClientName(cliScanner.nextLine().trim());
        }));
        findCriteriaTypeMap.put(StrategyType.BY_CREATED_AT, ((cliScanner, criteria) -> {
            System.out.println("Введите дату создания заказа: ");
            criteria.setCreatedAt(LocalDate.parse(cliScanner.nextLine()));
        }));
        for (StrategyType strategyType : strategiesTypes) {
            if(!findCriteriaTypeMap.containsKey(strategyType)) {
                throw new StrategyNotFoundException("Передан некорректный тип стратегии" + strategyType);
            }
            findCriteriaTypeMap.get(strategyType).accept(scanner, findCriteria);
        }
        return findCriteria;
    }

    public static List<StrategyType> inputSearchStrategy(Scanner scanner) {
        System.out.println("Введите один или несколько фильтров (через запятую): ");
        String[] inputs = scanner.nextLine().split(",");
        List<StrategyType> strategyTypes = new ArrayList<>();
        for (String strategy : inputs) {
            strategyTypes.add(CLIUtil.fromDescription(strategy.trim()));
        }
        return strategyTypes;
    }

}
