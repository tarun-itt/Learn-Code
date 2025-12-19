public class OracleDBConnection implements DBConnection {
    private String connectionString = "jdbc:oracle:thin:@localhost:1521:articles_db";
    private boolean isConnected = false;
    
    @Override
    public String connect() {
        isConnected = true;
        return "Oracle: Connected to " + connectionString;
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