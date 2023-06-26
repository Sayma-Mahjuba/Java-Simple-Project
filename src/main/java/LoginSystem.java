import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LoginSystem {
    private static final String USER_FILE_PATH = "./src/main/resources/UserList.json";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        JSONArray users = loadUsers();


        while (true) {
            System.out.print("Enter your username: ");
            String username = scanner.nextLine();

            System.out.print("Enter password: ");
            String password = scanner.nextLine();


            int userIndex = loginUser(users, username, password);

            if (userIndex == -1) {
                System.out.println("Invalid username or password. Please try again.");
            } else if (userIndex == 0) {
                System.out.println("Welcome admin! Please create new questions in the question bank.");
                // Perform actions specific to the admin user
                break;
            } else if (userIndex == 1) {
                System.out.println("Welcome " + username + " to the quiz! We will throw you 10 questions. Each MCQ mark is 1 and no negative marking. Are you ready? Press 's' to start.");
                // Perform actions specific to the student user
                break;
            }
        }

        scanner.close();
    }

    private static JSONArray loadUsers() {
        JSONParser jsonParser = new JSONParser();
        JSONArray users = null;

        try (FileReader fileReader = new FileReader(USER_FILE_PATH)) {
            Object obj = jsonParser.parse(fileReader);
            users = (JSONArray) obj;
        } catch (FileNotFoundException e) {
            System.out.println("User file not found.");
            System.exit(1);
        } catch (IOException | ParseException e) {
            System.out.println("Error reading user file.");
            System.exit(1);
        }

        return users;
    }

    private static int loginUser(JSONArray users, String username, String password) {
        for (int i = 0; i < users.size(); i++) {
            JSONObject user = (JSONObject) users.get(i);
            String storedUsername = (String) user.get("username");
            String storedPassword = (String) user.get("password");

            if (storedUsername.equals(username) && storedPassword.equals(password)) {
                return i; // User found and credentials match
            }
        }

        return -1;
    }
}
