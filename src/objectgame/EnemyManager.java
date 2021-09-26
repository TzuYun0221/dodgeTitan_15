package objectgame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import userinterface.GameScreen;
import util.Resource;

public class EnemyManager {
 private List<Enemy> enemies;
 private Random random;
 
 private BufferedImage imageObstacle1, imageObstacle2, imageObstacle3, imageObstacle4;
 private MainChar mainchar;
 private GameScreen gameScreen;
 
 public EnemyManager(MainChar mainchar, GameScreen gameScreen) {
  this.gameScreen = gameScreen;
  this.mainchar = mainchar;
  enemies = new ArrayList<Enemy>();
  imageObstacle1 = Resource.getResourceImage("images/pure_titan2.png");
  imageObstacle2 = Resource.getResourceImage("images/pure_titan1.png");
//  imageObstacle3 = Resource.getResourceImage("images/images/titan.png.png");
//  imageObstacle4 = Resource.getResourceImage("images/pure_colossal3.png");
  random = new Random(); //�H��
  
  enemies.add(getRandomObstacles()); //��random��k���H���X�{��ê��
 }
 
 public void update() {
  //�o��
  for(Enemy e: enemies) {
   e.update();
   if(e.isOver() && !e.scoreGot()) {
    gameScreen.plusScore(20);
    e.setScoreGot(true);    
   }
   //�����ê���}�`
   if(e.getBound().intersects(mainchar.getBound())) {
    mainchar.setAlive(false);
   }
  }
  
  Enemy firstEnemy = enemies.get(0);
  if(firstEnemy.isOutofScreen()) {
   enemies.remove(firstEnemy);
   enemies.add(getRandomObstacles());
  }
  
 }
 
 //�e��ê��
 public void draw(Graphics g) {
  for(Enemy e: enemies) {
   e.draw(g);
  }
 }
 
 //�^���l��
 public void reset() {
  enemies.clear();
  enemies.add(getRandomObstacles());
 }
 
 //�H���X�{��ê��
 private Obstacles getRandomObstacles(){
  Obstacles obstacles;
  obstacles = new Obstacles(mainchar);
  obstacles.setX(800);
  obstacles.setY(235);
  
  int order = random.nextInt(3);
  if (order == 0) {
   obstacles.setImage(imageObstacle1);
//  } else if (order == 1) {
//   obstacles.setImage(imageObstacle1);
//  } else if (order == 2) {
//   obstacles.setImage(imageObstacle3);
  } else {
   obstacles.setImage(imageObstacle2);
  }
  return obstacles;
 }
 
 

}
