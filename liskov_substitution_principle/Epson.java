import java.util.Arrays;

public class Epson extends OfficePrinter {
    
    public Epson() {
        super("Epson", 100, Arrays.asList("A4", "A5"));
    }
    
    @Override
    public PrintResult print(PrintJob job) {
        if (!canPrint(job)) {
            if (!supportedSizes.contains(job.paperSize)) {
                return new PrintResult(false, model + ": " + job.paperSize + " not supported", 0);
            }
            return new PrintResult(false, model + ": Need " + job.pageCount + " sheets, have " + currentPaperCount, 0);
        }
        
        currentPaperCount -= job.pageCount;
        String colorMode = job.colorRequired ? "color" : "grayscale";
        return new PrintResult(true, model + ": " + job.documentName + " (" + job.pageCount + " pages, " + colorMode + ")", job.pageCount);
    }
}
