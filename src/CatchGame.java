
import java.awt.Color;
import java.util.ArrayList;

/**
 * This class manages the interactions between the different pieces of the game:
 * the Board, the Daleks, and the Doctor. It determines when the game is over
 * and whether the Doctor won or lost.
 */
public class CatchGame {

    /**
     * Instance variables go up here Make sure to create a Board, 3 Daleks, and
     * a Doctor
     */
    private Board b;
    private Dalek one;
    private Dalek two;
    private Dalek three;
    private Doctor doctor;
    private int win = 3;
    private int lose = 1;

    /**
     * The constructor for the game. Use it to initialize your game variables.
     * (create people, set positions, etc.)
     */
    public CatchGame() {
        //create 12x12 board
        b = new Board(12, 12);
        //create doctor in random position
        doctor = new Doctor((int) (Math.random() * 12), (int) (Math.random() * 12));
        //create 3 daleks in random positions
        one = new Dalek((int) (Math.random() * 12), (int) (Math.random() * 12));
        two = new Dalek((int) (Math.random() * 12), (int) (Math.random() * 12));
        three = new Dalek((int) (Math.random() * 12), (int) (Math.random() * 12));

    }

    /**
     * The playGame method begins and controls a game: deals with when the user
     * selects a square, when the Daleks move, when the game is won/lost.
     */
    public void playGame() {
        //place down the doctor and daleks
        b.putPeg(Color.GREEN, doctor.getRow(), doctor.getCol());

        b.putPeg(Color.BLACK, one.getRow(), one.getCol());
        b.putPeg(Color.BLACK, two.getRow(), two.getCol());
        b.putPeg(Color.BLACK, three.getRow(), three.getCol());
        
        

        //play until either the doctor is caught or the daleks have all crashed into each other
        while (win > 0 && lose > 0) {
            
            Coordinate click = b.getClick();
            
            //DOCTOR'S TURN
            //getting where the player clicks
            int clickRow = click.getRow();
            int clickCol = click.getCol();
            //remove the doctor peg
            b.removePeg(doctor.getRow(), doctor.getCol());
            //move the doctor
            doctor.move(clickRow, clickCol);
            //replace the doctor
            b.putPeg(Color.GREEN, doctor.getRow(), doctor.getCol());
            
            //DALEK'S TURN
            //remove the pegs and move the positions of the daleks
            if (one.hasCrashed() == false) {
                b.removePeg(one.getRow(), one.getCol());
                one.advanceTowards(doctor);
                
            }
            if (two.hasCrashed() == false) {
                b.removePeg(two.getRow(), two.getCol());
                two.advanceTowards(doctor);   
            }
            if (three.hasCrashed() == false) {
                b.removePeg(three.getRow(), three.getCol());
                three.advanceTowards(doctor);   
            }
            //place down pegs in new positions
            if (one.hasCrashed() == false) {
            b.putPeg(Color.BLACK, one.getRow(), one.getCol());
            }
            if (two.hasCrashed() == false) {
            b.putPeg(Color.BLACK, two.getRow(), two.getCol());
            }
            if (three.hasCrashed() == false) {
            b.putPeg(Color.BLACK, three.getRow(), three.getCol());
            }
        
   
            //DOCTOR CAUGHT DETECTION
            if(doctor.getRow() == one.getRow() && doctor.getCol() == two.getCol()){
                b.putPeg(Color.yellow, doctor.getRow(), doctor.getCol());
                b.putPeg(Color.yellow, one.getRow(), one.getCol());
                lose--;
            }
            if(doctor.getRow() == two.getRow() && doctor.getCol() == two.getCol()){
                b.putPeg(Color.yellow, doctor.getRow(), doctor.getCol());
                b.putPeg(Color.yellow, two.getRow(), two.getCol());
                lose--;
            }
            if(doctor.getRow() == three.getRow() && doctor.getCol() == three.getCol()){
                b.putPeg(Color.yellow, doctor.getRow(), doctor.getCol());
                b.putPeg(Color.yellow, three.getRow(), three.getCol());
                lose--;
            }

            //DALEK CRASH DETECTION
            if (one.getRow() == two.getRow() && one.getCol() == two.getCol()) {
                if(one.hasCrashed() == false || two.hasCrashed() == false){
                    win = win -2;
                    }
                one.crash();
                b.putPeg(Color.red, one.getRow(), one.getCol());
                two.crash();              
                b.putPeg(Color.red, two.getRow(), two.getCol());

            } 
            if (one.getRow() == three.getRow() && one.getCol() == three.getCol()) {
                if(one.hasCrashed() == false || three.hasCrashed() == false){
                    win = win -2;
                    }
                one.crash();
                b.putPeg(Color.red, one.getRow(), one.getCol());
                three.crash();
                b.putPeg(Color.red, three.getRow(), three.getCol());
                
            } 
            if (two.getRow() == three.getRow() && two.getCol() == three.getCol()) {
                if(two.hasCrashed() == false || three.hasCrashed() == false){
                    win = win -2;
                    }
                two.crash();
                b.putPeg(Color.red, two.getRow(), two.getCol());
                three.crash();
                b.putPeg(Color.red, three.getRow(), three.getCol());
                
            }
        }
            
        //if all daleks crashed display a win message
        if (win <= 0) {
            b.displayMessage("Congratulations! You won!");

        }
        //if doctor is caught display a lose message
        if (lose <= 0) {
            b.displayMessage("You Lost. Try Again!");
        }

    }
}

