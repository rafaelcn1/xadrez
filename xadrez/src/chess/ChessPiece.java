package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece {

	private Color color;

	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public ChessPosition getChessPosition() {
		return ChessPosition.fromPosition(position);
	}

	// Metodo para testar se a cor da peça adversaria é diferente.
	protected boolean isThereOpponentPiece(Position position) {
		// Pegando a peça
		ChessPiece pieceAdversaria = (ChessPiece) getBoard().piece(position);
		return pieceAdversaria != null && pieceAdversaria.getColor() != getColor();

	}

}
