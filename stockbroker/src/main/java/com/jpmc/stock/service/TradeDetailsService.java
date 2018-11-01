/**
 * 
 */
package com.jpmc.stock.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.jpmc.stock.model.TradeDetails;

/**
 * @author Chinmoy
 * Interface to handle the trade details received from different sources.
 *  
 */
public interface TradeDetailsService {
    
    /**
     * 
    * @author Chinmoy
    * @param {@link ArrayList}
    * @return void
    * 
    *  This method is used to receive the Trade Details from Facade class.
    */
    public void tradeReceived(List<TradeDetails> tradeDetails);

}
