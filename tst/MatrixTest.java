import org.junit.Assert;
import org.junit.Test;

public class MatrixTest {

    @Test
    public void intAddTest(){

        int test01 = 0;
        int test02 = 0;

        int test11 = 1;
        int test12 = 0;

        int test21 = -25;
        int test22 = 50;

        int test0Result = Matrix.add(test01, test02);
        int test1Result = Matrix.add(test11, test12);
        int test2Result = Matrix.add(test21, test22);

        Assert.assertEquals(0, test0Result);
        Assert.assertEquals(1, test1Result);
        Assert.assertEquals(25, test2Result);
    }

    @Test
    public void doubleAddTest(){

        double test01 = 0.0;
        double test02 = 0.0;

        double test11 = 1.0;
        double test12 = 0.0;

        double test21 = -25.0;
        double test22 = 50.0;

        double test0Result = Matrix.add(test01, test02);
        double test1Result = Matrix.add(test11, test12);
        double test2Result = Matrix.add(test21, test22);

        Assert.assertEquals(0.0, test0Result, 0.00001);
        Assert.assertEquals(1.0, test1Result, 0.00001);
        Assert.assertEquals(25.0, test2Result, 0.00001);
    }

    @Test
    public void matrixIntAddTest(){

        Integer[][] foo = new Integer[2][2];
        for(int x = 0; x < foo.length; x++){
            for (int y = 0; y < foo[0].length; y++){
                foo[x][y] = 0;
            }
        }

        Integer[][] bar = new Integer[2][2];
        for(int x = 0; x < bar.length; x++){
            for (int y = 0; y < bar[0].length; y++){
                bar[x][y] = 1;
            }
        }

        Integer[][] spam = new Integer[2][3];
        for(int x = 0; x < spam.length; x++){
            for (int y = 0; y < spam[0].length; y++){
                spam[x][y] = 8;
            }
        }

        Integer[][] fred = new Integer[2][3];
        for(int x = 0; x < fred.length; x++){
            for (int y = 0; y < fred[0].length; y++){
                fred[x][y] = 5;
            }
        }

        Matrix<Integer> matrix01 = new Matrix<>(foo);
        Matrix<Integer> matrix02 = new Matrix<>(bar);
        Integer[][] tmp = new Integer[][]{{1, 1}, {1, 1}};
        Matrix<Integer> ans0 = new Matrix<>(tmp);

        Matrix<Integer> matrix11 = new Matrix<>(bar);
        Matrix<Integer> matrix12 = new Matrix<>(bar);
        tmp = new Integer[][]{{2, 2}, {2, 2}};
        Matrix<Integer> ans1 = new Matrix<>(tmp);

        Matrix<Integer> matrix21 = new Matrix<>(spam);
        Matrix<Integer> matrix22 = new Matrix<>(fred);
        tmp = new Integer[][]{{13, 13, 13}, {13, 13, 13}};
        Matrix<Integer> ans2 = new Matrix<>(tmp);


        Matrix<Integer> matrixAdd0 = Matrix.add(matrix01, matrix02);
        Matrix<Integer> matrixAdd1 = Matrix.add(matrix11, matrix12);
        Matrix<Integer> matrixAdd2 = Matrix.add(matrix21, matrix22);


        Assert.assertEquals(ans0, matrixAdd0);
        Assert.assertEquals(ans1, matrixAdd1);
        Assert.assertEquals(ans2, matrixAdd2);
    }

    @Test
    public void intMultiplyTest(){

        int test01 = 0;
        int test02 = 0;

        int test11 = 1;
        int test12 = 0;

        int test21 = -25;
        int test22 = 10;

        int test31 = 3;
        int test32 = 6;

        int test0Result = Matrix.multiply(test01, test02);
        int test1Result = Matrix.multiply(test11, test12);
        int test2Result = Matrix.multiply(test21, test22);
        int test3Result = Matrix.multiply(test31, test32);

        Assert.assertEquals(0, test0Result);
        Assert.assertEquals(0, test1Result);
        Assert.assertEquals(-250, test2Result);
        Assert.assertEquals(18, test3Result);
    }
    
    @Test
    public void doubleMultiplyTest(){

        double test01 = 0;
        double test02 = 0;

        double test11 = 1;
        double test12 = 0;

        double test21 = -25;
        double test22 = 10;

        double test31 = 3;
        double test32 = 6;

        double test0Result = Matrix.multiply(test01, test02);
        double test1Result = Matrix.multiply(test11, test12);
        double test2Result = Matrix.multiply(test21, test22);
        double test3Result = Matrix.multiply(test31, test32);

        Assert.assertEquals(0, test0Result, 0.0000000001);
        Assert.assertEquals(0, test1Result, 0.0000000001);
        Assert.assertEquals(-250, test2Result, 0.0000000001);
        Assert.assertEquals(18, test3Result, 0.0000000001);
    }

