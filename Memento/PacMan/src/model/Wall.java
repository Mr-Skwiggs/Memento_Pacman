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
public class Wall extends Bloc { 
    public Wall(Point position){
        super(position, Color.BLACK);
    }
    
    public Wall(Wall w){
        super(w);
    }
}
