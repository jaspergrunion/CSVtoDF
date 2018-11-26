# CSVtoDF
Java framework to read CSV data into a Dataframe like object

Mimics some of the functionality of dataframes found in R and Python

Java files:

ReadCSV.java - reads in a comma delimited file and creates a 2d string array off of it
Dataframe.java - defines a dataframe object off of a 2d string array
CSVtoDF.java - defines main method and shows example object calls

Dataframe features: 
    - Columns scanned and defined either <num> or <str>
    - Describe() shows data dimensions, column names, and prints first 5 rows
    - SummaryStats() will show univariate statistics for <num> columns, or a frequency table for <str> columns
    - Columns can be accessed by column number
    
Future functionality:
    - Column access by name
    - Add date field type
    - Create new column from transformation of existing columns and add to data frame
    - Grouping operations in summary
