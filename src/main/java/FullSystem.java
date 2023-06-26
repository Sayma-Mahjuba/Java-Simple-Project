import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class FullSystem {
    public static void main(String[] args) {

        JSONArray quizQuestions = readQuizQuestions();


        login(quizQuestions);
    }

    private static JSONArray readQuizQuestions() {
        try {
            JSONParser jsonParser = new JSONParser();
            FileReader reader = new FileReader("./src/main/resources/QuizList.json");
            return (JSONArray) jsonParser.parse(reader);
        } catch (FileNotFoundException e) {
            System.out.println("Quiz file not found.");
        } catch (IOException e) {
            System.out.println("Error reading quiz file.");
        } catch (ParseException e) {
            System.out.println("Error parsing quiz file.");
        }
        return null;
    }

    private static void login(JSONArray quizQuestions) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your username");
        String username = scanner.nextLine();
        System.out.println("Enter password");
        String password = scanner.nextLine();

        if (username.equals("admin") && password.equals("1234")) {
            System.out.println("Welcome admin! Please create new questions in the question bank.");
            addQuestionsToQuiz(quizQuestions, scanner);
        } else if (isValidStudent(username, password)) {
            System.out.println("Welcome " + username + " to the quiz! We will throw you 10 questions. Each MCQ mark is 1 and there is no negative marking.");
            System.out.println("Are you ready? Press 's' to start.");
            String start = scanner.nextLine();
            if (start.equals("s")) {
                int score = generateQuiz(quizQuestions, scanner);
                displayResult(score);
            }
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
    }

    private static boolean isValidStudent(String username, String password) {
        try {
            JSONParser jsonParser = new JSONParser();
            FileReader reader = new FileReader("./src/main/resources/UserList.json");
            JSONArray users = (JSONArray) jsonParser.parse(reader);

            for (Object obj : users) {
                JSONObject user = (JSONObject) obj;
                String userRole = (String) user.get("role");
                String storedUsername = (String) user.get("username");
                String storedPassword = (String) user.get("password");

                if (userRole.equals("student") && username.equals(storedUsername) && password.equals(storedPassword)) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Users file not found.");
        } catch (IOException e) {
            System.out.println("Error reading users file.");
        } catch (ParseException e) {
            System.out.println("Error parsing users file.");
        }
        return false;
    }

    private static void addQuestionsToQuiz(JSONArray quizQuestions, Scanner scanner) {
        String continueAdding = "s";
        while (continueAdding.equals("s")) {
            JSONObject question = new JSONObject();

            System.out.println("Input your question");
            System.out.print("Admin:>");
            String questionText = scanner.nextLine();
            question.put("question", questionText);

            System.out.println("Input option 1:");
            System.out.print("Admin:>");
            String option1 = scanner.nextLine();
            question.put("option 1", option1);

            System.out.println("Input option 2:");
            System.out.print("Admin:>");
            String option2 = scanner.nextLine();
            question.put("option 2", option2);

            System.out.println("Input option 3:");
            System.out.print("Admin:>");
            String option3 = scanner.nextLine();
            question.put("option 3", option3);

            System.out.println("Input option 4:");
            System.out.print("Admin:>");
            String option4 = scanner.nextLine();
            question.put("option 4", option4);

            System.out.println("What is the answer key?");
            System.out.print("Admin:>");
            String answerKey = scanner.nextLine();
            question.put("answerkey", answerKey);

            quizQuestions.add(question);

            System.out.println("Saved successfully! Do you want to add more questions? (press s for start and q for quit)");
            continueAdding = scanner.nextLine();
        }

        // Save the updated quiz questions to the JSON file
        saveQuizQuestions(quizQuestions);
    }

    private static void saveQuizQuestions(JSONArray quizQuestions) {
        try (FileWriter fileWriter = new FileWriter("./src/main/resources/QuizList.json")) {
            fileWriter.write(quizQuestions.toJSONString());
            fileWriter.flush();
            System.out.println("Quiz questions saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving quiz questions.");
        }
    }

    private static int generateQuiz(JSONArray quizQuestions, Scanner scanner) {
        int score = 0;
        List<Integer> generatedIndices = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            int randomIndex;
            do {
                randomIndex = random.nextInt(quizQuestions.size());
            } while (generatedIndices.contains(randomIndex));

            generatedIndices.add(randomIndex);
            JSONObject question = (JSONObject) quizQuestions.get(randomIndex);
            System.out.println("[Question " + (i + 1) + "] " + question.get("question"));
            System.out.println("1. " + question.get("option 1"));
            System.out.println("2. " + question.get("option 2"));
            System.out.println("3. " + question.get("option 3"));
            System.out.println("4. " + question.get("option 4"));
            System.out.print("Student:>");

            String userAnswer = scanner.nextLine();
            String answerKey = question.get("answerkey").toString();
            if (userAnswer.equals(answerKey)) {
                score++;
            }
        }

        return score;
    }

    private static void displayResult(int score) {
        System.out.println("Quiz completed. Here is your result:");
        System.out.println("You have got " + score + " out of 10");

        if (score >= 8) {
            System.out.println("Excellent! You have passed the quiz.");
        } else if (score >= 5) {
            System.out.println("Good. You have passed the quiz.");
        } else if (score >= 2) {
            System.out.println("Very poor! You have failed the quiz.");
        } else {
            System.out.println("Very sorry. You have failed the quiz.");
        }
    }
}
