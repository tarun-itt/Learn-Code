package logging;

import interfaces.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileLogger implements Logger {
    private final String logFilePath;
    private final StringBuilder buffer;
    private final DateTimeFormatter timestampFormat;

    public FileLogger(String logFilePath) {
        this.logFilePath = logFilePath;
        this.buffer = new StringBuilder();
        this.timestampFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    @Override
    public void log(String message) {
        String timestamp = LocalDateTime.now().format(timestampFormat);
        buffer.append("[").append(timestamp).append("] ").append(message).append("\n");
    }

    @Override
    public void flush() {
        try (FileWriter writer = new FileWriter(logFilePath)) {
            writer.write(buffer.toString());
        } catch (IOException e) {
            System.err.println("Failed to write log file: " + e.getMessage());
        }
    }
}
