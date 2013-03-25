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

class Resistor {
  float x, y;
  int diameter;
  
  Resistor(float x, float y) {
    this.x = x;
    this.y = y;

    diameter = 10;
  }

  void display() {
    noStroke();
    fill(200);
    rotate(random(PI));
    // ellipse(x, y, diameter, diameter);
    stroke(200);
    line(x, y, x + 25, y);
    line(x + 25, y, x + 35, y + 10);
    line(x + 35, y + 10, x + 45, y - 10);
    line(x + 45, y - 10, x + 55, y + 10);
    line(x + 55, y + 10, x + 65, y - 10);
    line(x + 65, y - 10, x + 75, y + 10);
    line(x + 75, y + 10, x + 85, y - 10);
    line(x + 85, y - 10, x + 95, y);
    line(x + 95, y, x + 115, y);
  }
}


