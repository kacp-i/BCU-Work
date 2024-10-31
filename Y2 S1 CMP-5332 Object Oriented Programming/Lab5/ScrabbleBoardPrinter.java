public class ScrabbleBoardPrinter {

	public static void printBoard(char[][] board) {
		// calculates the length of the board
		int boardBoundary = (board[0].length * 2) + 2;
		
		// sets the top line for the board
		for (int x = 0; x <= boardBoundary; x++) {
			if (x == 0 || x == boardBoundary) {
				System.out.print("+");
			}
			else {
				System.out.print("-");
			}
		}
		
		// sets the main body of the board
		for (char[] elem : board) {
			System.out.print("\n| ");
			for (char letter : elem){
				System.out.print(letter);
				System.out.print(" ");
			}
			System.out.print("|");
		}
		System.out.println();
		
		
		// sets the bottom line of the board
		for (int x = 0; x <= boardBoundary; x++) {
			if (x == 0 || x == boardBoundary) {
				System.out.print("+");
			}
			else {
				System.out.print("-");
			}
		}
	}
	
	public static void main(String[] args) {
		char[][] samp = new char[][] {{ '.', '.', 'W', '.', '.' }, { '.', '.', 'O', '.', '.' }, { '.', '.', 'R', '.', '.' }, { 'H', 'E', 'L', 'L', 'O' }, { '.', '.', 'D', '.', '.' }};
		char[][] samp1 = new char[][] {{'.', '.', '.', '.', '.', '.'}, {'.', '.', 'W', '.', '.', '.'}, {'.', '.', 'O', 'O', '.', '.'}, {'.', '.', '.', 'W', '.', '.'}};
		
		// testing
		printBoard(samp);
		System.out.println();
		printBoard(samp1);
	}
 }
