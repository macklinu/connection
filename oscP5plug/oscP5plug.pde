import oscP5.*;

OscP5 oscP5;

void setup() {
  size(400,400);
  oscP5 = new OscP5(this, 9000); // listen at port 9000
  setupPlugs();
}

void draw() {
  background(0);
}