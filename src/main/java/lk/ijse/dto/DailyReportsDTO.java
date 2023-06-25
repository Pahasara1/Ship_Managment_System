package lk.ijse.dto;

import lk.ijse.entity.DailyReports;

public class DailyReportsDTO extends DailyReports {
    private String ReportId;
    private String EmployeeNic;
    private String TemperatureLevel;
    private String MachineRunning;

    public DailyReportsDTO() {
    }

    public DailyReportsDTO(String reportId, String employeeNic, String temperatureLevel, String machineRunning) {
        ReportId = reportId;
        EmployeeNic = employeeNic;
        TemperatureLevel = temperatureLevel;
        MachineRunning = machineRunning;
    }

    public String getReportId() {
        return ReportId;
    }

    public void setReportId(String reportId) {
        ReportId = reportId;
    }

    public String getEmployeeNic() {
        return EmployeeNic;
    }

    public void setEmployeeNic(String employeeNic) {
        EmployeeNic = employeeNic;
    }

    public String getTemperatureLevel() {
        return TemperatureLevel;
    }

    public void setTemperatureLevel(String temperatureLevel) {
        TemperatureLevel = temperatureLevel;
    }

    public String getMachineRunning() {
        return MachineRunning;
    }

    public void setMachineRunning(String machineRunning) {
        MachineRunning = machineRunning;
    }

    @Override
    public String toString() {
        return "DailyReports{" +
                "ReportId='" + ReportId + '\'' +
                ", EmployeeNic='" + EmployeeNic + '\'' +
                ", TemperatureLevel='" + TemperatureLevel + '\'' +
                ", MachineRunning='" + MachineRunning + '\'' +
                '}';
    }
}
