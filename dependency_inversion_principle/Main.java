public class Main {
    
    public static void main(String[] args) {
        DBConnection mysqlConnection = new MySQLDBConnection();
        ArticleStorageManager timesOfIndiaStorage = new ArticleStorageManager(mysqlConnection);
        
        String mysqlStatus = timesOfIndiaStorage.initialize();
        System.out.println(mysqlStatus);
        
        boolean articleSaved = timesOfIndiaStorage.saveArticle("Budget 2025", "Finance Minister presents union budget");
        System.out.println("Article saved: " + articleSaved);
        
        timesOfIndiaStorage.shutdown();
        
        System.out.println();
        
        DBConnection oracleConnection = new OracleDBConnection();
        ArticleStorageManager hinduStorage = new ArticleStorageManager(oracleConnection);
        
        String oracleStatus = hinduStorage.initialize();
        System.out.println(oracleStatus);
        
        boolean opinionSaved = hinduStorage.saveArticle("Climate Change Impact", "Rising temperatures affect monsoon patterns");
        System.out.println("Article saved: " + opinionSaved);
        
        hinduStorage.shutdown();
    }
}
