import java.io.*;

class Result {

    /*
     * Complete the 'timeInWords' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. INTEGER h
     *  2. INTEGER m
     */

    public static String timeInWords(int h, int m) 
    {
        // Write your code here
        // String[] hour =  {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve"};
        
        String[] words = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "quarter", "sixteen", "seventeen", "eighteen", "nineteen", "twenty", "twenty one", "twenty two", "twenty three", "twenty four", "twenty five", "twenty six", "twenty seven", "twenty eight", "twenty nine", "half"};
        
        String result = "";
        
        
        if (m <= 30 && m > 0)
        {   
            result += words[m-1] + " " + ((m % 15 == 0) ? "" : (m > 1) ? "minutes " : "minute ");          
            result += "past ";
            result += words[h-1];
        }
        else if (m > 30)
        {
            m = 60-m;
            result += words[m-1] + " " + ((m % 15 == 0) ? "" : (m > 1) ? "minutes " : "minute ");          
            result += "to ";
            result += words[h];
        }
        else
        {
            result += words[h-1]+" ";
            result +="o' clock";
        }
        
        return result;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int h = Integer.parseInt(bufferedReader.readLine().trim());

        int m = Integer.parseInt(bufferedReader.readLine().trim());

        String result = Result.timeInWords(h, m);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
