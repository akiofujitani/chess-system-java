package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();		//Instantiate a new chess match >> chess/ChessMatch
		List<ChessPiece> captured = new ArrayList<>();
		
		while (!chessMatch.getCheckMate()) {									
			try {
				UI.clearScreen();						//Clear the terminal screen
				UI.printMatch(chessMatch, captured);				//print the chess board and pieces application/UI
				System.out.println();
				System.out.print("Source: ");
				ChessPosition source = UI.readChessPosition(sc);	//instantiate a ChessPosition and reads user input for piece selection application/UI
				
				boolean[][] possibleMoves = chessMatch.possibleMoves(source);	//check possible moves of the selected piece boardgame/Piece >> ChessPiece >> Each piece
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces(), possibleMoves);	//print the board with the possible moves matrix 
				
				System.out.println();
				System.out.print("Taget: ");
				ChessPosition target = UI.readChessPosition(sc);
				
				ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
				
				if (capturedPiece != null) {
					captured.add(capturedPiece);
				}
				
				if (chessMatch.getPromoted() != null) {
					System.out.print("Enter piece for promotion (B/N/R/Q): ");
					String type = sc.nextLine();
					chessMatch.replacePromotedPiece(type);
				}
			}
			catch (ChessException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
		UI.clearScreen();
		UI.printMatch(chessMatch, captured);

	}

}
