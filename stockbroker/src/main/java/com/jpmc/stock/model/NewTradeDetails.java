/**
 * 
 */
package com.jpmc.stock.model;

import java.time.LocalDate;

/**
 * @author Chinmoy
 * This is extended class for Base TradeDetails.
 * Here we add extra parameters used in business logic.
 */
public class NewTradeDetails extends TradeDetails{
    
    private LocalDate finalSettlmntDate;
    
    private double amountInUSD;
    
    public LocalDate getFinalSettlmntDate() {
        return finalSettlmntDate;
    }

    public void setFinalSettlmntDate(LocalDate finalSettlmntDate) {
        this.finalSettlmntDate = finalSettlmntDate;
    }

    public NewTradeDetails(String entity, String instruction, double agreedFx, String currency, LocalDate instructionDate, LocalDate settlementDate, int units,
            double pricePerUnit, LocalDate finalSettlmntDate, double amountInUSD) {
        super(entity, instruction, agreedFx, currency, instructionDate, settlementDate, units, pricePerUnit);
        this.finalSettlmntDate = finalSettlmntDate;
        this.amountInUSD = amountInUSD;
    }
    
    

    public double getAmountInUSD() {
        return amountInUSD;
    }



    public void setAmountInUSD(double amountInUSD) {
        this.amountInUSD = amountInUSD;
    }

    public NewTradeDetails(TradeDetails trade) {
        super(trade);
    }

    @Override
    public String toString() {
        return "NewTradeDetails [finalSettlmntDate=" + finalSettlmntDate + ", amountInUSD=" + amountInUSD + ", getEntity()=" + getEntity()
                + ", getInstruction()=" + getInstruction() + ", getAgreedFx()=" + getAgreedFx() + ", getCurrency()=" + getCurrency() + ", getUnits()="
                + getUnits() + ", getPricePerUnit()=" + getPricePerUnit() + ", getInstructionDate()=" + getInstructionDate() + ", getSettlementDate()="
                + getSettlementDate() + "]";
    }

}
