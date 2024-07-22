import java.util.Scanner;

public class ticTacToe {
    private static char[][] board = new char[3][3]; // 3x3 game board
    private static char currentPlayer = 'X'; // Starting player

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain;

        do {
            initializeBoard(); // Set up a new game board
            boolean gameEnded = false;

            // Main game loop
            while (!gameEnded) {
                printBoard(); // Display the current state of the board
                playerMove(scanner); // Get the current player's move
                gameEnded = checkForWin() || checkForDraw(); // Check for win or draw
                if (!gameEnded) {
                    switchPlayer(); // Switch to the other player
                }
            }

            // Display the final board and result
            printBoard();
            if (checkForWin()) {
                System.out.println("Player " + currentPlayer + " wins!"); // Current player has won
            } else {
                System.out.println("The game is a draw!"); // No winner and board is full
            }

            // Ask if players want to play another round
            System.out.print("Do you want to play another game? (yes/no): ");
            playAgain = scanner.next().equalsIgnoreCase("yes");

        } while (playAgain); // Repeat the game if players choose to play again

        scanner.close(); // Close the scanner resource
    }

    // Method to initialize the board with empty spaces
    private static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' '; // Empty space
            }
        }
    }

    // Method to print the current state of the board
    private static void printBoard() {
        System.out.println("  1 2 3"); // Column numbers
        for (int i = 0; i < 3; i++) {
            System.out.print((i + 1) + " "); // Row numbers
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]); // Print each cell
                if (j < 2) System.out.print("|"); // Print column separators
            }
            System.out.println();
            if (i < 2) System.out.println("  -----"); // Print row separators
        }
    }

    // Method to get and validate the player's move
    private static void playerMove(Scanner scanner) {
        int row, col;
        boolean validMove;

        do {
            System.out.print("Player " + currentPlayer + ", enter your move (row and column): ");
            row = scanner.nextInt() - 1; // Convert to 0-based index
            col = scanner.nextInt() - 1; // Convert to 0-based index
            validMove = isValidMove(row, col); // Check if the move is valid
            if (!validMove) {
                System.out.println("This move is not valid. Try again."); // Invalid move message
            }
        } while (!validMove); // Repeat until a valid move is entered

        board[row][col] = currentPlayer; // Update the board with the current player's move
    }

    // Method to check if the player's move is valid
    private static boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' '; // Valid move criteria
    }

    // Method to switch the current player
    private static void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X'; // Toggle between 'X' and 'O'
    }

    // Method to check if there is a winner
    private static boolean checkForWin() {
        return (checkRows() || checkColumns() || checkDiagonals()); // Check rows, columns, and diagonals
    }

    // Method to check if any row has a winning combination
    private static boolean checkRows() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true; // Winning row found
            }
        }
        return false;
    }

    // Method to check if any column has a winning combination
    private static boolean checkColumns() {
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                return true; // Winning column found
            }
        }
        return false;
    }

    // Method to check if any diagonal has a winning combination
    private static boolean checkDiagonals() {
        return ((board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
                (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer)); // Winning diagonal found
    }

    // Method to check if the game is a draw (no empty spaces and no winner)
    private static boolean checkForDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false; // Board is not full yet
                }
            }
        }
        return true; // Board is full and no winner, hence a draw
    }
}
