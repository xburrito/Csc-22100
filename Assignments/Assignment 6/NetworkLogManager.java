import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class NetworkLogManager {

    private final static SimpleDateFormat formatter = new SimpleDateFormat("MMM dd yyy HH:mm:ss");

    private final ArrayList<LogEntry> listLogEntries;

    public enum SearchField {
        ID, TIMESTAMP, SOURCE, DESTINATION, PROTOCOL, LENGTH, DESCRIPTION
    }

    public NetworkLogManager() {

        this.listLogEntries = new ArrayList<LogEntry>();
    }

    public boolean loadFile(String fileName) throws FileNotFoundException {

        /*
         * Convert to code using Streams.
         * Do not use Scanner or while
         */
        Scanner scanner = null;

        try {
            scanner = new Scanner(new File(fileName));

            String line = "";

            while (scanner.hasNext()) {

                line = scanner.nextLine();

                String arr[] = line.split(",");

                try {

                    if (!listLogEntries.add(new LogEntry(arr[0].trim(), arr[1].trim(), arr[2].trim(), arr[3].trim(), arr[4].trim(), arr[5].trim(), arr[6].trim())))
                        System.out.printf("Skipping line: %s%n", line);
                }
                catch (IllegalArgumentException ex) {
                    System.out.printf("Skipping line: %s%n", line);
                }
            }
        }
        catch (FileNotFoundException fnfe) {

            return false;
        }
        finally {

            scanner.close();
        }

        return true;
    }

    public String toString() {

        return String.format("NetworkLogManager: there are %,d valid records", listLogEntries.size());
    }

    public List<LogEntry> searchById(String str) {

        return searchBy(str, SearchField.ID);
    }

    public List<LogEntry> searchByTimestamp(String str) {

        return searchBy(str, SearchField.TIMESTAMP);
    }

    public List<LogEntry> searchBySource(String str) {

        return searchBy(str, SearchField.SOURCE);
    }

    public List<LogEntry> searchByDestination(String str) {

        return searchBy(str, SearchField.DESTINATION);
    }

    public List<LogEntry> searchByProtocol(String str) {

        return searchBy(str, SearchField.PROTOCOL);
    }

    public List<LogEntry> searchByLength(String str) {

        return searchBy(str, SearchField.LENGTH);
    }

    public List<LogEntry> searchByDescription(String str) {

        return searchBy(str, SearchField.DESCRIPTION);
    }

    public List<LogEntry> searchByRange(String fromDate, String toDate) throws ParseException {

        /*
         * Convert to code using Streams.
         * Do not use for
         */
        List<LogEntry> retList = new ArrayList<LogEntry>();

        try {
            Date from = formatter.parse(fromDate);
            Date to = formatter.parse(toDate);

            for (LogEntry logEnt : listLogEntries) {

                Date timestamp = formatter.parse(logEnt.getTimestamp());

                if (timestamp.compareTo(from) >= 0 && timestamp.compareTo(to) <= 0)
                    retList.add(logEnt);
            }
        }
        catch (ParseException pe) {

            pe.printStackTrace();
        }

        return retList;
    }

    private List<LogEntry> searchBy(String searchVal, SearchField field) {

        /*
         * Convert to code using Streams.
         * Do not use for
         */
        List<LogEntry> retList = new ArrayList<LogEntry>();
        for (LogEntry logEnt : listLogEntries) {

            String logEntryValue = getSearchValue(logEnt, field);

            if (logEntryValue.equals(searchVal))
                retList.add(logEnt);
        }

        return retList;
    }

    private String getSearchValue(LogEntry logEnt, SearchField field) {

        switch (field) {
            case ID:
                return logEnt.getId();
            case TIMESTAMP:
                return logEnt.getTimestamp();
            case SOURCE:
                return logEnt.getSource();
            case DESTINATION:
                return logEnt.getDestination();
            case DESCRIPTION:
                return logEnt.getDescription();
            case PROTOCOL:
                return logEnt.getProtocol();
            case LENGTH:
                return logEnt.getLength();
            default:
                return null;
        }
    }
}