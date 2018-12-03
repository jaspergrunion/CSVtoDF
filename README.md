# CSVtoDF
Java framework to read CSV data into a Dataframe like object  
Mimics some of the functionality of dataframes found in R and Python

Dependencies: 
- https://mvnrepository.com/artifact/org.apache.commons/commons-math3
- https://mvnrepository.com/artifact/org.jfree/jfreechart

Java files:
- ReadCSV.java: reads in a comma delimited file and creates a 2d string array from it
- Dataframe.java - instantiates a dataframe object from a 2d string array
- CSVtoDF.java - defines main method and shows example object calls

Dataframe features:
- Columns scanned and defined either as string or numeric
- Describe() shows data dimensions, column names, and prints first 5 rows
- SummaryStats() will show univariate statistics for numeric columns, or a frequency table for string columns
- Columns can be accessed by column name or number
- Date columns can be extracted based on format string
- Can generate xy scatterplots
- Can generate a time series plot given a date column and a numeric column
- Can create new column from transformation of existing columns and add to data frame
    
Future functionality:
- Grouping operations in summary

```
// Read from csv file
String fileref = "/Users/jlgunnin/Downloads/testfile.csv";
String[][] rawDFmat = ReadCSV.getRawDF(fileref);

// Instantiate data frame object
Dataframe myDF = new Dataframe(rawDFmat);

// Dataframe describe and print methods
myDF.describe();

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

// Summary statistics and frequency tables
// Produce output for all columns 
myDF.summaryStatistics(); 

// Produce output for select columns
myDF.univStats("gpa");

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

myDF.freqCounts("gender");

Frequency counts for 'gender': {Male=240, Female=160}

// Column printing
myDF.printCol("gpa", 5);

gpa
<num>
[3]

3.61
3.67
4
3.19
2.93

// Numeric column access
double[] gpa = myDF.getNumCol("gpa");
System.out.println("Value of gpa at position 123: " + gpa[123]);
        
Value of gpa at position 123: 2.98

// String column access
String[] status = myDF.getStrCol("status");
System.out.println("Value of status at position 354: " + status[354]);

Value of status at position 354: Active

// Date column access
Date[] date = myDF.getDateCol("date", "yyyy-MM-dd");

// Scatter plots
myDF.scatterPlot("gre", "gpa");
myDF.scatterPlot("gre", "rank");

// Time series plots
myDF.timeSeriesPlot("date", "yyyy-MM-dd", "gpa");
myDF.timeSeriesPlot("date", "yyyy-MM-dd", "gre");

// Add column which is the uppercase of the status column
String[] status2 = new String[myDF.getNrows()];
for (int r = 0; r < myDF.getNrows(); r++) {
    status2[r] = status[r].toUpperCase();
}
Dataframe newDF = myDF.addCol(status2, "status2");
System.out.println();
newDF.describe();

Dataframe with 400 rows and 9 columns
Column names: [id, admit, gre, gpa, rank, status, gender, date, status2]

First 5 rows:

id                  admit               gre                 gpa                 rank                status              gender              date                status2             
<num>               <num>               <num>               <num>               <num>               <str>               <str>               <str>               <str>               
[0]                 [1]                 [2]                 [3]                 [4]                 [5]                 [6]                 [7]                 [8]                 

1                   0                   380                 3.61                3                   Active              Male                2011-01-01          ACTIVE              
2                   1                   660                 3.67                3                   Inactive            Female              2011-01-08          INACTIVE            
3                   1                   800                 4                   1                   Other               Male                2011-01-15          OTHER               
4                   1                   640                 3.19                4                   Active              Male                2011-01-22          ACTIVE              
5                   0                   520                 2.93                4                   Inactive            Female              2011-01-29          INACTIVE 

// Add column which is gre squared and then graph
double[] gre = myDF.getNumCol("gre");
double[] gre2 = new double[myDF.getNrows()];
for (int r = 0; r < myDF.getNrows(); r++) {
    gre2[r] = Math.pow(gre[r], 2);
}
Dataframe newDF2 = newDF.addCol(gre2, "gre2");
System.out.println();
newDF2.describe();

newDF2.scatterPlot("gre", "gre2");

Dataframe with 400 rows and 10 columns
Column names: [id, admit, gre, gpa, rank, status, gender, date, status2, gre2]

First 5 rows:

id                  admit               gre                 gpa                 rank                status              gender              date                status2             gre2                
<num>               <num>               <num>               <num>               <num>               <str>               <str>               <str>               <str>               <num>               
[0]                 [1]                 [2]                 [3]                 [4]                 [5]                 [6]                 [7]                 [8]                 [9]                 

1                   0                   380                 3.61                3                   Active              Male                2011-01-01          ACTIVE              144400.0            
2                   1                   660                 3.67                3                   Inactive            Female              2011-01-08          INACTIVE            435600.0            
3                   1                   800                 4                   1                   Other               Male                2011-01-15          OTHER               640000.0            
4                   1                   640                 3.19                4                   Active              Male                2011-01-22          ACTIVE              409600.0            
5                   0                   520                 2.93                4                   Inactive            Female              2011-01-29          INACTIVE            270400.0            



```
