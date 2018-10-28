/**
 * 
 */
package com.jpmc.stock.service;

import java.util.List;

import com.jpmc.stock.model.TradeDetails;

/**
 * @author Chinmoy
 * Interface to handle the trade details received from different sources.
 *  
 */
public interface TradeDetailsService {
    
    public void tradeReceived(List<TradeDetails> tradeDetails);

}
