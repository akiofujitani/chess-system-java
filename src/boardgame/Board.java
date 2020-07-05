package boardgame;

public class Board {

	private int rows;
	private int columns;
	private Piece[][] pieces;
	
	public Board(int rows, int columns) {
		if (rows < 1 || columns < 1) {
			throw new BoardException("Error creating board: there must be at least 1 row and 1 column");
		}
		this.rows = rows;						//Set value to rows
		this.columns = columns;					//Set value to columns
		pieces = new Piece[rows][columns];		//Create a matrix type Piece
	}
	
	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}
	
	public Piece piece(int row, int column) {
		if (!positionExists(row, column)) {
			throw new BoardException("Position not on the board");
		}
		return pieces[row][column];
	}
	
	public Piece piece(Position position) {								//return the matrix pieces values converted from position						
		if (!positionExists(position)) {								//check if position exists... 
			throw new BoardException("Position not on the board");		//throw exception if error
		}
		return pieces[position.getRow()][position.getColumn()];			//return the matrix pieces with row and column
	}
	
	public void placePiece(Piece piece, Position position) {			//place piece on the board
		if (thereIsAPiece(position)) {
			throw new BoardException("There is already a piece on position " + position);
		}
		pieces[position.getRow()][position.getColumn()] = piece;	
		piece.position = position;
	}
	
	public Piece removePiece(Position position) {							//remove piece from specific position
	if (!positionExists(position)) {										//exception
			throw new BoardException("Position not on the board");		
		}
		if (piece(position) == null) {										//no piece is in the position
			return null;													//return
		}
		Piece aux = piece(position);										//create a Piece aux to store the position
		aux.position = null;												//remove position value of the aux 
		pieces[position.getRow()][position.getColumn()] = null;				//remove value from pieces matrix
		return aux;															//return aux
	}
	
	private boolean positionExists(int row, int column) {					//Check position on the matrix
		return row >= 0 && row < rows && column >= 0 && column < columns;	//check if values are within the matrix size
	}
	
	public boolean positionExists(Position position) {						//Check position on the matrix
		return positionExists(position.getRow(), position.getColumn());		//call positionExists method << orz
	}
	
	public boolean thereIsAPiece(Position position) {						//Check if position exists in the matrix
		if (!positionExists(position)) {									//call positionExists method << orz
			throw new BoardException("Position not on the board");			//throw exception if error 
		}
		return piece(position) != null;										//return position not null, meaning it exists
	}
}
