package com.jpmc.stock.business;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.jpmc.stock.model.NewTradeDetails;
import com.jpmc.stock.model.TradeDetails;

/**
 * @author Chinmoy 
 * Engine to Calculate the INCOMING/SELL for each day of Trade. 
 * Calculate the OUTGOING/BUY for each day of Trade. 
 * Sort the Entity based on INCOMING / OUTGOING.
 * 
 */
public interface TradeEngine {
    
    /**
     * Calculate the INCOMING/SELL for each day of Trade.
     * @param List
     * @return Map
     * 
     */
    public Map<LocalDate, Double> calculateIncoming(List<NewTradeDetails> newTradeList) ;
    
    /**
     * Used to calculate the OUTGOING/BUY for each day of Trade.
     * @param List
     * @return Map
     */
    public Map<LocalDate, Double> calculateOutgoing(List<NewTradeDetails> newTradeList) ;
    
    /**
     * Calculate the Amount in USD.
     * @param List
     * @return List
     */
    public List<NewTradeDetails> calcAmountinUSD(List<NewTradeDetails> tradeList) ;
    
    
    /**
     * Used to sort Enity based on Incoming(Sell) 
     * @param List
     * @return Map
     */
    public Map<String, Double> rankIncoming(List<NewTradeDetails> newTradeList) ;
    
    /**
     * Used to sort Enity based on Outgoing(Sell) 
     * @param List
     * @return Map
     */
    public Map<String, Double> rankOutgoing(List<NewTradeDetails> newTradeList) ;
    
}
