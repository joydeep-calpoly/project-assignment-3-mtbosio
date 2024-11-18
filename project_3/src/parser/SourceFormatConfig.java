package parser;

import java.util.Scanner;

public class SourceFormatConfig {
	public enum Source {
        FILE, URL
    }

    public enum Format {
        NEWSAPI, SIMPLE, JSON
    }
    
    private Source sourceType;
    private Format formatType;
    private String sourcePath;

    public SourceFormatConfig() {
        getUserInput();
    }

    private void getUserInput() {
        Scanner scanner = new Scanner(System.in);
        
        // get format first
        System.out.println("Enter the format type (newsapi/simple/json): ");
        String formatInput = scanner.nextLine().trim().toLowerCase();

        // get source input, default to url if newsapi
        String sourceInput;
        if(!formatInput.equals("newsapi")){
        	System.out.println("Enter the source type (file/url): ");
        	sourceInput = scanner.nextLine().trim().toLowerCase();
        } else {
        	sourceInput = "url";
        }
        
        switch (sourceInput) {
            case "file":
                sourceType = Source.FILE;
                break;
            case "url":
                sourceType = Source.URL;
                break;
            default:
                System.out.println("Invalid source type. Defaulting to 'file'.");
                sourceType = Source.FILE;
        }
        if (sourceType == Source.FILE) {
            System.out.println("Enter the file path: ");
            sourcePath = scanner.nextLine().trim();
        } else {
            System.out.println("Enter the URL: ");
            sourcePath = scanner.nextLine().trim();
        }

        switch (formatInput) {
            case "newsapi":
                formatType = Format.NEWSAPI;
                break;
            case "simple":
                if (sourceType == Source.URL) {
                    System.out.println("Simple format is not supported for URL sources. Defaulting to 'newsapi'.");
                    formatType = Format.NEWSAPI;
                } else {
                    formatType = Format.SIMPLE;
                }
                break;
            case "json":
            	formatType = Format.JSON;
            	break;
            default:
                System.out.println("Invalid format type. Defaulting to 'newsapi'.");
                formatType = Format.NEWSAPI;
        }
        scanner.close();
    }
    
    @Override
    public String toString() {
        return "SourceType: " + sourceType + ", FormatType: " + formatType + ", SourcePath: " + sourcePath;
    }
    
    /**
     * Returns the source type specified by the user.
     *
     * @return the Source enum representing the type of source (e.g., file or URL)
     */
    public Source getSourceType() {
        return sourceType;
    }

    /**
     * Returns the format type specified by the user.
     *
     * @return the Format enum representing the type of format (e.g., newsapi, simple, or JSON)
     */
    public Format getFormatType() {
        return formatType;
    }
    
    /**
     * Returns the path or URL of the source specified by the user.
     *
     * @return a string representing the file path or URL of the source
     */
    public String getSourcePath() {
        return sourcePath;
    }
    

}
