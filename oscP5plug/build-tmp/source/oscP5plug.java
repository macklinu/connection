import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import oscP5.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class oscP5plug extends PApplet {


// import netP5.*;

OscP5 oscP5;
// NetAddress myRemoteLocation;

public void setup() {
  size(400,400);
  // frameRate(25);
  oscP5 = new OscP5(this, 9000); // listen at port 9000
  // myRemoteLocation = new NetAddress("127.0.0.1", 12000); // not sending anything
  setupPlugs();
}

public void draw() {
  background(0);
}
/////////////////
// OSC METHODS //
/////////////////

public void setupPlugs() {
// all of the plugs
  // midi channel 1
  oscP5.plug(this,"fader1_1","/midi/cc12/1");
  oscP5.plug(this,"fader2_1","/midi/cc13/1");
  oscP5.plug(this,"fader3_1","/midi/cc14/1");
  oscP5.plug(this,"fader4_1","/midi/cc15/1");
  oscP5.plug(this,"fader5_1","/midi/cc16/1");
  oscP5.plug(this,"fader6_1","/midi/cc17/1");
  oscP5.plug(this,"fader7_1","/midi/cc18/1");
  oscP5.plug(this,"fader8_1","/midi/cc19/1");
  oscP5.plug(this,"knob1_1","/midi/cc22/1");
  oscP5.plug(this,"knob2_1","/midi/cc23/1");
  oscP5.plug(this,"knob3_1","/midi/cc24/1");
  oscP5.plug(this,"knob4_1","/midi/cc25/1");
  oscP5.plug(this,"knob5_1","/midi/cc26/1");
  oscP5.plug(this,"knob6_1","/midi/cc27/1");
  oscP5.plug(this,"knob7_1","/midi/cc28/1");
  oscP5.plug(this,"knob8_1","/midi/cc29/1");
  oscP5.plug(this,"toggle1_1","/midi/cc32/1");
  oscP5.plug(this,"toggle2_1","/midi/cc33/1");
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
  // midi channel 2
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

///////////////
// channel 1 //
///////////////

public void fader1_1(float f) {
  println(f);  
}

public void fader2_1(float f) {
  println(f);  
}

public void fader3_1(float f) {
  println(f);  
}

public void fader4_1(float f) {
  println(f);  
}

public void fader5_1(float f) {
  println(f);  
}

public void fader6_1(float f) {
  println(f);  
}

public void fader7_1(float f) {
  println(f);  
}

public void fader8_1(float f) {
  println(f);  
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

public void toggle2_1(float f) {
  println(f);  
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
    String[] appletArgs = new String[] { "oscP5plug" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
