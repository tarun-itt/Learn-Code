public class PrintJob {
    public String documentName;
    public int pageCount;
    public String paperSize;
    public boolean colorRequired;
    
    public PrintJob(String documentName, int pageCount, String paperSize, boolean colorRequired) {
        this.documentName = documentName;
        this.pageCount = pageCount;
        this.paperSize = paperSize;
        this.colorRequired = colorRequired;
    }
}
