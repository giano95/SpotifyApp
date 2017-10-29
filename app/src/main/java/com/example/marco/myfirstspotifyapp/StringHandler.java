package com.example.marco.myfirstspotifyapp;


public class StringHandler {

    private static final int NOT_FOUND = -1;

    public static String setProperly(String oldString){

        String newString;
        int startingPos = oldString.indexOf('(');
        int endingPos = oldString.indexOf(')');

        if(startingPos == NOT_FOUND || endingPos == NOT_FOUND)
            return oldString;
        else
            newString = oldString.replace(oldString.substring(startingPos, endingPos + 1), "");

        return newString;
    }

    public static String getTipsString(String oldName, int n){

        String newName;

        if( n >= oldName.length())
            return oldName;
        else
            newName = oldName.substring(0, n + 1);

        return newName;
    }

    public static boolean compare(String stringOne, String stringTwo){

        if(stringOne.toUpperCase().equals(stringTwo.toUpperCase()))
            return true;
        else
            return false;
    }
}
