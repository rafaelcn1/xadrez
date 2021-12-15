package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Queen extends ChessPiece {

	public Queen(Board board, Color color) {
		super(board, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean[][] possibleMoves() {
		// Cria��o de uma matriz com as linhas e colunas do tabuleiro
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

		// Iniciando uma posi��o
		Position p = new Position(0, 0);

		// Movimenta��es

		// Acima
		p.setValues(position.getRow() - 1, position.getColumn());

		// Enquanto no tabuleiro tiver posi��o existente e n�o existir uma pe�a no
		// tabuleiro
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			// A matriz na posi��o da linha e coluna, recebe true, para ficar na posi��o do
			// tabuleiro
			mat[p.getRow()][p.getColumn()] = true;
			// a linha da pe�a vai receber a linha - 1 para subir
			p.setRow(p.getRow() - 1);
		}
		// Checar se existe uma peca adversaria na posi��o
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			// ir ficar na posi��o do adversario no tabuleiro
			mat[p.getRow()][p.getColumn()] = true;
		}

		// Acima - Direita
		p.setValues(position.getRow() - 1, position.getColumn() + 1);
		// Enquanto no tabuleiro tiver posi��o existente e n�o existir uma pe�a no
		// tabuleiro
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			// A matriz na posi��o da linha e coluna, recebe true, para ficar na posi��o do
			// tabuleiro
			mat[p.getRow()][p.getColumn()] = true;
			// P vai receber os novos valores, linha vai ser linha atual - 1 e coluna vai
			// ser coluna atual + 1
			p.setValues(p.getRow() - 1, p.getColumn() + 1);
		}

		// Checar se existe uma peca adversaria na posi��o
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			// ir ficar na posi��o do adversario no tabuleiro
			mat[p.getRow()][p.getColumn()] = true;
		}

		// Direita
		p.setValues(position.getRow(), position.getColumn() + 1);
		// Enquanto no tabuleiro tiver posi��o existente e n�o existir uma pe�a no
		// tabuleiro
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			// A matriz na posi��o da linha e coluna, recebe true, para ficar na posi��o do
			// tabuleiro
			mat[p.getRow()][p.getColumn()] = true;
			// P vai receber os novos valores, linha vai ser linha atual - 1 e coluna vai
			// ser coluna atual + 1
			p.setValues(p.getRow(), p.getColumn() + 1);
		}

		// Checar se existe uma peca adversaria na posi��o
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			// ir ficar na posi��o do adversario no tabuleiro
			mat[p.getRow()][p.getColumn()] = true;
		}

		// Direita/Baixo
		p.setValues(position.getRow() + 1, position.getColumn() + 1);
		// Enquanto no tabuleiro tiver posi��o existente e n�o existir uma pe�a no
		// tabuleiro
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			// A matriz na posi��o da linha e coluna, recebe true, para ficar na posi��o do
			// tabuleiro
			mat[p.getRow()][p.getColumn()] = true;
			// P vai receber os novos valores, linha vai ser linha atual - 1 e coluna vai
			// ser coluna atual + 1
			p.setValues(p.getRow() + 1, p.getColumn() + 1);
		}

		// Checar se existe uma peca adversaria na posi��o
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			// ir ficar na posi��o do adversario no tabuleiro
			mat[p.getRow()][p.getColumn()] = true;
		}

		// Baixo
		p.setValues(position.getRow() + 1, position.getColumn());
		// Enquanto no tabuleiro tiver posi��o existente e n�o existir uma pe�a no
		// tabuleiro
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			// A matriz na posi��o da linha e coluna, recebe true, para ficar na posi��o do
			// tabuleiro
			mat[p.getRow()][p.getColumn()] = true;
			// P vai receber os novos valores, linha vai ser linha atual - 1 e coluna vai
			// ser coluna atual + 1
			p.setValues(p.getRow() + 1, p.getColumn());
		}

		// Checar se existe uma peca adversaria na posi��o
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			// ir ficar na posi��o do adversario no tabuleiro
			mat[p.getRow()][p.getColumn()] = true;
		}

		// Baixo/Esquerda
		p.setValues(position.getRow() + 1, position.getColumn() - 1);
		// Enquanto no tabuleiro tiver posi��o existente e n�o existir uma pe�a no
		// tabuleiro
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			// A matriz na posi��o da linha e coluna, recebe true, para ficar na posi��o do
			// tabuleiro
			mat[p.getRow()][p.getColumn()] = true;
			// P vai receber os novos valores, linha vai ser linha atual - 1 e coluna vai
			// ser coluna atual + 1
			p.setValues(p.getRow() + 1, p.getColumn() - 1);
		}

		// Checar se existe uma peca adversaria na posi��o
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			// ir ficar na posi��o do adversario no tabuleiro
			mat[p.getRow()][p.getColumn()] = true;
		}

		// Esquerda
		p.setValues(position.getRow(), position.getColumn() - 1);
		// Enquanto no tabuleiro tiver posi��o existente e n�o existir uma pe�a no
		// tabuleiro
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			// A matriz na posi��o da linha e coluna, recebe true, para ficar na posi��o do
			// tabuleiro
			mat[p.getRow()][p.getColumn()] = true;
			// P vai receber os novos valores, linha vai ser linha atual - 1 e coluna vai
			// ser coluna atual + 1
			p.setValues(p.getRow(), p.getColumn() - 1);
		}

		// Checar se existe uma peca adversaria na posi��o
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			// ir ficar na posi��o do adversario no tabuleiro
			mat[p.getRow()][p.getColumn()] = true;
		}

		// Esquerda/Acima
		p.setValues(position.getRow() - 1, position.getColumn() - 1);
		// Enquanto no tabuleiro tiver posi��o existente e n�o existir uma pe�a no
		// tabuleiro
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			// A matriz na posi��o da linha e coluna, recebe true, para ficar na posi��o do
			// tabuleiro
			mat[p.getRow()][p.getColumn()] = true;
			// P vai receber os novos valores, linha vai ser linha atual - 1 e coluna vai
			// ser coluna atual + 1
			p.setValues(p.getRow() - 1, p.getColumn() - 1);
		}

		// Checar se existe uma peca adversaria na posi��o
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			// ir ficar na posi��o do adversario no tabuleiro
			mat[p.getRow()][p.getColumn()] = true;
		}

		return mat;
	}

	@Override
	public String toString() {
		return "Q";
	}

}
