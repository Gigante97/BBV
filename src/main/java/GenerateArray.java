import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class GenerateArray {


    public static String[] generate(String filename) throws IOException {
        int count;
        String myList[] = null;
        FileReader fileReader =null;
        String s;
        int symbol;
        int i;

        count = countLines(filename);
        if(count<=0){
            return null;
        }
        myList = new String[count];

        try{
            fileReader = new FileReader(filename);
            s="";
            i=0;
            do {
                symbol = fileReader.read();
                if((char)symbol == '\n'){
                    s = s.substring(0,s.length()-1);
                    myList[i] =s;
                    s="";
                    i++;
                } else {
                    s = s+(char)symbol;
                }
            } while (fileReader.ready());
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
        finally {
            try {
                if(fileReader!=null) {
                    fileReader.close();
                }
            } catch (Exception e) {
                System.out.println("close error");
            }
        }
        return myList;

    }

    public static int countLines(String filename) throws IOException {
        int count =0;
        FileReader reader = null;
        int symbol;

        try {
            reader = new FileReader(filename);

            do {
                symbol =reader.read();
                if ((char)symbol == '\n') {
                    count++;
                }
            }while(reader.ready());

        } catch (IOException e) {
            System.out.println("error: " + e);
        }
        finally {
            try {
                if (reader!=null) {
                    reader.close();}
            } catch (Exception e) {
                System.out.println("Closed");
            }
        }
        return count;
    }
}
