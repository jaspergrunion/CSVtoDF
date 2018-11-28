import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import java.util.HashMap;

public class Dataframe {

    // Declare object data types
    private String[][] data;
    private int nrows;
    private int ncols;
    private String[] names;
    private String[] coltypes;

    // Initialize object
    public Dataframe(String[][] dataraw) {
        this.nrows = dataraw.length - 1;
        this.ncols = dataraw[0].length;
        this.names = new String[this.ncols];
        for (int c = 0; c < this.ncols; c++) {
            this.names[c] = dataraw[0][c];
        }
        this.data = new String[this.nrows][this.ncols];
        for (int r = 1; r < this.nrows + 1; r++) {
            for (int c = 0; c < this.ncols; c++) {
                this.data[r-1][c] = dataraw[r][c];
            }
        }
        this.coltypes = new String[this.ncols];
        for (int c = 0; c < this.ncols ; c++) {
            this.coltypes[c] = getColType(c);
        }
    }

    // Get a column's name given its column number
    private String getColName(int colnum){
        String resultName = "";
        resultName = this.names[colnum];
        return resultName;
    }

    // Get a column's type given its column number
    private String getColType(int colnum){
        String[] tempCol = new String[this.nrows];
        for (int r = 0; r < nrows; r++) {
            tempCol[r] = this.data[r][colnum];
        }

        int[] anychar = new int[tempCol.length];
        int haschar = 0;
        for (int i = 0; i < tempCol.length ; i++) {

            if(!(tempCol[i].matches("^[-+]?[0-9]*\\.?[0-9]+$"))) {
                anychar[i] = 1;
                haschar += anychar[i];
            }
        }

        String resultColType = "num";
        if (haschar > 0) {
            resultColType = "str";
        }

        return resultColType;
    }

    // Get a column's number given its name
    private int colNumFromName(String colname){
        int resultColNum = -1;
        for (int i = 0; i < this.ncols ; i++) {
            if (this.names[i].equals(colname)){
                resultColNum = i;
            }
        }
        return resultColNum;
    }

    // Retrieve a string column by column number or name
    public String[] getStrCol(int colnum){
        String[] resultCol = new String[this.nrows];
        for (int r = 0; r < nrows; r++) {
            resultCol[r] = this.data[r][colnum];
        }
        return resultCol;
    }

    public String[] getStrCol(String colname){
        String[] resultCol = new String[this.nrows];
        int colnum = colNumFromName(colname);
        for (int r = 0; r < nrows; r++) {
            resultCol[r] = this.data[r][colnum];
        }
        return resultCol;
    }

    // Retrieve a numeric column by column number or name
    public double[] getNumCol(int colnum){
        double[] resultCol = new double[this.nrows];
        for (int r = 0; r < nrows; r++) {
            resultCol[r] = Double.valueOf(this.data[r][colnum]);
        }
        return resultCol;
    }

    public double[] getNumCol(String colname){
        double[] resultCol = new double[this.nrows];
        int colnum = colNumFromName(colname);
        for (int r = 0; r < nrows; r++) {
            resultCol[r] = Double.valueOf(this.data[r][colnum]);
        }
        return resultCol;
    }

    // Return number of rows
    public int getNrows(){ return this.nrows; }

    // Return number of columns
    public int getNcols(){ return this.ncols; }

    // Return array of names
    public String[] getNames(){
        return this.names;
    }

    // Get names in pretty format
    private void getNamesPretty(){
        System.out.print("Column names: [");
        for (int c = 0; c < this.ncols; c++) {
            if (c < (this.ncols - 1)) {
                System.out.print(this.names[c] + ", ");
            } else {
                System.out.print(this.names[c] + "]");
            }
        }
        System.out.println();
    }

    // Describe dataframe
    public void describe() {
        System.out.println("Dataframe with " + this.nrows + " rows and " + this.ncols + " columns");
        getNamesPretty();
        System.out.println();
        printData(5);
    }

    // Prints rows of data
    public void printData() {
        printData(this.nrows);
    }

