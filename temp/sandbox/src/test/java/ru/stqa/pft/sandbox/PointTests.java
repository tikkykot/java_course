package ru.stqa.pft.sandbox;

import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testDistance(){
    PointData P = new PointData(0, 1, 2, -2);
    assert () == 2.23606797749979;
  }
}
