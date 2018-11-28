
public class CSVtoDF {

    public static void main(String[] args) {

        // Read from csv file
        String fileref = "/Users/jlgunnin/Downloads/testfile.csv";
//        String fileref = "/Users/jlgunnin/Dropbox/R/output/nfl.csv";
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
    }

}
