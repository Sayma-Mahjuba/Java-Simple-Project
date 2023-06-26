import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class UserLogin {
    private static final String USERS_FILE_PATH = "./src/main/resources/UserList.json";
    private static final String QUIZ_FILE_PATH = "./src/main/resources/QuizList.json";

    public static void main(String[] args) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONArray usersArray = (JSONArray) jsonParser.parse(new FileReader(USERS_FILE_PATH));
        JSONArray quizArray = (JSONArray) jsonParser.parse(new FileReader(QUIZ_FILE_PATH));

        Scanner scanner = new Scanner(System.in);
        String username;
        String password;

        System.out.println("Enter your username");
        username = scanner.nextLine();

        System.out.println("Enter your password");
        password = scanner.nextLine();

        boolean loggedIn = false;
        boolean isAdmin = false;

        for (Object obj : usersArray) {
            JSONObject user = (JSONObject) obj;
            String storedUsername = (String) user.get("username");
            String storedPassword = (String) user.get("password");
            String role = (String) user.get("role");

            if (storedUsername.equals(username) && storedPassword.equals(password)) {
                loggedIn = true;
                if (role.equals("admin")) {
                    isAdmin = true;
                    System.out.println("Welcome " + username + "! Please create new questions in the question bank.");
                    break;
                } else {
                    System.out.println("Welcome " + username + " to the quiz! We will throw you 10 questions. Each MCQ mark is 1 and no negative marking. Are you ready? Press 's' to start.");
                    // Code for student quiz functionality goes here
                    break;
                }
            }
        }

        if (!loggedIn) {
            System.out.println("Invalid username or password. Exiting the program.");
            System.exit(0);
        }

        if (isAdmin) {
            addQuestions(quizArray, scanner);
        }

        FileWriter fileWriter = new FileWriter(QUIZ_FILE_PATH);
        fileWriter.write(quizArray.toJSONString());
        fileWriter.flush();
        fileWriter.close();

        System.out.println("Questions added to the quiz bank. Exiting the program.");
    }

    private static void addQuestions(JSONArray quizArray, Scanner scanner) {
        String choice = "";

        while (!choice.equals("q")) {
            System.out.println("Input your question:");
            String question = scanner.nextLine();

            System.out.println("Input option 1:");
            String option1 = scanner.nextLine();

            System.out.println("Input option 2:");
            String option2 = scanner.nextLine();

            System.out.println("Input option 3:");
            String option3 = scanner.nextLine();

            System.out.println("Input option 4:");
            String option4 = scanner.nextLine();

            System.out.println("What is the answer key?");
            String answerKey = scanner.nextLine();

            JSONObject quizObject = new JSONObject();
            quizObject.put("question", question);
            quizObject.put("option 1", option1);
            quizObject.put("option 2", option2);
            quizObject.put("option 3", option3);
            quizObject.put("option 4", option4);
            quizObject.put("answerkey", answerKey);

            quizArray.add(quizObject);
            System.out.println("Saved successfully!");

            System.out.println("Do you want to add more questions? (Press 's' to start and 'q' to quit)");
            choice = scanner.nextLine();
        }
    }
}
