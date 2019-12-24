import java.util.regex.Pattern;

public class LogEntry {

    /*
     * Maximum valid int value: 2147483647
     * Valid range is 0 to 2147483647. -0 is set as valid
     */
    private final static Pattern patternValidPositiveInt = Pattern.compile("-0"
                    + "|[\\d]{0,9}"
                    + "|[01][\\d]{0,9}"
                    + "|20[0-9]{0,8}"
                    + "|21[0-3][0-9]{0,7}"
                    + "|214[0-6][0-9]{0,6}"
                    + "|2147[0-3][0-9]{0,5}"
                    + "|21474[0-7][0-9]{0,4}"
                    + "|214748[0-2][0-9]{0,3}"
                    + "|2147483[0-5][0-9]{0,2}"
                    + "|21474836[0-3][0-9]{0,1}"
                    + "|214748364[0-7]");

    private final static Pattern patternValidString = Pattern.compile("(\\w+\\s*\\.*:*\\-*)+");

    private String id;
    private String timestamp;
    private String source;
    private String destination;
    private String protocol;
    private String length;
    private String description;

    public LogEntry(String id, String timestamp, String source, String destination, String protocol, String length, String description) {

        if (!isValidInt(id) || !isValidStr(timestamp) || !isValidStr(source) || !isValidStr(destination) || !isValidStr(protocol) || !isValidInt(length) || !isValidStr(description))
            throw new IllegalArgumentException("One or more values are invalid");

        this.id = id;
        this.timestamp = timestamp;
        this.source = source;
        this.destination = destination;
        this.protocol = protocol;
        this.length = length;
        this.description = description;
    }

    public String toString() {

        return String.format("%s,%s,%s,%s,%s,%s,%s", id, timestamp, source, destination, protocol, length, description);
    }

    public String getId() {

        return id;
    }

    public String getTimestamp() {

        return timestamp;
    }

    public String getSource() {

        return source;
    }

    public String getDestination() {

        return destination;
    }

    public String getProtocol() {

        return protocol;
    }

    public String getLength() {

        return length;
    }

    public String getDescription() {

        return description;
    }

    private boolean isValidInt(String str) {

        return isValidStr(str) && patternValidPositiveInt.matcher(str).matches();
    }

    private boolean isValidStr(String str) {

        return str != null && patternValidString.matcher(str).matches();
    }
}