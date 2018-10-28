package com.jpmc.stock.model;

import java.time.LocalDate;

public class TradeDetails {
    
    private String entity;
    private String instruction;
    private double agreedFx;
    private String currency;
    private LocalDate instructionDate;
    private LocalDate settlementDate;
    private int units;
    private double pricePerUnit;
    
   
    public TradeDetails(String entity, String instruction, double agreedFx, String currency, LocalDate instructionDate, LocalDate settlementDate, int units,
            double pricePerUnit) {
        super();
        this.entity = entity;
        this.instruction = instruction;
        this.agreedFx = agreedFx;
        this.currency = currency;
        this.instructionDate = instructionDate;
        this.settlementDate = settlementDate;
        this.units = units;
        this.pricePerUnit = pricePerUnit;
    }
    public TradeDetails(TradeDetails trade) {
        this(trade.entity,trade.instruction,trade.agreedFx,trade.currency,trade.instructionDate,trade.settlementDate,trade.units,trade.pricePerUnit);
    }
    public String getEntity() {
        return entity;
    }
    public void setEntity(String entity) {
        this.entity = entity;
    }
    public String getInstruction() {
        return instruction;
    }
    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }
   
    public double getAgreedFx() {
        return agreedFx;
    }
    public void setAgreedFx(double agreedFx) {
        this.agreedFx = agreedFx;
    }
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    
    public int getUnits() {
        return units;
    }
    public void setUnits(int units) {
        this.units = units;
    }
    public double getPricePerUnit() {
        return pricePerUnit;
    }
    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }
    
    public LocalDate getInstructionDate() {
        return instructionDate;
    }
    public void setInstructionDate(LocalDate instructionDate) {
        this.instructionDate = instructionDate;
    }
    public LocalDate getSettlementDate() {
        return settlementDate;
    }
    public void setSettlementDate(LocalDate settlementDate) {
        this.settlementDate = settlementDate;
    }
    @Override
    public String toString() {
        return "TradeDetails [entity=" + entity + ", instruction=" + instruction + ", agreedFx=" + agreedFx + ", currency=" + currency + ", instructionDate="
                + instructionDate + ", settlementDate=" + settlementDate + ", units=" + units + ", pricePerUnit=" + pricePerUnit + "]";
    }
    
}
