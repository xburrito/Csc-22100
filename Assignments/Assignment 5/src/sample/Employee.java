// Albert Chang
// Csc 22100 - F

package sample;

public class Employee {

    private String name;
    private String department;
    private String extension;
    private final int id;
    private static int idCount = 0;

    public Employee() {

        this("", "", "");
    }

    public Employee(String name, String department, String extension) {
        this.name = name;
        this.department = department;
        this.extension = extension;
        this.id = ++idCount;
    }

    public Employee(int id, String name, String department, String extension) {
        this.name = name;
        this.department = department;
        this.extension = extension;
        this.id = id;
    }

    public String getName() {

        return name;
    }

    public int getId(){
        return id;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getDepartment() {

        return department;
    }

    public void setDepartment(String department) {

        this.department = department;
    }

    public String getExtension() {

        return extension;
    }

    public void setExtension(String extension) {

        this.extension = extension;
    }

    public boolean validCheck(){
        return nameValid() && companyValid() && extensionValid();
    }

    // Names consist of only 1 or 2 Strings. Each string must start with an uppercase letter and at least two letters. Numbers excluded!
    public boolean nameValid() {
        return this.name.matches("([A-Z][a-zA-Z]{2,})|([A-Z][a-zA-Z]{2,}\\s[A-Z][a-zA-Z]{2,})");
    }

    // Company consist of only 1 or 2 Strings. Each string must start with an uppercase letter and at least two letters. Numbers included!
    public boolean companyValid() {
        return this.department.matches("([A-Z][a-zA-Z0-9]*)|([A-Z][a-zA-Z0-9]*\\s[A-Z][a-zA-Z0-9]*)");
    }

    // Extensions begin with 1, 2, or 3 numbers followed by a dash and then followed by 1 to 2 numbers
    public boolean extensionValid() {
        return this.extension.matches("[0-9]{1,3}-[0-9]{1,2}");
    }

    @Override
    public String toString() {
        return String.format("Employee >> ID: %d, Name: %s, Department: %s, Extension: %s", id, name, department, extension);
    }
}