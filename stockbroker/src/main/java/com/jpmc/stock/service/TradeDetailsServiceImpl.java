package com.jpmc.stock.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jpmc.stock.business.TradeReportEngine;
import com.jpmc.stock.model.NewTradeDetails;
import com.jpmc.stock.model.TradeDetails;

/**
 * Implementation class for TradeDetails Service.
 */
@Component
public class TradeDetailsServiceImpl implements TradeDetailsService {
    
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    TradeReportEngine tradeReportEngine;
    
    @Override
    public void tradeReceived(List<TradeDetails> tradeList) {
        
        List<NewTradeDetails> newTradeList = tradeReportEngine.calcAmountSettlDate(tradeList);
        tradeReportEngine.calculateIncoming(newTradeList);
        tradeReportEngine.calculateOutgoing(newTradeList);
        tradeReportEngine.sortEntity(newTradeList);
    }

}
