// Albert Chang
// Csc 22100 - F

// Libraries Used
import java.io.*;
import java.util.*;
import java.awt.Color;

public abstract class Shape implements Comparable<Shape> {
  private final int id;
  private final String name;
  private final String description;
  private final Color color;

  public Shape(int id, String name, String description, Color color){
    this.id = id;
    this.name = name;
    this.description = description;
    this.color = color;
  }

  public abstract double area();
  public abstract double perimeter();

  @Override
  public String toString(){
    return String.format(id + " " + name + " " + description + " " + color);
  }

  public int getId(){
    return id;
  }

  public String getName(){
    return name;
  }

  public String getDescription(){
    return description; 
  }
  public Color getColor(){
    return color;
  }

}