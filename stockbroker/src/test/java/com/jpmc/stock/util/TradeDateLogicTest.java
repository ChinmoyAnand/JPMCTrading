package com.jpmc.stock.util;

import static com.jpmc.stock.test.TestDataFactory.createTradeList;
import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.jpmc.stock.model.NewTradeDetails;
import com.jpmc.stock.model.TradeDetails;

public class TradeDateLogicTest {
    
    //Class to be tested
    private TradeDateLogic tradeDateLogic;
    
    @Before
    public void setup() throws Exception {
        tradeDateLogic = new TradeDateLogic();
    }
    
    @Test
    public void shouldTestSettlementDateForCurrencyAED(){
        //Currency = AED , Settl Date = Jan,2,2016 Sat
        List<NewTradeDetails> newTradeDetailsList;
        newTradeDetailsList = tradeDateLogic.adjustSettlmentDate(createTradeList());
        assertEquals(LocalDate.of(2016, Month.JANUARY, 3), newTradeDetailsList.get(0).getFinalSettlmntDate());
    }
    
    @Test
    public void shouldTestSettlementDateForCurrencyINR(){
        //Currency = INR , Settl Date = Jan,2,2016 Sat
        List<NewTradeDetails> newTradeDetailsList;
        List<TradeDetails> tradeDetailsList = createTradeList();
        newTradeDetailsList = tradeDateLogic.adjustSettlmentDate(tradeDetailsList);
        assertEquals(LocalDate.of(2016, Month.JANUARY, 4), newTradeDetailsList.get(1).getFinalSettlmntDate());
    }

}
