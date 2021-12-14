package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();
		ArrayList<ChessPiece> captured = new ArrayList<>();

		while (true) {
			try {
				UI.clearScreen();
				UI.printMatch(chessMatch, captured);
				System.out.println();
				System.out.print("DIGITE A POSICAO DE ORIGEM DA PECA: ");
				ChessPosition source = UI.readChessPostion(sc);

				// imprimir as possivies posições de origens apartir da posição lida
				boolean[][] possibleMoves = chessMatch.possibleMoves(source);

				// limpando a tela
				UI.clearScreen();

				// imprimindo novamente o tabuleiro
				UI.printBoard(chessMatch.getPieces(), possibleMoves);

				System.out.println();

				System.out.print("DIGITE A POSICAO DE DESTINO DA PECA: ");
				ChessPosition target = UI.readChessPostion(sc);

				ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
				
				if(capturedPiece != null) {
					captured.add(capturedPiece);
				}

			} catch (ChessException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			} catch (InputMismatchException e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
				sc.nextLine();
			}

		}

	}

}
