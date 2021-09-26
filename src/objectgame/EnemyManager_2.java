package objectgame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import userinterface.GameScreen;
import util.Resource;

public class EnemyManager_2 {
 private List<Enemy> enemies_2;
 private Random random;
 
 private BufferedImage imageObstacle1, imageObstacle2;
 private MainChar mainchar;
 private GameScreen gameScreen;
 
 public EnemyManager_2(MainChar mainchar, GameScreen gameScreen) {
  this.gameScreen = gameScreen;
  this.mainchar = mainchar;
  enemies_2 = new ArrayList<Enemy>();
  imageObstacle1 = Resource.getResourceImage("images/titan.png");
  imageObstacle2 = Resource.getResourceImage("images/titan.png");

  random = new Random();
  enemies_2.add(getRandomObstacles());
 }
 
 
 public void update() {
  for(Enemy e: enemies_2) {
   e.update();
   if(e.isOver() && !e.scoreGot()) {
    gameScreen.plusScore(25);
    e.setScoreGot(true);    
   }
   if(e.getBound2().intersects(mainchar.getBound()) || e.getBound2_2().intersects(mainchar.getBound())) {
    mainchar.setAlive(false);
   }
  }
  Enemy firstEnemy = enemies_2.get(0);
  if(firstEnemy.isOutofScreen()) {
   enemies_2.remove(firstEnemy);
   enemies_2.add(getRandomObstacles());
  }
  
 }
 
 public void draw(Graphics g) {
  for(Enemy e: enemies_2) {
   e.draw(g);
  }
 }
  
 public void reset() {
  enemies_2.clear();
  enemies_2.add(getRandomObstacles());
 }
 
 private Obstacles getRandomObstacles(){
  Obstacles obstacles;
  obstacles = new Obstacles(mainchar);
  obstacles.setX(4900);
  obstacles.setY(235);
  
  int order = random.nextInt(2);
  if (order == 0) {
   obstacles.setImage(imageObstacle2);
  } else {
   obstacles.setImage(imageObstacle1);
  } 
  return obstacles;
  
  
 }

}