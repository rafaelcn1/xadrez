package boardgame;

public abstract class Piece {
	protected Position position;
	private Board board;

	public Piece(Board board) {
		this.board = board;
	}

	protected Board getBoard() {
		return board;
	}

	public abstract boolean[][] possibleMoves();

	public boolean possibleMove(Position position) {

		return possibleMoves()[position.getRow()][position.getColumn()];

	}

	// Metodo para checar se existe alguma possibilidade da peça se mover
	public boolean isThereAnyPossibleMove() {
		boolean[][] possibleMoves = possibleMoves();
		for (int i = 0; i < possibleMoves.length; i++) {
			for (int j = 0; j < possibleMoves.length; j++) {
				if (possibleMoves[i][j]) {
					return true;
				}
			}
		}
		return false;
	}

}
