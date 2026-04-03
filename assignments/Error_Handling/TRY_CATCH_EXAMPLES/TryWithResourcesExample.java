import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TryWithResourcesExample {
    public static void main(String[] args) {
        String filePath = "dummy.txt";
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            System.out.println("File opened.");
            System.out.println(reader.readLine());
        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        }
    }
}
