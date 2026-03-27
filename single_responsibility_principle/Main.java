public class Main {
    
    public static void main(String[] args) {
        Board board = new Board(3);
        
        BoardPresenter presenter = new BoardPresenter(board);
        presenter.displayBoard();
        
        System.out.println();
        
        BoardShaper shaper = new BoardShaper(3);
        System.out.println("Row indexes: " + shaper.rowIndexes());
        
        System.out.println("Values at row 0: " + board.valuesAt(shaper.rowIndexes().get(0)));
        System.out.println("Values at row 1: " + board.valuesAt(shaper.rowIndexes().get(1)));
        System.out.println("Values at row 2: " + board.valuesAt(shaper.rowIndexes().get(2)));
    }
}
