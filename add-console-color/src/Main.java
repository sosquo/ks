import util.ConsoleColors;
import util.ConsoleUtil;

public class Main {

    public static void main(String[] args) {
        System.out.println("我是一条log");
        /* 日志字体颜色 */
        System.out.println(ConsoleColors.GREEN + "我是一条log,我有颜色吗" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.WHITE + "我是一条log,我有颜色吗" + ConsoleColors.RESET);
        /* 日志背景颜色 */
        System.out.println(ConsoleColors.YELLOW_BACKGROUND + "我是一条log,我有背景吗" + ConsoleColors.RESET);
        /* 日志字体加粗颜色 */
        System.out.println(ConsoleColors.RED_BOLD + "我是一条特殊log" + ConsoleColors.RESET);
        /* 日志字体加粗颜色 */
        System.out.println(ConsoleColors.RED_UNDERLINED + "我是一条特殊log" + ConsoleColors.RESET);

        ConsoleUtil.printlnWithGreen("要想生活过得去");
    }

}