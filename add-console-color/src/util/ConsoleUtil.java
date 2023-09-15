package util;

public class ConsoleUtil {


    private static final String COLOR_PREFIX = "LLZ -- ";

    public static void printlnWithGreen(String str) {
        printlnWithColor(getStrWithGreen(str));
    }

    private static void printlnWithColor(String str) {
        System.out.println(str);
    }

    private static String getStrWithGreen(String str) {
        return ConsoleColors.GREEN + COLOR_PREFIX +  str + ConsoleColors.RESET;
    }
}
