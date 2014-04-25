/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;

/**
 *
 * @author boverhae
 */
public class Enemy extends Character {
    private static Image img_normal = new ImageIcon("images/enemy_normal.png").getImage();
    private static Image img_zombie = new ImageIcon("images/enemy_zombie.png").getImage();
    private static int ZOMBIE_TIME = 20;
    private enum State {NORMAL, ZOMBIE};
    
    private State state;
    private int time;
    
    public Enemy(Point position){
        super(position);
        becameZombie();
        setRandomDirection();
    }
    
    public Enemy(Enemy e){
        super(e);
        state = e.state;
        time = e.time;
    }
    
    @Override
    public void move(){
        super.move();
        if(--time==0 && state==State.ZOMBIE){
            this.state = State.NORMAL;
        }
    }
    
    public final void becameZombie(){
        this.state = State.ZOMBIE;
        this.time = ZOMBIE_TIME;
    }
        
    @Override
    public void paintOn(Graphics g){
        if(this.state == State.NORMAL){
            g.drawImage(img_normal, position.x*Maze.SQUARE_SIZE, position.y*Maze.SQUARE_SIZE, Maze.SQUARE_SIZE, Maze.SQUARE_SIZE, null);
        }
        if(this.state == State.ZOMBIE){
            g.drawImage(img_zombie, position.x*Maze.SQUARE_SIZE, position.y*Maze.SQUARE_SIZE, Maze.SQUARE_SIZE, Maze.SQUARE_SIZE, null);
        }
    }
}
