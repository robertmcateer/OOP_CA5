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
}
