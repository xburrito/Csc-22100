// Albert Chang
// Csc 22100 - F

// Libraries Used
import java.util.TreeSet;
import java.awt.Color;

class ShapeList {

  TreeSet<Shape> shapeSet = new TreeSet<Shape>();

  public boolean add(Shape a) throws Exception {
    if (shapeSet.contains(a)){
      throw new Exception("Duplicate shape");
    }
    else {
      shapeSet.add(a);
      return true;
    }
  }

  public TreeSet<Shape2D> get2DShapes(){

    TreeSet<Shape2D> shape_2D = new TreeSet<Shape2D>();

    for (Shape val : shapeSet) {
      if ((val instanceof Shape2D) && !(val instanceof Shape3D)){
        Shape2D downcast = (Shape2D) val;
        shape_2D.add(downcast);
      }
    }

    return shape_2D;
  }

  public TreeSet<Shape3D> get3DShapes(){

    TreeSet<Shape3D> shape_3D = new TreeSet<Shape3D>();

    for (Shape val : shapeSet) {
      if (val instanceof Shape3D){
        Shape3D downcast = (Shape3D) val;
        shape_3D.add(downcast);
      }
    }

    return shape_3D;
  }

  // Used to set up the grid output layout. Values within array indicate how many '-' before inserting a corner '+'
  public void printFormatted(){
    StringBuilder separator = new StringBuilder();
    int[] line = new int[]{6,13,7,23,19,0};

    for(int i = 0; i < 6; i++){
      separator.append("+");
      for(int z = 0; z < line[i]; z++) {
        separator.append("-");
      }
    }

    // Used to print the titles of each column in the grid
    System.out.println(separator);
    System.out.print(String.format("| %-5s|", "ID"));
    System.out.print(String.format(" %-12s|", "Name"));
    System.out.print(String.format(" %-6s|", "Color"));
    System.out.print(String.format(" %-22s|", "Dimentions"));
    System.out.print(String.format(" %-18s| \n", "Description"));

    // Fills the grid with the shapes and its descriptions obtained from the txt file
    for (Shape val : shapeSet) {

      StringBuilder Dimensions = new StringBuilder();
      StringBuilder colors = new StringBuilder();

      if((val instanceof Shape2D) && !(val instanceof Shape3D)){
        Dimensions.append(((Shape2D)val).getDimentions());
      }
      else if (val instanceof Shape3D){
        Dimensions.append(((Shape3D)val).getDimentions());
      }

      if((val.getColor()).equals(Color.RED))
          colors.append("Red");
      else if((val.getColor()).equals(Color.BLUE))
          colors.append("Blue");
      else if((val.getColor()).equals(Color.BLACK))
          colors.append("Black");
      else if((val.getColor()).equals(Color.GREEN))
          colors.append("Green");
      else if((val.getColor()).equals(Color.WHITE))
          colors.append("White");
      else
          colors.append(val.getColor());

    System.out.println(separator);
    System.out.print(String.format("| %-5s|", val.getId()));
    System.out.print(String.format(" %-12s|", val.getName()));
    System.out.print(String.format(" %-6s|", colors));
    System.out.print(String.format(" %-22s|", Dimensions));
    System.out.print(String.format(" %-18s| \n", val.getDescription()));
    }

    System.out.println(separator);
  }

  public int getSize(){
    return shapeSet.size();
  }

}
