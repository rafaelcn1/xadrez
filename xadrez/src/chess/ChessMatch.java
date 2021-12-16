package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Queen;
import chess.pieces.Rook;

public class ChessMatch {
	private int turn; // Vez
	private Color currentPlayer; // Jogador atual
	private Board board;
	private boolean check;
	private boolean checkMate;
	private ChessPiece enPassantVulnerable;

	private ChessPiece promoted;

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

	public boolean getCheck() {
		return check;
	}

	public boolean getCheckMate() {
		return checkMate;
	}

	public ChessPiece getEnPassantVulnerable() {
		return enPassantVulnerable;
	}

	public ChessPiece getPromoted() {
		return promoted;
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

		if (testCheck(currentPlayer)) {
			undoMove(source, target, capturedPiece);
			throw new ChessException("jogada invalida! Voce nao pode se colocar em xeque!");
		}

		ChessPiece movedPiece = (ChessPiece) board.piece(target);

		// Teste de jogada de promoção de peça
		promoted = null;
		if (movedPiece instanceof Pawn) {
			if (movedPiece.getColor() == Color.BRANCA && target.getRow() == 0
					|| movedPiece.getColor() == Color.PRETA && target.getRow() == 7) {
				promoted = (ChessPiece) board.piece(target);
				promoted = replacePromotedPiece("Q");
			}
		}

		if (testCheck(opponent(currentPlayer))) {
			check = true;
		} else {
			check = false;
		}

		// Testar se o oponente levou um xeque mate
		if (testCheckMate(opponent(currentPlayer))) {
			checkMate = true;
		} else {
			nextTurn();
		}

		// Jogada especial, en passant
		if (movedPiece instanceof Pawn
				&& (target.getRow() == source.getRow() - 2 || target.getRow() == source.getRow() + 2)) {
			enPassantVulnerable = movedPiece;

		} else {
			enPassantVulnerable = null;
		}

		return (ChessPiece) capturedPiece;
	}

	public ChessPiece replacePromotedPiece(String type) {
		if (promoted == null) {
			throw new IllegalStateException("Nao tem peca para ser promovida!");
		}

		if (!type.equals("B") && !type.equals("C") && !type.equals("R") && !type.equals("Q")) {
			return promoted;
		}

		Position pos = promoted.getChessPosition().toPosition();
		Piece p = board.removePiece(pos);
		piecesOnTheBoard.remove(p);

		ChessPiece newPiece = newPiece(type, promoted.getColor());
		board.placePiece(newPiece, pos);
		piecesOnTheBoard.add(newPiece);

		return newPiece;
	}

	private ChessPiece newPiece(String type, Color color) {
		if (type.equals("B"))
			return new Bishop(board, color);
		if (type.equals("C"))
			return new Knight(board, color);
		if (type.equals("Q"))
			return new Queen(board, color);
		return new Rook(board, color);
	}

	private Piece makeMove(Position source, Position target) {
		ChessPiece p = (ChessPiece) board.removePiece(source);
		p.increaseMoveCount();
		Piece capturedPiece = board.removePiece(target);
		board.placePiece(p, target);

		if (capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}

		// Rook pequeno
		if (p instanceof King && target.getColumn() == source.getColumn() + 2) {
			Position sourceTorre = new Position(source.getRow(), source.getColumn() + 3);
			Position targetTorre = new Position(source.getRow(), source.getColumn() + 1);
			ChessPiece rook = (ChessPiece) board.removePiece(sourceTorre);
			board.placePiece(rook, targetTorre);
			rook.increaseMoveCount();
		}

		// Rook Grande
		if (p instanceof King && target.getColumn() == source.getColumn() - 2) {
			Position sourceTorre = new Position(source.getRow(), source.getColumn() - 4);
			Position targetTorre = new Position(source.getRow(), source.getColumn() - 1);
			ChessPiece rook = (ChessPiece) board.removePiece(sourceTorre);
			board.placePiece(rook, targetTorre);
			rook.increaseMoveCount();
		}

		// Jogada especial en passant
		if (p instanceof Pawn) {
			if (source.getColumn() != target.getColumn() && capturedPiece == null) {
				Position pawnPosition;
				if (p.getColor() == Color.BRANCA) {
					pawnPosition = new Position(target.getRow() + 1, target.getColumn());
				} else {
					pawnPosition = new Position(target.getRow() - 1, target.getColumn());
				}
				capturedPiece = board.removePiece(pawnPosition);
				capturedPieces.add(capturedPiece);
				piecesOnTheBoard.remove(capturedPiece);
			}
		}

		return capturedPiece;
	}

