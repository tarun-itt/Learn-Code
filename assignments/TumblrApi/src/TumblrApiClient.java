package tumblrapi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Handles communication with the Tumblr API.
 * Responsible for fetching data and parsing the response.
 */
public class TumblrApiClient {

    private static final String API_URL_TEMPLATE = "https://%s.tumblr.com/api/read/json?type=photo&num=%d&start=%d";

    public String fetchJson(String blogName, int start, int num) throws Exception {
        String urlString = String.format(API_URL_TEMPLATE, blogName, num, start);
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

        StringBuilder sb = new StringBuilder();
        String output;
        while ((output = br.readLine()) != null) {
            sb.append(output);
        }

        conn.disconnect();

        String response = sb.toString();

        if (response.startsWith("var tumblr_api_read = ")) {
            response = response.replace("var tumblr_api_read = ", "");
        }
        if (response.endsWith(";")) {
            response = response.substring(0, response.length() - 1);
        }
        return response;
    }

    public Blog parseBlogInfo(String jsonResponse) {
        String title = extractValue(jsonResponse, "\"title\":\"", "\"");
        String name = extractValue(jsonResponse, "\"name\":\"", "\"");
        String description = extractValue(jsonResponse, "\"description\":\"", "\"");
        String totalPostsStr = extractValue(jsonResponse, "\"posts-total\":", ",");
        
        int totalPosts = 0;
        try {
            totalPosts = Integer.parseInt(totalPostsStr.trim());
        } catch (NumberFormatException e) {
        }

        return new Blog(title, name, description, totalPosts);
    }

    public List<Post> parsePosts(String jsonResponse) {
        List<Post> posts = new ArrayList<>();
        
        int postsStartIndex = jsonResponse.indexOf("\"posts\":[");
        if (postsStartIndex == -1) return posts;
        
        String postsContent = jsonResponse.substring(postsStartIndex);
        
        // Split by `{"id":` which marks the start of a post object in the API response
        String[] rawPosts = jsonResponse.split("\\{\"id\":");
        
        // Skipped the first element as it is the header before the first post
        for (int i = 1; i < rawPosts.length; i++) {
            String rawPost = rawPosts[i];

            String id = rawPost.split(",")[0].replace("\"", "");

            List<String> images = extractAllMatches(rawPost, "\"photo-url-1280\":\"", "\"");
            
            if (!images.isEmpty()) {
                posts.add(new Post(id, images));
            }
        }
        
        return posts;
    }

    private String extractValue(String source, String startMarker, String endMarker) {
        int start = source.indexOf(startMarker);
        if (start == -1) return "N/A";
        start += startMarker.length();
        int end = source.indexOf(endMarker, start);
        if (end == -1) return "N/A";
        return source.substring(start, end);
    }
    
    private List<String> extractAllMatches(String source, String startMarker, String endMarker) {
        List<String> results = new ArrayList<>();
        int start = 0;
        while (true) {
            int foundStart = source.indexOf(startMarker, start);
            if (foundStart == -1) break;
            foundStart += startMarker.length();
            
            int foundEnd = source.indexOf(endMarker, foundStart);
            if (foundEnd == -1) break;
            
            String value = source.substring(foundStart, foundEnd);
            value = value.replace("\\/", "/");
            results.add(value);
            
            start = foundEnd;
        }
        return results;
    }
}
