package models;

public class AMIConnection {
    private final String user;
    private final String pass;
    private final String ip;
    private final int port;
    public AMIConnection(){
        this.user = "leonor";
        this.pass = "leonor2021";
        this.ip = "192.168.1.143";
        this.port = 5038;
    }
    public AMIConnection( String ip, int port, String user, String pass){
        this.user = user;
        this.pass = pass;
        this.ip = ip;
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }
}
