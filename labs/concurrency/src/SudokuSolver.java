import java.util.*;

public class SudokuSolver {

    // "Near worst case" example from
    // http://en.wikipedia.org/wiki/Sudoku_algorithms
    public static final String testCaseString =
            "153 " + "178 " + "185 " +
                    "221 " + "242 " +
                    "335 " + "357 " +
                    "424 " + "461 " +
                    "519 " +
                    "605 " + "677 " + "683 " +
                    "722 " + "741 " +
                    "844 " + "889 ";

    public static final int N = 9;

    public static void main(String[] args) {
        String result = solve(testCaseString);
        boolean correct = isLegalSolution(result, testCaseString);
        System.out.println("Your solver ouput is " + (correct ? "right" : "wrong"));
    }


    public static String solve(String s) {
        int[][] matrix = readInput(s);

        solve(matrix);

        print("result: ", matrix);

        return matrixToString(matrix);
    }

    private static boolean solve(int[][] matrix) {

        for (int col = 0; col < N; col++) {
            for (int row = 0; row < N; row++) {
                if (matrix[col][row] == 0) {
                    for (matrix[col][row] = 1; matrix[col][row] <= N; matrix[col][row]++) {
                        if (isBoardLegal(matrix) && solve(matrix)) {
                            return true;
                        }
                    }

                    matrix[col][row] = 0;
                    return false;
                }

            }
        }

        return true;
    }

    private static boolean isBoardLegal(int[][] matrix) {
        // check 3x3 grids
        if (!isRegionLegal(matrix, 0, 2, 0, 2)) return false;
        if (!isRegionLegal(matrix, 3, 5, 0, 2)) return false;
        if (!isRegionLegal(matrix, 6, 8, 0, 2)) return false;
        if (!isRegionLegal(matrix, 0, 2, 3, 5)) return false;
        if (!isRegionLegal(matrix, 3, 5, 3, 5)) return false;
        if (!isRegionLegal(matrix, 6, 8, 3, 5)) return false;
        if (!isRegionLegal(matrix, 0, 2, 6, 8)) return false;
        if (!isRegionLegal(matrix, 3, 5, 6, 8)) return false;
        if (!isRegionLegal(matrix, 6, 8, 6, 8)) return false;

        // check columns
        if (!isRegionLegal(matrix, 0, 0, 0, 8)) return false;
        if (!isRegionLegal(matrix, 1, 1, 0, 8)) return false;
        if (!isRegionLegal(matrix, 2, 2, 0, 8)) return false;
        if (!isRegionLegal(matrix, 3, 3, 0, 8)) return false;
        if (!isRegionLegal(matrix, 4, 4, 0, 8)) return false;
        if (!isRegionLegal(matrix, 5, 5, 0, 8)) return false;
        if (!isRegionLegal(matrix, 6, 6, 0, 8)) return false;
        if (!isRegionLegal(matrix, 7, 7, 0, 8)) return false;
        if (!isRegionLegal(matrix, 8, 8, 0, 8)) return false;

        // check rows
        if (!isRegionLegal(matrix, 0, 8, 0, 0)) return false;
        if (!isRegionLegal(matrix, 0, 8, 1, 1)) return false;
        if (!isRegionLegal(matrix, 0, 8, 2, 2)) return false;
        if (!isRegionLegal(matrix, 0, 8, 3, 3)) return false;
        if (!isRegionLegal(matrix, 0, 8, 4, 4)) return false;
        if (!isRegionLegal(matrix, 0, 8, 5, 5)) return false;
        if (!isRegionLegal(matrix, 0, 8, 6, 6)) return false;
        if (!isRegionLegal(matrix, 0, 8, 7, 7)) return false;
        if (!isRegionLegal(matrix, 0, 8, 8, 8)) return false;

        return true;
    }

    private static boolean isRegionLegal(int[][] matrix, int colMin, int colMax, int rowMin, int rowMax) {
        boolean[] isPresent = {false, false, false, false, false, false, false, false, false};

        for (int row = rowMin; row <= rowMax; row++) {
            for (int col = colMin; col <= colMax; col++) {
                if (matrix[col][row] > 0) {
                    int index = matrix[col][row];
                    if (isPresent[index - 1]) {
                        return false;
                    }
                    isPresent[index - 1] = true;
                }
            }
        }

        return true;
    }

    public static boolean isLegalSolution(String solution,
                                          String initialConfig) {

        int[][] solutionmatrix = readInput(solution);
        int[][] initialConfigMatrix = readInput(initialConfig);

        if (!isValid(solutionmatrix)) {
            return false;
        }

        // check that it's fully filled in
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                if (solutionmatrix[i][j] == 0
                        || solutionmatrix[i][j] < 0
                        || solutionmatrix[i][j] > 9) {
                    return false;
                }
                // check that it matches with initialConfigMatrix
                if (initialConfigMatrix[i][j] != 0 &&
                        initialConfigMatrix[i][j] != solutionmatrix[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    // Check if a partially filled matrix has any conflicts
    public static boolean isValid(int[][] matrix) {
        // Row constraints
        for (int i = 0; i < N; ++i) {
            boolean[] present = new boolean[N + 1];
            for (int j = 0; j < N; ++j) {
                if (matrix[i][j] != 0 && present[matrix[i][j]]) {
                    return false;
                } else {
                    present[matrix[i][j]] = true;
                }
            }
        }

        // Column constraints
        for (int j = 0; j < N; ++j) {
            boolean[] present = new boolean[N + 1];
            for (int i = 0; i < N; ++i) {
                if (matrix[i][j] != 0 && present[matrix[i][j]]) {
                    return false;
                } else {
                    present[matrix[i][j]] = true;
                }
            }
        }

        // Region constraints
        for (int I = 0; I < 3; ++I) {
            for (int J = 0; J < 3; ++J) {
                boolean[] present = new boolean[N + 1];
                for (int i = 0; i < 3; ++i) {
                    for (int j = 0; j < 3; ++j) {
                        if (matrix[3 * I + i][3 * J + j] != 0 &&
                                present[matrix[3 * I + i][3 * J + j]]) {
                            return false;
                        } else {
                            present[matrix[3 * I + i][3 * J + j]] = true;
                        }
                    }
                }
            }
        }
        return true;
    }

    public static int[][] readInput(String arg) {
        String[] args = arg.split("\\s");
        return readInput(args);
    }

    public static int[][] readInput(String[] args) {
        int[][] result = new int[N][];
        for (int k = 0; k < N; k++) {
            result[k] = new int[N];
        }
        for (int k = 0; k < args.length; k++) {
            int val = new Integer(args[k]);
            // format: 634 -> in row 6, col 4 value is 4
            result[val / 100][(val % 100) / 10] = (val % 10);
        }
        return result;
    }

    static String matrixToString(int[][] matrix) {
        String result = "";
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                result = result + i + j + matrix[i][j] + " ";
            }
        }
        return result;
    }

    static void print(String msg, int[][] matrix) {
        System.out.println(msg);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + ((j < 8) ? " " : "\n"));
            }
        }
    }
}
