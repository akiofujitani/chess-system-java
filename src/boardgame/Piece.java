package boardgame;

public class Piece {

	protected Position position;
	private Board board;
	
	public Piece(Board board) {
		this.board = board;
		position = null;		//This is not necessary, Java already set null value to objects when no value is attributed
	}

	protected Board getBoard() {
		return board;
	}
}
