import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.List;

public class A6Driver {

    private static final StringBuilder separator = new StringBuilder();

    /*
     * Convert to code using Streams.
     * Do not use for
     */

    static {
        for (int i = 0; i < 110; i++)
            separator.append('-');
    }

    public static void main(String[] args) throws FileNotFoundException, ParseException {

        NetworkLogManager networkLogManager = new NetworkLogManager();

        System.out.println(networkLogManager);

        if (networkLogManager.loadFile("Network.log")) {

            System.out.println(separator.toString());

            System.out.println(networkLogManager);

            System.out.println(separator.toString());

            System.out.printf("There are %d records with id 1%n", networkLogManager.searchById("1").size());
            System.out.printf("There are %d records with id 9%n", networkLogManager.searchById("9").size());
            System.out.printf("There are %d records with protocol TCP %n", networkLogManager.searchByProtocol("TCP").size());
            System.out.printf("There are %d records with protocol UDP %n", networkLogManager.searchByProtocol("UDP").size());

            System.out.println(separator.toString());

            List<LogEntry> list = networkLogManager.searchByRange("Jan 1 2018 00:00:00", "Dec 31 2018 23:59:59");

            System.out.printf(String.format("There are %,d entries from 2018", list.size()));
        }
        else
            System.err.println("Failed to load file");
    }
}