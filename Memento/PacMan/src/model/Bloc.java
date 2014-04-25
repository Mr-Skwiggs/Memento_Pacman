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
public abstract class Bloc implements Paintable {    
    protected Point position;
    protected Color color;

    Bloc(Point position){
        this(position, Color.BLACK);
    }
    
    Bloc(Point position, Color color){
        this.position = position;
        this.color = color;
    }
    
    Bloc(Bloc c){
        this.color = c.color;
        this.position = c.position;
    }
    
    @Override
    public void paintOn(Graphics g){
        g.setColor(this.color);
        g.fillRect(position.x*Maze.SQUARE_SIZE, position.y*Maze.SQUARE_SIZE, Maze.SQUARE_SIZE,Maze.SQUARE_SIZE);
    }
    
   public boolean isOn(Point p){
        return p.x == this.position.x && p.y == this.position.y;
    }
    
    public boolean intersects(Bloc other){
        return isOn(other.position);
    }
}
