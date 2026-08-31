import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'bomberMan' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. STRING_ARRAY grid
     */
    
    public static List<String> allBomb(int r, int c)
    {
        List<String> result = new ArrayList<>();
        for (int rr = 0; rr < r; rr++)
        {
            String subResult = "";
            for (int cc = 0; cc < c; cc++)
            {
                subResult += "O";
            } 
            result.add(subResult);
        }
        return result;
    }
    
    public static char[][] allBombArray(int r, int c)
    {
        char[][] result = new char[r][c];
        for (int rr = 0; rr < r; rr++)
            for (int cc = 0; cc < c; cc++)
                result[rr][cc] = 'O';
        return result;
    }
    
    public static List<String> secondState(List<String> grid)
    {
        int r = grid.size();
        int c = grid.get(0).length();
        
        char[][] chars = allBombArray(r, c);
        
        for (int rr = 0; rr < r; rr++)
            for (int cc = 0; cc < c; cc++)
                if (grid.get(rr).charAt(cc) == 'O')
                {
                    chars[rr][cc] = '.';
                    chars[Math.min(r-1, rr+1)][cc] = '.';
                    chars[Math.max(0, rr-1)][cc] = '.';
                    chars[rr][Math.min(c-1, cc+1)] = '.';
                    chars[rr][Math.max(0, cc-1)] = '.';
                }
                
        List<String> result = new ArrayList<>();
        
        for (int ii = 0; ii < r; ii++)
        {
            String subResult = new String(chars[ii]);
            result.add(subResult);
        }
                
        return result;
    }
    
    public static List<String> thirdState(List<String> grid)
    {
        List<String> secondState = secondState(grid);
        
        int r = grid.size();
        int c = grid.get(0).length();
        
        char[][] chars = allBombArray(r, c);
        
        for (int rr = 0; rr < r; rr++)
            for (int cc = 0; cc < c; cc++)
                if (secondState.get(rr).charAt(cc) == 'O')
                {
                    chars[rr][cc] = '.';
                    chars[Math.min(r-1, rr+1)][cc] = '.';
                    chars[Math.max(0, rr-1)][cc] = '.';
                    chars[rr][Math.min(c-1, cc+1)] = '.';
                    chars[rr][Math.max(0, cc-1)] = '.';
                }
                
        List<String> result = new ArrayList<>();
        
        for (int ii = 0; ii < r; ii++)
        {
            String subResult = new String(chars[ii]);
            result.add(subResult);
        }
                
        return result; 
    }

    public static List<String> bomberMan(int n, List<String> grid) 
    {
        // Write your code here
        if (n < 2)
            return grid;
        else if (n % 2 == 0)
            return allBomb(grid.size(), grid.get(0).length());
        else if (n % 4 == 3)
            return secondState(grid);
        else
            return thirdState(grid);
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int r = Integer.parseInt(firstMultipleInput[0]);

        int c = Integer.parseInt(firstMultipleInput[1]);

        int n = Integer.parseInt(firstMultipleInput[2]);

        List<String> grid = IntStream.range(0, r).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .collect(toList());

        List<String> result = Result.bomberMan(n, grid);

        bufferedWriter.write(
            result.stream()
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
