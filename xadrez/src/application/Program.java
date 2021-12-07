package application;

import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();

		while (true) {
			UI.printBoard(chessMatch.getPieces());
			System.out.println();
			System.out.print("DIGITE A POSIÇÃO DE ORIGEM DA PEÇA: ");
			ChessPosition source = UI.readChessPostion(sc);
			
			System.out.println();
			
			System.out.print("DIGITE A POSIÇÃO DE DESTINO DA PEÇA: ");
			ChessPosition target = UI.readChessPostion(sc);
			
			ChessPiece capturePiece = chessMatch.performChessMove(source, target);
			

		}

	}

}
