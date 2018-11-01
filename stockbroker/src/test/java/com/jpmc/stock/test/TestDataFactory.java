package com.jpmc.stock.test;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import com.jpmc.stock.model.NewTradeDetails;
import com.jpmc.stock.model.TradeDetails;

public abstract class TestDataFactory {

    public static List<TradeDetails> createTradeList() {

        List<TradeDetails> tradeDetailsList = new ArrayList<>();
        //TradeDetails(String entity, String instruction, double agreedFx, String currency, LocalDate instructionDate, LocalDate settlementDate, int units, double pricePerUnit)
        //Jan 1 - Fri , Jan 4- Mon
        tradeDetailsList.add(new TradeDetails("foo", "B", 0.5, "AED", LocalDate.of(2016, Month.JANUARY, 1), LocalDate.of(2016, Month.JANUARY, 2), 200, 100.25));
        tradeDetailsList.add(new TradeDetails("foo", "B", 0.5, "INR", LocalDate.of(2016, Month.JANUARY, 1), LocalDate.of(2016, Month.JANUARY, 2), 200, 100.25));
        tradeDetailsList.add(new TradeDetails("bar", "B", 0.5, "SAR", LocalDate.of(2016, Month.JANUARY, 1), LocalDate.of(2016, Month.JANUARY, 3), 200, 100.25));
        tradeDetailsList.add(new TradeDetails("bar", "B", 0.5, "SGP", LocalDate.of(2016, Month.JANUARY, 1), LocalDate.of(2016, Month.JANUARY, 3), 200, 100.25));
        tradeDetailsList.add(new TradeDetails("bar", "B", 0.5, "INR", LocalDate.of(2016, Month.JANUARY, 1), LocalDate.of(2016, Month.JANUARY, 5), 200, 100.25));
        tradeDetailsList.add(new TradeDetails("john", "B", 0.5, "AED", LocalDate.of(2016, Month.JANUARY, 1), LocalDate.of(2016, Month.JANUARY, 1), 200,100.25));
        tradeDetailsList.add(new TradeDetails("john", "B", 0.5, "SGP", LocalDate.of(2016, Month.JANUARY, 1), LocalDate.of(2016, Month.JANUARY, 3), 200,100.25));
        tradeDetailsList.add(new TradeDetails("john", "B", 0.5, "SGP", LocalDate.of(2016, Month.JANUARY, 1), LocalDate.of(2016, Month.JANUARY, 4), 200,100.25));
        
        tradeDetailsList.add(new TradeDetails("foo", "S", 0.5, "AED", LocalDate.of(2016, Month.JANUARY, 1), LocalDate.of(2016, Month.JANUARY, 2), 200,100.25));
        tradeDetailsList.add(new TradeDetails("foo", "S", 0.5, "SGP", LocalDate.of(2016, Month.JANUARY, 1), LocalDate.of(2016, Month.JANUARY, 5), 200, 100.25));
        tradeDetailsList.add(new TradeDetails("bar", "S", 0.5, "INR", LocalDate.of(2016, Month.JANUARY, 1), LocalDate.of(2016, Month.JANUARY, 2), 200,100.25));
        tradeDetailsList.add(new TradeDetails("bar", "S", 0.5, "SAR", LocalDate.of(2016, Month.JANUARY, 1), LocalDate.of(2016, Month.JANUARY, 3), 200,100.25));
        tradeDetailsList.add(new TradeDetails("john", "S", 0.5, "INR", LocalDate.of(2016, Month.JANUARY, 1), LocalDate.of(2016, Month.JANUARY, 2), 200,100.25));
        tradeDetailsList.add(new TradeDetails("john", "S", 0.5, "SGP", LocalDate.of(2016, Month.JANUARY, 1), LocalDate.of(2016, Month.JANUARY, 5), 200,100.25));
        tradeDetailsList.add(new TradeDetails("john", "S", 0.5, "SAR", LocalDate.of(2016, Month.JANUARY, 1), LocalDate.of(2016, Month.JANUARY, 1), 200,100.25));
        
        return tradeDetailsList;

    }
    
