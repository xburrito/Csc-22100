// Albert Chang
// Csc 22100 - F

public class LogEntry{

    // Private member variables
    private String id, timestamp, source, destination, protocol, length, description;

    // Non-Default Constructor
    public LogEntry(String id, String timestamp, String source, String destination, String protocol, String length, String description){
        if( !id.matches("[0-9]+") || timestamp.isEmpty() || timestamp == null || source.isEmpty() || source == null || destination.isEmpty() || destination == null || protocol.isEmpty() || protocol == null || protocol.equals("-") || length.isEmpty() || length == null || length.equals("0") || description.isEmpty() || description == null){
            throw new IllegalArgumentException();
        }
        this.id = id;
        this.timestamp = timestamp;
        this.source = source;
        this.destination = destination;
        this.protocol = protocol;
        this.length = length;
        this.description = description;

    }

    // Overriding the toString() method
    @Override
    public String toString(){
        return id + ", " + timestamp + ", " + source + ", " + destination
                + ", " + protocol + ", " + length + ", " + description;
    }

    // Getters
    public String getId(){
        return id;
    }
    public String getTimestamp(){
        return timestamp;
    }
    public String getSource(){
        return source;
    }
    public String getDestination(){
        return destination;
    }
    public String getProtocol(){
        return protocol;
    }
    public String getLength(){
        return length;
    }
    public String getDescription(){
        return description;
    }
}