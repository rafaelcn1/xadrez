package boardgame;

public class Board {

	private int rows;
	private int columns;

	private Piece[][] pieces;

	public Board(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public Piece piece(int row, int coloumn) {
		return pieces[row][coloumn];
	}

	public Piece piece(Position position) {
		return pieces[position.getRow()][position.getColumn()];
	}

	// Metodo responsavel para colocar uma pe�a no taboleiro, onde vai receber uma
	// pe�a e a posi��o
	public void placePiece(Piece piece, Position position) {
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
		return piece(position) != null;
	}

}
