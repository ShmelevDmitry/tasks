
import java.util.ArrayList;
import java.util.List;

public class Task1 {
    public static void main(String[] args){
        int n=0, m=0, k=0, i=0;
         try {
             n = Integer.parseInt(args[0]);
             m = Integer.parseInt(args[1]);
        }
        catch (NumberFormatException e){
            System.err.println("Invalid input data!");
        }
        catch (ArrayIndexOutOfBoundsException ex){
            System.err.println("No input data!");
        }

        int[] arr = GetArray(n);
        int[] step = new int[m];
        List<Integer> result = new ArrayList<>();
        do {
            if (i > n-1)
                i=0;
            if (k < m-1){
                step[k] = arr[i];
                i++;
                k++;
            }
            else {
                result.add(step[0]);
                step[k] = arr[i];
                k=0;
            }

        } while (step[m-1] != arr[0]);

        for (int j:
                result) {
            System.out.print(j);
        }
    }
    static int[] GetArray(int n){
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i+1;
        }
        return arr;
    }
}