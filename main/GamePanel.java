/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import character.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import tile.TileManager;


public class GamePanel extends JPanel implements Runnable{
    
    //SCREEN SETTINGS
    final int originalTileSize = 16; //16x16 tile
    final int scale = 3;
    
    public final int tileSize = originalTileSize * scale; //48x48 tile and need to be puclic if you want to access from other packages
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; //768 pixels
    public final int screenHeight = tileSize * maxScreenRow; //576 pixels
    
    //FPS
    int FPS = 60;
    
    TileManager tileM = new TileManager(this);
    
    KeyHandler keys = new KeyHandler();
    Thread gameThread;
    public CollisionChecker collisionchecker = new CollisionChecker(this);
    Player player = new Player(this,keys);
    

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.red);
        this.setDoubleBuffered(true); //If set to true, all drawing will be done offscreen buffer
        this.addKeyListener(keys);
        this.setFocusable(true);
    }
    
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    

    @Override
    public void run() {
        
       double drawInterval = 1000000000/FPS; //For precise calculation -> 0.01666 seconds
       double nextDrawTime = System.nanoTime() + drawInterval;
       
       
       while(gameThread != null){ //As long thread exist, it will repeat

           //1. UPDATE: update information like chara position
           update();
           //2. DRAW: draw the screen with updated info
           repaint();
           
           try {
               double remainingTime = nextDrawTime -System.nanoTime();
               remainingTime = remainingTime/1000000;
               
               if(remainingTime < 0){
                   remainingTime = 0;
               }
               Thread.sleep((long)remainingTime);
               
               nextDrawTime += drawInterval;
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
    }
    public void update(){
       player.update();
    }
    public void paintComponent(Graphics g){
        
        super.paintComponent(g);//Super = parent of this class which JPanel
        
        Graphics2D g2 = (Graphics2D)g;
        
        tileM.draw(g2); //First Layer
        player.draw(g2);
        g2.dispose(); //Save some memory
        
        
    }
}