	public void undoMove(Position source, Position target, Piece capturedPiece) {
		ChessPiece p = (ChessPiece) board.removePiece(target);
		p.decreaseMoveCount();
		board.placePiece(p, source);

		if (capturedPiece != null) {
			board.placePiece(capturedPiece, target);
			capturedPieces.remove(capturedPiece);
			piecesOnTheBoard.add(capturedPiece);
		}

		// Rook pequeno
		if (p instanceof King && target.getColumn() == source.getColumn() + 2) {
			Position sourceTorre = new Position(source.getRow(), source.getColumn() + 3);
			Position targetTorre = new Position(source.getRow(), source.getColumn() + 1);
			ChessPiece rook = (ChessPiece) board.removePiece(targetTorre);
			board.placePiece(rook, sourceTorre);
			rook.decreaseMoveCount();
		}

		// Rook Grande
		if (p instanceof King && target.getColumn() == source.getColumn() - 2) {
			Position sourceTorre = new Position(source.getRow(), source.getColumn() - 4);
			Position targetTorre = new Position(source.getRow(), source.getColumn() - 1);
			ChessPiece rook = (ChessPiece) board.removePiece(targetTorre);
			board.placePiece(rook, sourceTorre);
			rook.decreaseMoveCount();
		}

		// Jogada especial en passant
		if (p instanceof Pawn) {
			if (source.getColumn() != target.getColumn() && capturedPiece == enPassantVulnerable) {
				ChessPiece pawn = (ChessPiece) board.removePiece(target);
				Position pawnPosition;
				if (p.getColor() == Color.BRANCA) {
					pawnPosition = new Position(3, target.getColumn());
				} else {
					pawnPosition = new Position(4, target.getColumn());
				}
				board.placePiece(pawn, pawnPosition);
			}
		}
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

	private Color opponent(Color color) {
		return (color == color.BRANCA ? color.PRETA : color.BRANCA);
	}

	private ChessPiece king(Color color) {
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == color)
				.collect(Collectors.toList());
		for (Piece p : list) {
			if (p instanceof King) {
				return (ChessPiece) p;
			}
		}
		throw new IllegalStateException("Nao existe um Rei da cor " + color + " no tabuleiro!");
	}

	private boolean testCheck(Color color) {
		Position kingPosition = king(color).getChessPosition().toPosition();
		List<Piece> opponentPieces = piecesOnTheBoard.stream()
				.filter(x -> ((ChessPiece) x).getColor() == opponent(color)).collect(Collectors.toList());

		for (Piece p : opponentPieces) {
			boolean[][] mat = p.possibleMoves();
			if (mat[kingPosition.getRow()][kingPosition.getColumn()]) {
				return true;
			}
		}
		return false;
	}

	private boolean testCheckMate(Color color) {
		if (!testCheck(color)) {
			return false;
		}

		// Pegando todas as peças do tabuleiuro com o filtro da cor inforada no metodo!
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == color)
				.collect(Collectors.toList());

