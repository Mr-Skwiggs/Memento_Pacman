/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import model.Maze.Memento;
import view.PacmanJPanel;

/**
 *
 * @author 1407picaeymaex
 */
public class GateKeeper {
    
    private List<Object> mementos = new ArrayList<>();
    final Object Origine ;
    
    public GateKeeper(Object o){
        Origine = o ;
    }
    
    public void save(Object o){
        mementos.add(o);
    }
    public Object restore(){
        Object temp ;
        if(mementos.isEmpty())
            temp = new Memento((Memento) Origine) ;
        else{
            temp = mementos.get(mementos.size()-1);
            mementos.remove(mementos.size()-1);
        }
        return temp ;
    }
}
