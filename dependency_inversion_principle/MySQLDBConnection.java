public class MySQLDBConnection implements DBConnection {
    private String connectionString = "jdbc:mysql://localhost:3306/articles_db";
    private boolean isConnected = false;
    
    @Override
    public String connect() {
        isConnected = true;
        return "MySQL: Connected to " + connectionString;
    }
    
    @Override
    public void disconnect() {
        isConnected = false;
    }
    
    @Override
    public boolean executeQuery(String query) {
        if (!isConnected) {
            return false;
        }
        return true;
    }
}
