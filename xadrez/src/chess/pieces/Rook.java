package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece {

	public Rook(Board board, Color color) {
		super(board, color);
	}

	// Metodo ToString retorna apenas a letra, pq a letra irá ser impressa no
	// tabuleiro
	@Override
	public String toString() {
		return "R";
	}

	// Metodo de possiveis movimentos da peça, no caso Torre
	@Override
	public boolean[][] possibleMoves() {
		// TODO Auto-generated method stub
		boolean[][] matrizTemp = new boolean[getBoard().getRows()][getBoard().getColumns()];
		Position posicaoTemp = new Position(0, 0);

		// Setar a posi da peça, na posição temporaria

		// Inicio da checagem posições e movimentar para cima
		posicaoTemp.setValues(position.getRow() - 1, position.getColumn());

		while (getBoard().positionExists(posicaoTemp) && !getBoard().thereIsAPiece(posicaoTemp)) {
			matrizTemp[posicaoTemp.getRow()][posicaoTemp.getColumn()] = true;
			// Fazer a linha subir mais uma posição para cima.
			posicaoTemp.setRow(posicaoTemp.getRow() - 1);
		}

		// Checar se existe uma peca adversaria na posição
		if (getBoard().positionExists(posicaoTemp) && isThereOpponentPiece(posicaoTemp)) {
			matrizTemp[posicaoTemp.getRow()][posicaoTemp.getColumn()] = true;
		}
		// Fim da checagem posições e movimentar para cima

		// Inicio da checagem posições e movimentar para baixo
		posicaoTemp.setValues(position.getRow() + 1, position.getColumn());

		while (getBoard().positionExists(posicaoTemp) && !getBoard().thereIsAPiece(posicaoTemp)) {
			matrizTemp[posicaoTemp.getRow()][posicaoTemp.getColumn()] = true;
			// Fazer a linha subir mais uma posição para cima.
			posicaoTemp.setRow(posicaoTemp.getRow() + 1);
		}

		// Checar se existe uma peca adversaria na posição
		if (getBoard().positionExists(posicaoTemp) && isThereOpponentPiece(posicaoTemp)) {
			matrizTemp[posicaoTemp.getRow()][posicaoTemp.getColumn()] = true;
		}
		// Fim da checagem posições e movimentar para cima

		// Inicio da checagem posições e movimentar a esquerda!
		posicaoTemp.setValues(position.getRow(), position.getColumn() - 1);

		while (getBoard().positionExists(posicaoTemp) && !getBoard().thereIsAPiece(posicaoTemp)) {
			matrizTemp[posicaoTemp.getRow()][posicaoTemp.getColumn()] = true;
			// Fazer a linha ir para a proxima posição para a esquerda.
			posicaoTemp.setColumn(posicaoTemp.getColumn() - 1);
		}

		// Checar se existe uma peca adversaria na posição
		if (getBoard().positionExists(posicaoTemp) && isThereOpponentPiece(posicaoTemp)) {
			matrizTemp[posicaoTemp.getRow()][posicaoTemp.getColumn()] = true;
		}

		// Fim da checagem posições e movimentar a esquerda!

		// Inicio da checagem posições e movimentar a direita!
		posicaoTemp.setValues(position.getRow(), position.getColumn() + 1);

		while (getBoard().positionExists(posicaoTemp) && !getBoard().thereIsAPiece(posicaoTemp)) {
			matrizTemp[posicaoTemp.getRow()][posicaoTemp.getColumn()] = true;
			// Fazer a linha ir para a proxima posição para a direita.
			posicaoTemp.setColumn(posicaoTemp.getColumn() + 1);
		}

		// Checar se existe uma peca adversaria na posição
		if (getBoard().positionExists(posicaoTemp) && isThereOpponentPiece(posicaoTemp)) {
			matrizTemp[posicaoTemp.getRow()][posicaoTemp.getColumn()] = true;
		}
		// Fim da checagem posições e movimentar a esquerda!
		return matrizTemp;
	}

}
