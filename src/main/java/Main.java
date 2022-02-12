import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {
        GenerateArray generateArray = new GenerateArray();
        BD bd = new BD();
        bd.connect();
        bd.checkCisHitory(generateArray.generate("cis.txt"));
        bd.disconnect();

    }
}
