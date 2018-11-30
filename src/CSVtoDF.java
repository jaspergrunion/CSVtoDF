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

//        double[] gpa2 = new double[myDF.getNrows()];
//        for (int i = 0; i < myDF.getNrows() ; i++) {
//            gpa2[i] = gpa[i] * 2;
//        }
//
//        for (int i = 0; i < 10; i++) {
//            System.out.println();
//            System.out.println(gpa[i]);
//            System.out.println(gpa2[i]);
//        }

    }

}
