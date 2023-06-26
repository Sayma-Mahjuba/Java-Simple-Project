import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WriteJsonQuiz {

    public static void main(String[] args) throws IOException, ParseException {
        String filepath="./src/main/resources/QuizList.json";
        JSONParser jsonparser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonparser.parse(new FileReader(filepath));
        JSONObject quizObject=new JSONObject();

        quizObject.put("question","Which is not part of system testing?");
        quizObject.put("option 1","Regression Testing");
        quizObject.put("option 2","Sanity Testing");
        quizObject.put("option 3","Load Testing");
        quizObject.put("option 4","Unit Testing");
        quizObject.put("answerkey","4");

        jsonArray.add(quizObject);
        System.out.println(jsonArray);

        FileWriter fileWriter= new FileWriter(filepath);
        fileWriter.write(jsonArray.toJSONString());
        fileWriter.flush();
        fileWriter.close();

    }
}
