package parser;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class ParserCreatorVisitor implements ParserVisitor{
	/**
	 * Visits a JSONParser to parse articles from the specified source file.
	 *
	 * @param p      the JSONParser instance used for parsing
	 * @param source the file path of the JSON source to parse
	 * @return a list of Article objects parsed from the JSON file, or null if an error occurs
	 */
	@Override
	public List<Article> visit(JSONParser p, String source) {
		try {
			List<Article> jsonArticles = p.parse(new FileInputStream(source));
			NewsLogger.printArticles(jsonArticles);
			return jsonArticles;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Visits a SimpleParser to parse articles from the specified source file.
	 *
	 * @param p      the SimpleParser instance used for parsing
	 * @param source the file path of the JSON source to parse
	 * @return a list of Article objects parsed from the JSON file, or null if an error occurs
	 */
	@Override
	public List<Article> visit(SimpleParser p, String source) {
		try {
			List<Article> simpleArticles = p.parse(new FileInputStream(source));
			NewsLogger.printArticles(simpleArticles);
			return simpleArticles;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Visits a NewsAPIParser to parse articles fetched from the News API.
	 *
	 * @param p      the NewsAPIParser instance used for parsing
	 * @param source the URL or API endpoint from which to fetch and parse top headlines
	 * @return a list of Article objects parsed from the News API response, or null if an error occurs
	 */
	@Override
	public List<Article> visit(NewsAPIParser p, String source) {
		NewsAPIFetcher fetcher = new NewsAPIFetcher();
		try {
			List<Article> topHeadlines = p.parse(fetcher.fetchTopHeadlines(source));
			NewsLogger.printArticles(topHeadlines);
			return topHeadlines;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	

}