		for (Piece p : list) {
			boolean[][] mat = p.possibleMoves();
			for (int i = 0; i < board.getRows(); i++) { // percorrer as linhas da matriz
				for (int j = 0; j < board.getColumns(); j++) { // percorrer as colunas da matriz!
					if (mat[i][j]) {
						// Dar um downcast na peça P com o ChessPiece, chamando o metodo toPosition;
						Position source = ((ChessPiece) p).getChessPosition().toPosition();
						// atribuindo o destino com uma nova posição com o alinha de i e coluna j
						Position target = new Position(i, j);

						// movimentando a peça p da origem para o destino
						Piece capturedPiece = makeMove(source, target);

						// Testar se ainda está em check
						boolean testCheck = testCheck(color);

						// desfazes o movimento para não bugar o tabuleiro
						undoMove(source, target, capturedPiece);

						// Se testCheck for falso, é pq tem como tirar o rei do check
						if (!testCheck) {
							return false; // pq nao está em check mate
						}

					}

				}

			}
		}
		return true;
	}

	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition()); // Colocar a peça no tabuleiuro
		piecesOnTheBoard.add(piece); // Toda vez que uma peça for adicionada no tabuleiro, será adicionado na lista
	}

	private void initialSetup() {
		placeNewPiece('e', 8, new King(board, Color.PRETA, this));
		placeNewPiece('e', 1, new King(board, Color.BRANCA, this));

		placeNewPiece('a', 7, new Pawn(board, Color.BRANCA, this));

		/*
		 * placeNewPiece('d', 1, new Queen(board, Color.BRANCA)); placeNewPiece('a', 1,
		 * new Rook(board, Color.BRANCA)); placeNewPiece('c', 1, new Bishop(board,
		 * Color.BRANCA)); placeNewPiece('f', 1, new Bishop(board, Color.BRANCA));
		 * placeNewPiece('b', 1, new Knight(board, Color.BRANCA)); placeNewPiece('g', 1,
		 * new Knight(board, Color.BRANCA)); placeNewPiece('h', 1, new Rook(board,
		 * Color.BRANCA)); placeNewPiece('a', 2, new Pawn(board, Color.BRANCA, this));
		 * placeNewPiece('b', 2, new Pawn(board, Color.BRANCA, this));
		 * placeNewPiece('c', 2, new Pawn(board, Color.BRANCA, this));
		 * placeNewPiece('d', 2, new Pawn(board, Color.BRANCA, this));
		 * placeNewPiece('e', 2, new Pawn(board, Color.BRANCA, this));
		 * placeNewPiece('f', 2, new Pawn(board, Color.BRANCA, this));
		 * placeNewPiece('g', 2, new Pawn(board, Color.BRANCA, this));
		 * placeNewPiece('h', 2, new Pawn(board, Color.BRANCA, this));
		 * 
		 * placeNewPiece('b', 8, new Knight(board, Color.PRETA)); placeNewPiece('g', 8,
		 * new Knight(board, Color.PRETA)); placeNewPiece('c', 8, new Bishop(board,
		 * Color.PRETA)); placeNewPiece('f', 8, new Bishop(board, Color.PRETA));
		 * placeNewPiece('a', 8, new Rook(board, Color.PRETA)); placeNewPiece('h', 8,
		 * new Rook(board, Color.PRETA)); placeNewPiece('d', 8, new Queen(board,
		 * Color.PRETA)); placeNewPiece('a', 7, new Pawn(board, Color.PRETA, this));
		 * placeNewPiece('b', 7, new Pawn(board, Color.PRETA, this)); placeNewPiece('c',
		 * 7, new Pawn(board, Color.PRETA, this)); placeNewPiece('d', 7, new Pawn(board,
		 * Color.PRETA, this)); placeNewPiece('e', 7, new Pawn(board, Color.PRETA,
		 * this)); placeNewPiece('f', 7, new Pawn(board, Color.PRETA, this));
		 * placeNewPiece('g', 7, new Pawn(board, Color.PRETA, this)); placeNewPiece('h',
		 * 7, new Pawn(board, Color.PRETA, this));
		 */
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
