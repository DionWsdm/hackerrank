import java.io.*;
import java.util.*;
import java.util.stream.*;

class Result {

    /*
     * Complete the 'sherlockAndAnagrams' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING s as parameter.
     */
     
    public static char[] mergeSort(char[] c1)
    {
        return mergeSort(c1, 0, c1.length);
    }

    // end is exclusive
    public static char[] mergeSort(char[] c1, int from, int length)
    {
        if (length == 1)
        {
            char[] merged = {c1[from]};
            return merged;
        }

        int newLength = (int) Math.ceil(length/2);
        char[] sorted1 = mergeSort(c1, from, newLength);
        char[] sorted2 = mergeSort(c1, from+newLength, length-newLength);

        int index1 = 0;
        int index2 = 0;
        char[] merged = new char[length];
        for (int ii = 0; ii < merged.length; ii++)
        {
            if (index1 >= sorted1.length)
                merged[ii] = sorted2[index2++];
            else if (index2 >= sorted2.length)
                merged[ii] = sorted1[index1++];
            else
                merged[ii] = (sorted1[index1] < sorted2[index2]) ? sorted1[index1++] : sorted2[index2++];
        }

        return merged;
    }

    public static int sherlockAndAnagrams(String s) 
    {
        // Write your code here
        int result = 0;
        
        Map<String, Integer> dict = new HashMap<>();
        
        int n = s.length();
        for (int stringLength = 1; stringLength < n; stringLength++)
        {
            for (int jj = 0; jj <= n-stringLength; jj++)
            {
                String s1 = s.substring(jj, jj+stringLength);
                char[] c1 = s1.toCharArray();
                String sorted = new String(mergeSort(c1));
                dict.put(sorted, dict.getOrDefault(sorted, 0)+1);
            }
        }
        
        Set<String> keys = dict.keySet();
        for (String key : keys)
        {
            int count = dict.get(key);
            if (count > 1)
                result += count*(count-1)/2;
        }
            
        return result;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                String s = bufferedReader.readLine();

                int result = Result.sherlockAndAnagrams(s);

                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
