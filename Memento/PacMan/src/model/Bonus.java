/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.awt.Color;
import java.awt.Point;

/**
 *
 * @author boverhae
 */
public class Bonus extends Coin{
    public Bonus(Point position){
        super(position);
        this.color = Color.GREEN;
    }
    
}
