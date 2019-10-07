import java.lang.reflect.Array;

public class Matrix<E> {

//    private final Class<? extends E> cls;
    private E[][] matrix;

//    public Matrix(Class<E> cls, int x, int y){
//        this.cls = cls;
//        matrix = (E[][]) Array.newInstance(cls, x, y);
//
////        matrix = (E[][]) new Object[x][y];
//    }

    public Matrix(int x, int y){

        matrix = (E[][]) new Object[x][y];
    }

//    public Matrix(Class<E> cls, E[][] matrix){
//        this.cls = cls;
//        this.matrix = matrix;
//    }

    public Matrix(E[][] matrix){
        this.matrix = matrix;
    }

//    public Matrix(Class<E> cls, E singleMatrix){
//        this.cls = cls;
//        E[][] newMatrix = (E[][]) Array.newInstance(cls, 1, 1);
//        newMatrix[0][0] = singleMatrix;
//        this.matrix = newMatrix;
//    }

    public Matrix(E singleValue){
        E[][] tempMatrix = (E[][]) new Object[1][1];
        tempMatrix[0][0] = singleValue;
    }


    /**
     * adds 2 matrices of a generic type
     * @param matrix1 first matrix to add
     * @param matrix2 second matrix to add
     * @return sum of the matrices
     * @throws IllegalArgumentException one or more invalid matrix sizes
     */
    public static <E> Matrix<E> add(Matrix<E> matrix1, Matrix<E> matrix2) throws IllegalArgumentException{

        if(matrix1.matrix.length < 1 || matrix2.matrix.length < 1)
            throw new IllegalArgumentException("Matrix is of size zero!");
        if(matrix1.matrix.length != matrix2.matrix.length ||
                matrix1.matrix[0].length != matrix2.matrix[0].length)
            throw new IllegalArgumentException("Matrix sizes do not match!");

//        E[][] matrixSum = (E[][]) Array.newInstance(matrix1.cls, matrix1.matrix.length, matrix1.matrix[0].length);
        E[][] matrixSum = (E[][]) new Object[matrix1.matrix.length][matrix1.matrix[0].length];

        for(int x = 0; x < matrix1.matrix.length; x++){
            for(int y = 0; y < matrix1.matrix[0].length; y++){

                matrixSum[x][y] = add(matrix1.matrix[x][y], matrix2.matrix[x][y]);
            }
        }

//        return new Matrix(matrix1.cls, matrixSum);
        return new Matrix(matrixSum);
    }


    /**
     * catches unimplemented types. Should never be called!
     * @param e1 first generic
     * @param e2 second generic
     * @param <E> the type :P
     * @return null
     */
    private static <E> E add(E e1, E e2){
        return null;
    }

    private static Integer add(Integer i1, Integer i2){
        return i1 + i2;
    }

    private static Double add(Double d1, Double d2){
        return d1 + d2;
    }


    /**
     * performs a dot product on two matrices
     * @param matrix1 the first matrix
     * @param matrix2 the second matrix
     * @param <E> the type of the matrices
     * @return the resulting matrix
     */
    public static <E> Matrix multiply(Matrix<E> matrix1, Matrix<E> matrix2) throws IllegalArgumentException{

        if(matrix1.matrix.length < 1 || matrix2.matrix.length < 1)
            throw new IllegalArgumentException("Matrix is of size zero!");
        if(matrix1.matrix.length != matrix2.matrix[0].length ||
                matrix1.matrix[0].length != matrix2.matrix.length)
            throw new IllegalArgumentException("Matrix sizes do not match!");

//        E[][] matrixSum = (E[][]) Array.newInstance(matrix1.cls, matrix1.matrix[0].length, matrix1.matrix[0].length);
        E[][] matrixSum = (E[][]) new Object[matrix1.matrix[0].length][matrix1.matrix[0].length];

        for(int x = 0; x < matrix1.matrix.length; x++){
            for(int y = 0; y < matrix2.matrix.length; y++){
                E sum = null;
                for(int v = 0; v < matrix1.matrix[0].length; v++) {

                    E prod = multiply(matrix1.matrix[x][v], matrix2.matrix[v][y]);

                    if(sum == null){
                        sum = prod;
                    }
                    else{
                        sum = add(sum, prod);
                    }
                }
                matrixSum[x][y] = add(matrix1.matrix[x][y], matrix2.matrix[x][y]);
            }
        }

//        return new Matrix(matrix1.cls, matrixSum);
        return new Matrix(matrixSum);
    }


