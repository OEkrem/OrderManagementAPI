package com.oekrem.SpringMVCBackEnd.email;

public class EMailUtil {

    public static String formatEmailBody(String name, String message) {
        return "Hello " + name + ",\n\n" + message + "\n\nBest Regards, OEkrem";
    }

}
