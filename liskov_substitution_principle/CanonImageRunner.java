import java.util.Arrays;

public class CanonImageRunner extends OfficePrinter {
    private int monthlyDutyCount;
    private static final int MONTHLY_DUTY_LIMIT = 5000;
    
    public CanonImageRunner() {
        super("Canon imageRUNNER", 500, Arrays.asList("A4", "A3", "A5"));
        this.monthlyDutyCount = 0;
    }
    
    @Override
    public PrintResult print(PrintJob job) {
        if (!canPrint(job)) {
            if (!supportedSizes.contains(job.paperSize)) {
                return new PrintResult(false, model + ": " + job.paperSize + " not supported", 0);
            }
            return new PrintResult(false, model + ": Need " + job.pageCount + " sheets, have " + currentPaperCount, 0);
        }
        
        if (monthlyDutyCount + job.pageCount > MONTHLY_DUTY_LIMIT) {
            return new PrintResult(false, model + ": Monthly limit reached (" + MONTHLY_DUTY_LIMIT + ")", 0);
        }
        
        currentPaperCount -= job.pageCount;
        monthlyDutyCount += job.pageCount;
        
        String colorMode = job.colorRequired ? "color" : "grayscale";
        return new PrintResult(true, model + ": " + job.documentName + " (" + job.pageCount + " pages, " + colorMode + "). Usage: " + monthlyDutyCount + "/" + MONTHLY_DUTY_LIMIT, job.pageCount);
    }
    
    public void resetMonthlyCounter() {
        monthlyDutyCount = 0;
    }
}
