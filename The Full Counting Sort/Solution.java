import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'countSort' function below.
     *
     * The function accepts 2D_STRING_ARRAY arr as parameter.
     */

    public static void countSort(List<List<String>> arr) 
    {
        int n = arr.size();

        Map<Integer, Integer> dict = new HashMap<>();
        List<List<String>> sorted = new ArrayList<>();
        
        // O(N)
        for (int ii = 0; ii < n; ii++)
        {
            List<String> pair = arr.get(ii);
            
            int pos = Integer.parseInt(pair.get(0));
            String s = pair.get(1);

            int index = dict.getOrDefault(pos, -1);
            // System.out.println("index: "+index);
            
            if (index == -1)
            {
                List<String> newList = new ArrayList<>();
                newList.add((ii < n/2) ? "-" : s);
                sorted.add(newList);
                index = sorted.size()-1; 
            }
            else
            {            
                // System.out.println("ii: "+ii+", s: "+s);
                sorted.get(index).add((ii < n/2) ? "-" : s);
            }

            dict.put(pos, index);
        }
        
        // O(N)
        StringBuilder sb = new StringBuilder();
        for (int ii = 0; ii < 100; ii++)
        {
            int index = dict.getOrDefault(ii, -1);
            if (index != -1)
            {
                List<String> strings = sorted.get(index);
                // System.out.println("strings: "+strings);
                for (String string : strings)
                    sb.append(string).append(' ');
            }
        }
        System.out.print(sb);
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<String>> arr = new ArrayList<>();

        IntStream.range(0, n).forEach(i -> {
            try {
                arr.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        Result.countSort(arr);

        bufferedReader.close();
    }
}
