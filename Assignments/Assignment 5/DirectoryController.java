// Albert Chang
// Csc 22100 - F

package sample;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.stage.FileChooser;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.util.Optional;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.xml.bind.JAXB;
import javafx.stage.Stage;
import javafx.fxml.FXML;

public class DirectoryController {

    @FXML
    private TextField txtFldName;
    @FXML
    private TextField txtFldCompany;
    @FXML
    private TextField txtFldExtension;
    @FXML
    private Label lblCurrRecord;
    @FXML
    private Button btnNavDel;
    @FXML
    private Button btnNavAdd;
    @FXML
    private Button btnNavNext;
    @FXML
    private Button btnNavPrev;
    @FXML
    private Button btnSerialize;
    @FXML
    private Label lblFilename;
    @FXML

    private static File selectedFile = null;
    private static int EmplstIndex = 0;
    private static EmployeeList Emplist = null;

    // Disables all the input fields when starting up the application.
    @FXML
    private void initialize() {
        txtFldName.setDisable(true);
        txtFldCompany.setDisable(true);
        txtFldExtension.setDisable(true);
        btnNavAdd.setDisable(true);
        btnNavDel.setDisable(true);
        btnNavNext.setDisable(true);
        btnNavPrev.setDisable(true);
        btnSerialize.setDisable(true);
    }

    public void buttonClicked(ActionEvent e){
        Button buttonChoice = (Button) e.getSource();

        switch(buttonChoice.getId()) {
            case "btnLoad":
                BtnLoadFunction();
                break;
            case "btnNavAdd":
                BtnNavAddFunction();
                break;
            case "btnNavDel":
                BtnNavDelFunction();
                break;
            case "btnNavPrev":
                BtnNavPrevFunction();
                break;
            case "btnNavNext":
                BtnNavNextFunction();
                break;
            case "btnSerialize":
                BtnSerializeFunction();
                break;
            case "btnExit":
                BtnNavExitFunction();
                break;
        }
    }

    private void BtnLoadFunction() {
        Alert alert = new Alert(AlertType.ERROR);

        FileChooser fileChooser = new FileChooser();
        selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile == null) {
            alert.setTitle("Error Loading File");
            alert.setHeaderText("No File Selected");
            alert.setContentText("Click load and select a file to continue...");
            alert.showAndWait();
            return;
        }

        if (!fileUnmarshal()) {
            alert.setTitle("Error when reading the File");
            alert.setHeaderText("Could not parse file");
            alert.setContentText("Click load and select a file to continue...");
            alert.showAndWait();
            return;
        }

        System.out.println("selectedFile: " + selectedFile.getAbsolutePath());
        System.out.println(Emplist.getLst());

        // Performs a check to make sure data entered is valid
        if (!emplValidCheck() && Emplist.getLst().get(0).getId() != 0) {
            alert.setTitle("Error Validating File");
            alert.setHeaderText("Invalid Employees");
            alert.setContentText("Select a file with 'valid employees' and try again.");
            alert.showAndWait();

            // reset list of employees
            Emplist.setLst(new ArrayList<>());
            return;
        }

        // Enables the set file name text
        lblFilename.setText("File: " + selectedFile.getName());

        // Enables the add button
        btnNavAdd.setDisable(false);

        // Enables the serialize button
        btnSerialize.setDisable(false);

        // Loads the first employee data to input fields
        txtFldName.setText(Emplist.getLst().get(0).getName());
        txtFldCompany.setText(Emplist.getLst().get(0).getDepartment());
        txtFldExtension.setText(Emplist.getLst().get(0).getExtension());

        // Update EmplstIndex
        EmplstIndex = 0;

        // Update current record label
        // Upon first startup, when the file being read is an empty file, show 0 of 0. Otherwise, it will populate the number of records present
        if (Emplist.getLst().get(0).getId() == 0) {
            lblCurrRecord.setText("0 of 0");
        } else {
            lblCurrRecord.setText(String.format("%d of %d", EmplstIndex + 1, Emplist.getLst().size()));
        }


        // Disable delete button if size < 1, else enable
        btnNavDel.setDisable(Emplist.getLst().size() < 1);

        // Disable next button if size == 1, else enable
        btnNavNext.setDisable(Emplist.getLst().size() == 1);

