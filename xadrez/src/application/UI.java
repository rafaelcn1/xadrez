package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class UI {

	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

	// Cores do texto

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_PRETA = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	// Cores do fundo

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

	// Metodo para limpar a tela
	public static void clearScreen() {
		System.out.println("\033[H\033[2J");
		System.out.flush();
	}

	// Metodo para lê a posição da peça
	public static ChessPosition readChessPostion(Scanner sc) {
		try {
			String s = sc.nextLine();
			char column = s.charAt(0);
			int row = Integer.parseInt(s.substring(1));
			return new ChessPosition(column, row);
		} catch (RuntimeException e) {
			throw new InputMismatchException("Erro a ler a posicao! Favor informar de A1 a H8!");
		}

	}

	public static void printBoard(ChessPiece[][] pieces) {
		
		System.out.println("XXX| A | B | C | D | E | F | G | H |XXX");
		System.out.println("===|===============================|===");

		for (int i = 0; i < pieces.length; i++) {
			System.out.print(" " + ((8 - i)) + " ");
			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j], false);
			}
			System.out.print("| " + (8 - i));
			System.out.println();
		}
		System.out.println("===|===============================|===");
		System.out.println("XXX| A | B | C | D | E | F | G | H |XXX");

	}

	private static void printPiece(ChessPiece piece, boolean background) {
		if (background) {
			System.out.print(ANSI_BLUE_BACKGROUND);
		}
		if (piece == null) {
			System.out.print("| -");
			System.out.print(ANSI_RESET);
		} else {
			if (piece.getColor() == Color.BRANCA) {
				System.out.print("| ");
				System.out.print(ANSI_WHITE + piece + ANSI_RESET);
			} else {
				System.out.print("| " + ANSI_YELLOW + piece + ANSI_RESET);
			}
		}
		System.out.print(" ");
	}
	
	public static void printMatch(ChessMatch chessMatch) {
		printBoard(chessMatch.getPieces());
		System.out.println();
		System.out.println("Vez: " + chessMatch.getTurn());
		System.out.println("Aguardado o jogador das pecas " + chessMatch.getCurrentPlayer() + " jogar!");
	}

	public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
		// TODO Auto-generated method stub
		System.out.println("XXX| A | B | C | D | E | F | G | H |XXX");
		System.out.println("===|===============================|===");

		for (int i = 0; i < pieces.length; i++) {
			System.out.print(" " + ((8 - i)) + " ");
			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j], possibleMoves[i][j]);
			}
			System.out.print("| " + (8 - i));
			System.out.println();
		}
		System.out.println("===|===============================|===");
		System.out.println("XXX| A | B | C | D | E | F | G | H |XXX");

	}

}
