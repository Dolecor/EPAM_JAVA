package db;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;

public class Appender extends AppenderBase<ILoggingEvent> {

    private final DB db = new DB();

    @Override
    protected void append(ILoggingEvent event) {
        db.insert(event.getLevel().levelStr, event.getMessage());
    }
}
