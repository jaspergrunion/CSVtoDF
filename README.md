# CSVtoDF
Java framework to read CSV data into a Dataframe like object  
Mimics some of the functionality of dataframes found in R and Python

Dependencies: https://mvnrepository.com/artifact/org.apache.commons/commons-math3

Java files:
- ReadCSV.java: reads in a comma delimited file and creates a 2d string array from it
- Dataframe.java - instantiates a dataframe object from a 2d string array
- CSVtoDF.java - defines main method and shows example object calls

Dataframe features:
- Columns scanned and defined either as string or numeric
- Describe() shows data dimensions, column names, and prints first 5 rows
- SummaryStats() will show univariate statistics for numeric columns, or a frequency table for string columns
- Columns can be accessed by column number
    
Future functionality:
- Column access by name
- Add date field type
- Create new column from transformation of existing columns and add to data frame
- Grouping operations in summary

```
Dataframe with 400 rows and 8 columns
Column names: [id, admit, gre, gpa, rank, status, gender, date]

First 5 rows:

id                  admit               gre                 gpa                 rank                status              gender              date                
<num>               <num>               <num>               <num>               <num>               <str>               <str>               <str>               
[0]                 [1]                 [2]                 [3]                 [4]                 [5]                 [6]                 [7]                 

1                   0                   380                 3.61                3                   Active              Male                2011-01-01          
2                   1                   660                 3.67                3                   Inactive            Female              2011-01-08          
3                   1                   800                 4                   1                   Other               Male                2011-01-15          
4                   1                   640                 3.19                4                   Active              Male                2011-01-22          
5                   0                   520                 2.93                4                   Inactive            Female              2011-01-29 

Column: gpa
Type: num
N: 400.0
Sum: 1355.96
Min: 2.26
Pct25: 3.13
Mean: 3.39
Median: 3.395
Pct75: 3.67
Max: 4.0
Std Dev: 0.38

Frequency counts for 'status': {Active=196, Inactive=116, Other=88}

gre
<num>
[2]

380
660
800
640
520

status
<str>
[5]

Active
Inactive
Other
Active
Inactive

Value of myNumCol at position 354: 540.0
Value of myStrCol at position 123: Active

```
