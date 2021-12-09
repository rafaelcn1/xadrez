package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

	public King(Board board, Color color) {
		super(board, color);
		// TODO Auto-generated constructor stub
	}

	// Metodo ToString retorna apenas a letra, pq a letra ir� ser impressa no
	// tabuleiro
	@Override
	public String toString() {
		return "K";
	}

	//Metodo de possiveis movimentos da pe�a, no caso Rei
		@Override
		public boolean[][] possibleMoves() {
			// TODO Auto-generated method stub
			boolean[][] matrizTemp = new boolean[getBoard().getRows()][getBoard().getColumns()];
			return matrizTemp;
		}
}
