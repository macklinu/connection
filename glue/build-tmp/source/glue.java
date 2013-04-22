import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import org.openkinect.*; 
import org.openkinect.processing.*; 
import oscP5.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class glue extends PApplet {

// Daniel Shiffman
// Kinect Point Cloud example
// http://www.shiffman.net
// https://github.com/shiffman/libfreenect/tree/master/wrappers/java/processing





OscP5 oscP5;
Kinect kinect; // Kinect Library object

float a = 0;
float trans = 0;
boolean vividBackground = false;
float lineAmount = 0;
float mainZ = -50;

// Size of kinect image
int w, h;

// We'll use a lookup table so that we don't have to repeat the math over and over
float[] depthLookUp = new float[2048];

int skip = 8; // calculate and draw every nth pixel (default 8)
float shift = 0;

public void setup() {
  w = 640;
  h = 480;
  colorMode(HSB);
  size(w, h, P3D);
  oscP5 = new OscP5(this, 9000); // listen at port 9000
  setupPlugs();
  kinect = new Kinect(this);
  kinect.start();
  kinect.enableDepth(true);
  // We don't need the grayscale image in this example
  // so this makes it more efficient
  kinect.processDepthImage(false);

  // Lookup table for all possible depth values (0 - 2047)
  for (int i = 0; i < depthLookUp.length; i++) {
    depthLookUp[i] = rawDepthToMeters(i);
  }
}

public void draw() {
  background(0);
  fill(255);
  text("Kinect FR: " + (int)kinect.getDepthFPS() + "\nProcessing FR: " + (int)frameRate,10,16);

  // Get the raw depth as array of integers
  int[] depth = kinect.getRawDepth();

  // Translate and rotate
  translate(width/2,height/2,mainZ);
  rotateY(a);

  for(int x=0; x<w; x+=skip) {
    for(int y=0; y<h; y+=skip) {
      int offset = x+y*w;

      // Convert kinect data to world xyz coordinate
      int rawDepth = depth[offset];
      PVector v = depthToWorld(x,y,rawDepth);

      // stroke(255);
      pushMatrix();
      // Scale up by 200
      float factor = 300;
      translate(v.x*factor,v.y*factor,factor-v.z*factor);
      // Draw a point
      // point(0,0);
      if (vividBackground) stroke(30, 0, 255, v.z*factor*trans);
      else stroke(30, 0, 255, (1/v.z)*factor*trans);
      // fill(255, factor-v.z*factor/2);
      // ellipse(0, 0, v.x*factor/8,v.y*factor/8);
      line(shift - v.x*factor/2, 0, v.x*factor*lineAmount, v.y*factor*lineAmount);
      popMatrix();
    }
  }
}

// These functions come from: http://graphics.stanford.edu/~mdfisher/Kinect.html
public float rawDepthToMeters(int depthValue) {
  if (depthValue < 2047) {
    return (float)(1.0f / ((double)(depthValue) * -0.0030711016f + 3.3309495161f));
  }
  return 0.0f;
}

public PVector depthToWorld(int x, int y, int depthValue) {

  final double fx_d = 1.0f / 5.9421434211923247e+02f;
  final double fy_d = 1.0f / 5.9104053696870778e+02f;
  final double cx_d = 3.3930780975300314e+02f;
  final double cy_d = 2.4273913761751615e+02f;

  PVector result = new PVector();
  double depth =  depthLookUp[depthValue]; //rawDepthToMeters(depthValue);
  result.x = (float)((x - cx_d) * depth * fx_d);
  result.y = (float)((y - cy_d) * depth * fy_d);
  result.z = (float)(depth);
  return result;
}

public void stop() {
  kinect.quit();
  super.stop();
}

/////////////////
// OSC METHODS //
/////////////////

public void setupPlugs() {
// all of the plugs
  // midi channel 1
  oscP5.plug(this,"skip","/midi/cc12/1");
  oscP5.plug(this,"trans","/midi/cc13/1");
  oscP5.plug(this,"lineAmount","/midi/cc14/1");
  oscP5.plug(this,"fader4_1","/midi/cc15/1");
  oscP5.plug(this,"fader5_1","/midi/cc16/1");
  oscP5.plug(this,"mainZ","/midi/cc17/1");
  oscP5.plug(this,"shift","/midi/cc18/1");
  oscP5.plug(this,"rotate","/midi/cc19/1");
  oscP5.plug(this,"knob1_1","/midi/cc22/1");
  oscP5.plug(this,"knob2_1","/midi/cc23/1");
  oscP5.plug(this,"knob3_1","/midi/cc24/1");
  oscP5.plug(this,"knob4_1","/midi/cc25/1");
  oscP5.plug(this,"knob5_1","/midi/cc26/1");
  oscP5.plug(this,"knob6_1","/midi/cc27/1");
  oscP5.plug(this,"knob7_1","/midi/cc28/1");
  oscP5.plug(this,"knob8_1","/midi/cc29/1");
  oscP5.plug(this,"toggle1_1","/midi/cc32/1");
  oscP5.plug(this,"vividBackground","/midi/cc33/1");
  oscP5.plug(this,"toggle3_1","/midi/cc34/1");
  oscP5.plug(this,"toggle4_1","/midi/cc35/1");
  oscP5.plug(this,"toggle5_1","/midi/cc36/1");
  oscP5.plug(this,"toggle6_1","/midi/cc37/1");
  oscP5.plug(this,"toggle7_1","/midi/cc38/1");
  oscP5.plug(this,"toggle8_1","/midi/cc39/1");
  // midi channel 2
  oscP5.plug(this,"fader1_2","/midi/cc12/2");
  oscP5.plug(this,"fader2_2","/midi/cc13/2");
  oscP5.plug(this,"fader3_2","/midi/cc14/2");
  oscP5.plug(this,"fader4_2","/midi/cc15/2");
  oscP5.plug(this,"fader5_2","/midi/cc16/2");
  oscP5.plug(this,"fader6_2","/midi/cc17/2");
  oscP5.plug(this,"fader7_2","/midi/cc18/2");
  oscP5.plug(this,"fader8_2","/midi/cc19/2");
  oscP5.plug(this,"knob1_2","/midi/cc22/2");
  oscP5.plug(this,"knob2_2","/midi/cc23/2");
  oscP5.plug(this,"knob3_2","/midi/cc24/2");
  oscP5.plug(this,"knob4_2","/midi/cc25/2");
  oscP5.plug(this,"knob5_2","/midi/cc26/2");
  oscP5.plug(this,"knob6_2","/midi/cc27/2");
  oscP5.plug(this,"knob7_2","/midi/cc28/2");
  oscP5.plug(this,"knob8_2","/midi/cc29/2");
  oscP5.plug(this,"toggle1_2","/midi/cc32/2");
  oscP5.plug(this,"toggle2_2","/midi/cc33/2");
  oscP5.plug(this,"toggle3_2","/midi/cc34/2");
  oscP5.plug(this,"toggle4_2","/midi/cc35/2");
  oscP5.plug(this,"toggle5_2","/midi/cc36/2");
  oscP5.plug(this,"toggle6_2","/midi/cc37/2");
  oscP5.plug(this,"toggle7_2","/midi/cc38/2");
  oscP5.plug(this,"toggle8_2","/midi/cc39/2");
  // midi channel 3
  oscP5.plug(this,"fader1_3","/midi/cc12/3");
  oscP5.plug(this,"fader2_3","/midi/cc13/3");
  oscP5.plug(this,"fader3_3","/midi/cc14/3");
  oscP5.plug(this,"fader4_3","/midi/cc15/3");
  oscP5.plug(this,"fader5_3","/midi/cc16/3");
  oscP5.plug(this,"fader6_3","/midi/cc17/3");
  oscP5.plug(this,"fader7_3","/midi/cc18/3");
  oscP5.plug(this,"fader8_3","/midi/cc19/3");
  oscP5.plug(this,"knob1_3","/midi/cc22/3");
  oscP5.plug(this,"knob2_3","/midi/cc23/3");
  oscP5.plug(this,"knob3_3","/midi/cc24/3");
  oscP5.plug(this,"knob4_3","/midi/cc25/3");
  oscP5.plug(this,"knob5_3","/midi/cc26/3");
  oscP5.plug(this,"knob6_3","/midi/cc27/3");
  oscP5.plug(this,"knob7_3","/midi/cc28/3");
  oscP5.plug(this,"knob8_3","/midi/cc29/3");
  oscP5.plug(this,"toggle1_3","/midi/cc32/3");
  oscP5.plug(this,"toggle2_3","/midi/cc33/3");
  oscP5.plug(this,"toggle3_3","/midi/cc34/3");
  oscP5.plug(this,"toggle4_3","/midi/cc35/3");
  oscP5.plug(this,"toggle5_3","/midi/cc36/3");
  oscP5.plug(this,"toggle6_3","/midi/cc37/3");
  oscP5.plug(this,"toggle7_3","/midi/cc38/3");
  oscP5.plug(this,"toggle8_3","/midi/cc39/3");
}

public float oscMap(float f, float lo, float hi){
  return map(f, 0.f, 1.f, lo, hi);
}

///////////////
// channel 1 //
///////////////

public void skip(float f) {
  skip = (int) oscMap(f, 64, 8);
}

public void trans(float f) {
  trans = f;
}

public void lineAmount(float f) {
  lineAmount = oscMap(f, -1.f, 1.f);
}

public void fader4_1(float f) {
  println(f);  
}

public void fader5_1(float f) {
  println(f);  
}

public void mainZ(float f) {
  mainZ = oscMap(f, -50, 300);
}

public void shift(float f) {
  shift = oscMap(f, -400.f, 400.f)  ;
}

public void rotate(float f) {
  a = oscMap(f, 0.f, TWO_PI);
}

public void knob1_1(float f) {
  println(f);  
}

public void knob2_1(float f) {
  println(f);  
}

public void knob3_1(float f) {
  println(f);  
}

public void knob4_1(float f) {
  println(f);  
}

public void knob5_1(float f) {
  println(f);  
}

public void knob6_1(float f) {
  println(f);  
}

public void knob7_1(float f) {
  println(f);  
}

public void knob8_1(float f) {
  println(f);  
}

public void toggle1_1(float f) {
  println(f);  
}

public void vividBackground(float f) {
  if (f == 1) vividBackground = true;
  else vividBackground = false;  
}

public void toggle3_1(float f) {
  println(f);  
}

public void toggle4_1(float f) {
  println(f);  
}

public void toggle5_1(float f) {
  println(f);  
}

public void toggle6_1(float f) {
  println(f);  
}

public void toggle7_1(float f) {
  println(f);  
}

public void toggle8_1(float f) {
  println(f);
}

///////////////
// channel 2 //
///////////////

public void fader1_2(float f) {
  println(f);  
}

public void fader2_2(float f) {
  println(f);  
}

public void fader3_2(float f) {
  println(f);  
}

public void fader4_2(float f) {
  println(f);  
}

public void fader5_2(float f) {
  println(f);  
}

public void fader6_2(float f) {
  println(f);  
}

public void fader7_2(float f) {
  println(f);  
}

public void fader8_2(float f) {
  println(f);  
}

public void knob1_2(float f) {
  println(f);  
}

public void knob2_2(float f) {
  println(f);  
}

public void knob3_2(float f) {
  println(f);  
}

public void knob4_2(float f) {
  println(f);  
}

public void knob5_2(float f) {
  println(f);  
}

public void knob6_2(float f) {
  println(f);  
}

public void knob7_2(float f) {
  println(f);  
}

public void knob8_2(float f) {
  println(f);  
}

public void toggle1_2(float f) {
  println(f);  
}

public void toggle2_2(float f) {
  println(f);  
}

public void toggle3_2(float f) {
  println(f);  
}

public void toggle4_2(float f) {
  println(f);  
}

public void toggle5_2(float f) {
  println(f);  
}

public void toggle6_2(float f) {
  println(f);  
}

public void toggle7_2(float f) {
  println(f);  
}

public void toggle8_2(float f) {
  println(f);
}

///////////////
// channel 3 //
///////////////

public void fader1_3(float f) {
  println(f);  
}

public void fader2_3(float f) {
  println(f);  
}

public void fader3_3(float f) {
  println(f);  
}

public void fader4_3(float f) {
  println(f);  
}

public void fader5_3(float f) {
  println(f);  
}

public void fader6_3(float f) {
  println(f);  
}

public void fader7_3(float f) {
  println(f);  
}

public void fader8_3(float f) {
  println(f);  
}

public void knob1_3(float f) {
  println(f);  
}

public void knob2_3(float f) {
  println(f);  
}

public void knob3_3(float f) {
  println(f);  
}

public void knob4_3(float f) {
  println(f);  
}

public void knob5_3(float f) {
  println(f);  
}

public void knob6_3(float f) {
  println(f);  
}

public void knob7_3(float f) {
  println(f);  
}

public void knob8_3(float f) {
  println(f);  
}

public void toggle1_3(float f) {
  println(f);  
}

public void toggle2_3(float f) {
  println(f);  
}

public void toggle3_3(float f) {
  println(f);  
}

public void toggle4_3(float f) {
  println(f);  
}

public void toggle5_3(float f) {
  println(f);  
}

public void toggle6_3(float f) {
  println(f);  
}

public void toggle7_3(float f) {
  println(f);  
}

public void toggle8_3(float f) {
  println(f);
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "glue" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
