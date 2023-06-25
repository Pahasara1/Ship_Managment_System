package lk.ijse.tdm;

public class GenerateOtpTM {
    private String gmail;
    private String otp;

    public GenerateOtpTM() {
    }

    public GenerateOtpTM(String gmail, String otp) {
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
        return "GenerateOtpTM{" +
                "gmail='" + gmail + '\'' +
                ", otp='" + otp + '\'' +
                '}';
    }
}
