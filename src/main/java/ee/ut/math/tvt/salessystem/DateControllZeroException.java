package ee.ut.math.tvt.salessystem;

public class DateControllZeroException extends RuntimeException {

    public DateControllZeroException() {
    }

    public DateControllZeroException(String message) {
        super(message);
    }

    public DateControllZeroException(String message, Throwable cause) {
        super(message, cause);
    }
}
