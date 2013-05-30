package com.project.parsers.data;

public class LoginData
{
    private static String userName;

    public static String getUserName()
    {
        return userName;
    }

    public static void setUserName(String userName)
    {
        LoginData.userName = userName;
    }

}
