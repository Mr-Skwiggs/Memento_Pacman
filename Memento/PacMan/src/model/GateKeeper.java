/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import model.Maze.Memento;

/**
 *
 * @author 1407picaeymaex
 */
public class GateKeeper implements Serializable {
    
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
    public void saveToFile(Object o) throws IOException{
        save(o);
        FileOutputStream fos = new FileOutputStream("saved Pacman.pac");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(mementos);
        oos.close();
    }
    
    public void loadSavedFile() throws IOException, ClassNotFoundException{
        if(new File("saved Pacman.pac").exists()){
            FileInputStream fis = new FileInputStream("saved Pacman.pac");
            ObjectInputStream ois = new ObjectInputStream(fis);
            this.mementos = (List<Object>) ois.readObject();
            ois.close();
        }
    }
}
