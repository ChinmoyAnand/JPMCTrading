package com.jpmc.stock.facade;


import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jpmc.stock.exception.ApplicationException;
import com.jpmc.stock.model.TradeDetails;
import com.jpmc.stock.service.TradeDetailsService;

/**
 * @author chinmoy
 * This class is a Facade class , used to receive Trading Records from different source systems.
 * 
 */
@Component
public class TradeFacade {
    
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    TradeDetailsService tradeDetailsService;
    
    
    
    public TradeFacade(TradeDetailsService tradeDetailsService){
        super();
        this.tradeDetailsService = tradeDetailsService;
    }
    
    public void receiveTradeDetails() throws ApplicationException {

        List<TradeDetails> tradeList = createRandomObj();
        LOGGER.debug("TradeDetails List Size::: " + tradeList.size());
        tradeDetailsService.tradeReceived(tradeList);
}

    private List<TradeDetails> createRandomObj() {
        
        List<TradeDetails> tradeDetailsList = new ArrayList<>();
        
        TradeDetails tradeDetails = new TradeDetails("foo", "B", 0.5, "SGP", LocalDate.of(2016, Month.JANUARY, 1), LocalDate.of(2016, Month.JANUARY, 2)
                                                     ,  200, 100.25);

        TradeDetails tradeDetails1 = new TradeDetails("foo", "S", 0.5, "SGP", LocalDate.of(2016, Month.JANUARY, 1), LocalDate.of(2016, Month.JANUARY, 2)
                                                      , 200, 100.25);
        TradeDetails tradeDetails2 = new TradeDetails("bar", "B", 0.5, "SGP", LocalDate.of(2016, Month.JANUARY, 1), LocalDate.of(2016, Month.JANUARY, 2)
                                                      , 200, 100.25);
        TradeDetails tradeDetails3 = new TradeDetails("bar", "B", 0.5, "SGP", LocalDate.of(2016, Month.JANUARY, 1), LocalDate.of(2016, Month.JANUARY, 3)
                                                      , 200, 100.25);
        TradeDetails tradeDetails4 = new TradeDetails("bar", "B", 0.5, "SGP", LocalDate.of(2016, Month.JANUARY, 1), LocalDate.of(2016, Month.JANUARY, 5)
                                                      , 200, 100.25);
        TradeDetails tradeDetails5 = new TradeDetails("john", "B", 0.5, "AED", LocalDate.of(2016, Month.JANUARY, 1), LocalDate.of(2016, Month.JANUARY, 1)
                                                      , 200, 100.25);
        TradeDetails tradeDetails6 = new TradeDetails("john", "B", 0.5, "SGP", LocalDate.of(2016, Month.JANUARY, 1), LocalDate.of(2016, Month.JANUARY, 5)
                                                      , 200, 100.25);
        TradeDetails tradeDetails7 = new TradeDetails("bar", "B", 0.5, "SGP", LocalDate.of(2016, Month.JANUARY, 1), LocalDate.of(2016, Month.JANUARY, 5)
                                                      , 200, 100.25);
        TradeDetails tradeDetails8 = new TradeDetails("foo", "S", 0.5, "SGP", LocalDate.of(2016, Month.JANUARY, 1), LocalDate.of(2016, Month.JANUARY, 5)
                                                      , 200, 100.25);
        TradeDetails tradeDetails9 = new TradeDetails("bar", "B", 0.5, "SGP", LocalDate.of(2016, Month.JANUARY, 1), LocalDate.of(2016, Month.JANUARY, 5)
                                                      , 200, 100.25);
        TradeDetails tradeDetails10 = new TradeDetails("john", "S", 0.5, "SAR", LocalDate.of(2016, Month.JANUARY, 1), LocalDate.of(2016, Month.JANUARY, 2)
                                                      , 200, 100.25);
        tradeDetailsList.add(tradeDetails);
        tradeDetailsList.add(tradeDetails1);
        tradeDetailsList.add(tradeDetails2);
        tradeDetailsList.add(tradeDetails3);
        tradeDetailsList.add(tradeDetails4);//
        tradeDetailsList.add(tradeDetails5);
        tradeDetailsList.add(tradeDetails6);
        tradeDetailsList.add(tradeDetails7);
        tradeDetailsList.add(tradeDetails8);
        tradeDetailsList.add(tradeDetails9);
        tradeDetailsList.add(tradeDetails10);
        
        return tradeDetailsList;
        
    }
}
