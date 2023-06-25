package lk.ijse.entity;

public class UseMachines {
    private String MachineId;
    private String MaterialId;
    private String EmployeeNic;
    private String Time;
    private String Date;


    public UseMachines() {

    }

    public UseMachines(String machineId, String materialId, String employeeNic,  String time,String date) {
        MachineId = machineId;
        MaterialId = materialId;
        EmployeeNic = employeeNic;
        Time = time;
        Date = date;
    }

    public String getMachineId() {
        return MachineId;
    }

    public void setMachineId(String machineId) {
        MachineId = machineId;
    }

    public String getMaterialId() {
        return MaterialId;
    }

    public void setMaterialId(String materialId) {
        MaterialId = materialId;
    }

    public String getEmployeeNic() {
        return EmployeeNic;
    }

    public void setEmployeeNic(String employeeNic) {
        EmployeeNic = employeeNic;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    @Override
    public String toString() {
        return "UsingMachinesTM{" +
                "MachineId='" + MachineId + '\'' +
                ", MaterialId='" + MaterialId + '\'' +
                ", EmployeeNic='" + EmployeeNic + '\'' +
                ", Time='" + Time + '\'' +
                ", Date='" + Date + '\'' +
                '}';
    }
}