    public static List<NewTradeDetails> createNewTradeList() {

        List<NewTradeDetails> tradeDetailsList = new ArrayList<>();
        //TradeDetails(String entity, String instruction, double agreedFx, String currency, LocalDate instructionDate, LocalDate settlementDate, int units, double pricePerUnit)
        //Jan 1 - Fri ,2- Sat,3-Sun, Jan 4- Mon
        tradeDetailsList.add(new NewTradeDetails("foo", "B", 0.5, "AED", LocalDate.of(2016, Month.JANUARY, 1), LocalDate.of(2016, Month.JANUARY, 2), 200, 100.25,
                                                 LocalDate.of(2016, Month.JANUARY, 3),10025.0));
        tradeDetailsList.add(new NewTradeDetails("foo", "B", 0.5, "INR", LocalDate.of(2016, Month.JANUARY, 1), LocalDate.of(2016, Month.JANUARY, 2), 200, 100.25,
                                                 LocalDate.of(2016, Month.JANUARY, 4),10025.0));
        tradeDetailsList.add(new NewTradeDetails("bar", "B", 0.5, "SAR", LocalDate.of(2016, Month.JANUARY, 1), LocalDate.of(2016, Month.JANUARY, 3), 200, 100.25,
                                                 LocalDate.of(2016, Month.JANUARY, 3),10025.0));         
        tradeDetailsList.add(new NewTradeDetails("bar", "B", 0.5, "SGP", LocalDate.of(2016, Month.JANUARY, 1), LocalDate.of(2016, Month.JANUARY, 3), 200, 100.25,
                                                 LocalDate.of(2016, Month.JANUARY, 4),10025.0));
        tradeDetailsList.add(new NewTradeDetails("bar", "B", 0.5, "INR", LocalDate.of(2016, Month.JANUARY, 1), LocalDate.of(2016, Month.JANUARY, 5), 200, 100.25,
                                                 LocalDate.of(2016, Month.JANUARY, 5),10025.0));
        tradeDetailsList.add(new NewTradeDetails("john", "B", 0.5, "AED", LocalDate.of(2016, Month.JANUARY, 1), LocalDate.of(2016, Month.JANUARY, 1), 200,100.25,
                                                 LocalDate.of(2016, Month.JANUARY, 3),10025.0));
        tradeDetailsList.add(new NewTradeDetails("john", "B", 0.5, "SGP", LocalDate.of(2016, Month.JANUARY, 1), LocalDate.of(2016, Month.JANUARY, 3), 200,100.25,
                                                 LocalDate.of(2016, Month.JANUARY, 4),10025.0));
        tradeDetailsList.add(new NewTradeDetails("john", "B", 0.5, "SGP", LocalDate.of(2016, Month.JANUARY, 1), LocalDate.of(2016, Month.JANUARY, 4), 200,100.25,
                                                 LocalDate.of(2016, Month.JANUARY, 4),10025.0));
        
        
        tradeDetailsList.add(new NewTradeDetails("foo", "S", 0.5, "AED", LocalDate.of(2016, Month.JANUARY, 1), LocalDate.of(2016, Month.JANUARY, 2), 200,100.25,
                                                 LocalDate.of(2016, Month.JANUARY, 3),10025.0));
        tradeDetailsList.add(new NewTradeDetails("foo", "S", 0.5, "SGP", LocalDate.of(2016, Month.JANUARY, 1), LocalDate.of(2016, Month.JANUARY, 5), 200, 100.25,
                                                 LocalDate.of(2016, Month.JANUARY, 5),10025.0));
        tradeDetailsList.add(new NewTradeDetails("bar", "S", 0.5, "INR", LocalDate.of(2016, Month.JANUARY, 1), LocalDate.of(2016, Month.JANUARY, 2), 200,100.25,
                                                 LocalDate.of(2016, Month.JANUARY, 4),10025.0));
        tradeDetailsList.add(new NewTradeDetails("bar", "S", 0.5, "SAR", LocalDate.of(2016, Month.JANUARY, 1), LocalDate.of(2016, Month.JANUARY, 3), 200,100.25,
                                                 LocalDate.of(2016, Month.JANUARY, 3),10025.0));
        tradeDetailsList.add(new NewTradeDetails("john", "S", 0.5, "INR", LocalDate.of(2016, Month.JANUARY, 1), LocalDate.of(2016, Month.JANUARY, 2), 200,100.25,
                                                 LocalDate.of(2016, Month.JANUARY, 4),10025.0));
        tradeDetailsList.add(new NewTradeDetails("john", "S", 0.5, "SGP", LocalDate.of(2016, Month.JANUARY, 1), LocalDate.of(2016, Month.JANUARY, 5), 200,100.25,
                                                 LocalDate.of(2016, Month.JANUARY, 5),10025.0));
        tradeDetailsList.add(new NewTradeDetails("john", "S", 0.5, "SAR", LocalDate.of(2016, Month.JANUARY, 1), LocalDate.of(2016, Month.JANUARY, 1), 200,100.25,
                                                 LocalDate.of(2016, Month.JANUARY, 3),10025.0));
        
        return tradeDetailsList;

    }

}
