package ru.stqa.pft.sandbox;

public class DistPoint {
  public static void main(String[] args){
    Point p1 = new Point(4, -7);
    Point p2 = new Point(3, -3);
    System.out.println("Расстояние между точками =" + p1.distance(p2));
  }

}

