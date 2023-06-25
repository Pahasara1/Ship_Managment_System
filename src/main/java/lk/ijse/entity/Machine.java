package lk.ijse.entity;

import lk.ijse.dto.MachineDTO;

public class Machine extends MachineDTO {

    private String MachineId;
    private String MachineName;
    private String Quality;
    private String RepairCount;
    private String Date;
    private String Time;

    public Machine() {

    }

    public Machine(String machineId, String machineName, String quality, String repairCount, String date, String time) {
        MachineId = machineId;
        MachineName = machineName;
        Quality = quality;
        RepairCount = repairCount;
        Date = date;
        Time = time;
    }


    public String getMachineId() {
        return MachineId;
    }

    public void setMachineId(String machineId) {
        MachineId = machineId;
    }

    public String getMachineName() {
        return MachineName;
    }

    public void setMachineName(String machineName) {
        MachineName = machineName;
    }

    public String getQuality() {
        return Quality;
    }

    public void setQuality(String quality) {
        Quality = quality;
    }

    public String getRepairCount() {
        return RepairCount;
    }

    public void setRepairCount(String repairCount) {
        RepairCount = repairCount;
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
        return "Machine{" +
                "MachineId='" + MachineId + '\'' +
                ", MachineName='" + MachineName + '\'' +
                ", Quality='" + Quality + '\'' +
                ", RepairCount=" + RepairCount +
                ", Date='" + Date + '\'' +
                ", Time='" + Time + '\'' +
                '}';
    }
}
