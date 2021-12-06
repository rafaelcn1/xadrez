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

	// Metodo responsavel para colocar uma peça no taboleiro, onde vai receber uma
	// peça e a posição
	public void placePiece(Piece piece, Position position) {
		// A posição da linha e coluna da matriz pieces irá receber a piece declarada no
		// metodo placePiece
		pieces[position.getRow()][position.getColumn()] = piece;
		// A antiga posição da peça irá receber a posição recebida no metodo placePiece
		piece.position = position;

	}

	// Metodo para testar se uma posição existe por uma linha e pela coluna
	public boolean positionExists(int row, int column) {
		return row >= 0 && row <= rows && column >= 0 && column < columns;
	}

	public boolean positionExists(Position position) {
		// Retornando o metado de cima, para testar se uma posição existe;
		return positionExists(position.getRow(), position.getColumn());
	}

	// MEtodo para testar se a peça existe, caso a piece(position) for diferente de
	// nulo, é porque existe uma peça
	public boolean therelsAPiece(Position position) {
		return piece(position) != null;
	}

}
