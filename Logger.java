
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static final String LOG_FORMAT = "[%s] [%s] %s: %s";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static LogLevel logLevel = LogLevel.INFO; // Varsayılan log seviyesi

    public static void setLogLevel(LogLevel level) {
        logLevel = level;
    }

    public static void log(LogLevel level, String tag, String message) {
        if (level.ordinal() < logLevel.ordinal()) {
            return; // Belirtilen log seviyesi varsayılan log seviyesinden düşükse loglama yapma
        }

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        String log = String.format(LOG_FORMAT, timestamp, level.toString(), tag, message);

        String coloredLog = getColoredLog(level, log);
        System.out.println(coloredLog);
    }

    private static String getColoredLog(LogLevel level, String log) {
        String colorCode;
        switch (level) {
            case TRACE:
                colorCode = ANSI_CYAN;
                break;
            case DEBUG:
                colorCode = ANSI_CYAN;
                break;
            case INFO:
                colorCode = ANSI_RESET;
                break;
            case WARNING:
                colorCode = ANSI_YELLOW;
                break;
            case ERROR:
                colorCode = ANSI_RED;
                break;
            default:
                colorCode = ANSI_RESET;
                break;
        }
        return colorCode + log + ANSI_RESET;
    }

    public enum LogLevel {
        TRACE,
        DEBUG,
        INFO,
        WARNING,
        ERROR
    }
}
