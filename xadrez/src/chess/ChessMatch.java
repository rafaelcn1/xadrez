package chess;

import java.util.ArrayList;
import java.util.List;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
	private int turn; // Vez
	private Color currentPlayer; // Jogador atual
	private Board board;

	private List<Piece> piecesOnTheBoard = new ArrayList<Piece>();
	private List<Piece> capturedPieces = new ArrayList<Piece>();

	public ChessMatch() {
		board = new Board(8, 8);
		turn = 1;
		currentPlayer = Color.BRANCA;
		initialSetup();
	}

	public int getTurn() {
		return turn;
	}

	public Color getCurrentPlayer() {
		return currentPlayer;
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

	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validateSourcePosition(source);
		validateTargetPosition(source, target);
		Piece capturedPiece = makeMove(source, target);
		nextTurn();
		return (ChessPiece) capturedPiece;
	}

	private Piece makeMove(Position source, Position target) {
		Piece p = board.removePiece(source);
		Piece capturedPiece = board.removePiece(target);
		board.placePiece(p, target);

		if (capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}
		return capturedPiece;
	}

	private void validateSourcePosition(Position position) {
		if (!board.thereIsAPiece(position)) {
			throw new ChessException("Nao existe peca na posicao de origem!");
		}

		if (currentPlayer != ((ChessPiece) board.piece(position)).getColor()) {
			throw new ChessException("A peca escolhida eh do adversario!");
		}
		if (!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("Nao existe movimentos possiveis para a peca escolhida!");
		}
	}

	private void validateTargetPosition(Position source, Position target) {
		if (!board.piece(source).possibleMove(target)) {
			throw new ChessException("A peca escolhida nao pode se mover para a posicao de destino!");
		}
	}

	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition()); // Colocar a peça no tabuleiuro
		piecesOnTheBoard.add(piece); // Toda vez que uma peça for adicionada no tabuleiro, será adicionado na lista
	}

	private void initialSetup() {
		placeNewPiece('c', 1, new Rook(board, Color.BRANCA));
		placeNewPiece('c', 2, new Rook(board, Color.BRANCA));
		placeNewPiece('d', 2, new Rook(board, Color.BRANCA));
		placeNewPiece('e', 2, new Rook(board, Color.BRANCA));
		placeNewPiece('e', 1, new Rook(board, Color.BRANCA));
		placeNewPiece('d', 1, new King(board, Color.BRANCA));

		placeNewPiece('c', 7, new Rook(board, Color.PRETA));
		placeNewPiece('c', 8, new Rook(board, Color.PRETA));
		placeNewPiece('d', 7, new Rook(board, Color.PRETA));
		placeNewPiece('e', 7, new Rook(board, Color.PRETA));
		placeNewPiece('e', 8, new Rook(board, Color.PRETA));
		placeNewPiece('d', 8, new King(board, Color.PRETA));
	}

	public boolean[][] possibleMoves(ChessPosition sourcePosition) {
		// Convertendo a posição de origem em posição
		Position position = sourcePosition.toPosition();

		// validando a posição
		validateSourcePosition(position);

		// Retornando os possivies movimentos da peça no tabuleiro
		return board.piece(position).possibleMoves();
	}

	private void nextTurn() {
		turn++;
		currentPlayer = (currentPlayer == Color.BRANCA) ? Color.PRETA : Color.BRANCA; // mudando o jgador atual
	}
}
