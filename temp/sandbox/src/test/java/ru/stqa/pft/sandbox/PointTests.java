package ru.stqa.pft.sandbox;

import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testDistance() {
    PointData P = new PointData(0, 1, 2, -2);
    assert Point.distance(P) == 2.23606797749979;
  }

   @Test
    public void testDistance1 () {
      PointData P = new PointData(0, 1, 3, -3);
      assert Point.distance(P) == 3.1622776601683795;
    }

  @Test
  public void testDistance2 () {
    PointData P = new PointData(0, 1, 0, -1);
    assert Point.distance(P) == 1.4142135623730951;
  }
}