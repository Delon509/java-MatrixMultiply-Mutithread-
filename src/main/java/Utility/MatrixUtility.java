package Utility;

import Utility.ConsoleUtility;

import java.util.concurrent.atomic.AtomicInteger;

public class MatrixUtility {
    public volatile AtomicInteger numberOfCalculated = new AtomicInteger(0);
    public volatile Integer[][] result;

    private void calculateMatrixMultiForOneCell(int row,int col, Integer[][] matrix1, Integer[][] matrix2)  {
        // the number of matrix 1 col must equal to matrix 2 row
        if(matrix1[row].length != matrix2.length) throw new IllegalArgumentException("the number of matrix 1 col must equal to matrix 2 row");
        int currentResult = 0;
        for(int i=0;i <matrix1[row].length; ++i){
            currentResult += matrix1[row][i] * matrix2[i][col];
        }
        result[row][col] = currentResult;
        numberOfCalculated.incrementAndGet();
    }
    private void calculateMatrixMultiForOneRow(int row,Integer[][] matrix1, Integer[][] matrix2)  {
        for(int i=0;i<matrix2[0].length;++i){
            calculateMatrixMultiForOneCell(row,i,matrix1,matrix2);
        }
    }
    public Integer[][] calculateMatrixMultiOneThreadOneRow(Integer[][] matrix1,Integer[][]matrix2, boolean show)  {
        if(!isValidMatrix(matrix1)) throw new IllegalArgumentException("matrix 1 is not a valid matrix");
        if(!isValidMatrix(matrix2)) throw new IllegalArgumentException("matrix 2 is not a valid matrix");
        if(matrix1[0].length != matrix2.length) throw new IllegalArgumentException("the number of matrix 1 col must equal to matrix 2 row");
        result = new Integer[matrix1.length][matrix2[0].length];
        long startTime = System.nanoTime();
        long numberOfCell = (long) matrix1.length * matrix2[0].length;
        new Thread(() ->{
            for(int i = 0; i< matrix1.length; ++i){
                int finalI = i;
                new Thread(() ->{
                    calculateMatrixMultiForOneRow(finalI,matrix1,matrix2);
                }).start();
            }
        }).start();
        while (numberOfCell != numberOfCalculated.get()){
            if(show){
//                for (int i = 0; i < 50; ++i) System.out.print("\r\n");
                ConsoleUtility.clearConsole();
                printMatrix(result);
                System.out.println("Current value of numberOfCalculated: " + numberOfCalculated.get());
                long process = numberOfCalculated.get() / numberOfCell *100;
                System.out.println("Percentage :" + process);

            }
        }
        long stopTime = System.nanoTime();
        if(show){
//            for (int i = 0; i < 50; ++i) System.out.print("\r\n");
            ConsoleUtility.clearConsole();
            printMatrix(result);
            System.out.println("Finished");
            long elapsedTime = stopTime - startTime;
            double seconds = (double)elapsedTime / 1E9;
            System.out.println("Time being used: "+ seconds +" seconds");
        }
        Integer[][] temp = result;
        result = null;
        numberOfCalculated.set(0);
        return temp;
    }
    public void printMatrix(Integer[][] input){
        for(int i=0;i< input.length;++i){
            for(int j=0;j< input[0].length;++j){
                if(input[i][j] == null) System.out.printf("%6c",'x');
                else System.out.printf("%6d",input[i][j]);
            }
            System.out.print("\n");
        }
    }
    public boolean isValidMatrix(Integer[][] input){
        if(input == null || input.length ==0 || input[0].length == 0) return false;
        int col = input[0].length;
        for(int i=0;i<input.length;++i){
            if(col != input[i].length) return  false;
            for(int j=0;j<input[i].length;++j){
                if(input[i][j] == null) return false;
            }
        }
        return  true;
    }
}
