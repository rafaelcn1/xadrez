 package chess.pieces;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

	public King(Board board, Color color) {
		super(board, color);
		// TODO Auto-generated constructor stub
	}

	// Metodo ToString retorna apenas a letra, pq a letra irá ser impressa no
	// tabuleiro
	@Override
	public String toString() {
		return "K";
	}
	
	//Metodo para verificar se a peça do rei é diferente de nula e a cor diferente do adversario
	public boolean canMove(Position position) {
		ChessPiece piece = (ChessPiece) getBoard().piece(position);
		return piece != null && piece.getColor() != getColor();
	}

	//Metodo de possiveis movimentos da peça, no caso Rei
		@Override
		public boolean[][] possibleMoves() {
			// TODO Auto-generated method stub
			boolean[][] matrizTemp = new boolean[getBoard().getRows()][getBoard().getColumns()];
			return matrizTemp;
		}
}
