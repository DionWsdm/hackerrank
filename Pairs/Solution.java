import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'pairs' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER k
     *  2. INTEGER_ARRAY arr
     */

    public static int pairs(int k, List<Integer> arr) 
    {
        // Write your code here
        int n = arr.size();
        int result = 0;
        
        Map<Integer, Integer> dict = new HashMap<>();
        Set<Integer> nums1 = new HashSet<>();
        
        for (int ii = 0; ii < n; ii++)
        {
            int num = arr.get(ii);
            int count = dict.getOrDefault(num, 0)+1;
            dict.put(num, count);
            nums1.add(num);
        }

        for (Integer num : nums1)
        {
            Integer here = dict.get(num);
            
            if (dict.containsKey(num))
            {
                int up = dict.getOrDefault(num+k, 0);
                int bot = dict.getOrDefault(num-k,0);
                result += here * up + here * bot;
            }
            dict.remove(num);
        }
        
        return result;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int k = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        int result = Result.pairs(k, arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
