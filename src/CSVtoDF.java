import java.util.Date;

public class CSVtoDF {

    public static void main(String[] args) {

        // Read from csv file
        String fileref = "/Users/jlgunnin/Downloads/testfile.csv";
        String[][] rawDFmat = ReadCSV.getRawDF(fileref);

        // Instantiate data frame object
        Dataframe myDF = new Dataframe(rawDFmat);

        // Dataframe describe and print methods
        myDF.describe();

        // Summary statistics and frequency tables
        myDF.summaryStatistics();
        myDF.univStats("gpa");
        myDF.freqCounts("gender");

        // Column printing
        myDF.printCol("gpa", 5);

        // Column access
        System.out.println();
        double[] gpa = myDF.getNumCol("gpa");
        System.out.println("Value of gpa at position 123: " + gpa[123]);

        System.out.println();
        String[] status = myDF.getStrCol("status");
        System.out.println("Value of status at position 354: " + status[354]);

        // Extract a date column and print
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
        System.out.println();
        newDF.describe();

        // Add column which is gre to the power of 5 and then graph
        double[] gre = myDF.getNumCol("gre");
        double[] gre2 = new double[myDF.getNrows()];
        for (int r = 0; r < myDF.getNrows(); r++) {
            gre2[r] = Math.pow(gre[r], 5);
        }
        Dataframe newDF2 = newDF.addCol(gre2, "gre2");
        System.out.println();
        newDF2.describe();

        newDF2.scatterPlot("gre", "gre2");

    }

}
