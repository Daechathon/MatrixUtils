/**
 * Created by Brandon Aldridge on 10/10/2019
 * Matrix operations for numbers and regular expressions
 * @param <E> Type of the contents of the matrix
 */
public class Matrix<E> {

    private E[][] matrix;

    public Matrix(int x, int y){
        matrix = (E[][]) new Object[x][y];
    }

    public Matrix(E[][] matrix){

        this.matrix = matrix;
    }

    public Matrix(E singleValue){
        E[][] tempMatrix = (E[][]) new Object[1][1];
        tempMatrix[0][0] = singleValue;
        matrix = tempMatrix;
    }

    public E[][] getMatrix(){
        return matrix;
    }

    public void setMatrix(E[][] matrix) {
        this.matrix = matrix;
    }

    @Override
    public boolean equals(Object o){

        if(o == this)
            return true;

        if(!(o instanceof Matrix))
            return false;

        Matrix m = (Matrix) o;

        if(m.matrix.length != this.matrix.length)
            return false;

        //compare field values
        for(int x = 0; x < this.matrix.length; x++){
            for(int y = 0; y < this.matrix[x].length; y++){

                if(!this.matrix[x][y].equals(m.matrix[x][y]))
                    return false;
            }
        }
        return true;
    }

    /**
     * adds 2 matrices of a generic type
     * @param matrix1 first matrix to add
     * @param matrix2 second matrix to add
     * @return sum of the matrices
     */
    public static <E> Matrix<E> add(Matrix<E> matrix1, Matrix<E> matrix2){

        if(matrix1.matrix.length < 1 || matrix2.matrix.length < 1)
            throw new IllegalArgumentException("Matrix is of size zero!");
        if(matrix1.matrix.length != matrix2.matrix.length ||
                matrix1.matrix[0].length != matrix2.matrix[0].length)
            throw new IllegalArgumentException("Matrix sizes do not match!");

        E[][] matrixSum = (E[][]) new Object[matrix1.matrix.length][matrix1.matrix[0].length];

        //adds elements of the matrices
        for(int x = 0; x < matrix1.matrix.length; x++){
            for(int y = 0; y < matrix1.matrix[0].length; y++){

                matrixSum[x][y] = add(matrix1.matrix[x][y], matrix2.matrix[x][y]);
            }
        }

//        return new Matrix(matrix1.cls, matrixSum);
        return new Matrix(matrixSum);
    }


    /**
     * calls the appropriate add() based on parameters
     * throws IllegalArgumentException if unimplemented type is used
     *
     * @param e1 first value
     * @param e2 second value
     * @param <E> the type :P
     * @return sum of parameters
     */
    public static <E> E add(E e1, E e2){

        String foo = e1.getClass().getName();
        if(foo.equals("java.lang.Integer"))
            return (E) add((Integer) e1, (Integer) e2);
        if(foo.equals("java.lang.Double"))
            return (E) add((Double) e1, (Double) e2);
        if(foo.equals("Matrix"))
            return (E) add((Matrix) e1, (Matrix) e2);

        throw new IllegalArgumentException();
    }

    public static Integer add(Integer i1, Integer i2){
        return i1 + i2;
    }

    public static Double add(Double d1, Double d2){
        return d1 + d2;
    }


    /**
     * performs a dot product on two generic matrices
     *
     * @param matrix1 the first matrix
     * @param matrix2 the second matrix
     * @param <E> the type of the contents of the matrices
     * @return the resulting matrix
     */
    public static <E> Matrix<E> multiply(Matrix<E> matrix1, Matrix<E> matrix2) throws IllegalArgumentException{

        if(matrix1.matrix.length < 1 || matrix2.matrix.length < 1)
            throw new IllegalArgumentException("Matrix is of size zero!");
        if(matrix1.matrix.length != matrix2.matrix[0].length)
            throw new IllegalArgumentException("Matrix sizes are not compatible!");

        E[][] matrixSum = (E[][]) new Object[matrix2.matrix.length][matrix1.matrix[0].length];

        for(int y = 0; y < matrix1.matrix[0].length; y++){
            for(int x = 0; x < matrix2.matrix.length; x++){
                for(int v = 0; v < matrix1.matrix.length; v++) {

                    E prod = multiply(matrix1.matrix[v][y], matrix2.matrix[x][v]);

                    //sum the products
                    if(matrixSum[x][y] == null){
                        matrixSum[x][y] = prod;
                    }
                    else{
                        matrixSum[x][y] = add(matrixSum[x][y], prod);
                    }
                }
            }
        }
        return new Matrix(matrixSum);
    }


