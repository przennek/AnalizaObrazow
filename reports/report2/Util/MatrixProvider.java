package AnalizaObrazow.reports.report2.Util;

/**
 * Created by p on 12.05.16.
 */
final public class MatrixProvider {
    public static Integer[][] MATRIX_CUSTOM_DYLATE = new Integer[][]{{-1, 0, -1}, {0, -1, 0}, {-1, 0, -1}};
    public static Integer[][] MATRIX_3x255 = new Integer[][]{{255, 255, 255}, {255, 255, 255}, {255, 255, 255}};
    public static Integer[][] MATRIX_3x0 = new Integer[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
    public static Integer[][] MATRIX_POINTS = new Integer[][]{{-1, -1, -1, -1, 255, -1, -1, -1, -1}, {-1, -1, -1, -1, -1, -1, -1, -1, -1}, {-1, 255, -1, -1, -1, -1, -1, 255, -1}, {-1, -1, -1, -1, -1, -1, -1, -1, -1}, {255, -1, -1, -1, -1, -1, -1, -1, 255}, {-1, -1, -1, -1, -1, -1, -1, -1, -1}, {-1, 255, -1, -1, -1, -1, -1, 255, -1}, {-1, -1, -1, -1, -1, -1, -1, -1, -1}, {-1, -1, -1, -1, 255, -1, -1, -1, -1}};

    public static Integer[][] MATRIX_R1 = new Integer[][]{
            {-1, 0, -1},
            {255, 0, 0},
            {255, 255, -1},
    };

    public static Integer[][] MATRIX_R2 = new Integer[][]{
            {-1, 0, -1},
            {0, 0, 255},
            {-1, 255, 255},
    };

    public static Integer[][] MATRIX_R3 = new Integer[][]{
            {-1, 255, 255},
            {0, 0, 255},
            {-1, 0, -1}
    };
    public static Integer[][] MATRIX_R4 = new Integer[][]{
            {255, 255, -1},
            {255, 0, 0},
            {-1, 0, -1}
    };

    public static Integer[][] MATRIX_T1 = new Integer[][]{
            {255, 255, 255},
            {-1, 0, -1},
            {0, 0, 0}
    };

    public static Integer[][] MATRIX_T2 = new Integer[][]{
            {-1, 255, 255},
            {0, 0, 255},
            {-1, 0, -1}
    };

    public static Integer[][] rotate90(Integer[][] matrix) {
        int n = matrix.length;
        Integer[][] matrixOut = new Integer[n][n];
        Integer[][] buffer = new Integer[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
//                System.out.print(matrix[i][j] + " ");
                buffer[i][j] = matrix[j][i];
            }
//            System.out.println("\n");
        }

//        System.out.println("\n vs \n");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrixOut[i][j] = buffer[i][n-j-1];
//                System.out.print(matrixOut[i][j] + " ");
            }
//            System.out.println("\n");
        }

        return matrixOut;
    }
}
