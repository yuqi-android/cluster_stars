package com.hd.ibrarybase.component;


public class UserMgr {
    private static volatile UserMgr instance;

    private  String token="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyZWFsTmFtZSI6IuadjuWoheaWsCIsIm5iZiI6MTcxNTkwODQwNiwiaWQiOjc2NDE5LCJleHAiOjE3MTU5MzM2MDYsInVzZXJJZCI6MTc3NzU5ODkzOTE3ODg5MzMxMiwiaWF0IjoxNzE1OTA4NDA2LCJhY2NvdW50IjoibHl4In0.G3DKouW9l6OEfwSuHa2M2HFVkeX4-O7nWh1AyDIS5FI";
    private UserMgr(){

    }
    public static UserMgr getInstance(){
        if (instance == null){
            synchronized (UserMgr.class){
                if (instance == null){
                    instance = new UserMgr();
                }
            }
        }
        return instance;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void clearUserData(){
        this.token="";
    }

}
