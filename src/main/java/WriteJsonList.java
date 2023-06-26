import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WriteJsonList {

    public static void main(String[] args) throws IOException, ParseException {
        String filepath="./src/main/resources/UserList.json";
        JSONParser jsonparser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonparser.parse(new FileReader(filepath));
        JSONObject userObject=new JSONObject();

        userObject.put("username","sayma");
        userObject.put("password","1234");
        userObject.put("role","student");

        jsonArray.add(userObject);
        System.out.println(jsonArray);

        FileWriter fileWriter= new FileWriter(filepath);
        fileWriter.write(jsonArray.toJSONString());
        fileWriter.flush();
        fileWriter.close();

    }
}