    /**
     * calls the appropriate multiply() given parameter types
     * throws IllegalArgumentException if given unimplemented types
     *
     * @param e1 first value
     * @param e2 second value
     * @param <E> type
     * @return result of multiplication
     */
    public static <E> E multiply(E e1, E e2){

        String foo = e1.getClass().getName();
        if(foo.equals("java.lang.Integer"))
            return (E) multiply((Integer) e1, (Integer) e2);
        if(foo.equals("java.lang.Double"))
            return (E) multiply((Double) e1, (Double) e2);
        if(foo.equals("Matrix"))
            return (E) multiply((Matrix) e1, (Matrix) e2);

        throw new IllegalArgumentException();
    }

    public static Integer multiply(Integer i1, Integer i2){
        return i1 * i2;
    }

    public static Double multiply(Double d1, Double d2){
        return d1 * d2;
    }

    /**
     * performs the Kleene star operation
     * throws IllegalArgumentException if given unimplemented type
     *
     * @param e the value to operate over
     * @param <E> the type
     * @return result of Kleene star
     */
    public static <E> E star(E e){

        String foo = e.getClass().getName();
        if(foo.equals("Matrix"))
            return (E) star((Matrix) e);

        throw new IllegalArgumentException();
    }

    /**
     * performs the kleene star operation on a square matrix
     * matrix must be square or throws IllegalArgumentException
     *
     * @param matrix square matrix to be operated on
     * @param <E> type of matrix
     * @return resulting matrix from Kleene star
     */
    public static <E> Matrix<E> star(Matrix<E> matrix){

        if(matrix.matrix.length == 0 || matrix.matrix.length != matrix.matrix[0].length){
            throw new IllegalArgumentException();
        }

        if(matrix.matrix.length == 1){
            return matrix;
        }

        E[][] newMatrix = (E[][]) new Object[matrix.matrix.length][matrix.matrix[0].length];

        for(int x = 0; x < matrix.matrix.length; x++){
            for(int y = 0; y < matrix.matrix[0].length; y++){
                newMatrix[x][y] = matrix.matrix[x][y];
            }
        }

        Matrix<E> a = new Matrix<>(matrixPopulate(matrix.matrix, 0, 0, 0, 0));
        Matrix<E> b = new Matrix<>(matrixPopulate(matrix.matrix, 1, matrix.matrix.length - 1, 0, 0));
        Matrix<E> c = new Matrix<>(matrixPopulate(matrix.matrix, 0, 0, 1, matrix.matrix.length - 1));
        Matrix<E> d = new Matrix<>(matrixPopulate(matrix.matrix, 1, matrix.matrix.length - 1, 1, matrix.matrix.length - 1));

        Matrix<E> sendNewAs = star(add(a, multiply(multiply(b, star(d)), c)));
        Matrix<E> sendNewBs = multiply(star(add(a, multiply(b, multiply(star(d), c)))), multiply(b, star(d)));
        Matrix<E> sendNewCs = multiply(multiply(star(add(d, multiply(multiply(c, star(a)), b))), c), star(a));
        Matrix<E> sendNewDs = star(add(d, multiply(c, multiply(star(a), b))));

        newMatrix[0][0] = sendNewAs.matrix[0][0];
        for(int x = 0; x < newMatrix.length; x++){
            for(int y = 0; y < newMatrix[0].length; y++) {

                if(x == 0 && y > 0){
                    newMatrix[0][y] = sendNewCs.matrix[0][y - 1];
                }
                if(y == 0 && x > 0){
                    newMatrix[x][0] = sendNewBs.matrix[x - 1][0];
                }
                if(x > 0 && y > 0){
                    newMatrix[x][y] = sendNewDs.matrix[x - 1][y - 1];
                }
            }
        }
        return new Matrix<>(newMatrix);
    }

    /**
     * populates a sub matrix for the kleene star operation
     *
     * @param matrix original matrix
     * @param xStart x starting bounds
     * @param xEnd x ending bounds
     * @param yStart y starting bounds
     * @param yEnd y ending bounds
     * @param <E> type of matrix
     * @return the resulting sub matrix
     */
    private static <E> E[][] matrixPopulate(E[][] matrix, int xStart, int xEnd, int yStart, int yEnd){

        E[][] foo = (E[][]) new Object[xEnd - xStart + 1][yEnd - yStart + 1];

        for(int x = 0; x < xEnd - xStart + 1; x++){
            for(int y = 0; y < yEnd - yStart + 1; y++){

                foo[x][y] = matrix[x + xStart][y + yStart];
            }
        }
        return foo;
    }
}