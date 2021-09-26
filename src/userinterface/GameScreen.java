package userinterface;

import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
//import java.applet.Applet;
//import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

import objectgame.EnemyManager;
import objectgame.EnemyManager_2;
import objectgame.EnemyManager_3;
import objectgame.Land;
import objectgame.MainChar;
import objectgame.Obstacles;
import util.Resource;

public class GameScreen extends JPanel implements Runnable, KeyListener {
 
 public static final float GRAVITY = 0.2f; //���U�Ӫ��t��
 public static final float GROUNDY = 370; //by pixel.
 public static final int Game_FIRST_State = 0;
 public static final int Game_PLAY_State = 1;
 public static final int Game_OVER_State = 2;
 public static boolean finish;
 public static int score;
 
 private MainChar mainchar;
 private Thread thread;
 private Land land;
 private EnemyManager em;
 private EnemyManager_2 em2;
 private EnemyManager_3 em3;
  
 private int gameState = Game_FIRST_State;
 
 private BufferedImage imageGameOver;

 
 public GameScreen() {
  mainchar = new MainChar();
  mainchar.setX(100); //�D������m, �o�Ofirst_state�ɥD������m
  mainchar.setY(345);
  thread = new Thread(this);
  land  = new Land(this);
  em = new EnemyManager(mainchar, this);
  em2 = new EnemyManager_2(mainchar, this);
  em3 = new EnemyManager_3(mainchar, this);
//  imageGameOver = Resource.getResourceImage("images/GameOver.png");
 }
 
 public void startGame() {
  thread.start(); 
  
 }
 

 @Override
 public void run() {
  while(true) {
   try {
    update();
    repaint(); 
    Thread.sleep(5); //�I���ʪ��t��
   } catch (InterruptedException e) {
    e.printStackTrace();
   }
  }
 }
 
 public void update() {
  switch(gameState) {
  case Game_PLAY_State:
   mainchar.update(); //�D�����t��
   land.update(); //�I���u��
   em.update();
   em2.update();
   em3.update();
   if(mainchar.getAlive() == false) {
    gameState = Game_OVER_State;
   new Gameoverpanel(); 
   }
   break;
  }
 }
 
 public void plusScore(int score) {
  this.score += score; 
 }
 
 
 @Override
 public void paint(Graphics g) {
  g.setColor(Color.decode("#3D3D3D")); //JPanel���C��
  g.fillRect(0, 0, getWidth(), getHeight()); //���C��

  switch(gameState) {
   case Game_FIRST_State:
    mainchar.draw(g);
    g.setFont(new Font("SansSerif", Font.BOLD, 20)); 
    g.drawString("���@�ѡA�H�̦^�Q�_�F�Q���H��t������....", 250, 100);
    g.drawString("��ENTER�}�l�C��", 250, 200);
    break;
   case Game_PLAY_State:
    land.draw(g);
    mainchar.draw(g);
    em.draw(g);
    em2.draw(g);
    em3.draw(g);
    g.setFont(new Font("SansSerif", Font.BOLD, 20));
    g.drawString("SCORE: " + String.valueOf(score), 600, 50);
    break;
   case Game_OVER_State:
    land.draw(g);
    mainchar.draw(g);
    em.draw(g);
    em2.draw(g);
    em3.draw(g);
//    g.drawImage(imageGameOver, 100, 50, null);
    g.setFont(new Font("SansSerif", Font.BOLD, 20)); 
    g.drawString("Please press Enter to play again", 250, 200);
    break;
  
  }

 }

 private void resetGame() {
  mainchar.setAlive(true);
  //�N�D���ܦ^���w�]��
  mainchar.setX(100); 
  mainchar.setY(345);
  em.reset();
  em2.reset();
  em3.reset();
  score = 0;
 }
 
 @Override
 public void keyTyped(KeyEvent e) {

 }
 
 @Override
 public void keyPressed(KeyEvent e) {
  switch(e.getKeyCode()) {
  case KeyEvent.VK_ENTER:
   if(gameState == Game_FIRST_State) {
    gameState = Game_PLAY_State;
   } else if(gameState == Game_OVER_State) {
    resetGame(); 
    gameState = Game_PLAY_State;
   }
   break;
   
  case KeyEvent.VK_UP:{
   if(gameState == Game_PLAY_State) {
    mainchar.jump();
   }
   break;
  }
 
  }

 }

 @Override
 public void keyReleased(KeyEvent e) {
  
  
 }

 
}