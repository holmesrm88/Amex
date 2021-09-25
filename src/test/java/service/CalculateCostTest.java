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
        Assert.assertEquals(BigDecimal.valueOf(1.95), sum);
    }

    @Test
    public void returnPriceTest_emptyCart(){
        BigDecimal sum = cc.returnPrice(new String[]{});
        Assert.assertEquals(BigDecimal.ZERO, sum);
    }

    @Test
    public void getPricesTest(){
        cc.setPrices("Apple", BigDecimal.valueOf(2));
        cc.setPrices("Orange", BigDecimal.valueOf(4));

        Assert.assertEquals(BigDecimal.valueOf(2), cc.getPrice("Apple"));
        Assert.assertEquals(BigDecimal.valueOf(4), cc.getPrice("Orange"));
    }




}
