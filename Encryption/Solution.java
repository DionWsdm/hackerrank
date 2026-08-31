import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

class Result {

    /*
     * Complete the 'encryption' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */

    public static String encryption(String s) 
    {
        // Write your code here
        String result = "";
        s = s.replaceAll(" ", "");
        int l = s.length();
        
        // System.out.println("l: "+l);
        
        double sqrt = Math.sqrt(l);
        
        int row = (int) sqrt;
        int col = (sqrt > row) ? row+1 : row;
        
        // System.out.println("row: "+row+", col: "+col);
        
        // System.out.println(sqrt);
        
        for (int cc = 0; cc < col; cc++)
        {
            for (int rr = cc; rr < l; rr += col)
                result += s.charAt(rr);
                
            result += ' ';
        }
        
        return result;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();

        String result = Result.encryption(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

