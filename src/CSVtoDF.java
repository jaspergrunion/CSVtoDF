
public class CSVtoDF {

    public static void main(String[] args) {

//        Read from csv file
        String fileref = "/Users/jlgunnin/Downloads/testfile.csv";
        String[][] rawDFmat = ReadCSV.getRawDF(fileref);

//        Instantiate data frame object
        Dataframe myDF = new Dataframe(rawDFmat);

//        Dataframe describe and print methods
        myDF.describe();
        myDF.printData(7);

//        Summary statistics and frequency tables
        myDF.summaryStatistics();
        myDF.univStats(2);
        myDF.freqCounts(5);

//        Column access
        myDF.printCol(7, 5);
        double[] myNumCol = myDF.getNumCol(2);
        System.out.println("\nValue of myNumCol at position 354: " + myNumCol[354]);

        String[] myStrCol = myDF.getStrCol(5);
        System.out.println("Value of myStrCol at position 123: " + myStrCol[156]);

    }

}
