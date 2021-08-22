package ru.stqa.pft.sandbox;

public class Point {
  public static void main(String[] args){
    PointData P = new PointData(0, 1, 2, -2);
    System.out.println("Расстояние между точками =" + distance(P));
  }
  public static double distance(PointData P){
    return Math.sqrt((P.p2-P.p1)*(P.p2-P.p1)+(P.p4-P.p1)*(P.p4-P.p1));
  }

}