    public void printData(int rows){
        System.out.println("First " + rows +  " rows:");
        System.out.println();
        for (int c = 0; c < this.ncols; c++) {
            String outStr = String.format("%-20s", this.names[c]);
            System.out.print(outStr);
        }
        System.out.println();
        for (int c = 0; c < this.ncols; c++) {
            String outStr = String.format("%-20s", "<" + this.coltypes[c] + ">");
            System.out.print(outStr);
        }
        System.out.println();
        for (int c = 0; c < this.ncols; c++) {
            String outStr = String.format("%-20s", "[" + c + "]");
            System.out.print(outStr);
        }
        System.out.println();
        System.out.println();
        for (int r = 0; r < rows ; r++) {
            for (int c = 0; c < this.ncols; c++) {
                String outStr = String.format("%-20s", this.data[r][c]);
                System.out.print(outStr);
            }
            System.out.println();
        }
    }

    // Print a column by column number or name
    public void printCol(int colnum) {
        printCol(colnum, this.nrows);
    }

    public void printCol(String colname) {
        int colnum = colNumFromName(colname);
        printCol(colnum, this.nrows);
    }

    public void printCol(String colname, int nrows){
        int colnum = colNumFromName(colname);
        printCol(colnum, nrows);
    }

    public void printCol(int colnum, int nrows) {
        System.out.println();
        System.out.println(this.names[colnum]);
        System.out.println("<" + this.coltypes[colnum] + ">");
        System.out.println("[" + colnum + "]");
        System.out.println();

        for (int r = 0; r < nrows; r++) {
            System.out.println(this.data[r][colnum]);
        }
    }

    // Generate univariate stats by column number or name
    public void univStats(String colname){
        int colnum = colNumFromName(colname);
        univStats(colnum);
    }

    public void univStats(int colnum){
        double[] numCol = getNumCol(colnum);

        DescriptiveStatistics descriptiveStatistics = new DescriptiveStatistics();
        for (double v : numCol) {
            descriptiveStatistics.addValue(v);
        }

        double myN = descriptiveStatistics.getN();
        double mySum = descriptiveStatistics.getSum();
        double myMin = descriptiveStatistics.getMin();
        double myPct25 = descriptiveStatistics.getPercentile(25);
        double myMean = descriptiveStatistics.getMean();
        double myMedian = descriptiveStatistics.getPercentile(50);
        double myPct75 = descriptiveStatistics.getPercentile(75);
        double myMax = descriptiveStatistics.getMax();
        double mySD = descriptiveStatistics.getStandardDeviation();

        System.out.println();
        System.out.println("Column: " + getColName(colnum));
        System.out.println("Type: " + getColType(colnum));
        System.out.println("N: " + myN);
        System.out.println("Sum: " + String.format("%.2f", mySum));
        System.out.println("Min: " + myMin);
        System.out.println("Pct25: " + myPct25);
        System.out.println("Mean: " + String.format("%.2f", myMean));
        System.out.println("Median: " + myMedian);
        System.out.println("Pct75: " + myPct75);
        System.out.println("Max: " + myMax);
        System.out.println("Std Dev: " + String.format("%.2f", mySD));

    }

    // Generate frequency counts by column number or name
    public void freqCounts(String colname){
        int colnum = colNumFromName(colname);
        freqCounts(colnum);
    }

    public void freqCounts(int colnum){
        String[] inputArray = getStrCol(colnum);
        HashMap<String, Integer> elementCountMap = new HashMap<String, Integer>();
        for (String i : inputArray)
        {
            if(elementCountMap.containsKey(i))
            {
                elementCountMap.put(i, elementCountMap.get(i)+1);
            }
            else
            {
                elementCountMap.put(i, 1);
            }
        }
        System.out.println();
        System.out.println("Frequency counts for '" + getColName(colnum) + "': " + elementCountMap);
    }

    // Print either univariate stats or frequency counts for every column in data
    public void summaryStatistics() {
        for (int c = 0; c < this.ncols; c++) {

            if (getColType(c).equals("num")) {
                univStats(c);
            }
            else {
                freqCounts(c);
            }
        }
    }
}

