package hackatonauts.fireshield.listener.helpers;

import java.util.Calendar;

public class Logger {
    static Calendar calendar = Calendar.getInstance();

    public static void logDebug(String messege) {
        System.out.println("[Log DEBUG] " + calendar.toInstant().toString() + ": " + messege);
    }

    public static void logInfo(String messege) {
        System.out.println("[Log INFO] " + calendar.toInstant().toString() + ": " + messege);
    }

    public static void logError(String messege) {
        System.out.println("[Log ERROR] " + calendar.toInstant().toString() + ": " + messege);
    }

}
