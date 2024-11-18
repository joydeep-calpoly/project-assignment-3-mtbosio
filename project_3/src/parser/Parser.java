package parser;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public abstract class Parser {
    final ObjectMapper objectMapper = new ObjectMapper();;
    final Logger logger;

    public Parser(Logger logger) {
        this.logger = logger;
        setLoggerSettings(logger);
        objectMapper.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, true);
    }

    private void setLoggerSettings(Logger logger) {
        logger.setUseParentHandlers(false);
        FileHandler fileHandler;
        try {
            fileHandler = new FileHandler(getClass().getName() + ".log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (SecurityException | IOException e) {
            System.err.println("Failed to set up logger: " + e.getMessage());
        }
    }
    public abstract List<Article> parse(InputStream inputStream) throws IOException;
    
    public abstract List<Article> accept(ParserVisitor p, String source);
}
