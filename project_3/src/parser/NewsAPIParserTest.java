package parser;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class NewsAPIParserTest {
    private Logger logger;
    @Before
    public void setUp() {
        logger = Logger.getLogger(JSONParserTest.class.getName());
    }

    // test 3 well-formatted articles through mocking
    @Test
    public void testWellFormattedArticles() throws Exception {
        NewsAPIFetcher mockFetcher = Mockito.mock(NewsAPIFetcher.class);

        InputStream mockApiJson = new FileInputStream("inputs/NewsAPITestInputs/test1.txt");

        when(mockFetcher.fetchTopHeadlines("example")).thenReturn(mockApiJson);

        Parser parser = new NewsAPIParser(logger);
        List<Article> articles = parser.parse(mockFetcher.fetchTopHeadlines("example"));

        // Assertions
        assertEquals(3, articles.size());
        assertEquals("The latest on the coronavirus pandemic and vaccines: Live updates - CNN", articles.get(0).getTitle());
        assertEquals("https://www.cnn.com/world/live-news/coronavirus-pandemic-vaccine-updates-03-24-21/index.html", articles.get(0).getUrl());
        assertEquals("The coronavirus pandemic has brought countries to a standstill. Meanwhile, vaccinations have already started in some countries as cases continue to rise. Follow here for the latest.", articles.get(0).getDescription());
    }

    // test missing dates in 3 articles. Will parse 0 articles
    @Test
    public void testMissingDate() throws Exception {
        NewsAPIFetcher mockFetcher = Mockito.mock(NewsAPIFetcher.class);

        InputStream mockApiJson = new FileInputStream("inputs/NewsAPITestInputs/test2.txt");

        when(mockFetcher.fetchTopHeadlines("example")).thenReturn(mockApiJson);

        Parser parser = new NewsAPIParser(logger);
        List<Article> articles = parser.parse(mockFetcher.fetchTopHeadlines("example"));

        // Assertions
        assertEquals(0, articles.size());
    }

    // test missing description in 3 articles. Will parse 0 articles
    @Test
    public void testMissingDescription() throws Exception {
        NewsAPIFetcher mockFetcher = Mockito.mock(NewsAPIFetcher.class);

        InputStream mockApiJson = new FileInputStream("inputs/NewsAPITestInputs/test3.txt");

        when(mockFetcher.fetchTopHeadlines("example")).thenReturn(mockApiJson);

        Parser parser = new NewsAPIParser(logger);
        List<Article> articles = parser.parse(mockFetcher.fetchTopHeadlines("example"));

        // Assertions
        assertEquals(0, articles.size());
    }

    // test missing title in 3 articles. Will parse 0 articles
    @Test
    public void testMissingTitle() throws Exception {
        NewsAPIFetcher mockFetcher = Mockito.mock(NewsAPIFetcher.class);

        InputStream mockApiJson = new FileInputStream("inputs/NewsAPITestInputs/test4.txt");

        when(mockFetcher.fetchTopHeadlines("example")).thenReturn(mockApiJson);

        Parser parser = new NewsAPIParser(logger);
        List<Article> articles = parser.parse(mockFetcher.fetchTopHeadlines("example"));

        // Assertions
        assertEquals(0, articles.size());
    }

    // test missing url in 3 articles. Will parse 0 articles
    @Test
    public void testMissingURL() throws Exception {
        NewsAPIFetcher mockFetcher = Mockito.mock(NewsAPIFetcher.class);

        InputStream mockApiJson = new FileInputStream("inputs/NewsAPITestInputs/test5.txt");

        when(mockFetcher.fetchTopHeadlines("example")).thenReturn(mockApiJson);

        Parser parser = new NewsAPIParser(logger);
        List<Article> articles = parser.parse(mockFetcher.fetchTopHeadlines("example"));

        // Assertions
        assertEquals(0, articles.size());
    }

    // test missing articles field. Will parse 0 articles
    @Test
    public void testMissingArticles() throws Exception {
        NewsAPIFetcher mockFetcher = Mockito.mock(NewsAPIFetcher.class);

        InputStream mockApiJson = new FileInputStream("inputs/NewsAPITestInputs/test6.txt");

        when(mockFetcher.fetchTopHeadlines("example")).thenReturn(mockApiJson);

        Parser parser = new NewsAPIParser(logger);
        List<Article> articles = parser.parse(mockFetcher.fetchTopHeadlines("example"));

        // Assertions
        assertEquals(0, articles.size());
    }

    // test articles with [Removed] title. Will parse 0 articles
    @Test
    public void testRemovedArticles() throws Exception {
        NewsAPIFetcher mockFetcher = Mockito.mock(NewsAPIFetcher.class);

        InputStream mockApiJson = new FileInputStream("inputs/NewsAPITestInputs/test7.txt");

        when(mockFetcher.fetchTopHeadlines("example")).thenReturn(mockApiJson);

        Parser parser = new NewsAPIParser(logger);
        List<Article> articles = parser.parse(mockFetcher.fetchTopHeadlines("example"));

        // Assertions
        assertEquals(0, articles.size());
    }
}
