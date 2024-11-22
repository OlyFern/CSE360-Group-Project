package com.group43.cse360_project;

public class User {
    private String asuID;
    private String password;
    private UserType type;
    private String name;
    private String email;

    public User(String asuID, String password, UserType type, String name, String email) {
        this.asuID = asuID;
        this.password = password;
        this.type = type;
        this.name = name;
        this.email = email;
    }
    //Getters
    public String getAsuID() {
        return asuID;
    }

    public UserType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }


}
