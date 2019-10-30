import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;

public class A3Driver {

    private static final StringBuilder separator = new StringBuilder();

    static {
        for (int i = 0; i < 110; i++)
            separator.append('-');
    }

    public static void main(String[] args) throws FileNotFoundException, ParseException {

        NetworkLogManager networkLogManager = new NetworkLogManager();

        System.out.println(networkLogManager);

        if (networkLogManager.loadFile("src/Network.log")) {

            System.out.println(separator.toString());

            System.out.println(networkLogManager);

            System.out.println(separator.toString());

            System.out.print("Record with id 1: ");
            System.out.println(networkLogManager.searchById("1"));

            System.out.print("Record with id 9: ");
            System.out.println(networkLogManager.searchById("9"));

            System.out.println(separator.toString());

            ArrayList<LogEntry> list = networkLogManager.searchByRange("Jan 1 2018 00:00:00", "Dec 31 2018 23:59:59");

            System.out.printf(String.format("There are %,d entries from 2018", list.size()));
        }
        else
            System.err.println("Failed to load file");
    }
}