package parser;

import org.junit.Before;
import org.junit.Test;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class VisitorTests {
    private Logger logger;
    @Before
    public void setUp() {
        logger = Logger.getLogger(JSONParserTest.class.getName());
    }

    // test SimpleParser with visitor 
    @Test
    public void testSimpleParser() throws Exception {
        Parser parser = new SimpleParser(logger);
        ParserVisitor pv = new ParserCreatorVisitor();
		List<Article> articles = parser.accept(pv, "inputs/SimpleParserInputs/test1.txt");
				
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
	        Date expectedDate1 = dateFormat.parse("2021-04-16 09:53:23.709229");
	
        Article expectedArticle1 = new Article("Assignment #2",
	                "Extend Assignment #1 to support multiple sources and to introduce source processor.", expectedDate1,
	                "https://canvas.calpoly.edu/courses/55411/assignments/274503");
	
        assertTrue(articles.get(0).equals(expectedArticle1));
    }
    
    // test JSONParser with visitor
 	@Test
 	public void testJSONParser() throws Exception {
 		Parser parser = new JSONParser(logger);
        ParserVisitor pv = new ParserCreatorVisitor();
		List<Article> articles = parser.accept(pv, "inputs/JSONParserTestInputs/test1.json");
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
}