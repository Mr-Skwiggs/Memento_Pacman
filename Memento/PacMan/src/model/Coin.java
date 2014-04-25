/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author boverhae
 */
public class Coin extends Bloc {
    public Coin(Point position){
        super(position,Color.LIGHT_GRAY);
    }
    
    public Coin(Coin c){
        super(c);
    }
    
    @Override
    public void paintOn(Graphics g){
        g.setColor(color);
        g.fillOval(position.x*Maze.SQUARE_SIZE+Maze.SQUARE_SIZE/4, position.y*Maze.SQUARE_SIZE+Maze.SQUARE_SIZE/4, Maze.SQUARE_SIZE-Maze.SQUARE_SIZE/2, Maze.SQUARE_SIZE-Maze.SQUARE_SIZE/2);
        g.setColor(Color.BLACK);
        g.drawOval(position.x*Maze.SQUARE_SIZE+Maze.SQUARE_SIZE/4, position.y*Maze.SQUARE_SIZE+Maze.SQUARE_SIZE/4, Maze.SQUARE_SIZE-Maze.SQUARE_SIZE/2, Maze.SQUARE_SIZE-Maze.SQUARE_SIZE/2);
    }
}
