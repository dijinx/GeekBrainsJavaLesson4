import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public static final int SIZE = 3;
	public static final char[][] BOARD = new char[SIZE][SIZE];

	public static final char DOT_EMPTY = '.';
	public static final char DOT_X = 'X';
	public static final char DOT_O = 'O';
	public static final Scanner scanner = new Scanner(System.in);
	public static Random random = new Random();

	public static void init() {

		for (char[] chars : BOARD) {
			Arrays.fill(chars, DOT_EMPTY);
		}
	}

	public static void print() {
		for (int i = 0; i <= 3; i++) {
			System.out.print(i + " ");
		}
		System.out.println();
		for (int i = 0; i < BOARD.length; i++) {
			System.out.print(i + 1 + " ");
			for (int j = 0; j < BOARD[i].length; j++) {
				System.out.print(BOARD[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void humanTurn() {
		int x, y;
		do {
			System.out.println("Input coordinates");
			x = scanner.nextInt() - 1;
			y = scanner.nextInt() - 1;
		} while (!isValid(x, y));

		BOARD[x][y] = DOT_X;
	}

	public static void aiTurn() {
		int x, y;
		do {
			System.out.println("Ai turn");
			x = random.nextInt(SIZE);
			y = random.nextInt(SIZE);
		} while (!isValid(x, y));

		BOARD[x][y] = DOT_O;
	}
//  Переделать проверку победы,
//  чтобы она не была реализована просто набором условий, например, с использованием циклов.
	static boolean checkWin(char symb) {
		if (BOARD[0][0] == symb && BOARD[0][1] == symb && BOARD[0][2] == symb) return true;
		if (BOARD[1][0] == symb && BOARD[1][1] == symb && BOARD[1][2] == symb) return true;
		if (BOARD[2][0] == symb && BOARD[2][1] == symb && BOARD[2][2] == symb) return true;
		if (BOARD[0][0] == symb && BOARD[1][0] == symb && BOARD[2][0] == symb) return true;
		if (BOARD[0][1] == symb && BOARD[1][1] == symb && BOARD[2][1] == symb) return true;
		if (BOARD[0][2] == symb && BOARD[1][2] == symb && BOARD[2][2] == symb) return true;
		if (BOARD[0][0] == symb && BOARD[1][1] == symb && BOARD[2][2] == symb) return true;
		if (BOARD[2][0] == symb && BOARD[1][2] == symb && BOARD[0][2] == symb) return true;
		return false;
	}

	public static boolean isValid(int x, int y) {
		if (x < 0 || x > SIZE || y < 0 || y > SIZE) {
			return false;
		}
		char occupied = BOARD[x][y];
		return occupied == DOT_EMPTY;
	}

	public static boolean checkDraw() {
		for (int i = 0; i < BOARD.length; i++) {
			for (int j = 0; j < BOARD[i].length; j++) {
				if (BOARD[i][j] == DOT_EMPTY) {
					return false;
				}
			}
		}
		return true;
	}

	public static void main(String[] args) {
		init();
		print();
		while (true) {
			humanTurn();
			print();
			if (checkWin(DOT_X)) {
				System.out.println("Human has won!");
				break;
			}
			if (checkDraw()) {
				System.out.println("Draw");
				break;
			}
			aiTurn();
			print();
			if (checkWin(DOT_O)) {
				System.out.println("Ai has won!");
				break;
			}
			if (checkDraw()) {
				System.out.println("Draw");
				break;
			}
			System.out.println("GAME OVER");
		}
	}
}
