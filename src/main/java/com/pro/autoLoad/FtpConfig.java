package com.pro.autoLoad;

import java.io.Serializable;

public class FtpConfig implements Serializable {

    private int port;
    private String host;
    private String user;
    private String password;
    public FtpConfig(){}
    public FtpConfig(int port, String host, String user, String password) {
        this.port = port;
        this.host = host;
        this.user = user;
        this.password = password;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "FtpConfig{" +
                "port=" + port +
                ", host='" + host + '\'' +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
