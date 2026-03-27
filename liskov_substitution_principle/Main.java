public class Main {
    
    public static void main(String[] args) {
        PrintQueueManager queue = new PrintQueueManager();
        
        queue.registerPrinter(new Epson());
        queue.registerPrinter(new HPLaserJetPro());
        queue.registerPrinter(new CanonImageRunner());
        
        queue.printStatus();
        
        PrintJob tcsAnnualReport = new PrintJob("TCS_AnnualReport.pdf", 45, "A4", false);
        PrintJob infosysBrochure = new PrintJob("Infosys_Brochure.pdf", 30, "A4", true);
        PrintJob wiproBlueprint = new PrintJob("Wipro_Blueprint.pdf", 10, "A3", false);
        PrintJob relianceInvoices = new PrintJob("Reliance_Invoices.pdf", 200, "A4", false);
        PrintJob hdfcStatement = new PrintJob("HDFC_Statement.pdf", 15, "A4", false);
        
        System.out.println("=== Submitting Jobs ===\n");
        
        PrintResult tcsResult = queue.submitJob(tcsAnnualReport);
        System.out.println(tcsResult.message);
        
        PrintResult infosysResult = queue.submitJob(infosysBrochure);
        System.out.println(infosysResult.message);
        
        PrintResult wiproResult = queue.submitJob(wiproBlueprint);
        System.out.println(wiproResult.message);
        
        PrintResult relianceResult = queue.submitJob(relianceInvoices);
        System.out.println(relianceResult.message);
        
        PrintResult hdfcResult = queue.submitJob(hdfcStatement);
        System.out.println(hdfcResult.message);
        
        queue.printStatus();
    }
}
