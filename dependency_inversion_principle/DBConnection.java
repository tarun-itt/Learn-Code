public interface DBConnection {
    String connect();
    void disconnect();
    boolean executeQuery(String query);
}