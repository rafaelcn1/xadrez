package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Bishop extends ChessPiece {

	public Bishop(Board board, Color color) {
		super(board, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean[][] possibleMoves() {
		// TODO Auto-generated method stub
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		Position p = new Position(0, 0);

		// Setar a posi da peça, na posição temporaria

		// Inicio da checagem posições e movimentar para diagonal acima/esquerda
		p.setValues(position.getRow() - 1, position.getColumn() - 1);

		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			// Fazer a linha subir mais uma posição para diagonal acima/esquerda.
			p.setValues(p.getRow() - 1, p.getColumn() - 1);
		}

		// Checar se existe uma peca adversaria na posição
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		// Fim da checagem posições e movimentar para diagonal acima/esquerda

		// Inicio da checagem posições e movimentar para diagonal acima/direita
		p.setValues(position.getRow() - 1, position.getColumn() + 1);

		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			// Fazer a linha ir para a proxima posição para a esquerda.
			p.setValues(p.getRow() - 1, p.getColumn() + 1);
		}

		// Checar se existe uma peca adversaria na posição
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// Fim da checagem posições e movimentar para diagonal acima/direita

		// Inicio da checagem posições e movimentar para diagonal abaixo/esquerda
		p.setValues(position.getRow() + 1, position.getColumn() - 1);

		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			// Fazer a linha subir mais uma posição para cima.
			p.setValues(p.getRow() + 1, p.getColumn() - 1);
		}

		// Checar se existe uma peca adversaria na posição
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		// Fim da checagem posições e movimentar para diagonal abaixo/esquerda

		// Inicio da checagem posições e movimentar para diagonal abaixo/direita
		p.setValues(position.getRow() + 1, position.getColumn() + 1);

		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			// Fazer a linha ir para a proxima posição para a direita.
			p.setValues(p.getRow() + 1, p.getColumn() + 1);
		}

		// Checar se existe uma peca adversaria na posição
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		// Fim da checagem posições e movimentar para diagonal abaixo/direita
		return mat;
	}

	@Override
	public String toString() {
		return "B";
	}

}
