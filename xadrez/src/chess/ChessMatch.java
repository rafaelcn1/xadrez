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
		// Convertendo a posição de origem em posição
		Position position = sourcePosition.toPosition();

		// validando a posição
		validateSourcePosition(position);

		// Retornando os possivies movimentos da peça no tabuleiro
		return board.piece(position).possibleMoves();
	}

	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		// Convertendo as posições para a posição
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		// validando a poção
		validateSourcePosition(source);
		validateTargetPosition(source, target);
		// Capturar a peça
		Piece capturePiece = makeMove(source, target);
		return (ChessPiece) capturePiece;

	}

	private void validateTargetPosition(Position source, Position target) {
		// TODO Auto-generated method stub
		// Se não existe um possivel movimento, da peça de origem para o destino, não
		// vai poder mover.
		if (!board.piece(source).possibleMove(target)) {
			throw new ChessException("O movimento da peca de origem nao pode ser movido para o destino!");
		}

	}

	public void validateSourcePosition(Position position) {
		if (!board.therelsAPiece(position)) {
			throw new ChessException("Nao existe peca na posicao de origem!");
		}
		// Acessando a peça do tabuleiro e chamando o metodo boolean para checar se
		// existe possibilidade de mover ou não
		if (!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("Nao existe movimentacao possivel para a peca escolhida!");
		}
	}

	private Piece makeMove(Position source, Position target) {
		// Retirando a peça da posição de origem
		Piece p = board.removePiece(source);

		// Removendo a possivel peça na posição de destino
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
