import java.util.Iterator;

ArrayList<Resistor> resistor;

void setup() {
  size(displayWidth, displayHeight);
  resistor = new ArrayList<Resistor>();
  background(0);
}

void draw() {
  /*
  noStroke();
  fill(0, 50);
  rect(0, 0, width, height);
  */
  //background(100);
  // Using the iterator 
  Iterator<Resistor> it = resistor.iterator();
  while (it.hasNext()) {
    Resistor r = it.next();
    r.display();
  }
}

void mousePressed() {
  resistor.add(new Resistor(mouseX, mouseY));
}

