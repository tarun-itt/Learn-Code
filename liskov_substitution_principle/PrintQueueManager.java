import java.util.ArrayList;
import java.util.List;

public class PrintQueueManager {
    private List<OfficePrinter> availablePrinters;
    
    public PrintQueueManager() {
        this.availablePrinters = new ArrayList<>();
    }
    
    public void registerPrinter(OfficePrinter printer) {
        availablePrinters.add(printer);
    }
    
    public PrintResult submitJob(PrintJob job) {
        for (OfficePrinter printer : availablePrinters) {
            if (printer.canPrint(job)) {
                return printer.print(job);
            }
        }
        return new PrintResult(false, "No printer available for this job", 0);
    }
    
    public void printStatus() {
        System.out.println("\n--- Printer Status ---");
        for (OfficePrinter printer : availablePrinters) {
            System.out.println(printer.getModel() + ": " + printer.getRemainingPaper() + " sheets");
        }
        System.out.println();
    }
}
