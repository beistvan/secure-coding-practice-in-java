import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class UsersBirthdays {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
      
      System.out.print("Enter your username: ");
      String username = scanner.nextLine();

        if (username == null || username.isEmpty()) {
            System.out.println("Please provide a username.");
            scanner.close();
            return;
        }

        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:db.sqlite");
            Statement stmt = conn.createStatement();

            String query = "SELECT birthday FROM users WHERE username = '" + username + "'";
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                String birthday = rs.getString("birthday");
                System.out.println("The birthday of user " + username + " is " + birthday);
            } else {
                System.out.println("We couldn't find the user " + username);
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        scanner.close();
    }
}
// try to run in terminal
// java -classpath .:./sqlite-jdbc-3.36.0.3.jar UsersBirthdays
// input: lauren_33
// output: We couldn't find the user lauren_33
// Enter your username: lauren_33
// We couldn't find the user lauren_33
// input: admin' OR '1'='1
// output: The birthday of user admin' OR '1'='1 is 1988-03-12
// Enter your username: admin' OR '1'='1
// The birthday of user admin' OR '1'='1 is 1988-03-12
