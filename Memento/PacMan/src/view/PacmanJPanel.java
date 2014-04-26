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
import model.Maze;

/**
 *
 * @author boverhae
 */
public class PacmanJPanel extends JPanel implements Observer {
        
    private List<Maze> mazes = new ArrayList<>(); 
   
    public PacmanJPanel(Maze maze){
        super();
        this.mazes.add(maze);
        mazes.get(0).addObserver(this);
        this.setBackground(Color.WHITE);
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()){
                    case KeyEvent.VK_UP:
                        PacmanJPanel.this.mazes.get(0).setPacmanDirection(Direction.NORTH);
                        mazes.add(new Maze(mazes.get(0)));
                        break;
                    case KeyEvent.VK_DOWN:
                        PacmanJPanel.this.mazes.get(0).setPacmanDirection(Direction.SOUTH);
                        mazes.add(new Maze(mazes.get(0)));
                        break;
                    case KeyEvent.VK_LEFT:
                        PacmanJPanel.this.mazes.get(0).setPacmanDirection(Direction.WEST);
                        mazes.add(new Maze(mazes.get(0)));
                        break;
                    case KeyEvent.VK_RIGHT:
                        PacmanJPanel.this.mazes.get(0).setPacmanDirection(Direction.EAST);
                        mazes.add(new Maze(mazes.get(0)));
                        break;
                    case KeyEvent.VK_Z:
                        if(mazes.size() > 1){
                            mazes.set(0,new Maze(mazes.get(mazes.size()-1)));
                            mazes.remove(mazes.size()-1);
                        }
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
        mazes.get(0).paintOn(g);
        
    }

    @Override
    public void update(Observable o, Object arg) {
        this.repaint();
    }
    
}
