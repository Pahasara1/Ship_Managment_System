package lk.ijse.entity;

import lk.ijse.dto.MaterialsDTO;

public class Material extends MaterialsDTO {
    private String MaterialId;
    private String Materialname;
    private String SupplyCountry;
    private String Quality;
    private String SupplyDate;
    private String SupplyTime;

    public Material() {
    }

    public Material(String materialId, String materialname, String supplyCountry, String quality, String supplyDate, String supplyTime) {
        MaterialId = materialId;
        Materialname = materialname;
        SupplyCountry = supplyCountry;
        Quality = quality;
        SupplyDate = supplyDate;
        SupplyTime = supplyTime;
    }

    public String getMaterialId() {
        return MaterialId;
    }

    public void setMaterialId(String materialId) {
        MaterialId = materialId;
    }

    public String getMaterialname() {
        return Materialname;
    }

    public void setMaterialname(String materialname) {
        Materialname = materialname;
    }

    public String getSupplyCountry() {
        return SupplyCountry;
    }

    public void setSupplyCountry(String supplyCountry) {
        SupplyCountry = supplyCountry;
    }

    public String getQuality() {
        return Quality;
    }

    public void setQuality(String quality) {
        Quality = quality;
    }

    public String getSupplyDate() {
        return SupplyDate;
    }

    public void setSupplyDate(String supplyDate) {
        SupplyDate = supplyDate;
    }

    public String getSupplyTime() {
        return SupplyTime;
    }

    public void setSupplyTime(String supplyTime) {
        SupplyTime = supplyTime;
    }

    @Override
    public String toString() {
        return "MaterialTM{" +
                "MaterialId='" + MaterialId + '\'' +
                ", Materialname='" + Materialname + '\'' +
                ", SupplyCountry='" + SupplyCountry + '\'' +
                ", Quality='" + Quality + '\'' +
                ", SupplyDate='" + SupplyDate + '\'' +
                ", SupplyTime='" + SupplyTime + '\'' +
                '}';
    }
}
