// Albert Chang
// Csc 22100 - F

// Libraries Used
import java.io.*;
import java.util.*;
import java.awt.Color;

public abstract class Shape3D extends Shape2D {
  
  public final double length;
  // public final double width;
  // public final double height;

  Shape3D(int id, String name, String description, Color color, double height, double width, double length){
    super(id, name, description, color, height, width);
    this.length = length;
  }

  // This will append the getDimentions() from Shape2D java class
  public String getDimentions(){
    return super.getDimentions() + ":" + length;
  }

  @Override
  public int compareTo(Shape Comparison) {
    if((super.compareTo(Comparison) == 0)){
      Shape3D comparisons = (Shape3D) Comparison;
      if(this.length == comparisons.length){
        return 0;

      }
    }
    return -1;
  }
  
  @Override // Appends the toString()
  public String toString() { 
    return super.toString() + " " + height + " " + width; 
  }

}