    @Test
    public void matrixIntMultiplyTest(){

        Integer[][] foo = new Integer[2][2];
        for(int x = 0; x < foo.length; x++){
            for (int y = 0; y < foo[0].length; y++){
                foo[x][y] = 0;
            }
        }

        Integer[][] bar = new Integer[2][2];
        for(int x = 0; x < bar.length; x++){
            for (int y = 0; y < bar[0].length; y++){
                bar[x][y] = 1;
            }
        }

        Integer[][] spam = new Integer[2][4];
        for(int x = 0; x < spam.length; x++){
            for (int y = 0; y < spam[0].length; y++){
                spam[x][y] = 8;
            }
        }

        Integer[][] fred = new Integer[4][2];
        for(int x = 0; x < fred.length; x++){
            for (int y = 0; y < fred[0].length; y++){
                fred[x][y] = 5;
            }
        }

        Integer[][] uno = new Integer[1][1];
        uno[0][0] = 5;

        Integer[][] dos = new Integer[1][2];
        dos[0][0] = 5;
        dos[0][1] = 5;

        Matrix<Integer> matrix01 = new Matrix<>(foo);
        Matrix<Integer> matrix02 = new Matrix<>(bar);
        Integer[][] tmp = new Integer[][]{{0, 0}, {0, 0}};
        Matrix<Integer> ans0 = new Matrix<>(tmp);

        Matrix<Integer> matrix11 = new Matrix<>(bar);
        Matrix<Integer> matrix12 = new Matrix<>(bar);
        tmp = new Integer[][]{{2, 2}, {2, 2}};
        Matrix<Integer> ans1 = new Matrix<>(tmp);

        Matrix<Integer> matrix21 = new Matrix<>(spam);
        Matrix<Integer> matrix22 = new Matrix<>(fred);
        tmp = new Integer[][]{{80, 80, 80, 80}, {80, 80, 80, 80}, {80, 80, 80, 80}, {80, 80, 80, 80}};
        Matrix<Integer> ans2 = new Matrix<>(tmp);

        Matrix<Integer> matrix31 = new Matrix<>(bar);
        Matrix<Integer> matrix32 = new Matrix<>(fred);
        tmp = new Integer[][]{{10, 10}, {10, 10}, {10, 10}, {10, 10}};
        Matrix<Integer> ans3 = new Matrix<>(tmp);

        Matrix<Integer> matrix41 = new Matrix<>(dos);
        Matrix<Integer> matrix42 = new Matrix<>(uno);
        tmp = new Integer[][]{{25, 25}};
        Matrix<Integer> ans4 = new Matrix<>(tmp);


        Matrix<Integer> matrixMultiply0 = Matrix.multiply(matrix01, matrix02);
        Matrix<Integer> matrixMultiply1 = Matrix.multiply(matrix11, matrix12);
        Matrix<Integer> matrixMultiply2 = Matrix.multiply(matrix21, matrix22);
        Matrix<Integer> matrixMultiply3 = Matrix.multiply(matrix31, matrix32);
        Matrix<Integer> matrixMultiply4 = Matrix.multiply(matrix41, matrix42);


        Assert.assertEquals(ans0, matrixMultiply0);
        Assert.assertEquals(ans1, matrixMultiply1);
        Assert.assertEquals(ans2, matrixMultiply2);
        Assert.assertEquals(ans3, matrixMultiply3);
        Assert.assertEquals(ans4, matrixMultiply4);
    }

    @Test
    public void matrixIntStarTest(){

        Integer[][] spam = new Integer[2][2];
        for(int x = 0; x < spam.length; x++){
            for (int y = 0; y < spam[0].length; y++){
                spam[x][y] = 2;
            }
        }

        Integer[][] bar = new Integer[3][3];
        for(int x = 0; x < bar.length; x++){
            for (int y = 0; y < bar[0].length; y++){
                bar[x][y] = 2;
            }
        }

        Matrix<Integer> spamMatrix = new Matrix<>(spam);
        Matrix<Integer> actualMatrix = Matrix.star(spamMatrix);
        Integer[][] foo = new Integer[][]{{10, 40}, {40, 10}};
        Matrix<Integer> matrixAns = new Matrix<>(foo);

        Matrix<Integer> barMatrix = new Matrix<>(bar);
        Matrix<Integer> actualMatrix2 = Matrix.star(barMatrix);
        Integer[][] barArray = new Integer[][]{{402, 408040, 408040}, {40200, 1010, 101000}, {40200, 101000, 1010}};
        Matrix<Integer> matrixAns2 = new Matrix<>(barArray);


        Assert.assertEquals(matrixAns, actualMatrix);
        Assert.assertEquals(matrixAns2, actualMatrix2);
    }
}
