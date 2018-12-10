# CSVtoDF
Overview
- Java framework to read CSV data into a Dataframe like object  
- Mimics some of the functionality of dataframes found in R and Python
- Basic plotting, statistical summary, random sampling, correlation and regression are enabled by default

Dependencies: 
- https://mvnrepository.com/artifact/org.apache.commons/commons-math3
- https://mvnrepository.com/artifact/org.jfree/jfreechart

Java files:
- CSVtoDF.java - defines main method and shows example object calls
- Dataframe.java - defines a Dataframe object and useful associated methods

Features:
- Read from and write to csv files
- Scan columns and define as either string or numeric
- Print data dimensions, column names and first 5 rows
- Univariate statistics for numeric columns
- Frequency table for string columns
- Column access by column name or number
- Extract date columns with a format string
- Add a new column
- Select or reorder columns
- Slice rows using startrow, endrow parameters
- Filter rows by string column values or by numeric columns with operators
- Randomly sample rows for train, test partitioning
- Create an x vs y scatter plot of two numeric columns
- Create a time series plot given a date column and a numeric column
- Calculate correlation coefficient between two numeric columns
- Multivariate regression of y on X

```
// Create data frame from csv file
String fileref = "/Users/jlgunnin/IdeaProjects/CSVtoDF/testin.csv";
Dataframe myDF = Dataframe.readCSV(fileref);

// Dataframe describe method
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

// Summary statistics and frequency tables for all columns - output redacted
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

// Add column which is the uppercase of the status column
String[] status2 = new String[myDF.getNrows()];
for (int r = 0; r < myDF.getNrows(); r++) {
    status2[r] = status[r].toUpperCase();
}
Dataframe newDF = myDF.addCol(status2, "status2");
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
double[] gre2 = new double[gre.length];
for (int r = 0; r < myDF.getNrows(); r++) {
    gre2[r] = Math.pow(gre[r], 2); }
Dataframe newDF2 = newDF.addCol(gre2, "gre2");
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

// Add column which is the cumulative sum of gre
double[] gre = myDF.getNumCol("gre");
double[] grecumul = new double[gre.length];
double cumulsum = 0;
for (int i = 0; i < gre.length; i++) {
    cumulsum += gre[i];
    grecumul[i] = cumulsum;
}
Dataframe cumulDF = myDF.addCol(grecumul, "grecumul");
cumulDF.describe();

Dataframe with 400 rows and 9 columns
Column names: [id, admit, gre, gpa, rank, status, gender, date, grecumul]

First 5 rows:

id                  admit               gre                 gpa                 rank                status              gender              date                grecumul
<num>               <num>               <num>               <num>               <num>               <str>               <str>               <str>               <num>
[0]                 [1]                 [2]                 [3]                 [4]                 [5]                 [6]                 [7]                 [8]

1                   0                   380                 3.61                3                   Active              Male                2011-01-01          380.0
2                   1                   660                 3.67                3                   Inactive            Female              2011-01-08          1040.0
3                   1                   800                 4                   1                   Other               Male                2011-01-15          1840.0
4                   1                   640                 3.19                4                   Active              Male                2011-01-22          2480.0
5                   0                   520                 2.93                4                   Inactive            Female              2011-01-29          3000.0

// Select subset of columns or reorder
Dataframe myDF2 = myDF.selectColumns("id", "admit", "gre", "gpa");
myDF2.describe();

Dataframe with 400 rows and 4 columns
Column names: [id, admit, gre, gpa]

First 5 rows:

id                  admit               gre                 gpa                 
<num>               <num>               <num>               <num>               
[0]                 [1]                 [2]                 [3]                 

1                   0                   380                 3.61                
2                   1                   660                 3.67                
3                   1                   800                 4                   
4                   1                   640                 3.19                
5                   0                   520                 2.93     

// Slice dataset by row number
Dataframe sliceDF = myDF.sliceRows(50, 75);
sliceDF.describe();

Dataframe with 26 rows and 8 columns
Column names: [id, admit, gre, gpa, rank, status, gender, date]

First 5 rows:

id                  admit               gre                 gpa                 rank                status              gender              date                
<num>               <num>               <num>               <num>               <num>               <str>               <str>               <str>               
[0]                 [1]                 [2]                 [3]                 [4]                 [5]                 [6]                 [7]                 

50                  0                   400                 3.35                3                   Inactive            Female              2011-12-10          
51                  0                   640                 3.86                3                   Active              Male                2011-12-17          
52                  0                   440                 3.13                4                   Inactive            Female              2011-12-24          
53                  0                   740                 3.37                4                   Active              Male                2011-12-31          
54                  1                   680                 3.27                2                   Inactive            Male                2012-01-07 

// Filter to records where gender is "Female"
Dataframe females = myDF.filterRows("gender", "=", "Female");
females.describe();
females.freqCounts("gender");

Dataframe with 160 rows and 8 columns
Column names: [id, admit, gre, gpa, rank, status, gender, date]

First 5 rows:

id                  admit               gre                 gpa                 rank                status              gender              date                
<num>               <num>               <num>               <num>               <num>               <str>               <str>               <str>               
[0]                 [1]                 [2]                 [3]                 [4]                 [5]                 [6]                 [7]                 

2                   1                   660                 3.67                3                   Inactive            Female              2011-01-08          
5                   0                   520                 2.93                4                   Inactive            Female              2011-01-29          
6                   1                   760                 3                   2                   Other               Female              2011-02-05          
10                  0                   700                 3.92                2                   Active              Female              2011-03-05          
12                  0                   440                 3.22                1                   Other               Female              2011-03-19 

Frequency counts for 'gender': {Female=160}

// Filter to "Female" with status "Other"
Dataframe femalesOther = myDF.filterRows("gender", "=", "Female").filterRows("status", "=", "Other");
femalesOther.describe();
femalesOther.freqCounts("gender");
femalesOther.freqCounts("status");
 
Dataframe with 48 rows and 8 columns
Column names: [id, admit, gre, gpa, rank, status, gender, date]

First 5 rows:

id                  admit               gre                 gpa                 rank                status              gender              date                
<num>               <num>               <num>               <num>               <num>               <str>               <str>               <str>               
[0]                 [1]                 [2]                 [3]                 [4]                 [5]                 [6]                 [7]                 

6                   1                   760                 3                   2                   Other               Female              2011-02-05          
12                  0                   440                 3.22                1                   Other               Female              2011-03-19          
15                  1                   700                 4                   1                   Other               Female              2011-04-09          
30                  0                   520                 3.29                1                   Other               Female              2011-07-23          
32                  0                   760                 3.35                3                   Other               Female              2011-08-06          

Frequency counts for 'gender': {Female=48}

Frequency counts for 'status': {Other=48}

// Output to csv
String outref = "/Users/jlgunnin/IdeaProjects/CSVtoDF/testout.csv";
femalesOther.writeCSV(outref);

// Filter to records where gre is >= 580
Dataframe gre580 = myDF.filterRows("gre", ">=", 580);
gre580.describe();
gre580.univStats("gre");

Dataframe with 226 rows and 8 columns
Column names: [id, admit, gre, gpa, rank, status, gender, date]

First 5 rows:

id                  admit               gre                 gpa                 rank                status              gender              date                
<num>               <num>               <num>               <num>               <num>               <str>               <str>               <str>               
[0]                 [1]                 [2]                 [3]                 [4]                 [5]                 [6]                 [7]                 

2                   1                   660                 3.67                3                   Inactive            Female              2011-01-08          
3                   1                   800                 4                   1                   Other               Male                2011-01-15          
4                   1                   640                 3.19                4                   Active              Male                2011-01-22          
6                   1                   760                 3                   2                   Other               Female              2011-02-05          
10                  0                   700                 3.92                2                   Active              Female              2011-03-05          

Column: gre
Type: num
N: 226.0
Sum: 151260.00
Min: 580.0
Pct25: 620.0
Mean: 669.29
Median: 660.0
Pct75: 720.0
Max: 800.0
Std Dev: 68.98

// Select and filter in one line
Dataframe smallDF = myDF.selectColumns("id", "admit", "gre", "gpa").filterRows("gre", "=", 800);
smallDF.describe();

Dataframe with 25 rows and 4 columns
Column names: [id, admit, gre, gpa]

First 5 rows:

id                  admit               gre                 gpa
<num>               <num>               <num>               <num>
[0]                 [1]                 [2]                 [3]

3                   1                   800                 4
11                  0                   800                 4
19                  0                   800                 3.75
26                  1                   800                 3.66
34                  1                   800                 4

// Random sampling of rows
Dataframe train = myDF.sampleRows("<=", .7, 1234);
train.describe();

Dataframe with 279 rows and 8 columns
Column names: [id, admit, gre, gpa, rank, status, gender, date]

First 5 rows:

id                  admit               gre                 gpa                 rank                status              gender              date                
<num>               <num>               <num>               <num>               <num>               <str>               <str>               <str>               
[0]                 [1]                 [2]                 [3]                 [4]                 [5]                 [6]                 [7]                 

1                   0                   380                 3.61                3                   Active              Male                2011-01-01          
4                   1                   640                 3.19                4                   Active              Male                2011-01-22          
5                   0                   520                 2.93                4                   Inactive            Female              2011-01-29          
6                   1                   760                 3                   2                   Other               Female              2011-02-05          
7                   1                   560                 2.98                1                   Active              Male                2011-02-12          

Dataframe test = myDF.sampleRows(">", .7, 1234);
test.describe();

Dataframe with 121 rows and 8 columns
Column names: [id, admit, gre, gpa, rank, status, gender, date]

First 5 rows:

id                  admit               gre                 gpa                 rank                status              gender              date                
<num>               <num>               <num>               <num>               <num>               <str>               <str>               <str>               
[0]                 [1]                 [2]                 [3]                 [4]                 [5]                 [6]                 [7]                 

2                   1                   660                 3.67                3                   Inactive            Female              2011-01-08          
3                   1                   800                 4                   1                   Other               Male                2011-01-15          
9                   1                   540                 3.39                3                   Other               Male                2011-02-26          
15                  1                   700                 4                   1                   Other               Female              2011-04-09          
19                  0                   800                 3.75                2                   Active              Male                2011-05-07 

// Scatter plots
myDF.scatterPlot("gre", "gpa");
myDF.scatterPlot("gre", "rank");

// Time series plots
myDF.timeSeriesPlot("date", "yyyy-MM-dd", "gpa");
myDF.timeSeriesPlot("date", "yyyy-MM-dd", "gre");

// Correlation of two numeric columns
myDF.corr("gre", "gpa");
myDF.corr("gpa", "rank");

Correlation of gre and gpa: 0.3842658780208496

Correlation of gpa and rank: -0.05746076778557216

// Multivariate regression
myDF.linearModel("admit", "gre", "gpa", "rank");

Regression summary for [admit ~ gre + gpa + rank]:

intercept: -0.182
gre: 0.000
gpa: 0.151
rank: -0.110
r-squared: 0.089

```
