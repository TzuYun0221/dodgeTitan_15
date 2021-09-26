package objectgame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import userinterface.GameScreen;
import util.Resource;

public class EnemyManager_3 {
 private List<Enemy> enemies_3;
 private Random random;
 
 private BufferedImage imageObstacle1, imageObstacle2;
 private MainChar mainchar;
 private GameScreen gameScreen;
 
 public EnemyManager_3(MainChar mainchar, GameScreen gameScreen) {
  this.gameScreen = gameScreen;
  this.mainchar = mainchar;
  enemies_3 = new ArrayList<Enemy>();
  imageObstacle1 = Resource.getResourceImage("images/sky_titan.png");
  imageObstacle2 = Resource.getResourceImage("images/sky_titan.png");

  random = new Random();
  enemies_3.add(getRandomObstacles());
 }
 
 
 public void update() {
  for(Enemy e: enemies_3) {
   e.update();
   if(e.isOver() && !e.scoreGot()) {
    gameScreen.plusScore(20);
    e.setScoreGot(true);    
   }
   if(e.getBound3().intersects(mainchar.getBound())) {
    mainchar.setAlive(false);
   }
  }
  Enemy firstEnemy = enemies_3.get(0);
  if(firstEnemy.isOutofScreen()) {
   enemies_3.remove(firstEnemy);
   enemies_3.add(getRandomObstacles());
  }
  
 }
 
 public void draw(Graphics g) {
  for(Enemy e: enemies_3) {
   e.draw(g);
  }
 }
 
 public void reset() {
  enemies_3.clear();
  enemies_3.add(getRandomObstacles());
 }
 
 private Obstacles getRandomObstacles(){
  Obstacles obstacles;
  obstacles = new Obstacles(mainchar);
  obstacles.setX(1970);
  obstacles.setY(30);
  
  int order = random.nextInt(2);
  if (order == 0) {
   obstacles.setImage(imageObstacle2);
  } else {
   obstacles.setImage(imageObstacle1);
  } 
  return obstacles;
  
  
 }

}