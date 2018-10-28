package com.jpmc.stock.business;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
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

import com.jpmc.stock.model.Currency;
import com.jpmc.stock.model.NewTradeDetails;
import com.jpmc.stock.model.TradeDetails;
import com.jpmc.stock.model.TradeType;

/**
 * @author Chinmoy 
 * Engine to Calculate the INCOMING/SELL for each day of Trade. 
 * Calculate the OUTGOING/BUY for each day of Trade. 
 * Sort the Entity based on INCOMING / OUTGOING.
 * 
 */
@Component
public class TradeReportEngine {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    
    /**
     * Calculate the OUTGOING/BUY for each day of Trade.
     * 
     */
    public void calculateIncoming(List<NewTradeDetails> newTradeList) {
        LOGGER.debug("calculateIncoming");
        Map<LocalDate, Double> newMap = new HashMap<>();
        Map<LocalDate, List<NewTradeDetails>> filterIncomingTradeByDates = newTradeList.stream()
                .filter( t -> t.getInstruction().equals(TradeType.S.name()))
                .collect(Collectors.groupingBy(NewTradeDetails::getFinalSettlmntDate));
        filterIncomingTradeByDates.forEach((key, value) -> {
            LOGGER.debug("Date Key : " + key + " Value Size : " + value.size());
            newMap.put(key,value.stream().mapToDouble(NewTradeDetails::getAmountInUSD).sum());
        });
        LOGGER.info("********************************************************");
        LOGGER.info("****** REPORT FOR EACH DAY TRADING INCOMING/SELL********");
        newMap.forEach((date, amount) -> LOGGER.info("DATE::" + date + " AMOUNT(USD)::" + amount));
        LOGGER.info("********************************************************");
    }
    
    
    /**
     * Used to calculate the OUTGOING/BUY for each day of Trade.
     */
    public void calculateOutgoing(List<NewTradeDetails> newTradeList) {
        LOGGER.debug("calculateOutgoing");
        Map<LocalDate, Double> newMap = new HashMap<>();
        Map<LocalDate, List<NewTradeDetails>> filterOutgoingTradeByDates = newTradeList.stream()
                .filter(t -> t.getInstruction().equals(TradeType.B.name()))
                .collect(Collectors.groupingBy(NewTradeDetails::getFinalSettlmntDate));
        
        filterOutgoingTradeByDates.forEach((key, value) -> {
            LOGGER.debug("Date Key : " + key + " Value Size: " + value.size());
            /*for (TradeDetails t : value) {
                double amount = newMap.containsKey(key) ? newMap.get(key) : 0;
                amount += t.getAgreedFx() * t.getPricePerUnit() * t.getUnits();
                newMap.put(key, amount);
            }*/
            newMap.put(key,value.stream().mapToDouble(NewTradeDetails::getAmountInUSD).sum());
            
        });
        LOGGER.info("********************************************************");
        LOGGER.info("****** REPORT FOR EACH DAY TRADING OUTGOING/BUY*********");
        newMap.forEach((date, amount) -> LOGGER.info("DATE::" + date + " AMOUNT(USD)::" + amount));
        LOGGER.info("********************************************************");

    }
    
    /**
     * Method is used to calculate the Amount in USD
     * and check the Settlement Date if it falls on Saturday/Sunday, change it to Monday.
     * OR if the currency is AED/SAR, change the settlement date to Sunday if it falls on Fri/Sat.
     * @param List
     * @return List
     */
    public List<NewTradeDetails> calcAmountSettlDate(List<TradeDetails> tradeList) {

        List<NewTradeDetails> newTradeDetailsList = new ArrayList<>();
        for (TradeDetails trade : tradeList) {
            NewTradeDetails newTradeDetails = new NewTradeDetails(trade);
            //USD amount of a trade = Price per unit * Units * Agreed Fx
            newTradeDetails.setAmountInUSD(trade.getAgreedFx() * trade.getPricePerUnit() * trade.getUnits());
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
            if(newTradeDetails.getFinalSettlmntDate()==null)
                newTradeDetails.setFinalSettlmntDate(trade.getSettlementDate());
            newTradeDetailsList.add(newTradeDetails);
        }
        return newTradeDetailsList;
    }

    /**
     * Used to sort Enity based on Incoming(Sell) and Outgoing(Buy).
     * @param List
     * @return void
     */
    public void sortEntity(List<NewTradeDetails> newTradeList) {
        
        Map<String, Double> newMapBuy = new HashMap<>();
        Map<String, Double> newMapSell = new HashMap<>();
        
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
        Map<String, Double> sortedMapBuy = newMapBuy.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        
        LOGGER.info("********************************************************");
        LOGGER.info("****** RANKING OF ENITIES BASED ON OUTGOING/BUY  *******");
        sortedMapBuy.forEach((key,value) -> {
        LOGGER.info("Entity : " + key + "| Total Trade(BUY) Amount : " + value);
            });
        LOGGER.info("********************************************************");
        
      //Group Trades for SELL by Entity 
        Map<String, List<NewTradeDetails>> sellTradefilterByEntityMap = newTradeList.stream()
                .filter(t -> t.getInstruction().equals(TradeType.S.name()))
                .collect(Collectors.groupingBy(TradeDetails::getEntity));
        sellTradefilterByEntityMap.forEach((key, value) -> {
            LOGGER.debug("Entity : " + key + " No. Of Trade(SELL) : " + value.size());
            newMapSell.put(key, value.stream().collect(Collectors.summingDouble(NewTradeDetails::getAmountInUSD)));
        });
        Map<String, Double> sortedMapSell = newMapSell.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        
        LOGGER.info("********************************************************");
        LOGGER.info("****** RANKING OF ENITIES BASED ON INCOMING/SELL *******");
        sortedMapSell.forEach((key,value) -> {
        LOGGER.info("Entity : " + key + "| Total Trade(SELL) Amount : " + value);
            });
        LOGGER.info("********************************************************");
        
        
    }

}
