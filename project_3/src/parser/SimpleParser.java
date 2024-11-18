package parser;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class SimpleParser extends Parser {
    public SimpleParser(Logger logger) {
        super(logger);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS"));
    }

    /**
     * Parses the JSON data from the given InputStream to generate a list of Article objects.
     * Each Article is populated with relevant information from the JSON representation.
     *
     * @param inputStream an InputStream containing JSON data for a collection of News API articles
     * @return a List of Article objects parsed from Simple Formatted JSON, or an empty list if parsing fails
     * @see Article
     */
    @Override
    public List<Article> parse(InputStream inputStream) {
        try {
            Article article = objectMapper.readValue(inputStream, Article.class);
            return List.of(article);
        } catch (Exception e) {
            logger.warning("Failed to parse articles: " + e.getMessage());
            return Collections.emptyList();
        }
    }
    
    /**
	 * Accepts a ParserVisitor and a source string, delegating the parsing task
	 * to the visitor. 
	 *
	 * @param p      the ParserVisitor instance that will visit this parser
	 * @param source the source (file path or URL) from which to parse articles
	 * @return a list of Article objects parsed from the specified source
	 */
    @Override
	public List<Article> accept(ParserVisitor p, String source) {
		return p.visit(this, source);		
	}
}
