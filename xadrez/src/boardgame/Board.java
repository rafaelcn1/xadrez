package boardgame;

public class Board {

	private int rows;
	private int columns;

	private Piece[][] pieces;

	public Board(int rows, int columns) {
		if (rows < 0 || columns < 0) {
			throw new BoardException("Erro ao criar o tabuleiro! Necessário pelo menos 1 linha e 1 coluna ");
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

	// Metodo responsavel para colocar uma peça no taboleiro, onde vai receber uma
	// peça e a posição
	public void placePiece(Piece piece, Position position) {
		// Testando se existe uma peça na posição recebida;
		if (therelsAPiece(position)) {
			throw new BoardException("Já existe uma peça nessa posição! (Linha: " + position.getRow() + ", Coluna: "
					+ position.getColumn() + ")");
		}

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
		if (!positionExists(position)) {
			throw new BoardException("Posicao nao existe no tabuleiro!");
		}
		return piece(position) != null;
	}

	public Piece removePiece(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Posição não existe no tabuleiro!");
		}

		if (piece(position) == null) {
			return null;
		}
		// Salvando a peça da oisição informada em uma var auxiliar
		Piece aux = piece(position);

		// Na matrix de peças na posição informada vai receber null, onde irá remover
		pieces[position.getRow()][position.getColumn()] = null;
		return aux;
	}

}
