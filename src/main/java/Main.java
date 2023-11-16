import Utility.MatrixUtility;

public class Main {
    public static void main(String[] args)  {

        Integer[][] a = {
                {1,2,3},                       // row 1
                {4,5,6},
                {7,8,9}// row2
                                          // row3
        };
        Integer[][] b = {
                {7,8 },                       // row 1
                {9,10},                             // row2
                {11,12}                            // row3
        };
        MatrixUtility matrixMulti = new MatrixUtility();
        Integer[][] result = new Integer[0][];
        result = matrixMulti.calculateMatrixMultiOneThreadOneRow(a,b,true);




    }
}
