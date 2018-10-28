# stockExchange
Source Code for Implementation of Stock Exchange 
-----------------------------------------------------------------------------------------
The Architecture for Implementating the Trading Report is described below::
FACADE LAYER - This is the first layer which receives trade details from various source systems.
SERVICE LAYER - This layer manupulates and calculates the different logic required for report generation.
DAO LAYER - Not Required
ENGINE CLASS - This class calculates all the business logic required to generate the report.

---------------------------------------------------------------------------------------------------------------

The Project is a simple Spring Boot Project with no external jars involved. We have used spring core principle to do the DI.
It uses heavily Java 8 streams and lambda expressions to calculate the trading reports.
Log Level is set to INFO , we can set it to DEBUG to have more detail logs.
Sample data is loaded by Constructors.
