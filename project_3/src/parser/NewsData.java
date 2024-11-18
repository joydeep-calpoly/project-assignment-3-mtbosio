package parser;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties({ "status", "totalResults" })
public class NewsData {
    private List<Article> articles;
    /**
     * Returns the list of articles
     *
     * @return the list of Article objects
     */
    public List<Article> getArticles() {
        return articles;
    }
}