package service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

@RunWith(MockitoJUnitRunner.class)
public class CalculateCostTest {

    @InjectMocks
    CalculateCost cc;

    @Before
    public void Setup(){
        cc = new CalculateCost();
    }

    @Test
    public void returnPriceTest_success(){
        BigDecimal sum = cc.returnPrice(new String[]{"Orange","Orange","Orange", "Apple", "Apple"});
        Assert.assertEquals(BigDecimal.valueOf(1.1).setScale(2), sum);
    }

    @Test
    public void returnPriceTest_successOneApple(){
        BigDecimal sum = cc.returnPrice(new String[]{"Apple"});
        Assert.assertEquals(BigDecimal.valueOf(.6).setScale(2), sum);
    }

    @Test
    public void returnPriceTest_successOneOrange() {
        BigDecimal sum = cc.returnPrice(new String[]{"Orange"});
        Assert.assertEquals(BigDecimal.valueOf(.25), sum);
    }

    @Test
    public void returnPriceTest_emptyCart(){
        BigDecimal sum = cc.returnPrice(new String[]{});
        Assert.assertEquals(BigDecimal.valueOf(0).setScale(2
        ), sum);
    }

    @Test
    public void buyThreeForTwoDealTest_remainderOne(){
        cc.setOrder(Groceries.ORANGE, 4);
        BigDecimal sum = cc.buyThreeForTwoDeal(Groceries.ORANGE);
        Assert.assertEquals(BigDecimal.valueOf(.75),sum);
    }

    @Test
    public void buyThreeForTwoDealTest_remainderTwo(){
        cc.setOrder(Groceries.ORANGE, 5);
        BigDecimal sum = cc.buyThreeForTwoDeal(Groceries.ORANGE);
        Assert.assertEquals(BigDecimal.valueOf(1).setScale(2),sum);
    }

    @Test
    public void buyThreeForTwoDealTest_divisibleBy3(){
        cc.setOrder(Groceries.ORANGE, 3);
        BigDecimal sum = cc.buyThreeForTwoDeal(Groceries.ORANGE);
        Assert.assertEquals(BigDecimal.valueOf(.50).setScale(2),sum);
    }

    @Test
    public void buyOneGetOneTest_divisibleBy2(){
        cc.setOrder(Groceries.APPLE, 2);
        BigDecimal sum = cc.buyOneGetOneDeal(Groceries.APPLE);
        Assert.assertEquals(BigDecimal.valueOf(.60), sum);
    }

    @Test
    public void buyOneGetOneTest_notDivisibleBy2(){
        cc.setOrder(Groceries.APPLE, 3);
        BigDecimal sum = cc.buyOneGetOneDeal(Groceries.APPLE);
        Assert.assertEquals(BigDecimal.valueOf(1.2), sum);
    }

    @Test
    public void getPriceTest(){
        cc.setPrices(Groceries.ORANGE, BigDecimal.valueOf(25));
        Assert.assertEquals(BigDecimal.valueOf(25), cc.getPrice(Groceries.ORANGE));
    }
}
