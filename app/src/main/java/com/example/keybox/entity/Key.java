package com.example.keybox.entity;

public class Key {

    private int keyId;
    private String keyName;
    private String username;
    private String keyContent;


    public Key(int keyId, String keyName, String username, String keyContent) {
        this.keyId = keyId;
        this.keyName = keyName;
        this.username = username;
        this.keyContent = keyContent;
    }

    public int getKeyId(){ return keyId; }

    public void setKeyId(int keyId){ this.keyId = keyId; }

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
