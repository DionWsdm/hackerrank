import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'surfaceArea' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts 2D_INTEGER_ARRAY A as parameter.
     */

    public static int surfaceArea(List<List<Integer>> A) 
    {
        int result = 0;
        
        int r = A.size();
        int c = A.get(0).size();
        
        for (int rr = 0; rr < r; rr++)
        {
            for (int cc = 0; cc < c; cc++)
            {
                int cubes = A.get(rr).get(cc);
                result += 2; // top and bottom side
                
                if (cc == 0)
                    result += cubes; // front side if this is the first column
                else
                {
                    int prevFrontCubes = A.get(rr).get(cc-1);
                    // front side if this is not the first column
                    result += Math.max(0, cubes-prevFrontCubes);
                }
                
                if (cc == c-1)
                    result += cubes; // back side if this is the last column
                else
                {
                    int nextBackCubes = A.get(rr).get(cc+1);
                    // back side if this is not the last column
                    result += Math.max(0, cubes-nextBackCubes);
                }
                
                if (rr == 0)
                    result += cubes; // left side if this is the first row;
                else
                {
                    int prevLeftCubes = A.get(rr-1).get(cc);
                    // left side if this is not the first row
                    result += Math.max(0, cubes-prevLeftCubes);
                }
                
                if (rr == r-1)
                    result += cubes; // right side if this is the last row;
                else
                {
                    int nextRightCubes = A.get(rr+1).get(cc);
                    // right side if this is not the last row
                    result += Math.max(0, cubes-nextRightCubes);
                }
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

        int H = Integer.parseInt(firstMultipleInput[0]);

        int W = Integer.parseInt(firstMultipleInput[1]);

        List<List<Integer>> A = new ArrayList<>();

        IntStream.range(0, H).forEach(i -> {
            try {
                A.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = Result.surfaceArea(A);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
