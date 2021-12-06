package application;

import chess.ChessPiece;

public class UI {

	public static void printBoard(ChessPiece[][] pieces) {
		System.out.println("XXX| A | B | C | D | E | F | G | H |");
		System.out.println("===|===============================|");
		for (int i = 0; i < pieces.length; i++) {
			System.out.print(" " + (i + 1));
			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j]);
			}
			System.out.print(" |");
			System.out.println();
		}
		System.out.println("====================================");

	}

	private static void printPiece(ChessPiece piece) {
		if (piece == null) {
			//System.out.print("-");
			System.out.print(" | ");
		} else {
			System.out.print(piece);
		}
		

	}

}
