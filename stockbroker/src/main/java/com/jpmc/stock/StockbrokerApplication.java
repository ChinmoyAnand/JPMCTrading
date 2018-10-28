package com.jpmc.stock;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.jpmc.stock.exception.ApplicationException;
import com.jpmc.stock.facade.TradeFacade;

@SpringBootApplication
public class StockbrokerApplication {
    
	public static void main(String[] args) throws ApplicationException {
	    
	    //TradeFacade tradeFacade = new TradeFacade();
		ApplicationContext context = SpringApplication.run(StockbrokerApplication.class, args);
		TradeFacade tradeFacade = context.getBean(TradeFacade.class);
		tradeFacade.receiveTradeDetails();
	}
}
