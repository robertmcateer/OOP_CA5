package com.dkit.oopca5.client;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexChecker
{
    public static boolean isValidCourse(String id)
    {
        String regex = "[A-Z]{2}[0-9]{3}";
        // Compile the ReGex
        Pattern p = Pattern.compile(regex);
        String cao1 = ""+id;
        Matcher m = p.matcher(cao1);

        return m.matches();
    }

    public static boolean isValidCao(int Cao)
    {
        String regex = "[0-9]{5}";
        // Compile the ReGex
        Pattern p = Pattern.compile(regex);
        String cao1 = ""+Cao;
        Matcher m = p.matcher(cao1);

        return m.matches();
    }
    public static boolean isValidDOB(String DOB)
    {

        String regex = "(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))";
        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        Matcher m = p.matcher(DOB);

        return m.matches();
    }

    public static boolean isValidPass(String pass)
    {

        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        Matcher m = p.matcher(pass);

        return m.matches();
    }


}
