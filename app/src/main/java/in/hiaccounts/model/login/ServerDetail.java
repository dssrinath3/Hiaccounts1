package in.hiaccounts.model.login;

/**
 * Created by Prateek on 5/6/2017.
 */

public class ServerDetail {

    String portNumber;
    String baseUrl;
    String applicationName;

    public ServerDetail(String portNumber, String baseUrl, String applicationName) {
        this.portNumber = portNumber;
        this.baseUrl = baseUrl;
        this.applicationName = applicationName;
    }

    public String getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(String portNumber) {
        this.portNumber = portNumber;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }
}
