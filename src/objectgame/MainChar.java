package objectgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import static userinterface.GameScreen.GROUNDY;
import static userinterface.GameScreen.GRAVITY;
import util.Animation;
import util.Resource;


public class MainChar {
 
 private float x = 0;
 private float y = 0;
 private float speedY = 0;
 private Animation charRun;
 private Rectangle rect;
 private boolean isAlive = true;
 
 public MainChar() {
  charRun = new Animation(200); //200�@���t�רӶ]
  charRun.addFrame(Resource.getResourceImage("images/human2.png"));
  charRun.addFrame(Resource.getResourceImage("images/human3.png"));
  rect = new Rectangle();
 }
 
 
 public void update() {
  charRun.update();
  // �D�����D�έ����t��
  if (y >= GROUNDY - charRun.getFrame().getHeight() + 30) { //�D���b�a�W��
   speedY = 0;
   y = GROUNDY - charRun.getFrame().getHeight() + 30;
  } else {    //���b�a�W��
  speedY += GRAVITY;
  y += speedY;
  }
  
  //�]�w��¶�D�����|���
  rect.x = (int) x + 20;
  rect.y = (int) y;
  rect.width = (charRun.getFrame().getWidth());
  rect.height = (charRun.getFrame().getWidth());
 }
 
 public Rectangle getBound () {
  return rect;
 }
 
 //�e�D��
 public void draw(Graphics g) {
  g.setColor(Color.black); //�]�w�C��
  g.drawImage(charRun.getFrame(), (int) x, (int) y, null); //draw the main char of each frame
 }
 
 public void jump() {
  int topY = 120; //�̰��I
  //�D���p��
  if (y + speedY >= topY) {
   speedY = -8;
   y += speedY;
  } else {
   speedY = 0;
   y += speedY;
  }
 
 }
 
 
 public float getX() {
  return x;
 }
 public void setX(float x) {
  this.x = x;
 }
 public float getY() {
  return y;
 }
 public void setY(float y) {
  this.y = y;
 } 
 public float getSpeedY() {
  return speedY;
 }
 public void setSpeedY(float speedY) {
  this.speedY = speedY;
 }
 
 //�D������
 public void setAlive(boolean alive) {
  isAlive = alive;
 }
 
 //�O���O����
 public boolean getAlive() {
  return isAlive;
 }


}
