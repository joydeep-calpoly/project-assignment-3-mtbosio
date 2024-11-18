package parser;

import java.util.List;

public class NewsLogger {
    /**
     * Prints the details of each article in the provided list to the console.
     * For each Article, it displays the title, publication date, URL, and description.
     *
     * @param articles a List of Article objects to be printed
     */
    public static void printArticles(List<Article> articles) {
        for(Article article : articles) {
            System.out.println("Title: " + article.getTitle());
            System.out.println("At: " + article.getDateTime());
            System.out.println("URL: " + article.getUrl());
            System.out.println(article.getDescription() + "\n");
        }
    }
}
