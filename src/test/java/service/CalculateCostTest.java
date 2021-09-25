package service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import rx.Observable;
import rx.observers.TestSubscriber;

import java.math.BigDecimal;

@RunWith(MockitoJUnitRunner.class)
public class CalculateCostTest {

    @InjectMocks
    CalculateCost cc;

    TestSubscriber ts;


    @Before
    public void Setup(){
        cc = new CalculateCost();
        ts = new TestSubscriber();

    }

    @Test
    public void returnPriceTest_success(){
        BigDecimal sum = cc.returnPrice(new String[]{"Orange","Orange","Orange", "Apple", "Apple"});
        Assert.assertEquals(BigDecimal.valueOf(1.1).setScale(2), sum);
        ts.assertNoErrors();
    }

    @Test
    public void returnPriceTest_successOneApple(){
        BigDecimal sum = cc.returnPrice(new String[]{"Apple"});
        Assert.assertEquals(BigDecimal.valueOf(.6).setScale(2), sum);
        ts.assertNoErrors();
    }

    @Test
    public void returnPriceTest_successOneOrange() {
        BigDecimal sum = cc.returnPrice(new String[]{"Orange"});
        Assert.assertEquals(BigDecimal.valueOf(.25), sum);
        ts.assertNoErrors();
    }

    @Test
    public void returnPriceTest_emptyCart(){
        BigDecimal sum = cc.returnPrice(new String[]{});
        Assert.assertEquals(BigDecimal.valueOf(0).setScale(2), sum);
        ts.assertNoErrors();
    }

    @Test
    public void buyThreeForTwoDealTest_remainderOne(){
        cc.setOrder(Groceries.ORANGE, 4);
        BigDecimal sum = cc.buyThreeForTwoDeal(Groceries.ORANGE);
        Assert.assertEquals(BigDecimal.valueOf(.75),sum);
        ts.assertNoErrors();
    }

    @Test
    public void buyThreeForTwoDealTest_remainderTwo(){
        cc.setOrder(Groceries.ORANGE, 5);
        BigDecimal sum = cc.buyThreeForTwoDeal(Groceries.ORANGE);
        Assert.assertEquals(BigDecimal.valueOf(1).setScale(2),sum);
        ts.assertNoErrors();
    }

    @Test
    public void buyThreeForTwoDealTest_divisibleBy3(){
        cc.setOrder(Groceries.ORANGE, 3);
        BigDecimal sum = cc.buyThreeForTwoDeal(Groceries.ORANGE);
        Assert.assertEquals(BigDecimal.valueOf(.50).setScale(2),sum);
        ts.assertNoErrors();
    }

    @Test
    public void buyOneGetOneTest_divisibleBy2(){
        cc.setOrder(Groceries.APPLE, 2);
        BigDecimal sum = cc.buyOneGetOneDeal(Groceries.APPLE);
        Assert.assertEquals(BigDecimal.valueOf(.60), sum);
        ts.assertNoErrors();
    }

    @Test
    public void buyOneGetOneTest_notDivisibleBy2(){
        cc.setOrder(Groceries.APPLE, 3);
        BigDecimal sum = cc.buyOneGetOneDeal(Groceries.APPLE);
        Assert.assertEquals(BigDecimal.valueOf(1.2), sum);
        ts.assertNoErrors();
    }

    @Test
    public void getPriceTest(){
        cc.setPrices(Groceries.ORANGE, BigDecimal.valueOf(25));
        Assert.assertEquals(BigDecimal.valueOf(25), cc.getPrice(Groceries.ORANGE));
    }

    @Test
    public void observableTest(){
        TestSubscriber<CalculateCost> tcc = TestSubscriber.create();
        Observable.just(cc).subscribe(tcc);
        tcc.assertValue(cc);
        tcc.assertNoErrors();
    }

    @Test
    public void enoughProductInStockTest_success(){
        cc.setOrder(Groceries.APPLE, 3);
        boolean enoughProduct = cc.enoughProductInStock(Groceries.APPLE);
        Assert.assertTrue(enoughProduct);
        ts.assertNoErrors();
    }

    @Test
    public void enoughProductInStockTest_fail(){
        cc.setOrder(Groceries.ORANGE, 4);
        cc.setStock(Groceries.ORANGE, 1);
        boolean enoughProduct = cc.enoughProductInStock(Groceries.ORANGE);
        Assert.assertFalse(enoughProduct);
        ts.assertNoErrors();
    }

    @Test
    public void returnPriceTest_successNotEnoughOranges(){
        BigDecimal sum = cc.returnPrice(new String[]{"Orange","Orange","Orange","Orange", "Orange", "Apple", "Apple", "Apple"});
        Assert.assertEquals(BigDecimal.valueOf(1.95).setScale(2), sum);
        ts.assertNoErrors();
    }

    @Test
    public void returnPriceTest_successNotEnoughApples(){
        BigDecimal sum = cc.returnPrice(new String[]{"Orange","Orange","Orange","Orange", "Apple", "Apple", "Apple", "Apple"});
        Assert.assertEquals(BigDecimal.valueOf(1.95), sum);
        ts.assertNoErrors();
    }

    @Test
    public void returnPriceTest_successNotEnoughApplesOrOranges(){
        BigDecimal sum = cc.returnPrice(new String[]{"Orange","Orange","Orange","Orange" ,"Orange", "Apple", "Apple", "Apple", "Apple"});
        Assert.assertEquals(BigDecimal.valueOf(1.95), sum);
        ts.assertNoErrors();
    }
}