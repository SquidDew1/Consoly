package me.squiddew.consoly.console;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.layout.PatternLayout;

public class ConsoleReader {

    public static String LOG = "";

    public static void read(){
        var root = (Logger) LogManager.getRootLogger();
        var layout = PatternLayout.newBuilder().setPattern("[%d{HH:mm:ss}] [%t/%level]: %msg%n").build();

        var appender = new AbstractAppender("ShortAppender", null, layout, true, Property.EMPTY_ARRAY){
            @Override
            public void append(LogEvent event) {
                LOG += getLayout().toSerializable(event).toString();
                if (ConsoleWindow.textArea != null){
                    ConsoleWindow.textArea.setText(LOG);
                    ConsoleWindow.textArea.setCaretPosition(ConsoleWindow.textArea.getDocument().getLength());
                }
            }
        };
        appender.start();
        root.addAppender(appender);
    }
}
