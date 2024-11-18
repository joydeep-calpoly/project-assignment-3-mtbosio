package parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import org.junit.*;


public class SimpleParserTest {
    private Logger logger;

    @Before
    public void setUp() {
        logger = Logger.getLogger(JSONParserTest.class.getName());
    }

    // test well formatted simple format
    @Test
    public void testParseArticlesValidInput() throws Exception {
        FileInputStream testInput = new FileInputStream("inputs/SimpleParserInputs/test1.txt");
        Parser parser = new SimpleParser(logger);

        List<Article> articles = parser.parse(testInput);

        assertEquals(1, articles.size());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
        Date expectedDate1 = dateFormat.parse("2021-04-16 09:53:23.709229");

        Article expectedArticle1 = new Article("Assignment #2",
                "Extend Assignment #1 to support multiple sources and to introduce source processor.", expectedDate1,
                "https://canvas.calpoly.edu/courses/55411/assignments/274503");

        assertTrue(articles.get(0).equals(expectedArticle1));
    }

    // test missing date. Will parse 0 articles
    @Test
    public void testMissingDate() throws Exception {
        FileInputStream testInput = new FileInputStream("inputs/SimpleParserInputs/test2.txt");
        Parser parser = new SimpleParser(logger);

        List<Article> articles = parser.parse(testInput);

        assertEquals(0, articles.size());
    }

    // test missing description. Will parse 0 articles
    @Test
    public void testMissingDescription() throws Exception {
        FileInputStream testInput = new FileInputStream("inputs/SimpleParserInputs/test3.txt");
        Parser parser = new SimpleParser(logger);

        List<Article> articles = parser.parse(testInput);

        assertEquals(0, articles.size());
    }

    // test missing title. Will parse 0 articles
    @Test
    public void testMissingTitle() throws Exception {
        FileInputStream testInput = new FileInputStream("inputs/SimpleParserInputs/test4.txt");
        Parser parser = new SimpleParser(logger);

        List<Article> articles = parser.parse(testInput);

        assertEquals(0, articles.size());
    }

    // test missing url. Will parse 0 articles
    @Test
    public void testMissingURL() throws Exception {
        FileInputStream testInput = new FileInputStream("inputs/SimpleParserInputs/test5.txt");
        Parser parser = new SimpleParser(logger);

        List<Article> articles = parser.parse(testInput);

        assertEquals(0, articles.size());
    }

    // test empty object, Will parse 0 articles
    @Test
    public void testEmptyObject() throws Exception {
        FileInputStream testInput = new FileInputStream("inputs/SimpleParserInputs/test6.txt");
        Parser parser = new SimpleParser(logger);

        List<Article> articles = parser.parse(testInput);

        assertEquals(0, articles.size());
    }

}