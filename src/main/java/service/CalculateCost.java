package service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CalculateCost {
    private final Map<Groceries, BigDecimal> prices;
    private final Map<Groceries, Integer> order;


    public CalculateCost(){
        this.prices = new HashMap<>();
        prices.put(Groceries.APPLE, BigDecimal.valueOf(.60));
        prices.put(Groceries.ORANGE, BigDecimal.valueOf(.25));

        this.order = new HashMap<>();

    }

    public BigDecimal returnPrice(String[] groceries){
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
            sum = order.get(Groceries.APPLE) == 1 ? sum.add(prices.get(Groceries.APPLE)) : sum.add(buyOneGetOneDeal(Groceries.APPLE));
        }

        if(order.get(Groceries.ORANGE) != null) {
            sum = order.get(Groceries.ORANGE) == 1 ? sum.add(prices.get(Groceries.ORANGE)) : sum.add(buyThreeForTwoDeal(Groceries.ORANGE));
        }

        System.out.println("Cost of groceries: $" + sum.setScale(2));
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

    public void setPrices(Groceries item, BigDecimal price){
        prices.put(item, price);
    }

    public BigDecimal getPrice(Groceries item){
        return prices.get(item);
    }

    public void setOrder(Groceries item, Integer order){
        this.order.put(item, order);
    }

}
