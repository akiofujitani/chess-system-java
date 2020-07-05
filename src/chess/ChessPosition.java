package chess;

import boardgame.Position;

public class ChessPosition {

	private char column;
	private int row;
	
	public ChessPosition(char column, int row) {						//Constructor sets the column and row to chess type position
		if (column < 'a' || column > 'h' || row < 1 || row > 8) {		// error checking values must be between range
			throw new ChessException("Error instantiating ChessPosition. Valid values are from a1 to h8.");	//throw exception
		}
		this.column = column;
		this.row = row;
	}

	public char getColumn() {
		return column;
	}
	
	public int getRow() {
		return row;
	}
	
	protected Position toPosition() {									//Convert chess position to matrix position
		return new Position(8 - row, column - 'a');						//return values converted in matrix
	}
	
	protected static ChessPosition fromPosition(Position position) {		//translate chess position to matrix position
		return new ChessPosition((char) ('a' - position.getColumn()), 8 - position.getRow());	//instantiate and return a new chess position value
	}
	
	@Override
	public String toString() {
		return "" + column + row;										//start with "" to make the compiler allow concatenated values
	}
}
