Iniciando os estudos

Uma classe abstrata, em programação orientada a objetos, é uma classe que não pode ser instanciada diretamente. 
Em vez disso, ela é projetada para ser usada como uma classe base para outras classes, conhecidas como classes derivadas ou subclasses. 
Uma classe abstrata pode conter métodos abstratos, que são métodos sem implementação na classe abstrata, mas que devem ser implementados pelas subclasses.


package boardgame;

public abstract class Piece {
	protected Position position;
	private Board board;
	
	public Piece(Board board) {
		this.board = board;
		position = null;
	}

	protected Board getBoard() { //somente classes do mesmo pacote e subclasses vão poder acessar o tabuleiro de uma peça
		return board;
	}
	
	public abstract boolean[][] possibleMoves();
	
	public boolean possibleMove(Position position) {
		return possibleMoves()[position.getRow()][position.getColumn()]; //rook methods metodo concreto que tem um metodo abstrato
	}
	
	public boolean isThereAnyPossibleMove() {
		boolean[][] mat = possibleMoves();
		for(int i=0; i<mat.length; i++) {
			for(int j=0; j<mat.length; j++) {
				if(mat[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
} 

-------------------------------
package chess;

import boardgame.Board;
import boardgame.Piece;

public abstract class ChessPiece extends Piece {
	

	private Color color;
	
	public ChessPiece(Board board, Color color) {
		super(board); //o board será criado pelo construtor da super classe (piece)
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
	
}

-----------------------------------
package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece{

	public King(Board board, Color color) {
		super(board, color);
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "K";
	}
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		return mat;
	}
}

Dessa maneira vê-se que a classe principal e abstrata "Piece" tem alguns métodos que pelo fato das outras classes extenderem ela, elas também tenham acesso.
Não é possível instanciar uma "Piece", essa classe é utilizada apenas para criar os métodos abstratos e ser assim uma classe base para outras classes




------------------------------------
Sobrecarga
É quando se tem mais de uma versão de um método, variando a lista de parâmetros
Veja que há 2 versões do método printBoard, uma para quando quer saber os possiveis movimentos e assim será pintado de azul o background desses possiveis moviemtnos
E a outra quando apenas se quer colocar na tela o tabuleiro

public static void printBoard(ChessPiece[][] pieces) {
		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j], false);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}

	private static void printPiece(ChessPiece piece, boolean background) {
		if(background) {
			System.out.print(ANSI_BLUE_BACKGROUND);
		}
		if (piece == null) {
			System.out.print("-" + ANSI_RESET );
		} else {
			if (piece.getColor() == Color.WHITE) {
				System.out.print(ANSI_WHITE + piece + ANSI_RESET);
			} else {
				System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
			}
		}
		System.out.print(" ");
	}
	

	public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j], possibleMoves[i][j]);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}