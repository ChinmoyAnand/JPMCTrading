package com.jpmc.stock.business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.jpmc.stock.model.NewTradeDetails;
import com.jpmc.stock.model.TradeDetails;
import com.jpmc.stock.model.TradeType;

/**
 * @author Chinmoy.
 * Engine to Calculate the INCOMING/SELL for each day of Trade. 
 * Calculate the OUTGOING/BUY for each day of Trade. 
 * Sort the Entity based on INCOMING / OUTGOING.
 * 
 */
@Component
public class TradeEngineImpl implements TradeEngine{
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    
    @Override
    public List<NewTradeDetails> calcAmountinUSD(List<NewTradeDetails> tradeList) {
        List<NewTradeDetails> newTradeDetailsList = new ArrayList<>();
        for (NewTradeDetails trade : tradeList) {
            //USD amount of a trade = Price per unit * Units * Agreed Fx
            trade.setAmountInUSD(trade.getAgreedFx() * trade.getPricePerUnit() * trade.getUnits());
            newTradeDetailsList.add(trade);
        }
        return newTradeDetailsList;
    }

    @Override
    public Map<LocalDate, Double> calculateIncoming(List<NewTradeDetails> newTradeList) {
        LOGGER.debug("calculateIncoming");
        Map<LocalDate, Double> newMap = new HashMap<>();
        Map<LocalDate, List<NewTradeDetails>> filterIncomingTradeByDates = newTradeList.stream()
                .filter( t -> t.getInstruction().equals(TradeType.S.name()))
                .collect(Collectors.groupingBy(NewTradeDetails::getFinalSettlmntDate));
        
        filterIncomingTradeByDates.forEach((key, value) -> {
            LOGGER.debug("Date Key : " + key + " Value Size : " + value.size());
            newMap.put(key,value.stream().mapToDouble(NewTradeDetails::getAmountInUSD).sum());
        });
        
        return newMap;
    }
    
    @Override
    public Map<LocalDate, Double> calculateOutgoing(List<NewTradeDetails> newTradeList) {
        LOGGER.debug("calculateOutgoing");
        Map<LocalDate, Double> newMap = new HashMap<>();
        Map<LocalDate, List<NewTradeDetails>> filterOutgoingTradeByDates = newTradeList.stream()
                .filter(t -> t.getInstruction().equals(TradeType.B.name()))
                .collect(Collectors.groupingBy(NewTradeDetails::getFinalSettlmntDate));
        
        filterOutgoingTradeByDates.forEach((key, value) -> {
            LOGGER.debug("Date Key : " + key + " Value Size: " + value.size());
            newMap.put(key,value.stream().mapToDouble(NewTradeDetails::getAmountInUSD).sum());
            
        });
        
        return newMap;

    }
    
    @Override
    public Map<String, Double> rankIncoming(List<NewTradeDetails> newTradeList) {
        Map<String, Double> newMapSell = new HashMap<>();
      //Group Trades for SELL by Entity
        Map<String, List<NewTradeDetails>> sellTradefilterByEntityMap = newTradeList.stream()
                .filter(t -> t.getInstruction().equals(TradeType.S.name()))
                .collect(Collectors.groupingBy(TradeDetails::getEntity));
      //Sum Trade Amount 
        sellTradefilterByEntityMap.forEach((key, value) -> {
            LOGGER.debug("Entity : " + key + " No. Of Trade(SELL) : " + value.size());
            newMapSell.put(key, value.stream().collect(Collectors.summingDouble(NewTradeDetails::getAmountInUSD)));
        });
      //SORT IT 
        return newMapSell.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }


    @Override
    public Map<String, Double> rankOutgoing(List<NewTradeDetails> newTradeList) {
        Map<String, Double> newMapBuy = new HashMap<>();
        
        //Group Trades for BUY by Entity 
        Map<String, List<NewTradeDetails>> buyTradefilterByEntityMap = newTradeList.stream()
                .filter(t -> t.getInstruction().equals(TradeType.B.name()))
                .collect(Collectors.groupingBy(TradeDetails::getEntity));
        //Sum Trade Amount 
        buyTradefilterByEntityMap.forEach((key, value) -> {
            LOGGER.debug("Entity : " + key + " No. Of Trade(BUY) : " + value.size());
            newMapBuy.put(key, value.stream().collect(Collectors.summingDouble(NewTradeDetails::getAmountInUSD)));
        });
        //SORT IT 
        return newMapBuy.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

}
