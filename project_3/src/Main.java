import java.util.logging.Logger;

import parser.*;

public class Main {
	public static void main(String[] args) {
		String apiKey = "";
		if (args.length > 0) {
			apiKey = args[0];
		} else {
			System.out.println("Please provide an API key as an argument.");
			return;
		}

		SourceFormatConfig config = new SourceFormatConfig();
		Logger logger = Logger.getLogger(Main.class.getName());
		
		ParserVisitor pv = new ParserCreatorVisitor();
		System.out.println("Parsing " + config.getFormatType() + " article(s).\n");
		switch(config.getFormatType()) {
			case JSON:
				Parser jsonParser = new JSONParser(logger);
				jsonParser.accept(pv, config.getSourcePath());
				break;
			case SIMPLE:
				Parser simpleParser = new SimpleParser(logger);
				simpleParser.accept(pv, config.getSourcePath());
				break;
			case NEWSAPI:
				Parser newsAPIParser = new NewsAPIParser(logger);
				newsAPIParser.accept(pv, config.getSourcePath() + apiKey);
				break;
			default:
				break;
		}
	}
}
