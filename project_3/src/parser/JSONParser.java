package parser;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class JSONParser extends Parser {
    public JSONParser(Logger logger) {
        super(logger);
    }

    /**
     * Parses the JSON data from the given InputStream to generate a list of Article objects.
     * Each Article is populated with relevant information from the JSON representation.
     *
     * @param inputStream an InputStream containing JSON data for a collection of News API articles
     * @return a List of Article objects parsed from the JSON data, or an empty list if parsing fails
     * @see Article
     */
    @Override
    public List<Article> parse(InputStream inputStream) throws IOException {
        try{
            NewsData newsData = objectMapper.readValue(inputStream, NewsData.class);
            if(newsData.getArticles() == null){
                return Collections.emptyList();
            }
            return newsData.getArticles();
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
