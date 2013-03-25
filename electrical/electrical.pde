import java.util.Iterator;

ArrayList<Resistor> resistor;

void setup() {
  size(600, 400);
  resistor = new ArrayList<Resistor>();
}

void draw() {
  background(100);
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

