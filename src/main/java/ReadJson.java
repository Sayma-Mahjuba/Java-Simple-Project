import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadJson {
    public static void main(String[] args) throws ParseException, IOException {
        JSONParser jsonParser= new JSONParser();
        JSONObject users = (JSONObject) jsonParser.parse(new FileReader("./src/main/resources/users.json"));
        //JSONObject users  = (JSONObject) obj;
        System.out.println(users.get("admin"));
    }
}
