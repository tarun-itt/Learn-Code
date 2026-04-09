package tumblrapi;

import java.util.List;

public class TumblrViewerMain {

    public static void main(String[] args) {
        ConsoleUI ui = new ConsoleUI();
        TumblrApiClient client = new TumblrApiClient();

        try {
            String blogName = ui.getBlogName();
            String rangeInput = ui.getRange();

            int[] range = parseRange(rangeInput);
            int start = range[0];
            int end = range[1];
            
            // API is 0-indexed for start. User input 1-based.
            int apiStart = start - 1;
            int num = end - start + 1;

            // Handle API pagination (max 50 posts per request)
            int currentApiStart = apiStart;
            int postsRemaining = num;
            
            Blog blogInfo = null;
            boolean firstBatch = true;
            
            List<Post> allPosts = new java.util.ArrayList<>();
            
            while (postsRemaining > 0) {
                int fetchNum = Math.min(postsRemaining, 50);
                
                String json = client.fetchJson(blogName, currentApiStart, fetchNum);
                
                if (firstBatch) {
                    blogInfo = client.parseBlogInfo(json);
                    ui.printBlogDetails(blogInfo);
                    firstBatch = false;
                }
                
                List<Post> posts = client.parsePosts(json);
                allPosts.addAll(posts);
                
                currentApiStart += fetchNum;
                postsRemaining -= fetchNum;

                if (posts.isEmpty()) break;
            }
            
            ui.printPosts(allPosts);

        } catch (Exception e) {
            ui.printError("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static int[] parseRange(String rangeInput) {
        String[] parts = rangeInput.split("-");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid range format. Use syntax: start-end (e.g. 1-5)");
        }
        try {
            int start = Integer.parseInt(parts[0].trim());
            int end = Integer.parseInt(parts[1].trim());
            if (start < 1 || end < start) {
                 throw new IllegalArgumentException("Invalid range values.");
            }
            return new int[]{start, end};
        } catch (NumberFormatException e) {
             throw new IllegalArgumentException("Range must be numeric.");
        }
    }
}
