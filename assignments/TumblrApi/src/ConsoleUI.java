package tumblrapi;

import java.util.List;
import java.util.Scanner;

public class ConsoleUI {

    private final Scanner scanner;

    public ConsoleUI() {
        this.scanner = new Scanner(System.in);
    }

    public String getBlogName() {
        System.out.println("enter the Tumblr blog name:");
        String name = scanner.nextLine().trim();
        System.out.println();
        return name;
    }

    public String getRange() {
        System.out.println("enter the range:");
        String range = scanner.nextLine().trim();
        System.out.println();
        return range;
    }

    public void printBlogDetails(Blog blog) {
        System.out.println(blog.toString());
        System.out.println();
    }

    public void printPosts(List<Post> posts) {
        int index = 1;
        for (Post post : posts) {
            List<String> urls = post.getImageUrls();
            if (urls.isEmpty()) {
                continue;
            }
            
            System.out.print(index + ". ");
            for (int i = 0; i < urls.size(); i++) {
                if (i > 0) {
                    System.out.print("   ");
                }
                System.out.println(urls.get(i));
            }
            index++;
        }
    }

    public void printError(String message) {
        System.err.println("Error: " + message);
    }
}
