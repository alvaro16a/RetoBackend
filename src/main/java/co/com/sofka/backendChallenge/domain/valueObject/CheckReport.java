package co.com.sofka.backendChallenge.domain.valueObject;

public class CheckReport {

    private Boolean result;
    private String details;

    public CheckReport(Boolean result, String details) {
        this.result = result;
        this.details = details;
    }

    public CheckReport() {

    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
