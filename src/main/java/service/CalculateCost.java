package service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CalculateCost {
    private final Map<String, BigDecimal> prices;

    public CalculateCost(){
        this.prices = new HashMap<>();
        prices.put("Apple", BigDecimal.valueOf(.60));
        prices.put("Orange", BigDecimal.valueOf(.25));
    }

    public BigDecimal returnPrice(String[] groceries){
        BigDecimal sum = BigDecimal.ZERO;

        for(String item : groceries) {
            if(item.equalsIgnoreCase("Apple")){
                sum = sum.add(prices.get("Apple"));
            } else if (item.equalsIgnoreCase("Orange")){
                sum = sum.add(prices.get("Orange"));
            }
        }
        System.out.println("Cost of groceries: $" + sum);
        return sum;
    }

    public void setPrices(String item, BigDecimal price){
        prices.put(item, price);
    }

    public BigDecimal getPrice(String item){
        return prices.get(item);
    }


}
