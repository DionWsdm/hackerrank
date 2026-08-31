import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'nonDivisibleSubset' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER k
     *  2. INTEGER_ARRAY s
     */

    public static int nonDivisibleSubset(int k, List<Integer> s) 
    {
        // Write your code here    
        int result = 0;
        
        Map<Integer, List<Integer>> dict = new HashMap<>();
        
        for (int num : s)
        {
            int remainder = Math.floorMod(num, k);
            List<Integer> arr = dict.getOrDefault(remainder, new ArrayList<>());
            arr.add(num);
            dict.put(remainder, arr);
        }
        
        List<Integer> arr0 = dict.getOrDefault(0, new ArrayList<>());
        int arr0Size = arr0.size();
        
        if (arr0Size > 0)
            result += 1;
        
        for (int ii = 1; ii < k/2+1; ii++)
        {
            if (k % 2 == 0 && ii == k/2)
            {
                int size = dict.getOrDefault(ii, new ArrayList<>()).size();
                result += (size > 0) ? 1 : 0;
            }
            else
            {
                int leftSize = dict.getOrDefault(ii, new ArrayList<>()).size();
                int rightSize = dict.getOrDefault(k-ii, new ArrayList<>()).size();
                
                result += (leftSize > rightSize) ? leftSize : rightSize;
            }
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

        List<Integer> s = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        int result = Result.nonDivisibleSubset(k, s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

