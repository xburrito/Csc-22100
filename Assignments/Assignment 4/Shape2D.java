// Albert Chang
// Csc 22100 - F

// Libraries Used
import java.io.*;
import java.util.*;
import java.awt.Color;

public abstract class Shape2D extends Shape {

  public final double width;
  public final double height;

  Shape2D(int id, String name, String description, Color color, double height, double width){
    super(id, name, description, color);
    this.height = height;
    this.width = width;
  }

  public String getDimentions(){
    return height + ":" + width;
  }
  
  @Override
  public int compareTo(Shape Comparison){
    Shape2D comparisons = (Shape2D) Comparison;

    if((this.getName().compareTo(comparisons.getName()) == 0) && (this.height == comparisons.height) && (this.width == comparisons.width)){
      return 0;
    }
    else {
      return -1;
    }

  }

  @Override // Appends the toString()
  public String toString() { 
    return super.toString() + " " + height + " " + width; 
  }

}