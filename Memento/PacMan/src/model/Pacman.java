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
public class Pacman extends Character {
    private enum State {ALIVE, INVINCIBLE, DEAD};
    private static Image img_normal = new ImageIcon("images/pacman_normal.png").getImage();
    private static Image img_invincible = new ImageIcon("images/pacman_invincible.png").getImage();
    private static Image img_dead = new ImageIcon("images/pacman_dead.gif").getImage();
    
    private State state = State.ALIVE;
    private int time = 0;
    private int force = 3;
    
    public Pacman(Point position){
        super(position);
    }
    
    public Pacman(Pacman p){
        super(p);
        state = p.state;
        time = p.time;
        force = p.force;
    }
    
    public void touch(Enemy e){
        if(state == State.INVINCIBLE){
            e.moveToStart();
            e.becameZombie();
        }
        else if(state == State.ALIVE){
            if(force > 0){
                --force;
                moveToStart();
            } 
            if(force == 0){
                state = State.DEAD;
            }
        }
    }
    
    public void eat(Coin c){
        if(c instanceof Bonus){
            state = State.INVINCIBLE;
            time = 40;
        }
    }
    
    @Override
    public void move(){
        if(state != State.DEAD){
            super.move();
            if(--time==0 && state==State.INVINCIBLE){
                state = State.ALIVE;
            }
        }
    }
    
    @Override
    public void paintOn(Graphics g){
        if(state == State.ALIVE){
            g.drawImage(img_normal, position.x*Maze.SQUARE_SIZE, position.y*Maze.SQUARE_SIZE, Maze.SQUARE_SIZE, Maze.SQUARE_SIZE, null);
            g.setColor(Color.BLUE);
            g.drawString(""+force, position.x*Maze.SQUARE_SIZE+3, position.y*Maze.SQUARE_SIZE+15);
        } else if(state == State.INVINCIBLE){
            g.drawImage(img_invincible, position.x*Maze.SQUARE_SIZE, position.y*Maze.SQUARE_SIZE, Maze.SQUARE_SIZE, Maze.SQUARE_SIZE, null);
        } else if(state == State.DEAD){
            g.drawImage(img_dead, position.x*Maze.SQUARE_SIZE, position.y*Maze.SQUARE_SIZE, Maze.SQUARE_SIZE, Maze.SQUARE_SIZE, null);
        }
    }
    
    public boolean isDead(){
        return state == State.DEAD;
    }
}
