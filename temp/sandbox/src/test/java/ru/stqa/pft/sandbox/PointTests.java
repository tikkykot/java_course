package ru.stqa.pft.sandbox;

import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testDistance() {
    Point p1 = new Point(0, 1);
    Point p2 = new Point(2, -3);
    assert p1.distance(p2) == 4.47213595499958;
  }

  @Test
  public void testDistance1() {
    Point p1 = new Point(2, 1);
    Point p2 = new Point(1, -2);
    assert p1.distance(p2) == 3.1622776601683795;
  }

  @Test
  public void testDistance2() {
    Point p1 = new Point(4, -7);
    Point p2 = new Point(3, -3);
    assert p1.distance(p2) == 4.123105625617661;
  }
}
