package com.jpmc.stock.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jpmc.stock.business.TradeEngineImpl;
import com.jpmc.stock.model.NewTradeDetails;
import com.jpmc.stock.model.TradeDetails;
import com.jpmc.stock.util.TradeDateLogic;
import com.jpmc.stock.util.TradeReportGen;

/**
 * Implementation class for TradeDetails Service.
 */
@Component
public class TradeDetailsServiceImpl implements TradeDetailsService {
    
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    TradeEngineImpl tradeReportEngine;
    
    @Autowired
    TradeDateLogic tradeDateLogic;
    
    @Autowired
    TradeReportGen tradeReportGen;
    
    @Override
    public void tradeReceived(List<TradeDetails> tradeList) {
        
        //Step 1 - Loop through instructions and adjust the settlement date if required based on currency type
        List<NewTradeDetails> setldateTradeList = tradeDateLogic.adjustSettlmentDate(tradeList);
        //Step 2 - Calculate amount for each instruction
        List<NewTradeDetails> amountTradeList = tradeReportEngine.calcAmountinUSD(setldateTradeList);
        //Step 3 - Calculate aggregated USD amount from instruction set for each day
        Map<LocalDate, Double> sellTradingEachDay = tradeReportEngine.calculateIncoming(amountTradeList);
        Map<LocalDate, Double> buyTradingEachDay = tradeReportEngine.calculateOutgoing(amountTradeList);
        //Step 4 - Rank the Entities
        Map<String, Double> incomingRanking = tradeReportEngine.rankIncoming(amountTradeList);
        Map<String, Double> outgoingRanking = tradeReportEngine.rankOutgoing(amountTradeList);
        
        //Step - 5 Generate Report As-Required
        //Report 1 - Amount in USD settled incoming everyday
        tradeReportGen.amountIncomingEachDay(sellTradingEachDay);
        //Report 2 - Amount in USD settled outgoing everyday
        tradeReportGen.amountOutgoingEachDay(buyTradingEachDay);
        //Report 3 - Ranking of Entities for outgoing(BUY) 
        tradeReportGen.outgoingRank(outgoingRanking);
        //Report 4 - Ranking of Entities for incoming(SELL)
        tradeReportGen.incomingRank(incomingRanking);
        
    }

}
