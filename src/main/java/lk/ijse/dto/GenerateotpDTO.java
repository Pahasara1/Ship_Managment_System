package lk.ijse.dto;

public class GenerateotpDTO {
    private String gmail;
    private String otp;

    public GenerateotpDTO() {
    }

    public GenerateotpDTO(String gmail, String otp) {
        this.gmail = gmail;
        this.otp = otp;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    @Override
    public String toString() {
        return "Generateotp{" +
                "gmail='" + gmail + '\'' +
                ", otp='" + otp + '\'' +
                '}';
    }
}