        // Enable inputs
        txtFldName.setDisable(false);
        txtFldCompany.setDisable(false);
        txtFldExtension.setDisable(false);
    }

    private boolean emplValidCheck() {
        for(Employee emp : Emplist.getLst()){
            if(!emp.validCheck()) return false;
        }
        return true;
    }

    private boolean fileUnmarshal() {

        // For the case of an empty file for the first time being read. id = 0.
        if(selectedFile.length() == 0){
            Employee emptyEmployee = new Employee(0,"","","");
            List<Employee> emptyList = new ArrayList<>();
            emptyList.add(emptyEmployee);
            Emplist = new EmployeeList();
            Emplist.setLst(emptyList);
            return true;
        }
        // Performs if the file selected is not empty
        try(BufferedReader input = Files.newBufferedReader(Paths.get(selectedFile.toURI()))){
            Emplist = JAXB.unmarshal(input, EmployeeList.class);
            return true;
        }
        catch (IOException ioException){
            System.err.println("Error loading file!");
            return false;
        }
    }

    private void BtnNavAddFunction() {
        // Validate/Save current employee unless list is empty
        if(Emplist.getLst().size() > 0) {

            // Save/Update current employee data
            if(!saveUpdates()) return;

            // Enable prev button
            btnNavPrev.setDisable(false);
        }

        // Clear inputs for new entry
        txtFldName.setText("");
        txtFldCompany.setText("");
        txtFldExtension.setText("");

        // Enable inputs
        txtFldName.setDisable(false);
        txtFldCompany.setDisable(false);
        txtFldExtension.setDisable(false);

        // Add new entry
        Emplist.createNew();

        // Enable delete button
        btnNavDel.setDisable(false);

        // Disable next button
        btnNavNext.setDisable(true);

        // Update current record label
        EmplstIndex = Emplist.getLst().size() - 1;
        lblCurrRecord.setText(String.format("%d of %d", EmplstIndex + 1, Emplist.getLst().size()));
    }

    private Employee BtnNavDelFunction() {
        // Remove current displayed record
        Employee removed = Emplist.getLst().remove(EmplstIndex);

        // If list size is now <= 1, disable next/prev
        if(Emplist.getLst().size() <= 1) {
            // Disable next button
            btnNavNext.setDisable(true);

            // Disable previous button
            btnNavPrev.setDisable(true);
        }

        if(Emplist.getLst().size() == 0) {
            // Disables delete button
            btnNavDel.setDisable(true);

            EmplstIndex = 0;

            txtFldName.setText("");
            txtFldCompany.setText("");
            txtFldExtension.setText("");

            txtFldName.setDisable(true);
            txtFldCompany.setDisable(true);
            txtFldExtension.setDisable(true);

            lblCurrRecord.setText("0 of 0");

            return removed;
        }

        // If we are on the last element, index will be updated to prev
        if(EmplstIndex > Emplist.getLst().size() - 1) {
            EmplstIndex--;
        }

        dataInput(Emplist, EmplstIndex);

        // Updates current record label
        lblCurrRecord.setText(String.format("%d of %d", EmplstIndex + 1, Emplist.getLst().size()));

        // Disables next button if no next record, else enable
        btnNavNext.setDisable(EmplstIndex == Emplist.getLst().size() - 1);

        return removed;
    }

    private void BtnNavPrevFunction() {
        // Checks if there is a previous record present before enabling the prev button
        if(EmplstIndex <= 0) {
            //no prev record
            btnNavPrev.setDisable(true);
            return;
        }
        // Save/Updates current record
        if(!saveUpdates()) return;

        // Goes to previous record
        EmplstIndex--;
        dataInput(Emplist, EmplstIndex);

        // Update current records
        lblCurrRecord.setText(String.format("%d of %d", EmplstIndex + 1, Emplist.getLst().size()));

        // Checks to see if previous records still exist. If none do, the prev button will disable
        btnNavPrev.setDisable(EmplstIndex == 0);

        // Enables the next button
        btnNavNext.setDisable(false);
    }

    private void BtnNavNextFunction() {

        // Save/Update current records
        if(!saveUpdates()) return;

        // Goto next record
        EmplstIndex++;
        dataInput(Emplist, EmplstIndex);

        // Update current record
        lblCurrRecord.setText(String.format("%d of %d", EmplstIndex + 1, Emplist.getLst().size()));

        // Disables the next button if there is no record present after the one currently being viewed
        btnNavNext.setDisable(EmplstIndex == Emplist.getLst().size() - 1);

        // Enables the prev button
        btnNavPrev.setDisable(false);

    }

    private void dataInput(EmployeeList Emplist, int EmplstIndex){
        txtFldName.setText(Emplist.getLst().get(EmplstIndex).getName());
        txtFldCompany.setText(Emplist.getLst().get(EmplstIndex).getDepartment());
        txtFldExtension.setText(Emplist.getLst().get(EmplstIndex).getExtension());
    }

    private void BtnSerializeFunction() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Serialize");

        // Save/Update current employee
        if(!saveUpdates()) return;

        FileChooser fileChooser = new FileChooser();
        selectedFile = fileChooser.showOpenDialog(new Stage());
        if(selectedFile == null) { return; }

        if(fileMarshal()) {
            alert.setAlertType(AlertType.INFORMATION);
            System.out.printf("Employees serialized to file %s successfully!", selectedFile.getName());
            alert.setHeaderText(String.format("Employees serialized to file %s successfully!", selectedFile.getName()));
            alert.showAndWait();
        } else {
            System.out.printf("Error occurred serializing Employees to file @ %s.", selectedFile.getAbsolutePath());
            alert.setHeaderText(String.format("Error occurred serializing Employees to file @ %s.", selectedFile.getAbsolutePath()));
            alert.showAndWait();
            return;
        }

        lblFilename.setText("File: " + selectedFile.getName());
    }

    private boolean fileMarshal() {
        try(BufferedWriter output = Files.newBufferedWriter(Paths.get(selectedFile.toURI()))){
            JAXB.marshal(Emplist, output);
            return true;
        } catch(IOException ex) {
            System.err.println("Error writing to file!");
            return false;
        }
    }

    private boolean saveUpdates(){
        String employeeName = txtFldName.getText();
        String employeeCompany = txtFldCompany.getText();
        String employeeExtension = txtFldExtension.getText();

        // Creates new copy of current employee, updates and checks validation criteria
        Employee update = new Employee(Emplist.getLst().get(EmplstIndex).getId(), employeeName, employeeCompany, employeeExtension);

        if(!update.validCheck()) {
            showAlerts(update);
            return false;
        }

        Emplist.getLst().get(EmplstIndex).setName(update.getName());
        Emplist.getLst().get(EmplstIndex).setDepartment(update.getDepartment());
        Emplist.getLst().get(EmplstIndex).setExtension(update.getExtension());

        return true;
    }

    private void showAlerts(Employee updatedEmployee){
        Alert alert = new Alert(AlertType.ERROR);

        // Potential user errors when entering data will prompt these actions.
        alert.setTitle("Invalid Value");
        if(!updatedEmployee.nameValid()) {
            alert.setHeaderText("Invalid Name \nNames can be 1 or 2 words.\n" +
                    "1. Each word must start with an uppercase letter followed by at least 2 characters.\n" +
                    "2. Numbers are not allowed.");
            alert.showAndWait();
        }
        if(!updatedEmployee.companyValid()) {
            alert.setHeaderText("Invalid Company. Company name\n" +
                    "Companies can be 1 or 2 words.\n" +
                    "2. Each word must start with an uppercase letter or can just be a single uppercase letter.\n" +
                    "3. Numbers are allowed.");
            alert.showAndWait();
        }
        if(!updatedEmployee.extensionValid()) {
            alert.setHeaderText("Invalid Extension.\n"+
                    "Extensions can only start with 1,2, or 3 numbers followed by a - followed 1 or 2 numbers." );
            alert.showAndWait();
        }
    }

    // Provides the confirmation pop-up to ask user if they would like to exit the application.
    // If user clicks "OK", program will terminate
    private void BtnNavExitFunction() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("Are you sure you want to exit?");
        Optional<ButtonType> choice = alert.showAndWait();
        if(!choice.get().getText().equals("OK")) {
            return;
        }

        System.exit(0);
    }

}