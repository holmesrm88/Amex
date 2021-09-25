package service;

import rx.subjects.PublishSubject;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CalculateCost {
    private final Map<Groceries, BigDecimal> prices;
    private final Map<Groceries, Integer> order;
    private final PublishSubject<String> messages;
    private Map<Groceries, Integer> stock;



    public CalculateCost(){
        this.prices = new HashMap<>();
        prices.put(Groceries.APPLE, BigDecimal.valueOf(.60));
        prices.put(Groceries.ORANGE, BigDecimal.valueOf(.25));

        this.order = new HashMap<>();
        messages = messagingService();

        this.stock = new HashMap<>();
        stock.put(Groceries.APPLE, 3);
        stock.put(Groceries.ORANGE, 4);
    }

    public BigDecimal returnPrice(String[] groceries){
        messages.onNext("Please be patient Customer 1. Your order in being prepared now");
        BigDecimal sum = BigDecimal.ZERO;

        int apples = 0;
        int oranges = 0;
        for (String grocery : groceries) {
            if (grocery.equalsIgnoreCase(Groceries.APPLE.toString())) {
                apples++;
                order.put(Groceries.APPLE, apples);
            } else {
                oranges++;
                order.put(Groceries.ORANGE, oranges);
            }
        }

        if(order.get(Groceries.APPLE) != null){
            enoughProductInStock(Groceries.APPLE);
            sum = order.get(Groceries.APPLE) == 1 ? sum.add(prices.get(Groceries.APPLE)) : sum.add(buyOneGetOneDeal(Groceries.APPLE));
        }

        if(order.get(Groceries.ORANGE) != null) {
            enoughProductInStock(Groceries.ORANGE);
            sum = order.get(Groceries.ORANGE) == 1 ? sum.add(prices.get(Groceries.ORANGE)) : sum.add(buyThreeForTwoDeal(Groceries.ORANGE));
        }

        messages.onNext("Customer 1's order is out for delivery!");
        messages.onNext("Customer 1 order completed. Cost of groceries: $" + sum);
        return sum.setScale(2);
    }

    public BigDecimal buyOneGetOneDeal(Groceries item){
        BigDecimal sum = BigDecimal.ZERO;
        sum = order.get(item) % 2 == 0 ? sum.add(prices.get(item).multiply(BigDecimal.valueOf(order.get(item)))).divide(BigDecimal.valueOf(2)) : sum.add(prices.get(item).multiply(BigDecimal.valueOf(order.get(item))).subtract(prices.get(item)));
        return sum;
    }

    public BigDecimal buyThreeForTwoDeal(Groceries item){
        BigDecimal sum = BigDecimal.ZERO;
        if(order.get(item)% 3 == 0){
            sum = sum.add((prices.get(item)).multiply(BigDecimal.valueOf(order.get(item)))).subtract(prices.get(item));
        } else if (order.get(item) % 3 ==1){
            sum = sum.add((prices.get(item)).multiply(BigDecimal.valueOf(order.get(item)))).subtract((prices.get(item)).multiply(BigDecimal.valueOf((order.get(item)) % 3)));
        } else {
            sum = sum.add((prices.get(item)).multiply(BigDecimal.valueOf(order.get(item)))).subtract((prices.get(item)).multiply(BigDecimal.valueOf((order.get(item)) % 3).subtract(BigDecimal.ONE)));
        }
        return sum;
    }

    public boolean enoughProductInStock(Groceries item){
        boolean enoughProduct = true;
        if(order.get(item) > stock.get(item)){
            messages.onNext("Our apologies, but we cannot completely fill your order of "+item.label+" due to a shortage.");
            order.put(item, stock.get(item));
            stock.put(item, 0);
            enoughProduct = false;
        }
        return enoughProduct;
    }

    public void setStock(Groceries item, Integer amount){
        stock.put(item, amount);
    }

    public void setPrices(Groceries item, BigDecimal price){
        prices.put(item, price);
    }

    public BigDecimal getPrice(Groceries item){
        return prices.get(item);
    }

    public void setOrder(Groceries item, Integer order){
        this.order.put(item, order);
    }

    public PublishSubject<String> messagingService(){
        PublishSubject<String> messages = PublishSubject.create();
        messages.subscribe(System.out::println);
        return messages;
    }

}
