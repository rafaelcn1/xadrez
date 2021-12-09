package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece {

	public Rook(Board board, Color color) {
		super(board, color);
	}

	// Metodo ToString retorna apenas a letra, pq a letra irá ser impressa no
	// tabuleiro
	@Override
	public String toString() {
		return "R";
	}

	//Metodo de possiveis movimentos da peça, no caso Torre
	@Override
	public boolean[][] possibleMoves() {
		// TODO Auto-generated method stub
		boolean[][] matrizTemp = new boolean[getBoard().getRows()][getBoard().getColumns()];
		return matrizTemp;
	}

}
