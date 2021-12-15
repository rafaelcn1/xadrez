package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Knight extends ChessPiece {

	public Knight(Board board, Color color) {
		super(board, color);
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		Position p = new Position(0, 0);

		// L Acima/Direita
		p.setValues(position.getRow() - 2, position.getColumn() + 1);

		// Se a possição acima dele existir e estiver vazia
		if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		p.setValues(position.getRow() - 2, position.getColumn() + 1);

		// Checar se existe uma peca adversaria na posição
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			// ir ficar na posição do adversario no tabuleiro
			mat[p.getRow()][p.getColumn()] = true;
		}

		// L Acima/Esquerda
		p.setValues(position.getRow() - 2, position.getColumn() - 1);
		// acessar o tabuleiro e chamar o metodo para checagem de posição existente e
		// acessar o tabuleiro e checar se NÂO existe peça na poição P
		if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// Atualizar a posição P
		p.setValues(position.getRow() - 2, position.getColumn() - 1);

		// Checar se existe uma peca adversaria na posição
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			// ir ficar na posição do adversario no tabuleiro
			mat[p.getRow()][p.getColumn()] = true;
		}

		// L Abaixo/Direita
		p.setValues(position.getRow() + 2, position.getColumn() + 1);

		// Se a possição acima dele existir e estiver vazia
		if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		p.setValues(position.getRow() + 2, position.getColumn() + 1);

		// Checar se existe uma peca adversaria na posição
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			// ir ficar na posição do adversario no tabuleiro
			mat[p.getRow()][p.getColumn()] = true;
		}

		// L Abaixo/Esquerda
		p.setValues(position.getRow() + 2, position.getColumn() - 1);
		// acessar o tabuleiro e chamar o metodo para checagem de posição existente e
		// acessar o tabuleiro e checar se NÂO existe peça na poição P
		if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// Atualizar a posição P
		p.setValues(position.getRow() + 2, position.getColumn() - 1);

		// Checar se existe uma peca adversaria na posição
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			// ir ficar na posição do adversario no tabuleiro
			mat[p.getRow()][p.getColumn()] = true;
		}

		// L Direita/Acima
		p.setValues(position.getRow() - 1, position.getColumn() + 2);
		if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		p.setValues(position.getRow() - 1, position.getColumn() + 2);
		// Checar se existe uma peca adversaria na posição
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			// ir ficar na posição do adversario no tabuleiro
			mat[p.getRow()][p.getColumn()] = true;
		}

		// L Direita/Abaixo
		p.setValues(position.getRow() + 1, position.getColumn() + 2);
		if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		p.setValues(position.getRow() + 1, position.getColumn() + 2);
		// Checar se existe uma peca adversaria na posição
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			// ir ficar na posição do adversario no tabuleiro
			mat[p.getRow()][p.getColumn()] = true;
		}

		// L Esquerda/Acima
		p.setValues(position.getRow() - 1, position.getColumn() - 2);
		if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		p.setValues(position.getRow() - 1, position.getColumn() - 2);

		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// L Esquerda/Abaixo
		p.setValues(position.getRow() + 1, position.getColumn() - 2);
		if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		p.setValues(position.getRow() + 1, position.getColumn() - 2);
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		return mat;
	}

	@Override
	public String toString() {
		return "C";
	}

}
