#simple test case writing using Gherkin
Feature: Foreign Exchange Rates API Check

# Test ID : (Exchange Rates_01)
#Scenario 1:
Scenario: API response for all latest exchange rates 

Given API is available
When GET request is sent
Then response is received
And response will give success status
And response contains all latest exchange rates for base EURO


# Test ID : (Exchange Rates_02)
#Scenario 2:
Scenario: Negative scenario to check response for incorrect API call

Given incorrect API is used
When GET request is sent with incorrect API
Then response is received
And response contains error message


# Test ID : (Exchange Rates_03)
#Scenario 3:
Scenario Outline: API response check for currencies given in symbols parameter

Given API with symbols parameter "<value>" is available
When GET request is sent
Then response is received
And response will give success status
And response contains latest exchange rates for currencies given in symbols parameter
Examples:
| value |
| USD |
| USD,GBP |


# Test ID : (Exchange Rates_04)
#Scenario 4:
Scenario Outline: API response check for base currency other than EURO

Given API with base parameter "<value>" is available
When GET request is sent
Then response is received
And response will give success status
And response contains all latest exchange rates for base currency given in base parameter
Examples:
| currency |
| USD |
| GBP |


# Test ID : (Exchange Rates_06)
#Scenario 5:
Scenario: API response check for historic exchange rates on specific date

Given API for past date since 1999 is available
When GET request is sent
Then response is received
And response will give success status
And response contains historic exchange rates for base EURO for specified date


# Test ID : (Exchange Rates_09)
#Scenario 6:
Scenario: API response check for historic exchange rates on specific date having base and symbols parameter values

Given API for past date for base parameter and symbols parameter values is available
When GET request is sent
Then response is received
And response will give success status
And response contains historic exchange rates between base and symbols currencies on specified date


# Test ID : (Exchange Rates_12)
#Scenario 7:
Scenario: API response check for exchange rates on future date

Given API for future date is available
When GET request is sent
Then response is received
And response will give success status
And response contains latest exchange rates for base EURO

