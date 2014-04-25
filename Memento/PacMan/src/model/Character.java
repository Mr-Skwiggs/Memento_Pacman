/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.awt.Color;
import java.awt.Point;
import java.util.List;

/**
 *
 * @author boverhae
 */
public abstract class Character extends Bloc{

    private int dirX = 0;
    private int dirY = 0;
    private final Point start_point;
    
    private Direction direction = null;

    public Character(Point position){
        super(position);
        this.start_point = new Point(position);
    }

    public void move(){
        this.position.x += dirX;
        this.position.y += dirY;
    }
    
    public void setDirection(Direction d){
        this.direction = d;
        if(direction == null) {
            dirX=0;  dirY=0;
        } else {
            switch(d){
                case NORTH : dirX=0;  dirY=-1; break;
                case SOUTH : dirX=0;  dirY=1;  break;
                case EAST  : dirX=1;  dirY=0;  break;
                case WEST  : dirX=-1; dirY=0;  break;
            }
        }
    }
    
    public Direction getDirection(){
        return direction;
    }
    
    public void setRandomDirection(){
        int dir = Maze.GENERATOR.nextInt(Direction.values().length);
        setDirection(Direction.values()[dir]);
    }
    
    public void setRandomDirection(List<Direction> possibleDirections) {
        int i = Maze.GENERATOR.nextInt(possibleDirections.size());
        setDirection(possibleDirections.get(i));
    }
    
    public boolean isAboutToIntersect(Bloc other){
        return other.isOn(new Point(position.x+dirX,position.y+dirY));
    }
    
    public void moveToStart() {
        this.position = new Point(start_point);
    }
}
