package com.jpmc.stock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jpmc.stock.exception.ApplicationException;
import com.jpmc.stock.service.TradeDetailsService;
import static com.jpmc.stock.test.TestDataFactory.createTradeList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TradeServiceTest {
    
    @Autowired
    TradeDetailsService tradeDetails;
    
    @Test
    public void testTradeReceived() throws ApplicationException {
        // test that there was a call
        tradeDetails.tradeReceived(createTradeList());
    }
    
    

}
