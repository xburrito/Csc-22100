// Albert Chang
// Csc 22100 - F

// Libraries imported
import java.util.Scanner;

public class TestPainting {

    public static void main(String[] args) {

        //Pre-set values using Default Constructor
        Painting art = new Painting();
        
        art.setArtistName("Mark Rothko");
        art.setName("No. 6 (Violet, Green and Red");
        art.setPrice(186000000);
        art.setYear(1951);

        //Ask User for all inputs
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter the artist's name: ");
        String artistName = keyboard.nextLine();
        System.out.print("Enter the name of the painting: ");
        String name = keyboard.nextLine();
        System.out.print("Enter the price for the painting: ");
        double price = keyboard.nextDouble();
        System.out.print("Enter the year the painting was established: ");
        int year = keyboard.nextInt();
        keyboard.close();

        //Pre-set values using non-default Constructor
        Painting art2 = new Painting("Paul Cezanne", "The Card Players", 300000000, 1892);

        System.out.printf("%24s: %s%n", "Artist Name", art2.getArtistName());
        System.out.printf("%24s: %s%n", "Name", art2.getName());
        System.out.printf("%24s: %,.2f%n", "Price", art2.getPrice());
        System.out.printf("%24s: %d%n", "Year", art2.getYear());
        System.out.printf("%24s: %d%n", "Age", art2.getAge());
        System.out.printf("%24s: %,.2f - %,.2f%n", "Discounted Price Range", art2.getMinimumDiscountPrice(), art2.getMaximumDiscountPrice());
    }
}
