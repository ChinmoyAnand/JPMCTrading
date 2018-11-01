package com.jpmc.stock.business;

import static com.jpmc.stock.test.TestDataFactory.createNewTradeList;
import static org.junit.Assert.assertEquals;
import org.hamcrest.collection.IsMapContaining;
import static org.hamcrest.MatcherAssert.assertThat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;



public class TradeReportTest {
    
    //Class to be tested
    TradeEngineImpl tradeEngine;
    
    @Before
    public void setup() throws Exception {
        tradeEngine = new TradeEngineImpl();
    }
    
    @Test
    public void calcIncomingAmountTest(){
        Map<LocalDate, Double> incomingMap;
        incomingMap = tradeEngine.calculateIncoming(createNewTradeList());
        assertThat(incomingMap, IsMapContaining.hasEntry(LocalDate.of(2016, Month.JANUARY, 4), 20050.0));
        assertThat(incomingMap, IsMapContaining.hasEntry(LocalDate.of(2016, Month.JANUARY, 5), 20050.0));
        assertThat(incomingMap, IsMapContaining.hasEntry(LocalDate.of(2016, Month.JANUARY, 3), 30075.0));
    }
    
    @Test
    public void calcOutgoingAmountTest(){
        Map<LocalDate, Double> incomingMap;
        incomingMap = tradeEngine.calculateOutgoing(createNewTradeList());
        assertThat(incomingMap, IsMapContaining.hasEntry(LocalDate.of(2016, Month.JANUARY, 4), 40100.0));
        assertThat(incomingMap, IsMapContaining.hasEntry(LocalDate.of(2016, Month.JANUARY, 5), 10025.0));
        assertThat(incomingMap, IsMapContaining.hasEntry(LocalDate.of(2016, Month.JANUARY, 3), 30075.0));
    }
    
    @Test
    public void rankOutgoingAmountTest(){
        Map<String, Double> incomingMap;
        incomingMap = tradeEngine.rankOutgoing(createNewTradeList());
        //Check "bar" has highest rank
        assertEquals("bar",incomingMap.keySet().iterator().next());
        
      
    }
    
    @Test
    public void rankIncomingAmountTest(){
        Map<String, Double> incomingMap;
        incomingMap = tradeEngine.rankIncoming(createNewTradeList());
        //Check "john" has highest rank
        assertEquals("john",incomingMap.keySet().iterator().next());
        
      
    }

}
