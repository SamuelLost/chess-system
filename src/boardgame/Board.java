package boardgame;

public class Board {
    private int rows;
    private int columns;
    private Piece[][] pieces;

    public Board(int rows, int columns) {
        if (rows < 1 || columns < 1) {
            throw new BoardException("Error creating board: there must be at least 1 row and 1 column");
        }
        this.rows = rows;
        this.columns = columns;
        this.pieces = new Piece[rows][columns];
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    //Retorna a peça na posição (row, column)
    public Piece piece(int row, int column) {
        if(!positionExists(row, column)) {
            throw new BoardException("Position not on the board");
        }
        return pieces[row][column];
    }

    //Retorna a peça na posição (position)
    public Piece piece(Position position) {
        if(!positionExists(position)) {
            throw new BoardException("Position not on the board");
        }
        return pieces[position.getRow()][position.getColumn()];
    }

    //Coloca a peça na posição (row, column)
    public void placePiece(Piece piece, Position position) {
        //Verifica se a posição existe
        if(thereIsAPiece(position)) {
            throw new BoardException("There is already a piece on position " + position);
        }
        pieces[position.getRow()][position.getColumn()] = piece;
        piece.position = position;
    }

    //Remove a peça na posição (position)
    public Piece removePiece(Position position) {
        if(!positionExists(position)) {
            throw new BoardException("Position not on the board");
        }
        if(piece(position) == null) {
            return null;
        }
        Piece aux = piece(position);
        aux.position = null;
        pieces[position.getRow()][position.getColumn()] = null;
        return aux;
    } 

    //Verifica se a posição (row, column) existe
    private boolean positionExists(int row, int column) {
        return row >= 0 && row < rows && column >= 0 && column < columns;
    }

    //Verifica se a posição (position) existe
    public boolean positionExists(Position position) {
        return positionExists(position.getRow(), position.getColumn());
    }

    //Verifica se existe uma peça na posição (row, column)
    public boolean thereIsAPiece(Position position) {
        if (!positionExists(position)) {
            throw new BoardException("Position not on the board");
        }
        return piece(position) != null;
    }


}
