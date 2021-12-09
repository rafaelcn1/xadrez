package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece {

	public Rook(Board board, Color color) {
		super(board, color);
	}

	// Metodo ToString retorna apenas a letra, pq a letra ir� ser impressa no
	// tabuleiro
	@Override
	public String toString() {
		return "R";
	}

	// Metodo de possiveis movimentos da pe�a, no caso Torre
	@Override
	public boolean[][] possibleMoves() {
		// TODO Auto-generated method stub
		boolean[][] matrizTemp = new boolean[getBoard().getRows()][getBoard().getColumns()];
		Position posicaoTemp = new Position(0, 0);

		// Setar a posi da pe�a, na posi��o temporaria
		// Checar posi��es acima
		posicaoTemp.setValues(position.getRow() - 1, position.getColumn());

		while (getBoard().positionExists(posicaoTemp) && !getBoard().therelsAPiece(posicaoTemp)) {
			matrizTemp[posicaoTemp.getRow()][posicaoTemp.getColumn()] = true;
			// Fazer a linha subir mais uma posi��o para cima.
			posicaoTemp.setRow(posicaoTemp.getRow() - 1);
		}

		// Checar se existe uma peca adversaria na posi��o
		if (getBoard().positionExists(posicaoTemp) && isThereOpponentPiece(posicaoTemp)) {
			matrizTemp[posicaoTemp.getRow()][posicaoTemp.getColumn()] = true;
		}

		// Checar posi��es para abaixo
		posicaoTemp.setValues(position.getRow() + 1, position.getColumn());

		while (getBoard().positionExists(posicaoTemp) && !getBoard().therelsAPiece(posicaoTemp)) {
			matrizTemp[posicaoTemp.getRow()][posicaoTemp.getColumn()] = true;
			// Fazer a linha descer mais uma posi��o para cima.
			posicaoTemp.setRow(posicaoTemp.getRow() + 1);
		}

		// Checar se existe uma peca adversaria na posi��o
		if (getBoard().positionExists(posicaoTemp) && isThereOpponentPiece(posicaoTemp)) {
			matrizTemp[posicaoTemp.getRow()][posicaoTemp.getColumn()] = true;
		}

		// Checar posi��es para a esquerda!
		posicaoTemp.setValues(position.getRow(), position.getColumn() - 1);

		while (getBoard().positionExists(posicaoTemp) && !getBoard().therelsAPiece(posicaoTemp)) {
			matrizTemp[posicaoTemp.getRow()][posicaoTemp.getColumn()] = true;
			// Fazer a linha ir para a proxima posi��o para a esquerda.
			posicaoTemp.setColumn(posicaoTemp.getColumn() - 1);
		}

		// Checar se existe uma peca adversaria na posi��o
		if (getBoard().positionExists(posicaoTemp) && isThereOpponentPiece(posicaoTemp)) {
			matrizTemp[posicaoTemp.getRow()][posicaoTemp.getColumn()] = true;
		}

		// Checar posi��es para a direita!
		posicaoTemp.setValues(position.getRow(), position.getColumn() + 1);

		while (getBoard().positionExists(posicaoTemp) && !getBoard().therelsAPiece(posicaoTemp)) {
			matrizTemp[posicaoTemp.getRow()][posicaoTemp.getColumn()] = true;
			// Fazer a linha ir para a proxima posi��o para a direita.
			posicaoTemp.setColumn(posicaoTemp.getColumn() + 1);
		}

		// Checar se existe uma peca adversaria na posi��o
		if (getBoard().positionExists(posicaoTemp) && isThereOpponentPiece(posicaoTemp)) {
			matrizTemp[posicaoTemp.getRow()][posicaoTemp.getColumn()] = true;
		}

		return matrizTemp;
	}

}
