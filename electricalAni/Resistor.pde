import de.looksgood.ani.*;

class Resistor {
  AniSequence seq;
  // float in_x, in_y; // the starting point
  float start_x, start_y;
  float node_1_x, node_1_y;
  float node_2_x, node_2_y;
  float node_3_x, node_3_y;
  float node_4_x, node_4_y;
  float node_5_x, node_5_y;
  float node_6_x, node_6_y;
  float node_7_x, node_7_y;
  float node_8_x, node_8_y;
  float end_x, end_y;

  float x1, x2, y1, y2;
  float nodeTime;

  Resistor(float x, float y) {
    setNodes(x, y);
    setAnimation();
  }

  private void setNodes(float x, float y) {
    x1 = x2 = start_x = x; 
    y1 = y2 = start_y = y;
    node_1_x = x + 25; 
    node_1_y = y;
    node_2_x = x + 35; 
    node_2_y = y + 10;
    node_3_x = x + 45;
    node_3_y = y - 10;
    node_4_x = x + 55;
    node_4_y = y + 10;
    node_5_x = x + 65; 
    node_5_y = y - 10;
    node_6_x = x + 75; 
    node_6_y = y + 10;
    node_7_x = x + 85;
    node_7_y = y - 10;
    node_8_x = x + 95; 
    node_8_y = y;
    end_x = x + 115;
    end_y = y;
    
    
  }

  private void setAnimation() {
    Ani.init(electricalAni.this);
    seq = new AniSequence(electricalAni.this);
    
    nodeTime = 0.05;
    
    seq.beginSequence();

    seq.beginStep();
    seq.add(Ani.to(this, nodeTime, "x2", node_1_x, Ani.LINEAR));
    seq.add(Ani.to(this, nodeTime, "y2", node_1_y, Ani.LINEAR, "onEnd:end_1"));
    seq.endStep();

    seq.beginStep();
    seq.add(Ani.to(this, nodeTime, "x2", node_2_x, Ani.LINEAR));
    seq.add(Ani.to(this, nodeTime, "y2", node_2_y, Ani.LINEAR, "onEnd:end_2"));
    seq.endStep();

    seq.beginStep();
    seq.add(Ani.to(this, nodeTime, "x2", node_3_x, Ani.LINEAR));
    seq.add(Ani.to(this, nodeTime, "y2", node_3_y, Ani.LINEAR, "onEnd:end_3"));
    seq.endStep();

    seq.beginStep();
    seq.add(Ani.to(this, nodeTime, "x2", node_4_x, Ani.LINEAR));
    seq.add(Ani.to(this, nodeTime, "y2", node_4_y, Ani.LINEAR, "onEnd:end_4"));
    seq.endStep();

    seq.beginStep();
    seq.add(Ani.to(this, nodeTime, "x2", node_5_x, Ani.LINEAR));
    seq.add(Ani.to(this, nodeTime, "y2", node_5_y, Ani.LINEAR, "onEnd:end_5"));
    seq.endStep();

    seq.beginStep();
    seq.add(Ani.to(this, nodeTime, "x2", node_6_x, Ani.LINEAR));
    seq.add(Ani.to(this, nodeTime, "y2", node_6_y, Ani.LINEAR, "onEnd:end_6"));
    seq.endStep();

    seq.beginStep();
    seq.add(Ani.to(this, nodeTime, "x2", node_7_x, Ani.LINEAR));
    seq.add(Ani.to(this, nodeTime, "y2", node_7_y, Ani.LINEAR, "onEnd:end_7"));
    seq.endStep();

    seq.beginStep();
    seq.add(Ani.to(this, nodeTime, "x2", node_8_x, Ani.LINEAR));
    seq.add(Ani.to(this, nodeTime, "y2", node_8_y, Ani.LINEAR, "onEnd:end_8"));
    seq.endStep();

    seq.beginStep();
    seq.add(Ani.to(this, nodeTime, "x2", end_x, Ani.LINEAR));
    seq.add(Ani.to(this, nodeTime, "y2", end_y, Ani.LINEAR));
    seq.endStep();

    seq.endSequence();

    seq.start();
  }

  private void end_1() {
    x1 = node_1_x;
    y1 = node_1_y;
  }

  private void end_2() {
    x1 = node_2_x;
    y1 = node_2_y;
  }

  private void end_3() {
    x1 = node_3_x;
    y1 = node_3_y;
  }

  private void end_4() {
    x1 = node_4_x;
    y1 = node_4_y;
  }
  private void end_5() {
    x1 = node_5_x;
    y1 = node_5_y;
  }
  private void end_6() {
    x1 = node_6_x;
    y1 = node_6_y;
  }
  private void end_7() {
    x1 = node_7_x;
    y1 = node_7_y;
  }
  private void end_8() {
    x1 = node_8_x;
    y1 = node_8_y;
  }
  private void end_9() {
    x1 = node_8_x;
    y1 = node_8_y;
  }

  void display() {
    noStroke();
    fill(180);
    // ellipse(x, y, diameter, diameter);
    stroke(200);
    line(x1, y1, x2, y2);
  }
}

