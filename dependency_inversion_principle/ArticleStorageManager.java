public class ArticleStorageManager {
    private DBConnection dbConnection;
    
    public ArticleStorageManager(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }
    
    public String initialize() {
        return dbConnection.connect();
    }
    
    public boolean saveArticle(String articleTitle, String articleContent) {
        String insertQuery = "INSERT INTO articles (title, content) VALUES ('" + articleTitle + "', '" + articleContent + "')";
        return dbConnection.executeQuery(insertQuery);
    }
    
    public boolean deleteArticle(String articleId) {
        String deleteQuery = "DELETE FROM articles WHERE id = '" + articleId + "'";
        return dbConnection.executeQuery(deleteQuery);
    }
    
    public void shutdown() {
        dbConnection.disconnect();
    }
}