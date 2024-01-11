/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package character;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import main.GamePanel;
import main.KeyHandler;

/**
 *
 * @author izulr
 */
public class Player extends Character {
    
    GamePanel gp;
    KeyHandler keys;
    boolean collisionOn;

    public Player(GamePanel gp, KeyHandler keys) {
        
        this.gp = gp;
        this.keys = keys;
        
        solidArea = new Rectangle(8,8,20,20);
        
        setDefaultValues();
   
    }
    public void setDefaultValues(){
        x = 100;
        y = 100;
        speed = 4;
    }
    public void update(){
         if(keys.upPressed == true){
            y -= speed;
            
        }
        else if(keys.downPressed == true){
            y += speed;
        }
        else if(keys.leftPressed == true){
            x -= speed;
        }
        else if(keys.rightPressed == true){
           x += speed;
        }
         
       
       
    }
    public void draw(Graphics2D g2){
        g2.setColor(Color.white);
        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
    }


}
