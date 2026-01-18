public class SRP {
    
    public static void saveUser(User user) {
        if (!isValidUser(user)) {
             System.out.println("Invalid user data");
             return;
        }
        
        saveUserToDB(user);
        saveUserToFile(user);
    }

    private static boolean isValidUser(User user) {
        return user.name != null && !user.name.isEmpty() && 
               user.email != null && !user.email.isEmpty();
    }

    private static void saveUserToDB(User user) {
        db.insert("Users", user);
    }

    private static void saveUserToFile(User user) {
        String filePath = "/backup/users/" + user.id + ".txt";
        writeToFile(filePath, user);
    }

    public static void main(String[] args) {
        User user = new User("Alice", "alice@example.com", "123");
        saveUser(user);
    }
}
