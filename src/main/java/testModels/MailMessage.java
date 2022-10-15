package testModels;

public class MailMessage {
    private final String from;
    private final String to;

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getMessage() {
        return message;
    }

    private final String message;

    public MailMessage(String from, String to, String message) {
        this.from = from;
        this.to = to;
        this.message = message;
    }
}
