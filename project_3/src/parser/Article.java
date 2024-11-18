package parser;

import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties({ "source", "urlToImage", "content", "author" })
public final class Article {
	private final String title;
	private final String description;
	private final Date dateTime;
	private final String url;
	
	@JsonCreator
	public Article(	@JsonProperty("title") String title,
					@JsonProperty("description") String description, 
					@JsonProperty("publishedAt") Date dateTime, 
					@JsonProperty("url") String url) {
		this.title = title;
		this.description = description;
		this.dateTime = (Date) dateTime.clone();
		this.url = url;
	}
	
	/**
    * Returns the title of the article.
    * 
    * @return the article's title
    */
    public String getTitle() {
        return title;
    }

    /**
    * Returns the description of the article.
    * 
    * @return the article's description
    */
    public String getDescription() {
        return description;
    }

    /**
    * Returns the publication date and time of the article.
    * 
    * @return the publication date and time
    */
    public Date getDateTime() {
        return (Date) dateTime.clone();
    }

    /**
    * Returns the URL of the article
    * 
    * @return the article's URL
    */
    public String getUrl() {
        return url;
    }

	/**
	* Determines whether the specified object is equal to this article.
	* Two articles are considered equal if they have the same title, description, publication date, and URL.
	* 
	* @param obj the object to compare with this article
	* @return true if the specified object is equal to this article, false otherwise
	*/
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Article article = (Article) obj;

        return (Objects.equals(title, article.title)) &&
               (Objects.equals(description, article.description)) &&
               (Objects.equals(dateTime, article.dateTime)) &&
               (Objects.equals(url, article.url));
    }
}