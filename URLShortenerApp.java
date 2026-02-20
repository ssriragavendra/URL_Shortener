import java.util.HashMap;
import java.util.Map;
import java.util.Random;

interface URLService {
    String shortenURL(String longUrl);
    String getLongURL(String shortCode) throws Exception;
    double getUsageCount(String shortCode);
}

class MyURLShortener implements URLService {
    private Map<String, String> urlMap = new HashMap<>(); 
    private String domain = "https://citi.short/";

    @Override
    public String shortenURL(String longUrl) {
        String charSet = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM0123456789";
        StringBuilder shortCode = new StringBuilder();
        Random rnd = new Random();
        while (shortCode.length() < 6) {
            shortCode.append(charSet.charAt(rnd.nextInt(charSet.length())));
        }
        urlMap.put(shortCode.toString(), longUrl);
        return domain + shortCode.toString();
    }

    @Override
    public double getUsageCount(String shortCode) {
        return 0.0; 
    }

    @Override
    public String getLongURL(String shortCode) throws Exception {
        if (!urlMap.containsKey(shortCode)) {
            throw new Exception("Short URL not found!");
        }
        return urlMap.get(shortCode);
    }
}

public class URLShortenerApp {
    public static void main(String[] args) {
        URLService myService = new MyURLShortener();
        String longUrl = "https://www.citigroup.com/global/careers";
        String shortUrl = myService.shortenURL(longUrl);
        
        System.out.println("Original: " + longUrl);
        System.out.println("Shortened: " + shortUrl);
    }
}
