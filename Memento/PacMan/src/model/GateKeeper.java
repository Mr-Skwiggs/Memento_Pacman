/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import view.PacmanJPanel;

/**
 *
 * @author 1407picaeymaex
 */
public class GateKeeper {
    
    private List<Maze> mazes = new ArrayList<>();
    final Maze Origine ;
    
    public GateKeeper(Maze m){
        Origine = new Maze(m) ;
    }
    
    public void save(Maze m){
        mazes.add(new Maze(m));
    }
    public Maze restore(Maze m){
        Maze tempMaze ;
        if(mazes.isEmpty())
            tempMaze =  new Maze(Origine) ;
        else{
            tempMaze = mazes.get(mazes.size()-1);
            mazes.remove(mazes.size()-1);
        }
        return tempMaze ;
    }
}
