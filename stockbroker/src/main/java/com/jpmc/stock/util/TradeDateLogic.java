package com.jpmc.stock.util;

import java.time.DayOfWeek;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jpmc.stock.model.Currency;
import com.jpmc.stock.model.NewTradeDetails;
import com.jpmc.stock.model.TradeDetails;

@Component
public class TradeDateLogic {
    
    /**
     * Check the Settlement Date if it falls on Saturday/Sunday, change it to Monday.
     * OR if the currency is AED/SAR, change the settlement date to Sunday if it falls on Fri/Sat.
     * @param List
     * @return List
     */
    public List<NewTradeDetails> adjustSettlmentDate(List<TradeDetails> tradeList) {
        
        List<NewTradeDetails> newTradeDetailsList = new ArrayList<>();
        for (TradeDetails trade : tradeList) {
            NewTradeDetails newTradeDetails = new NewTradeDetails(trade);
            if (Currency.AED.name().equals(trade.getCurrency()) || Currency.SAR.name().equals(trade.getCurrency())) {
                if (trade.getSettlementDate().getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
                    newTradeDetails.setFinalSettlmntDate(trade.getSettlementDate().plus(Period.ofDays(2)));
                } else if (trade.getSettlementDate().getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
                    newTradeDetails.setFinalSettlmntDate(trade.getSettlementDate().plus(Period.ofDays(1)));
                }
            } else {
                if (trade.getSettlementDate().getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
                    newTradeDetails.setFinalSettlmntDate(trade.getSettlementDate().plus(Period.ofDays(2)));
                } else if (trade.getSettlementDate().getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
                    newTradeDetails.setFinalSettlmntDate(trade.getSettlementDate().plus(Period.ofDays(1)));
                }
            }
            // if no change in settlement date,Set Final Settlement date as Incoming Settlemnet date.
            if(newTradeDetails.getFinalSettlmntDate()==null)
                newTradeDetails.setFinalSettlmntDate(trade.getSettlementDate());
            newTradeDetailsList.add(newTradeDetails);
        }
        return newTradeDetailsList;
    }

}
