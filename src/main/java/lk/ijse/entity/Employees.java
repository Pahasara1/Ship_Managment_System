package lk.ijse.entity;

import lk.ijse.dto.EmployeesDTO;

public class Employees extends EmployeesDTO {
    private String Id;
    private String Name;
    private String Possision;
    private String NicNumber;
    private String AddDate;
    private String Time;

    public Employees() {
    }

    public Employees(String id, String name, String possision, String nicNumber, String addDate, String time) {
        Id = id;
        Name = name;
        Possision = possision;
        NicNumber = nicNumber;
        AddDate = addDate;
        Time = time;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPossision() {
        return Possision;
    }

    public void setPossision(String possision) {
        Possision = possision;
    }

    public String getNicNumber() {
        return NicNumber;
    }

    public void setNicNumber(String nicNumber) {
        NicNumber = nicNumber;
    }

    public String getAddDate() {
        return AddDate;
    }

    public void setAddDate(String addDate) {
        AddDate = addDate;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    @Override
    public String toString() {
        return "Employees{" +
                "Id='" + Id + '\'' +
                ", Name='" + Name + '\'' +
                ", Possision='" + Possision + '\'' +
                ", NicNumber='" + NicNumber + '\'' +
                ", AddDate='" + AddDate + '\'' +
                ", Time='" + Time + '\'' +
                '}';
    }
}
