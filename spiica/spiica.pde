
void setup() 
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
  
  void Load()
  {
    strokeWeight(1);
    stroke(0);
    fill(55,55,55,55);
    rect(iX, iY, iWidth, iHeight);    
    fill(30);
    text(iCaption, iX + iMargin, iY + iFontSize + iMargin - 1);     
  }
  
  boolean IsClicked()
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
  void Say(String message)
  {
    int fontSize = 16;
    PFont font = loadFont("AmericanTypewriter-20.vlw");
    textFont(font, fontSize); 
    iTextWidth = textWidth(message);        
    fill(20);
    text(message, width/2 - iTextWidth/2, 20);     
  }
  void Clear()
  {
    if(iTextWidth > 0)
    {
      strokeWeight(25);
      stroke(255);
      float x = width/2 - iTextWidth/2;
      line(x, 12, x + iTextWidth, 12);     
    }
  }
  float iTextWidth;
}

class Drawing
{
  void StartSquiggle(int x, int y)
  {
    iSquiggleStartX = x;
    iSquiggleStartY = y;    
  }
  void DrawSquiggle()
  {
    strokeWeight(5);
    stroke(30);
    line(iSquiggleStartX, iSquiggleStartY, mouseX, mouseY);
    iSquiggleStartX = mouseX;
    iSquiggleStartY = mouseY;  
  }
  
  void Save()
  {
    save("test.png");
  }
  
  void Clear()
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



void draw() 
{

}

void mouseClicked()
{
  if(saveMe.IsClicked())
  {
    drawing.Save();
    messge.say("Saved...");
  }
  if(cancelMe.IsClicked())
    drawing.Clear();
}

void mousePressed()
{
  drawing.StartSquiggle(mouseX, mouseY);
  if(!saveMe.IsClicked() && !cancelMe.IsClicked())
    message.Clear();
}

void mouseDragged() 
{
  drawing.DrawSquiggle();
}


