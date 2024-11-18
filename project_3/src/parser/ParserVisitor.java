package parser;

import java.util.List;

public interface ParserVisitor {
	public List<Article> visit(JSONParser p, String source);
	public List<Article> visit(SimpleParser p, String source);
	public List<Article> visit(NewsAPIParser p, String source);
}
