/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Random;
import javax.swing.Timer;

/**
 *
 * @author boverhae
 */
public class Maze extends Observable implements Paintable, ActionListener {
    static final Random GENERATOR = new Random();
    static final int SQUARE_SIZE = 20;
    static final int PROB_DIRECTION_CHANGE = 10;
    static final int TURN_TIME = 200;
    
    private Pacman pacman;
    private List<Enemy> enemies = new ArrayList<>();
    private List<Wall> walls = new ArrayList<>();
    private List<Coin> coins = new ArrayList<>();
    
    private boolean win = false;
    private boolean loose = false;
    
    private int width;
    private int height;
    
    public Maze(){
        buildMap(getMap());
        //execute toutes les 10ms la méthode actionPerformed
        Timer t = new Timer(TURN_TIME, this);
        t.setRepeats(true);
        t.start(); 
    }

    private int[][] getMap() {
        //0 : rien
        //1 : wall
        //2 : pacman
        //3 : enemy
        //4 : coin
        //5 : bonus coin
        int[][] map = 
        {
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,4,4,4,4,4,4,4,4,4,4,1,4,4,4,4,4,4,4,4,4,4,1},
            {1,5,1,1,1,4,1,1,1,1,4,1,4,1,1,1,1,4,1,1,1,5,1},
            {1,4,1,1,1,4,1,1,1,1,4,1,4,1,1,1,1,4,1,1,1,4,1},
            {1,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,1},
            {1,4,1,1,1,4,1,4,1,1,1,1,1,1,1,4,1,4,1,1,1,4,1},
            {1,4,4,4,4,4,1,4,1,1,1,1,1,1,1,4,1,4,4,4,4,4,1},
            {1,1,1,1,1,4,1,4,4,4,4,1,4,4,4,4,1,4,1,1,1,1,1},
            {1,1,1,1,1,4,1,1,1,1,4,1,4,1,1,1,1,4,1,1,1,1,1},
            {1,1,1,1,1,4,1,4,4,4,4,4,4,4,4,4,1,4,1,1,1,1,1},
            {1,1,1,1,1,4,1,4,1,0,0,0,0,0,1,4,1,4,1,1,1,1,1},
            {1,4,4,4,4,4,4,4,1,3,3,0,3,3,1,4,4,4,4,4,4,4,1},
            {1,1,1,1,1,4,1,4,1,1,1,1,1,1,1,4,1,4,1,1,1,1,1},
            {1,1,1,1,1,4,1,4,4,4,4,4,4,4,4,4,1,4,1,1,1,1,1},
            {1,1,1,1,1,4,1,4,1,1,1,1,1,1,1,4,1,4,1,1,1,1,1},
            {1,1,1,1,1,4,1,4,1,1,1,1,1,1,1,4,1,4,1,1,1,1,1},
            {1,4,4,4,4,4,4,4,4,4,4,1,4,4,4,4,4,4,4,4,4,4,1},
            {1,4,1,1,1,4,1,1,1,1,4,1,4,1,1,1,1,4,1,1,1,4,1},
            {1,5,4,4,1,4,4,4,4,4,4,2,4,4,4,4,4,4,1,4,4,5,1},
            {1,1,1,4,1,4,1,4,1,1,1,1,1,1,1,4,1,4,1,4,1,1,1},
            {1,4,4,4,4,4,1,4,4,4,4,1,4,4,4,4,1,4,4,4,4,4,1},
            {1,4,1,1,1,1,1,1,1,1,4,1,4,1,1,1,1,1,1,1,1,4,1},
            {1,4,1,1,1,1,1,1,1,1,4,1,4,1,1,1,1,1,1,1,1,4,1},
            {1,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
        };
        return map; 
    }

    private void buildMap(int[][] map) {
        this.width = map.length;
        this.height = map[0].length;
        for(int line = 0; line<map.length; ++line){
            for(int column = 0; column<map[line].length; ++column){
                switch(map[line][column]){
                    case 1: 
                        walls.add(new Wall(new Point(column,line)));
                        break;
                    case 2:
                        pacman = new Pacman(new Point(column,line));
                        break;
                    case 3:
                        enemies.add(new Enemy(new Point(column,line)));
                        break;
                    case 4:
                        coins.add(new Coin(new Point(column,line)));
                        break;   
                    case 5:
                        coins.add(new Bonus(new Point(column,line)));
                        break;
                }
            }
        }
    }
    
    @Override
    public void paintOn(Graphics g){
        for(Coin c : coins){
            c.paintOn(g);
        }
        if(pacman!=null){
            pacman.paintOn(g);
        }
        for(Enemy e : enemies){
            e.paintOn(g);
        }
        for(Wall w : walls){
            w.paintOn(g);
        } 
        if(win){
            g.setColor(Color.RED);
            g.fillRect(50, 50, 100, 100);
            g.setColor(Color.YELLOW);
            g.drawString("YOU WIN !", 70,100);
        } else if(loose){
            g.setColor(Color.RED);
            g.fillRect(50, 50, 100, 100);
            g.setColor(Color.YELLOW);
            g.drawString("YOU LOOSE !", 60,100);
        }
    }
    
    public void movePacman(Direction dir){
        for(Wall w : walls)
            if(pacman.isAboutToIntersect(w))
                pacman.setDirection(null);
        
        pacman.move();
        this.manageCollisions();
        this.setChanged();
        this.notifyObservers();
    }
    
    public List<Direction> getPossibleDirections(Character c){
        List<Direction> possible = new ArrayList();
        possible.addAll(Arrays.asList(Direction.values()));
        for(Wall w : walls){
            if(new Point(c.position.x, c.position.y-1).equals(w.position)){
                possible.remove(Direction.NORTH);
            } else if(new Point(c.position.x, c.position.y+1).equals(w.position)){
                possible.remove(Direction.SOUTH);
            } else if(new Point(c.position.x-1, c.position.y).equals(w.position)){
                possible.remove(Direction.WEST);
            } else if(new Point(c.position.x+1, c.position.y).equals(w.position)){
                possible.remove(Direction.EAST);
            }
        }
        return possible;
    }
    
    public void setPacmanDirection(Direction d){
        if(getPossibleDirections(pacman).contains(d))
            pacman.setDirection(d);
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        if(!win && !loose){
            //ennemis un peu cons mais bon...
            movePacman(pacman.getDirection());
            for(Enemy e : enemies){ 
                List<Direction> possible = getPossibleDirections(e);
                //si on peut garder la direction actuelle
                if(possible.contains(e.getDirection())){
                    //on a un peu de chance de changer de direction
                    if(GENERATOR.nextInt(PROB_DIRECTION_CHANGE)==0){
                        e.setRandomDirection(possible);
                    }
                } else {
                    //si on ne peut pas garder la direction actuelle,
                    //on change
                    e.setRandomDirection(possible);
                }
                e.move();
                manageCollisions();
            }

            if(coins.isEmpty()){
                win = true;
            } else if(pacman.isDead()){
                loose = true;
            }

            Maze.this.setChanged();
            Maze.this.notifyObservers();
        }
    }

    private void manageCollisions() {
        for(Enemy e : enemies){
            if(pacman.intersects(e)){
                pacman.touch(e);
            }            
        }
        for(int i = 0; i<coins.size(); ++i){
            if(coins.get(i).isOn(pacman.position)){
                pacman.eat(coins.get(i));
                coins.remove(coins.get(i));
                break;
            }
        }
    }
    
    public int getWidth(){
        return width*SQUARE_SIZE;
    }
    
    public int getHeight(){
        return height*SQUARE_SIZE;
    }
}
