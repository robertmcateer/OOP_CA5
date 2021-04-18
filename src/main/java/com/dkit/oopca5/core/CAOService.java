package com.dkit.oopca5.core;

/* The CAOService class has constants to define all of the messages that are sent between the Client and Server
 */

public class CAOService
{
    public static final int PORT_NUM = 50025;
    public static final String HOSTNAME = "localhost";

    public static final String BREAKING_CHARACTER = "%%";

    public static final String REGISTER_COMMAND = "REGISTER";
    public static final String SUCCESSFUL_REGISTER = "REGISTERED";
    public static final String FAILED_REGISTER = "REG FAILED";
    public static final String Already_Registered = "REG FAILED: STUDENT Exists";
    public static final String LOGIN_SUC = "LOGGED IN";
    public static final String LOGIN_FAIL = "LOGIN FAILED";
    public static final String DISPLAY_ALL_FAIL = "NO COURSES TO DISPLAY";
    public static final String FIND_COURSE_FAIL = "NO COURSE FOUND";
    public static final String FIND_COURSE_SUCC = "COURSE FOUND";
    public static final String DISPLAY_CURRENT_FAIL = "NO CURRENT CHOICES";
    public static final String UPDATE_CURRENT = "CHOICES UPDATED";

}
