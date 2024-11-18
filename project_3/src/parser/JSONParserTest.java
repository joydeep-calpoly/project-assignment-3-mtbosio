package parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import org.junit.*;


public class JSONParserTest {
	private Logger logger;
	@Before
	public void setUp() {
		logger = Logger.getLogger(JSONParserTest.class.getName());
	}

	// test multiple well formatted articles
	@Test
	public void testParseArticlesValidInput() throws Exception {
		FileInputStream testInput = new FileInputStream("inputs/JSONParserTestInputs/test1.json");
		Parser parser = new JSONParser(logger);

		List<Article> articles = parser.parse(testInput);

		assertEquals(2, articles.size());

		Article expectedArticle1 = new Article("Breaking News: Major Event Unfolds",
				"A major event has occurred in the city, affecting thousands.", new Date("20 Oct 2024 10:30:00 GMT"),
				"https://example.com/news/major-event");

		Article expectedArticle2 = new Article("Tech Advances in 2024",
				"2024 has seen a lot of technological breakthroughs.", new Date("19 Oct 2024 14:00:00 GMT"),
				"https://example.com/news/tech-advances");

		assertTrue(articles.get(0).equals(expectedArticle1));
		assertTrue(articles.get(1).equals(expectedArticle2));
	}

	// test multiple complicated successful articles
	@Test
	public void testParseArticlesValidInput2() throws Exception {
		FileInputStream testInput = new FileInputStream("inputs/JSONParserTestInputs/test6.json");
		Parser parser = new JSONParser(logger);

		List<Article> articles = parser.parse(testInput);

		assertEquals(3, articles.size());

		Article expectedArticle1 = new Article("Global Climate Change: What's Next?",
				"Scientists reveal alarming data about climate trends in the coming decades. The impacts will be widespread.",
				new Date("15 Oct 2024 16:55:00 GMT"), "https://example.com/news/climate-change");

		Article expectedArticle2 = new Article("The Rise of AI in Healthcare",
				"AI technologies are transforming healthcare, from diagnosis to treatment.",
				new Date("14 Oct 2024 09:30:00 GMT"), "https://example.com/news/ai-healthcare");

		Article expectedArticle3 = new Article("Local Farmer Wins Sustainability Award",
				"Recognition given for innovative practices that reduce waste and improve crop yield.",
				new Date("13 Oct 2024 12:10:00 GMT"), "https://example.com/news/farmer-award");

		assertEquals(expectedArticle1, articles.get(0));
		assertEquals(expectedArticle2, articles.get(1));
		assertEquals(expectedArticle3, articles.get(2));
	}

	// test missing description. Will parse 0 articles
	@Test
	public void testParseArticlesInvalidInputMissingFields() throws Exception {
		FileInputStream testInput = new FileInputStream("inputs/JSONParserTestInputs/test2.json");
		Parser parser = new JSONParser(logger);

		List<Article> articles = parser.parse(testInput);

		assertEquals(0, articles.size());
	}

	// test invalid date format. Will parse 0 articles
	@Test
	public void testParseArticlesInvalidInputInvalidDate() throws Exception {
		FileInputStream testInput = new FileInputStream("inputs/JSONParserTestInputs/test3.json");
		JSONParser parser = new JSONParser(logger);

		List<Article> articles = parser.parse(testInput);

		assertEquals(0, articles.size());
	}

	// test articles array empty. Will parse 0 articles
	@Test
	public void testParseArticlesInvalidInputNoArticles() throws Exception {
		FileInputStream testInput = new FileInputStream("inputs/JSONParserTestInputs/test4.json");
		Parser parser = new JSONParser(logger);

		List<Article> articles = parser.parse(testInput);

		assertEquals(0, articles.size());
	}

	// test missing articles array
	@Test
	public void testParseArticlesInvalidInputMissingArticlesField() throws Exception {
		FileInputStream testInput = new FileInputStream("inputs/JSONParserTestInputs/test5.json");
		Parser parser = new JSONParser(logger);

		List<Article> articles = parser.parse(testInput);

		assertEquals(0, articles.size());
	}

}
