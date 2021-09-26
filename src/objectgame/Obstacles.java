package objectgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;

import util.Resource;


public class Obstacles extends Enemy {
 
 public BufferedImage image;
 public int posX, posY;
 public Rectangle rect1;
 public Rectangle rect2;
 public Rectangle rect2_2;
 public Rectangle rect3;
 public MainChar mainchar;
 public boolean scoreGot = false;
 
 public Obstacles (MainChar mainchar) {
  this.mainchar = mainchar;
//  image = Resource.getResourceImage("images/titan.png");
//  posX = 600;
//  posY = 380;
  rect1 = new Rectangle();
  rect2 = new Rectangle();
  rect2_2 = new Rectangle();
  rect3 = new Rectangle();


 }
 
 public void update() {
  posX -= 2; //��ê����������
  //�@�륨�H�j�p
  rect1.x = posX + 55; 
  rect1.y = posY + 25; 
  rect1.width = image.getWidth() -75;
  rect1.height = image.getHeight();
  
  //���𥨤H�ج[
  rect2.x = posX + 60; 
  rect2.y = posY + 120; 
  rect2.width = image.getWidth() - 50;
  rect2.height = image.getHeight() - 120;
  
  rect2_2.x = posX + 150;
  rect2_2.y = posY + 70;
  rect2_2.width = image.getWidth() - 160;
  rect2_2.height = image.getHeight() - 120;
  
  //�Ť����H�ج[
  rect3.x = posX + 55; 
  rect3.y = posY + 60; //�Ϥ����_�I�b���U��
  rect3.width = image.getWidth() - 70;
  rect3.height = image.getHeight() - 80;

  
 }

 
 //����ê���j�p
 @Override
 public Rectangle getBound() {
  return rect1;
 }
 
 
 public Rectangle getBound2() {
  return rect2;
 } 
 
 public Rectangle getBound2_2() {
  return rect2_2;
 }
 
 public Rectangle getBound3() {
  return rect3;
 }
 
 //�s��
 @Override
 public void draw(Graphics g) { 
  g.drawImage(image, posX, posY, null);
  
 }
 
 public void setX(int x) {
  posX = x;
 }
 public void setY(int y) {
  posY = y;
 }
 public void setImage(BufferedImage image) {
  this.image = image;
 }
 
 //���b�ù��W
 @Override
 public boolean isOutofScreen() { 
  return (posX + image.getWidth() < 0);
 }
 
 //��ê���M�D���ۼ�
 @Override
 public boolean isOver() {
  return (mainchar.getX() > posX);   
   
 }
 
 //����
 @Override
 public boolean scoreGot() {
  return scoreGot;
 }
 
 //���o����
 @Override
 public void setScoreGot(boolean scoreGot) {
  this.scoreGot = scoreGot;
 }


}