import java.util.Arrays;

public class HPLaserJetPro extends OfficePrinter {
    
    public HPLaserJetPro() {
        super("HP LaserJet Pro", 250, Arrays.asList("A4", "A3", "A5"));
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
        
        if (job.colorRequired) {
            return new PrintResult(true, model + ": " + job.documentName + " (" + job.pageCount + " pages, forced grayscale)", job.pageCount);
        }
        
        return new PrintResult(true, model + ": " + job.documentName + " (" + job.pageCount + " pages, grayscale)", job.pageCount);
    }
}
