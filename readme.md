# Java Matrix Multiplication ( Multithreading)



For testing, I use `JUnit`.  
The function matrixUtility.calculateMatrixMultiOneThreadOneRow(a,b,show)  
a <-  matrix multiply (Integer[][])  
b <- the matrix get multiplied (Integer[][])   
show <- show the calculation in console or not (boolean)

![image](https://github.com/Delon509/java-MatrixMultiply-Mutithread-/tree/master/case1.JPG?raw=true)   

We can create thread base on the number of pair of row and column.    
Use the above image as an example , we will create 2*2 = 4 thread for calculation.