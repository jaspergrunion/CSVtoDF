import java.io.IOException;
import java.util.Date;

public class CSVtoDF {

    public static void main(String[] args) throws IOException {

        // Create data frame from csv file
        String fileref = "/Users/jlgunnin/IdeaProjects/CSVtoDF/testin.csv";
        Dataframe myDF = Dataframe.readCSV(fileref);

/*
        // Dataframe describe method
        myDF.describe();

        // Summary statistics and frequency tables for all columns
        myDF.summaryStatistics();

        // Produce output for select columns
        myDF.univStats("gpa");
        myDF.freqCounts("gender");

        // Column printing
        myDF.printCol("gpa", 5);

        // Column access
        double[] gpa = myDF.getNumCol("gpa");
        System.out.println("Value of gpa at position 123: " + gpa[123]);
        System.out.println();

        String[] status = myDF.getStrCol("status");
        System.out.println("Value of status at position 354: " + status[354]);
        System.out.println();

        // Extract a date column
        Date[] date = myDF.getDateCol("date", "yyyy-MM-dd");

        // Scatterplot of x vs y
        myDF.scatterPlot("gre", "gpa");
        myDF.scatterPlot("gre", "rank");

        // Time series plot of a numeric column
        myDF.timeSeriesPlot("date", "yyyy-MM-dd", "gpa");
        myDF.timeSeriesPlot("date", "yyyy-MM-dd", "gre");

        // Add column which is the uppercase of the status column
        String[] status2 = new String[myDF.getNrows()];
        for (int r = 0; r < myDF.getNrows(); r++) {
            status2[r] = status[r].toUpperCase();
        }
        Dataframe newDF = myDF.addCol(status2, "status2");
        newDF.describe();

        // Add column which is gre squared and then graph
        double[] gre = myDF.getNumCol("gre");
        double[] gre2 = new double[myDF.getNrows()];
        for (int r = 0; r < myDF.getNrows(); r++) {
            gre2[r] = Math.pow(gre[r], 2);
        }
        Dataframe newDF2 = newDF.addCol(gre2, "gre2");
        newDF2.describe();

        newDF2.scatterPlot("gre", "gre2");

        // Select subset of columns or reorder
        Dataframe myDF2 = myDF.selectColumns("id", "admit", "gre", "gpa");
        myDF2.describe();

        // Slice dataset by row number
        Dataframe sliceDF = myDF.sliceRows(50, 75);
        sliceDF.describe();

        // Filter to records where gender is "Female"
        Dataframe females = myDF.filterRows("gender", "=", "Female");
        females.describe();
        females.freqCounts("gender");

        // Filter to "Female" with status "Other"
        Dataframe femalesOther = myDF.filterRows("gender", "=", "Female").filterRows("status", "=", "Other");
        femalesOther.describe();
        femalesOther.freqCounts("gender");
        femalesOther.freqCounts("status");

        // Output to csv
        String outref = "/Users/jlgunnin/IdeaProjects/CSVtoDF/testout.csv";
        femalesOther.writeCSV(outref);

        // Filter to records where gre is >= 580
        Dataframe gre580 = myDF.filterRows("gre", ">=", 580);
        gre580.describe();
        gre580.univStats("gre");

        // Select and filter in one line
        Dataframe smallDF = myDF.selectColumns("id", "admit", "gre", "gpa").filterRows("gre", "=", 800);
        smallDF.describe();

        // Random sampling of rows
        Dataframe train = myDF.sampleRows("<=", .7, 1234);
        train.describe();

        Dataframe test = myDF.sampleRows(">", .7, 1234);
        test.describe();

        // Correlation of two numeric columns
        myDF.corr("gre", "gpa");
        myDF.corr("gpa", "rank");

        // Multivariate regression
        myDF.linearModel("admit", "gre", "gpa", "rank");
//*/

    }

}
