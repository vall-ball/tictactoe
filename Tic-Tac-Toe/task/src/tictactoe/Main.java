package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = "_________";

        String[][] innerMatrix = formInnerMatrix(string);
       // drawMatrix(innerMatrix);
        String[][] outerMatrix = formOuterMatrix(innerMatrix);
        drawMatrix(outerMatrix);

        int coordinateX;
        int coordinateY;
        int turnCount = 0;

        boolean flag = true;
        while (flag) {
            System.out.println("Enter the coordinates:");
            if (scanner.hasNextInt()) {
                coordinateX = scanner.nextInt();
                coordinateY = scanner.nextInt();
                if (coordinateX > 3 || coordinateX < 1 || coordinateY > 3 || coordinateY < 1) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else if (!innerMatrix[Math.abs(coordinateY - 3)][coordinateX - 1].equals(" ")) {
                    System.out.println("This cell is occupied! Choose another one!");
                } else {
                    if (turnCount % 2 == 0) {
                        innerMatrix[Math.abs(coordinateY - 3)][coordinateX - 1] = "X";
                    } else {
                        innerMatrix[Math.abs(coordinateY - 3)][coordinateX - 1] = "O";
                    }
                    outerMatrix = formOuterMatrix(innerMatrix);
                    drawMatrix(outerMatrix);
                    turnCount++;
                    if (isOWin(innerMatrix)) {
                        System.out.println("O wins");
                        flag = false;
                    } else if(isXWin(innerMatrix)) {
                        System.out.println("X wins");
                        flag = false;
                    } else if (!isNotFinished(innerMatrix)) {
                        System.out.println("Draw");
                        flag = false;
                    }
                }
            } else {
                System.out.println("You should enter numbers!");
                scanner.nextLine();
            }
        }



    }

    //the method drawing a matrix
    public static void drawMatrix (String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }

    //the method forming an inner matrix
    public static String[][] formInnerMatrix(String str) {
        String[][] innerMatrix = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                innerMatrix[i][j] = str.substring(i * 3 + j, i * 3 + j + 1);
                if (innerMatrix[i][j].equals("_")) {
                    innerMatrix[i][j] = " ";
                }
            }
        }
        return innerMatrix;
    }

    //the method forming an outer matrix
    public static String[][] formOuterMatrix(String[][] innerMatrix){
        String[][] matrix = new String[5][9];
        for (int i = 0; i < 9; i++) {
            matrix[0][i] = "-";
            matrix[4][i] = "-";
        }

        for (int i = 1; i <= 3; i++) {
            for (int j = 0; j < 9; j++) {
                if (j == 0 || j == 8) {
                    matrix[i][j] = "|";
                } else if (j == 1 || j == 3 || j == 5 || j == 7){
                    matrix[i][j] = " ";
                } else {
                    matrix[i][j] = innerMatrix[i - 1][j / 2 - 1];
                }
            }
        }
        return matrix;
    }

    public static int countX(String[][] matrix) {
        int countX = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matrix[i][j].equals("X")) {
                    countX++;
                }
            }
        }
        return countX;
    }

    public static int countO(String[][] matrix) {
        int countO = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matrix[i][j].equals("O")) {
                    countO++;
                }
            }
        }
        return countO;
    }


    public static boolean isXWin(String[][] matrix) {
        boolean answer = false;
        for (int i = 0; i < 3; i++) {
            if (matrix[i][0].equals(matrix[i][1]) && matrix[i][0].equals(matrix[i][2]) && matrix[i][0].equals("X")) {
                answer = true;
                break;
            } else if (matrix[0][i].equals(matrix[1][i]) && matrix[0][i].equals(matrix[2][i]) && matrix[0][i].equals("X")) {
                answer = true;
                break;
            } else if (matrix[0][0].equals(matrix[1][1]) && matrix[0][0].equals(matrix[2][2]) && matrix[0][0].equals("X")) {
                answer = true;
                break;
            } else if (matrix[0][2].equals(matrix[1][1]) && matrix[0][2].equals(matrix[2][0]) && matrix[0][2].equals("X")) {
                answer = true;
                break;
            }
        }
        return answer;
    }

    public static boolean isOWin(String[][] matrix) {
        boolean answer = false;
        for (int i = 0; i < 3; i++) {
            if (matrix[i][0].equals(matrix[i][1]) && matrix[i][0].equals(matrix[i][2]) && matrix[i][0].equals("O")) {
                answer = true;
                break;
            } else if (matrix[0][i].equals(matrix[1][i]) && matrix[0][i].equals(matrix[2][i]) && matrix[0][i].equals("O")) {
                answer = true;
                break;
            } else if (matrix[0][0].equals(matrix[1][1]) && matrix[0][0].equals(matrix[2][2]) && matrix[0][0].equals("O")) {
                answer = true;
                break;
            } else if (matrix[0][2].equals(matrix[1][1]) && matrix[0][2].equals(matrix[2][0]) && matrix[0][2].equals("O")) {
                answer = true;
                break;
            }
        }
        return answer;
    }

    public static boolean isNotFinished(String[][] matrix) {
        boolean answer = false;
        int sum = countO(matrix) + countX(matrix);
        if (!isXWin(matrix) && !isOWin(matrix) && sum < 9) {
            answer = true;
        }
        return answer;
    }

  /*  public static boolean isImpossible(char[][] matrix) {
        boolean answer = false;
        int difference = countO(matrix) - countX(matrix);
        if (isOWin(matrix) && isXWin(matrix) || Math.abs(difference) > 1) {
            answer = true;
        }
        return answer;
    }*/

}
