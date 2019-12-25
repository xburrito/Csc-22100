// Albert Chang
// Csc 22100 - F

package sample;

import java.util.ArrayList;
import java.util.List;

public class EmployeeList {

    private List<Employee> lst = new ArrayList<>();

    public List<Employee> getLst() {

        return lst;
    }

    public void createNew() {

        lst.add(new Employee());
    }

    public void setLst(List<Employee> lst) {

        this.lst = lst;
    }
}
