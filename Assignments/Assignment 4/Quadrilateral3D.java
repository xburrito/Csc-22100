// Albert Chang
// Csc 22100 - F

// Libraries Used
import java.awt.Color;

public class Quadrilateral3D extends Shape3D {
  
  Quadrilateral3D(int id, String name, String description, Color color, double height, double width, double length) {
    super(id, name, description, color, height, width, length);
  }

  public double area(){
    return (length * width * height);
  }

  public double perimeter(){
    return 2 * ((width * height) + (height * length) +(width * length));
  }
  
}