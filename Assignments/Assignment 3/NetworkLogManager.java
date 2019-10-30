// Albert Chang
// Csc 22100 - F

// Libraries used
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;

public class NetworkLogManager{

    // Private member variable(s)
    private ArrayList<LogEntry> listLogEntries;

    // Constructor
    public NetworkLogManager(){
        this.listLogEntries = new ArrayList<LogEntry>();
    }

    // Method for the Switch/case statement allowing calls by these names
    enum method{
        id, timestamp, source, destination, protocol, length, description;
    }

    // The method shared by all SearchBy() Methods
    private LogEntry searchBy(method searchValue, String passByValue){
        LogEntry outcome = null;
        switch (searchValue) {

            case id:
                for(int i = 0; i < listLogEntries.size(); i++){
                    if(listLogEntries.get(i).getId().equals(passByValue)){
                        outcome = new LogEntry(listLogEntries.get(i).getId(), listLogEntries.get(i).getTimestamp(), listLogEntries.get(i).getSource(), listLogEntries.get(i).getDestination(), listLogEntries.get(i).getProtocol(), listLogEntries.get(i).getLength(), listLogEntries.get(i).getDescription());
                    }
                }
                break;

            case timestamp:
                for(int i = 0; i < listLogEntries.size(); i++){
                    if(listLogEntries.get(i).getTimestamp().equals(passByValue)){
                        outcome = new LogEntry(listLogEntries.get(i).getId(), listLogEntries.get(i).getTimestamp(), listLogEntries.get(i).getSource(), listLogEntries.get(i).getDestination(), listLogEntries.get(i).getProtocol(), listLogEntries.get(i).getLength(), listLogEntries.get(i).getDescription());
                    }
                }
                break;

            case source:
                for(int i = 0; i < listLogEntries.size(); i++){
                    if(listLogEntries.get(i).getSource().equals(passByValue)){
                        outcome = new LogEntry(listLogEntries.get(i).getId(), listLogEntries.get(i).getTimestamp(), listLogEntries.get(i).getSource(), listLogEntries.get(i).getDestination(),listLogEntries.get(i).getProtocol(), listLogEntries.get(i).getLength(), listLogEntries.get(i).getDescription());
                    }
                }
                break;

            case destination:
                for(int i = 0; i < listLogEntries.size(); i++){
                    if(listLogEntries.get(i).getDestination().equals(passByValue)){
                        outcome = new LogEntry(listLogEntries.get(i).getId(), listLogEntries.get(i).getTimestamp(), listLogEntries.get(i).getSource(), listLogEntries.get(i).getDestination(), listLogEntries.get(i).getProtocol(), listLogEntries.get(i).getLength(), listLogEntries.get(i).getDescription());
                    }
                }

                break;
            case protocol:
                for(int i = 0; i < listLogEntries.size(); i++){
                    if(listLogEntries.get(i).getProtocol().equals(passByValue)){
                        outcome = new LogEntry(listLogEntries.get(i).getId(), listLogEntries.get(i).getTimestamp(), listLogEntries.get(i).getSource(), listLogEntries.get(i).getDestination(), listLogEntries.get(i).getProtocol(), listLogEntries.get(i).getLength(), listLogEntries.get(i).getDescription());
                    }
                }
                break;

            case length:
                for(int i = 0; i < listLogEntries.size(); i++){
                    if(listLogEntries.get(i).getLength().equals(passByValue)){
                        outcome = new LogEntry(listLogEntries.get(i).getId(), listLogEntries.get(i).getTimestamp(), listLogEntries.get(i).getSource(), listLogEntries.get(i).getDestination(), listLogEntries.get(i).getProtocol(), listLogEntries.get(i).getLength(), listLogEntries.get(i).getDescription());
                    }
                }
                break;

            case description:
                for(int i = 0; i < listLogEntries.size(); i++){
                    if(listLogEntries.get(i).getDescription().equals(passByValue)){
                        outcome = new LogEntry(listLogEntries.get(i).getId(), listLogEntries.get(i).getTimestamp(), listLogEntries.get(i).getSource(), listLogEntries.get(i).getDestination(), listLogEntries.get(i).getProtocol(), listLogEntries.get(i).getLength(), listLogEntries.get(i).getDescription());
                    }
                }
                break;
        }

        return outcome;
    }

    // Reading data from the file. Throws error if there is missing/corrupt data
    public Boolean loadFile(String Filename) throws FileNotFoundException{
        Scanner ReadFile = new Scanner(new File(Filename));
        while (ReadFile.hasNext()) {
            String line = ReadFile.nextLine();
            String a[] = line.split(",");
            try {
                LogEntry entry = new LogEntry(a[0], a[1], a[2], a[3], a[4], a[5], a[6]);
                listLogEntries.add(entry);
            }catch (IllegalArgumentException error){
                System.out.printf("Skipping line: %s%n", line);
            }
        }
        if(ReadFile != null){
            ReadFile.close();
        }
        return Boolean.TRUE;
    }

    //Overriding the toString() method
    @Override
    public String toString(){
        return "NetworkLogManager: there are " + listLogEntries.size() + " records";
    }

    // Search by ID
    public LogEntry searchById(String id){
        return searchBy(method.id, id);
    }

    // Search by Range of dates (Beginning to End)
    public ArrayList<LogEntry> searchByRange(String beginDate, String lastDate) throws ParseException{
        SimpleDateFormat dateStruct = new SimpleDateFormat("MMM dd yyy HH:mm:ss");
        Date begin = dateStruct.parse(beginDate);
        Date last = dateStruct.parse(lastDate);

        ArrayList<LogEntry> outcome = new ArrayList<>();
        for(int i = 0; i < listLogEntries.size(); i++){
            Date timestamp = dateStruct.parse(listLogEntries.get(i).getTimestamp());

            if(timestamp.compareTo(begin) >=  0 && timestamp.compareTo(last) <= 0){
                outcome.add(listLogEntries.get(i));
            }
        }
        return outcome;
    }

    // Search by Source
    public LogEntry searchBySource(String source){
        return searchBy(method.source, source);
    }

    // Search by Destination
    public LogEntry searchByDestination(String destination){
        return searchBy(method.destination, destination);
    }

    // Search by Protocol
    public LogEntry searchByProtocol(String protocol){
        return searchBy(method.protocol, protocol);
    }

    // Search by Length
    public LogEntry searchByLength(String length){
        return searchBy(method.length, length);
    }

    // Search by Description
    public LogEntry searchByDescription(String description){
        return searchBy(method.description, description);
    }
}