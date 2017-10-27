
import java.awt.Color;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jetkeo
 */
public class BoardExample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Board board = new Board(8, 8);
        //put down peg
        board.putPeg(Color.GREEN, 3, 5);
        board.putPeg(Color.BLUE, 1, 2);
        //remove a peg
        board.removePeg(3, 5);
        //message
        board.displayMessage("Hello");

        while (true) {
            //recieve a click
            Coordinate click = board.getClick();
            int clickRow = click.getRow();
            int clickCol = click.getCol();
            board.putPeg(Color.BLACK, clickRow, clickCol);

        }
    }
}
