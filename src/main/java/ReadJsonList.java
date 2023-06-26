import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadJsonList {
    public static void main(String[] args) throws IOException, ParseException {
        String filepath="./src/main/resources/UserList.json";
        JSONParser jsonParser= new JSONParser();
        JSONArray userArray = (JSONArray) jsonParser.parse(new FileReader(filepath));

        JSONObject userObject = (JSONObject) userArray.get(0);

    }
}
