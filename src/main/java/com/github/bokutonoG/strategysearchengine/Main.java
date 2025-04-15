package com.github.bokutonoG.strategysearchengine;
import com.github.bokutonoG.strategysearchengine.enums.StrategyType;
import com.github.bokutonoG.strategysearchengine.model.FindCriteria;
import com.github.bokutonoG.strategysearchengine.model.Order;
import com.github.bokutonoG.strategysearchengine.builder.SearchContextBuilder;
import com.github.bokutonoG.strategysearchengine.service.StrategyService;
import com.github.bokutonoG.strategysearchengine.utils.CLIUtil;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // создаем сканер для работы через CLI
        Scanner scanner = new Scanner(System.in);
        // собираем платформу заказов
        List<Order> orders = CLIUtil.getOrderList();
        // создаем стратегии
        List<StrategyType> strategies = CLIUtil.inputSearchStrategy(scanner);
        // создаем критерию
        FindCriteria criteria = CLIUtil.inputSearchCriteria(scanner, strategies);
        // создаем фабрику контекста
        SearchContextBuilder builder = new SearchContextBuilder();
        // создаем сервис и осуществляем поиск
        StrategyService strategyService = new StrategyService(builder.getSearchContext(strategies, criteria));
        var foundOrders = strategyService.findByStrategy(orders);
        // вывод с помощью утилиты
        CLIUtil.resultPrinter(foundOrders);
    }
}
