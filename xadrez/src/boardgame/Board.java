package boardgame;

public class Board {

	private int rows;
	private int columns;

	private Piece[][] pieces;

	public Board(int rows, int columns) {
		if (rows < 0 || columns < 0) {
			throw new BoardException("Erro ao criar o tabuleiro! Necess�rio pelo menos 1 linha e 1 coluna ");
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	public Piece piece(int row, int coloumn) {
		if (!positionExists(row, coloumn)) {
			throw new BoardException("Posicao nao existe no tabuleiro!");
		}
		return pieces[row][coloumn];
	}

	public Piece piece(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Posicao nao existe no tabuleiro!");
		}
		return pieces[position.getRow()][position.getColumn()];
	}

	// Metodo responsavel para colocar uma pe�a no taboleiro, onde vai receber uma
	// pe�a e a posi��o
	public void placePiece(Piece piece, Position position) {
		// Testando se existe uma pe�a na posi��o recebida;
		if (therelsAPiece(position)) {
			throw new BoardException("J� existe uma pe�a nessa posi��o! (Linha: " + position.getRow() + ", Coluna: "
					+ position.getColumn() + ")");
		}

		// A posi��o da linha e coluna da matriz pieces ir� receber a piece declarada no
		// metodo placePiece
		pieces[position.getRow()][position.getColumn()] = piece;
		// A antiga posi��o da pe�a ir� receber a posi��o recebida no metodo placePiece
		piece.position = position;

	}

	// Metodo para testar se uma posi��o existe por uma linha e pela coluna
	public boolean positionExists(int row, int column) {
		return row >= 0 && row <= rows && column >= 0 && column < columns;
	}

	public boolean positionExists(Position position) {
		// Retornando o metado de cima, para testar se uma posi��o existe;
		return positionExists(position.getRow(), position.getColumn());
	}

	// MEtodo para testar se a pe�a existe, caso a piece(position) for diferente de
	// nulo, � porque existe uma pe�a
	public boolean therelsAPiece(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Posicao nao existe no tabuleiro!");
		}
		return piece(position) != null;
	}

	public Piece removePiece(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Posi��o n�o existe no tabuleiro!");
		}

		if (piece(position) == null) {
			return null;
		}
		// Salvando a pe�a da oisi��o informada em uma var auxiliar
		Piece aux = piece(position);

		// Na matrix de pe�as na posi��o informada vai receber null, onde ir� remover
		pieces[position.getRow()][position.getColumn()] = null;
		return aux;
	}

}
