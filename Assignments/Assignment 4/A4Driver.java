
/**
 * Test file for Assignment #4.
 * 
 * DO NOT MODIFY
 */
import java.awt.Color;
import java.io.File;
import java.util.Scanner;
import java.util.TreeSet;

public class A4Driver {

    public static void main(String[] args) throws Exception {

        ShapeList shapeList = new ShapeList();
        Scanner scanner = new Scanner(new File("TestData.txt"));

        // skip file header
        String line = scanner.nextLine();

        while (scanner.hasNextLine()) {

            boolean result = false;
            Shape shape = null;

            line = scanner.nextLine();

            String[] lineParts = line.split(",");

            String[] dimensions = lineParts[2].split("-");

            try {

                switch (lineParts[3].toLowerCase()) {

                    case "rectangle":
                        shape = new Quadrilateral(Integer.parseInt(lineParts[0]), lineParts[3], lineParts[4], getColor(lineParts[1]), Double.parseDouble(dimensions[0]), Double.parseDouble(dimensions[1]));
                        break;
                    case "square":
                        shape = new Quadrilateral(Integer.parseInt(lineParts[0]), lineParts[3], lineParts[4], getColor(lineParts[1]), Double.parseDouble(dimensions[0]), Double.parseDouble(dimensions[1]));
                        break;
                    case "cuboid":
                        shape = new Quadrilateral3D(Integer.parseInt(lineParts[0]), lineParts[3], lineParts[4], getColor(lineParts[1]), Double.parseDouble(dimensions[0]), Double.parseDouble(dimensions[1]), Double.parseDouble(dimensions[2]));
                        break;
                    case "cube":
                        shape = new Quadrilateral3D(Integer.parseInt(lineParts[0]), lineParts[3], lineParts[4], getColor(lineParts[1]), Double.parseDouble(dimensions[0]), Double.parseDouble(dimensions[1]), Double.parseDouble(dimensions[2]));
                        break;
                    default:
                        System.err.println("Unrecognized shape, skipping: " + line);
                }

                if (shape != null) {

                    result = shapeList.add(shape);

                    if (!result)
                        System.err.println("Failed to add shape, skipping: " + line);
                }
            }
            catch (Exception ex) {
                System.err.println("   Duplicate shape, skipping: " + line);
                continue;
            }
        }

        TreeSet<Shape2D> set2D = shapeList.get2DShapes();
        System.out.printf("There are %d 2-Dimentional shapes%n", set2D.size());

        TreeSet<Shape3D> set3D = shapeList.get3DShapes();
        System.out.printf("There are %d 3-Dimentional shapes%n", set3D.size());

        shapeList.printFormatted();

        System.out.printf("%d rows%n", shapeList.getSize());

        scanner.close();
    }

    private static Color getColor(String strColor) {

        switch (strColor.toLowerCase()) {
            case "black":
                return Color.BLACK;
            case "blue":
                return Color.BLUE;
            case "green":
                return Color.GREEN;
            case "red":
                return Color.RED;
            case "white":
                return Color.WHITE;
        }
        return null;
    }
}