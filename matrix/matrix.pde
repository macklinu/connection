// Daniel Shiffman
// Kinect Point Cloud example
// http://www.shiffman.net
// https://github.com/shiffman/libfreenect/tree/master/wrappers/java/processing

import org.openkinect.*;
import org.openkinect.processing.*;

// Kinect Library object
Kinect kinect;

float a = 0;

// Size of kinect image
int w, h;


// We'll use a lookup table so that we don't have to repeat the math over and over
float[] depthLookUp = new float[2048];

void setup() {
  w = 640;
  h = 480;
  size(displayWidth,displayHeight,P3D);
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

void draw() {

  background(0);
  fill(255);
  textMode(SCREEN);
  text("Kinect FR: " + (int)kinect.getDepthFPS() + "\nProcessing FR: " + (int)frameRate,10,16);

  // Get the raw depth as array of integers
  int[] depth = kinect.getRawDepth();

  // We're just going to calculate and draw every 4th pixel (equivalent of 160x120)
  int skip = 8;

  // Translate and rotate
  scale(1.5);
  translate(width/4,height/4,-50);
  //rotateY(a);

  for(int x=0; x<w; x+=skip) {
    for(int y=0; y<h; y+=skip) {
      int mx = mouseX/30 + 1;
      int offset = x+y*w;

      // Convert kinect data to world xyz coordinate
      int rawDepth = depth[offset];
      PVector v = depthToWorld(x,y,rawDepth);

      // stroke(255);
      pushMatrix();
      // Scale up by 200
      float factor = 200;
      translate(v.x*factor,v.y*factor,factor-v.z*factor);
      // Draw a point
      // point(0,0);
      stroke(255, factor-v.z*factor/2);
      if (x % mx == 0) fill(255, 150);
      else fill(255, 30);
      textSize(8);
      text((int) random(9), v.x*factor/8,v.y*factor/8);
      // ellipse(0, 0, v.x*factor/8,v.y*factor/8);
      // line(0, 0, v.x*factor,v.y*factor);
      popMatrix();
    }
  }

  // Rotate
  a += 0.015f;
}

// These functions come from: http://graphics.stanford.edu/~mdfisher/Kinect.html
float rawDepthToMeters(int depthValue) {
  if (depthValue < 2047) {
    return (float)(1.0 / ((double)(depthValue) * -0.0030711016 + 3.3309495161));
  }
  return 0.0f;
}

PVector depthToWorld(int x, int y, int depthValue) {

  final double fx_d = 1.0 / 5.9421434211923247e+02;
  final double fy_d = 1.0 / 5.9104053696870778e+02;
  final double cx_d = 3.3930780975300314e+02;
  final double cy_d = 2.4273913761751615e+02;

  PVector result = new PVector();
  double depth =  depthLookUp[depthValue];//rawDepthToMeters(depthValue);
  result.x = (float)((x - cx_d) * depth * fx_d);
  result.y = (float)((y - cy_d) * depth * fy_d);
  result.z = (float)(depth);
  return result;
}

void stop() {
  kinect.quit();
  super.stop();
}

