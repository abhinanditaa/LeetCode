
import java.util.HashMap;
import java.util.Map;

public class Codec {
    private final Map<String, String> urlMap = new HashMap<>();
    private int id = 0;

    public String encode(String longUrl) {
        String shortUrl = "http://tinyurl.com/" + (++id);
        urlMap.put(shortUrl, longUrl);
        return shortUrl;
    }

    public String decode(String shortUrl) {
        return urlMap.get(shortUrl);
    }
}
