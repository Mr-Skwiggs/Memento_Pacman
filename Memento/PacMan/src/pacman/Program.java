/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import javax.swing.SwingUtilities;
import model.Maze;
import view.PacmanJFrame;

/**
 *
 * @author boverhae
 */
public class Program {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                Maze m = new Maze();
                PacmanJFrame f = new PacmanJFrame(m);
                f.setVisible(true);
            }
        });
    }
}
