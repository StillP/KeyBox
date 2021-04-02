package com.example.keybox.entity;

public class Key {

    private String keyName;
    private String username;
    private String keyContent;

    public Key(String keyName, String username, String keyContent) {
        this.keyName = keyName;
        this.username = username;
        this.keyContent = keyContent;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public String getKeyContent() {
        return keyContent;
    }

    public void setKeyContent(String keyContent) {
        this.keyContent = keyContent;
    }
}
