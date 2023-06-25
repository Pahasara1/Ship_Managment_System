package lk.ijse.dto;


import lk.ijse.entity.Material;

public class MaterialsDTO{

    private String MaterialId;
    private String MaterialName;
    private String SupplyCountry;
    private String Quality;
    private String SupplyDate;
    private String SupplyTime;

    public MaterialsDTO() {

    }

    public MaterialsDTO(String materialId, String materialName, String supplyCountry, String quality, String supplyDate, String supplyTime) {
        MaterialId = materialId;
        MaterialName = materialName;
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
        return MaterialName;
    }

    public void setMaterialname(String materialname) {
        MaterialName = materialname;
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
        return "Materials{" +
                "MaterialId='" + MaterialId + '\'' +
                ", Materialname='" + MaterialName + '\'' +
                ", SupplyCountry='" + SupplyCountry + '\'' +
                ", Quality='" + Quality + '\'' +
                ", SupplyDate='" + SupplyDate + '\'' +
                ", SupplyTime='" + SupplyTime + '\'' +
                '}';
    }
}
