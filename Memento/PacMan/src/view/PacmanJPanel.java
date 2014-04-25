/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import model.Direction;
import model.Maze;

/**
 *
 * @author boverhae
 */
public class PacmanJPanel extends JPanel implements Observer {
    
    private Maze maze;
    
    public PacmanJPanel(Maze maze){
        super();
        this.maze = maze;
        maze.addObserver(this);
        this.setBackground(Color.WHITE);
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()){
                    case KeyEvent.VK_UP:
                        PacmanJPanel.this.maze.setPacmanDirection(Direction.NORTH);
                        break;
                    case KeyEvent.VK_DOWN:
                        PacmanJPanel.this.maze.setPacmanDirection(Direction.SOUTH);
                        break;
                    case KeyEvent.VK_LEFT:
                        PacmanJPanel.this.maze.setPacmanDirection(Direction.WEST);
                        break;
                    case KeyEvent.VK_RIGHT:
                        PacmanJPanel.this.maze.setPacmanDirection(Direction.EAST);
                        break;
                }
            }
            @Override
            public void keyReleased(KeyEvent e){
                //PacmanJPanel.this.maze.movePacman(null);
            }
        });
        
        
        this.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                PacmanJPanel.this.requestFocusInWindow();
            }
        });
        
        this.setPreferredSize(new Dimension(maze.getHeight(),maze.getWidth()));
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        maze.paintOn(g);
        
    }

    @Override
    public void update(Observable o, Object arg) {
        this.repaint();
    }
    
}
