public class PrintResult {
    public boolean success;
    public String message;
    public int pagesUsed;
    
    public PrintResult(boolean success, String message, int pagesUsed) {
        this.success = success;
        this.message = message;
        this.pagesUsed = pagesUsed;
    }
}
