// Albert Chang
// Csc 22100 - F

// Libraries
import java.util.Calendar;
import java.util.Scanner;

public class Painting {
    
    // Initiating the variables
    private String artistName;
    private String name;
    private double price;
    private int year;

    // Default Constructor
    public Painting(){
        this.artistName = "-";
        this.name = "-";
        this.price = 0;
        this.year = 0;
    }
    // Non-Default Constructor
    public Painting(String artistName, String name, double price, int year){
        this.artistName = artistName;
        this.name = name;
        this.price = price;
        this.year = year;
    }

    // Setters
    public void setArtistName(String artistName){
        this.artistName = artistName;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setYear(int year) {
        this.year = year;
    }

    // Getters
    public String getArtistName(){
        return artistName;
    }
    public String getName(){
        return name;
    }
    public double getPrice() {
        return price;
    }
    public int getYear() {
        return year;
    }

    public double getMinimumDiscountPrice(){
        return price - (price * 0.15);
    }
    public double getMaximumDiscountPrice(){
        return price - (price * 0.10);
    }
    public int getAge(){
        return Calendar.getInstance().get(Calendar.YEAR) - getYear();
    }
}