    /**
     * catches unimplemented types. Should never be called!
     * @param e1
     * @param e2
     * @param <E>
     * @return
     */
    public static <E> E multiply(E e1, E e2){

        return null;
    }

    public static Integer multiply(Integer i1, Integer i2){
        return i1 * i2;
    }

    public static Double multiply(Double d1, Double d2){
        return d1 * d2;
    }

    /**
     * performs the unary star operation
     * this is the generic method that should never be called.
     * @param e
     * @param <E>
     * @return
     */
    public static <E> E star(E e){

        return null;
    }

    public static <E> Matrix<E> star(Matrix<E> matrix){

        if(matrix.matrix.length == 1){
            E[][] foo = (E[][]) Array.newInstance(matrix.getClass(), 1, 1);
            foo[0][0] = star(matrix.matrix[0][0]);
//            return new Matrix(matrix.cls, foo);
            return new Matrix(foo);
        }

//        E[][] newMatrix = (E[][]) Array.newInstance(matrix.cls, matrix.matrix[0].length, matrix.matrix[0].length);
        E[][] newMatrix = (E[][]) new Object[matrix.matrix[0].length][matrix.matrix[0].length];

        for(int x = 0; x < matrix.matrix.length; x++){
            for(int y = 0; y < matrix.matrix[0].length; y++){
                newMatrix[x][y] = matrix.matrix[x][y];
            }
        }

//        return new Matrix(matrix.cls, star(newMatrix));
        return new Matrix(star(newMatrix));
    }


    public static <E> E[][] star(E[][] matrix){



        E[][] newMatrix = (E[][]) Array.newInstance(matrix.getClass(), matrix[0].length, matrix[0].length);

//        E[][] a = (E[][]) Array.newInstance(matrix.getClass(), 1, 1);
//        E[][] b = (E[][]) Array.newInstance(matrix.getClass(), matrix.length - 1, 1);
//        E[][] c = (E[][]) Array.newInstance(matrix.getClass(), 1, matrix[0].length - 1);
//        E[][] d = (E[][]) Array.newInstance(matrix.getClass(), matrix.length - 1, matrix[0].length - 1);

        E[][] a = matrixPopulate(matrix, 0, 0, 0, 0);
        E[][] b = matrixPopulate(matrix, 1, matrix.length - 1, 0, 0);
        E[][] c = matrixPopulate(matrix, 0, 0, 1, matrix.length - 1);
        E[][] d = matrixPopulate(matrix, 1, matrix.length - 1, 1, matrix.length - 1);

        E[][] sendNewAs = star(add(a, multiply(multiply(b, star(d)), c)));
        E[][] sendNewBs = multiply(add(a, multiply(b, multiply(star(d), c))), multiply(b, star(d)));
        E[][] sendNewCs = multiply( multiply(add(d, multiply(multiply(c, star(a)), b)), c), star(a));
        E[][] sendNewDs = star(add(d, multiply(c, multiply(star(a), b))));

        newMatrix[0][0] = a[0][0];
        for(int i = 1; i < newMatrix.length - 1; i++){
            newMatrix[i][0] = b[i][0];
            newMatrix[0][i] = c[0][i];
            newMatrix[i][i] = d[i][i];
        }

        return newMatrix;
    }

    private static <E> E[][] matrixPopulate(E[][] matrix, int xStart, int xEnd, int yStart, int yEnd){

        E[][] foo = (E[][]) Array.newInstance(matrix.getClass(), xEnd - xEnd + 1, yEnd - yStart + 1);

        for(int x = xStart; x < xEnd; x++){
            for(int y = yStart; y < yEnd; y++){

                foo[x][y] = matrix[x][y];
            }
        }

        return foo;
    }
}
