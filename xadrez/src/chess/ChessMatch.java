package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

	private Board board;

	public ChessMatch() {
		board = new Board(8, 8);
		initialSetup();
	}

	public ChessPiece[][] getPieces() {
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];

		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getColumns(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j);
			}
		}

		return mat;
	}

	public boolean[][] possibleMoves(ChessPosition sourcePosition) {
		// Convertendo a posi��o de origem em posi��o
		Position position = sourcePosition.toPosition();

		// validando a posi��o
		validateSourcePosition(position);

		// Retornando os possivies movimentos da pe�a no tabuleiro
		return board.piece(position).possibleMoves();
	}

	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		// Convertendo as posi��es para a posi��o
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		// validando a po��o
		validateSourcePosition(source);
		validateTargetPosition(source, target);
		// Capturar a pe�a
		Piece capturePiece = makeMove(source, target);
		return (ChessPiece) capturePiece;

	}

	private void validateTargetPosition(Position source, Position target) {
		// TODO Auto-generated method stub
		// Se n�o existe um possivel movimento, da pe�a de origem para o destino, n�o
		// vai poder mover.
		if (!board.piece(source).possibleMove(target)) {
			throw new ChessException("O movimento da peca de origem nao pode ser movido para o destino!");
		}

	}

	public void validateSourcePosition(Position position) {
		if (!board.therelsAPiece(position)) {
			throw new ChessException("Nao existe peca na posicao de origem!");
		}
		// Acessando a pe�a do tabuleiro e chamando o metodo boolean para checar se
		// existe possibilidade de mover ou n�o
		if (!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("Nao existe movimentacao possivel para a peca escolhida!");
		}
	}

	private Piece makeMove(Position source, Position target) {
		// Retirando a pe�a da posi��o de origem
		Piece p = board.removePiece(source);

		// Removendo a possivel pe�a na posi��o de destino
		Piece capturePiece = board.removePiece(target);
		board.placePiece(p, target);
		return capturePiece;

	}

	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
	}

	private void initialSetup() {
		placeNewPiece('b', 6, new Rook(board, Color.WHITE));
		placeNewPiece('c', 1, new Rook(board, Color.WHITE));
		placeNewPiece('c', 2, new Rook(board, Color.WHITE));
		placeNewPiece('d', 2, new Rook(board, Color.WHITE));
		placeNewPiece('e', 2, new Rook(board, Color.WHITE));
		placeNewPiece('e', 1, new Rook(board, Color.WHITE));
		placeNewPiece('d', 1, new King(board, Color.WHITE));

		placeNewPiece('c', 7, new Rook(board, Color.BLACK));
		placeNewPiece('c', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 8, new King(board, Color.BLACK));
		placeNewPiece('h', 8, new King(board, Color.BLACK));

	}
}
