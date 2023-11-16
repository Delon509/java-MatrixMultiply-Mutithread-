import Utility.MatrixUtility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



public class MatrixUtilityTest {

    MatrixUtility matrixUtility;

    @BeforeEach
    void setUp(){
        matrixUtility = new MatrixUtility();
    }
    @Test
    @DisplayName("Simple Matrix Multipication should work")
    void testMatrixMultiply(){
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
        Integer[][] result = {
                {58,64},
                {139,154},
                {220,244}
        };
        assertArrayEquals(result,matrixUtility.calculateMatrixMultiOneThreadOneRow(a,b,false),"Matrix Multipication should work");
    }
    @Test
    @DisplayName("Invalid Matrix Size should throw exception")
    void testInvalidMatrixInputSize() {
        Integer[][] a = {
                {1,2},                       // row 1
                {4,5},
                {7,8,9}// row2
                // row3
        };
        Integer[][] b = {
                {7,8 },                       // row 1
                {9,10},                             // row2
                {11,12}                            // row3
        };
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> matrixUtility.calculateMatrixMultiOneThreadOneRow(a,b,false));
        assertEquals("matrix 1 is not a valid matrix", exception.getMessage());
    }
    @Test
    @DisplayName("Input Matrix contains null should throw exception")
    void testInvalidMatrixInputNull() {
        Integer[][] a = {
                {1,2,null},                       // row 1
                {4,5,6},
                {7,8,9}// row2
                // row3
        };
        Integer[][] b = {
                {7,8 },                       // row 1
                {9,10},                             // row2
                {11,12}                            // row3
        };
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> matrixUtility.calculateMatrixMultiOneThreadOneRow(a,b,false));
        assertEquals("matrix 1 is not a valid matrix", exception.getMessage());
    }
    @Test
    @DisplayName("When Matrix 1 col not equal to Matrix 2 row, the function should throw exception")
    void testInvalidMatrixInputLength() {
        Integer[][] a = {
                {1,2},                       // row 1
                {4,5},
                {7,8}// row2
                // row3
        };
        Integer[][] b = {
                {7,8 },                       // row 1
                {9,10},                             // row2
                {11,12}                            // row3
        };
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> matrixUtility.calculateMatrixMultiOneThreadOneRow(a,b,false));
        assertEquals("the number of matrix 1 col must equal to matrix 2 row", exception.getMessage());
    }
}
