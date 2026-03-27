import java.util.List;

public abstract class OfficePrinter {
    protected String model;
    protected int trayCapacity;
    protected int currentPaperCount;
    protected List<String> supportedSizes;
    
    public OfficePrinter(String model, int trayCapacity, List<String> supportedSizes) {
        this.model = model;
        this.trayCapacity = trayCapacity;
        this.currentPaperCount = trayCapacity;
        this.supportedSizes = supportedSizes;
    }
    
    public boolean canPrint(PrintJob job) {
        boolean hasEnoughPaper = currentPaperCount >= job.pageCount;
        boolean supportsPaperSize = supportedSizes.contains(job.paperSize);
        return hasEnoughPaper && supportsPaperSize;
    }
    
    public void loadPaper(int sheets) {
        int newCount = currentPaperCount + sheets;
        currentPaperCount = Math.min(newCount, trayCapacity);
    }
    
    public abstract PrintResult print(PrintJob job);
    
    public int getRemainingPaper() {
        return currentPaperCount;
    }
    
    public String getModel() {
        return model;
    }
}
