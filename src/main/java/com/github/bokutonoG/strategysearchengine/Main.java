package com.github.bokutonoG.strategysearchengine;

import com.github.bokutonoG.strategysearchengine.enums.StrategyType;
import com.github.bokutonoG.strategysearchengine.model.Order;
import com.github.bokutonoG.strategysearchengine.builder.SearchContextBuilder;
import com.github.bokutonoG.strategysearchengine.service.StrategyService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // создание аналога платформы где мы будем искать заказы
        List<Order> orders = new ArrayList<>();
        // создание формата даты и самих дат
        DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date1 = LocalDate.of(2002, 12, 15);
        LocalDate date2 = LocalDate.of(2002, 12, 16);
        // добавление заказов на платформу
        Collections.addAll(orders, new Order("Петя", date1), new Order("Вася", date2), new Order("Вася", date1));
        // подготовка ключей для стратегий в связи с CLI
        System.out.println("Введите один или несколько фильтров (через запятую): ");
        String[] inputs = scanner.nextLine().split(",");
        List<StrategyType> strategyTypes = new ArrayList<>();
        for (String strategy : inputs) {
            strategyTypes.add(StrategyType.fromDescription(strategy.trim()));
        }
        // создание билдера стратегий
        SearchContextBuilder searchContextBuilder = new SearchContextBuilder();
        StrategyService strategyService = new StrategyService(searchContextBuilder.getSearchContext(strategyTypes, scanner));
        List<Order> foundBy = strategyService.findByStrategy(orders);
        foundBy.forEach(System.out::println);

    }
}
