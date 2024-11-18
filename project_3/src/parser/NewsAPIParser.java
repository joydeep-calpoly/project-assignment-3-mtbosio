package parser;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public final class NewsAPIParser extends Parser{
	public NewsAPIParser(Logger logger) {
		super(logger);
	}

	/**
	 * Parses the JSON data from the given InputStream to generate a list of Article objects.
	 * Each Article is populated with relevant information from the JSON representation.
	 * Articles with a title of "[Removed]" are excluded from the resulting list.
	 *
	 * @param inputStream an InputStream containing JSON data for a collection of News API articles
	 * @return a List of Article objects parsed from the JSON data, or an empty list if parsing fails
	 * @see Article
	 */
	@Override
	public List<Article> parse(InputStream inputStream){
		try {
			NewsData newsData = objectMapper.readValue(inputStream, NewsData.class);
			return  newsData.getArticles()
					.stream()
					.filter(article -> !"[Removed]".equals(article.getTitle())) // remove articles that are null. They have a "[Removed]" title
					.collect(Collectors.toList());
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
