package troubleShootSearch.util;

/**
 * Global Logger class
 */
public final class MyLogger{

    /**
     * Enum representing possible debug levels
     */
    public enum DebugLevel { NO_OUTPUT, WARNINGS, SUCCESS, INPUT_READ, OPERATION_ADD }

    /**
     * Default debug level is NO_OUTPUT (0)
     */
    private static DebugLevel debugLevel = DebugLevel.NO_OUTPUT;

    /**
     * Accepts an integer and sets debug value based on that integer.
     *
     * @param levelIn Input integer
     */
    public static void setDebugValue(int levelIn) {

        switch (levelIn) {
            case 4:
                debugLevel = DebugLevel.OPERATION_ADD;
                break;

            case 3:
                debugLevel = DebugLevel.INPUT_READ;
                break;

            case 2:
                debugLevel = DebugLevel.SUCCESS;
                break;

            case 1:
                debugLevel = DebugLevel.WARNINGS;
                break;

            default:
                debugLevel = DebugLevel.NO_OUTPUT;
                break;
        }
    }

    /**
     * Sets the debug value to the given enum member.
     *
     * @param levelIn enum member
     */
    public static void setDebugValue(DebugLevel levelIn) {
        debugLevel = levelIn;
    }

    /**
     * Writes output to the screen if correct debug level is set.
     *
     * @param message Message to be written
     * @param levelIn Debug Level to write message at
     */
    public static void writeMessage(String message, DebugLevel levelIn) {
        if (levelIn == debugLevel)
            System.out.println(message);
    }

    public String toString() {
        return "The debug level has been set to the following " + debugLevel;
    }
}