/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.Maze;

/**
 *
 * @author boverhae
 */
public class PacmanJFrame extends JFrame {

    private Maze maze;
    private JPanel jpMaze;
    private JPanel jpMenu;

    public PacmanJFrame(Maze maze){
        super("Pacman");
        this.maze = maze;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        
        jpMaze = new PacmanJPanel(maze);
        this.add(jpMaze, BorderLayout.CENTER);
        jpMenu = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.add(jpMenu, BorderLayout.SOUTH);
        
       
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
    }

  
    
}
