import processing.core.*; 
import processing.xml.*; 

import java.applet.*; 
import java.awt.*; 
import java.awt.image.*; 
import java.awt.event.*; 
import java.io.*; 
import java.net.*; 
import java.text.*; 
import java.util.*; 
import java.util.zip.*; 
import java.util.regex.*; 

public class spiica extends PApplet {


public void setup() 
{
  background(255);
  smooth();
  size(600,600);


  saveMe = new Button("Save", 530, 580);
  cancelMe = new Button("Clear", 480, 580);
  drawing = new Drawing();
  message = new Message();
  message.Say("Ready to start drawing...");
}

Button saveMe;
Button cancelMe;
Drawing drawing;
Message message;


class Button
{
  Button(String caption, int x, int y)
  {
    iCaption = caption;
    iFontSize = 16;
    PFont font = loadFont("AmericanTypewriter-20.vlw");
    textFont(font, iFontSize); 
    iMargin = 3;
    iX = x;
    iY = y - iFontSize;
    iWidth = textWidth(iCaption) + 2 * iMargin;        
    iHeight = iFontSize + 2 * iMargin;
    Load();
  }
  
  public void Load()
  {
    strokeWeight(1);
    fill(55,55,55,55);
    rect(iX, iY, iWidth, iHeight);    
    fill(30);
    text(iCaption, iX + iMargin, iY + iFontSize + iMargin - 1);     
  }
  
  public boolean IsClicked()
  {
    if(mouseX >= iX && mouseX <= iX+iWidth)
    {
     if(mouseY >= iY && mouseY <= iY+iHeight)
     {
       return true;
     }
    }
   return false;
 }
  int iFontSize;
  String iCaption;
  int iMargin;
  int iX;
  int iY;
  float iWidth;
  float iHeight;
}

class Message
{
  public void Say(String message)
  {
    int fontSize = 16;
    PFont font = loadFont("AmericanTypewriter-20.vlw");
    textFont(font, fontSize); 
    iTextWidth = textWidth(message);        
    fill(20);
    text(message, width/2 - iTextWidth/2, 20);     
  }
  public void Clear()
  {
    if(iTextWidth > 0)
    {
      strokeWeight(25);
      stroke(255);
      float x = width/2 - iTextWidth/2;
      line(x, 10, x + iTextWidth, 10);     
    }
  }
  float iTextWidth;
}

class Drawing
{
  public void StartSquiggle(int x, int y)
  {
    iSquiggleStartX = x;
    iSquiggleStartY = y;    
  }
  public void DrawSquiggle()
  {
    strokeWeight(5);
    stroke(30);
    line(iSquiggleStartX, iSquiggleStartY, mouseX, mouseY);
    iSquiggleStartX = mouseX;
    iSquiggleStartY = mouseY;  
  }
  
  
  public void Clear()
  {
    background(255);
    smooth();
    size(600,600);
    saveMe.Load();
    cancelMe.Load();
  }
  
  int iSquiggleStartX;
  int iSquiggleStartY;
}



public void draw() 
{

}

public void mouseClicked()
{
  if(saveMe.IsClicked())
    message.Say("Sorry, this doesn't work just yet.");
  if(cancelMe.IsClicked())
    drawing.Clear();
}

public void mousePressed()
{
  drawing.StartSquiggle(mouseX, mouseY);
  if(!saveMe.IsClicked() && !cancelMe.IsClicked())
    message.Clear();
}

public void mouseDragged() 
{
  drawing.DrawSquiggle();
}



  static public void main(String args[]) {
    PApplet.main(new String[] { "--bgcolor=#ffffff", "spiica" });
  }
}
