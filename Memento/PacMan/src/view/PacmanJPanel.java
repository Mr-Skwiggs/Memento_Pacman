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
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import model.Direction;
import model.GateKeeper;
import model.Maze;

/**
 *
 * @author boverhae
 */
public class PacmanJPanel extends JPanel implements Observer {
        
    private Maze maze ;
    private GateKeeper gk ;
   
    public PacmanJPanel(Maze m){
        super();
        maze = m ;
        gk = new GateKeeper(maze);
        maze.addObserver(this);
        this.setBackground(Color.WHITE);
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e) {
                maze.stopTimer();
                switch(e.getKeyCode()){
                    case KeyEvent.VK_UP:
                        maze.setPacmanDirection(Direction.NORTH);
                        gk.save(maze);
                        break;
                    case KeyEvent.VK_DOWN:
                        maze.setPacmanDirection(Direction.SOUTH);
                        gk.save(maze);
                        break;
                    case KeyEvent.VK_LEFT:
                        maze.setPacmanDirection(Direction.WEST);
                        gk.save(maze);
                        break;
                    case KeyEvent.VK_RIGHT:
                        maze.setPacmanDirection(Direction.EAST);
                        gk.save(maze);
                        break;
                    case KeyEvent.VK_Z:
                            maze.deleteObserver(PacmanJPanel.this);
                            maze = gk.restore(maze);
                            maze.addObserver(PacmanJPanel.this);
                        break;
                }
                maze.startTimer();
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
