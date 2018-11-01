package com.jpmc.stock.util;

import java.time.LocalDate;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



/**
 * @author chinmoy
 * This Class is used to Generate the Report.
 */
@Component
public class TradeReportGen {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    
    public void amountIncomingEachDay(Map<LocalDate, Double> sellTradingEachDay){
        
        LOGGER.info("********************************************************");
        LOGGER.info("****** REPORT FOR EACH DAY TRADING INCOMING/SELL********");
        sellTradingEachDay.forEach((date, amount) -> LOGGER.info("DATE::" + date + " AMOUNT(USD)::" + amount));
        LOGGER.info("********************************************************");
    
        
    }
    
    public void amountOutgoingEachDay(Map<LocalDate, Double> buyTradingEachDay){
        
        LOGGER.info("********************************************************");
        LOGGER.info("****** REPORT FOR EACH DAY TRADING OUTGOING/BUY*********");
        buyTradingEachDay.forEach((date, amount) -> LOGGER.info("DATE::" + date + " AMOUNT(USD)::" + amount));
        LOGGER.info("********************************************************");
        
    }
    
    public void incomingRank(Map<String, Double> incomingRanking){
        
        LOGGER.info("********************************************************");
        LOGGER.info("****** RANKING OF ENITIES BASED ON INCOMING/SELL *******");
        incomingRanking.forEach((key,value) -> {
        LOGGER.info("Entity : " + key + "| Total Trade(SELL) Amount : " + value);
            });
        LOGGER.info("********************************************************");
        
    }
    
    public void outgoingRank(Map<String, Double> outgoingRanking){
        
        LOGGER.info("********************************************************");
        LOGGER.info("****** RANKING OF ENITIES BASED ON OUTGOING/BUY  *******");
        outgoingRanking.forEach((key,value) -> {
        LOGGER.info("Entity : " + key + "| Total Trade(BUY) Amount : " + value);
            });
        LOGGER.info("********************************************************");
        
    }
    
    

}
