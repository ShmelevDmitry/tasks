import java.io.FileReader;
import java.util.*;

public class NewTask4 {
    public static void main(String[] args){
        if (args.length == 0){
            System.out.println("¬ходной файл не найден");
            return;
        }
        List<Integer> arr = getArrayFromFile(args[0]);
        List<Integer> result = new ArrayList<>();
        Collections.sort(arr);
        int sum;
        for (int i = arr.get(0); i <= arr.get(arr.size()-1); i++) {
            sum = 0;
            for (int j = 0; j < arr.size(); j++) {
                sum += Math.abs(arr.get(j) - i);
            }
            result.add(sum);
        }
        Collections.sort(result);
        System.out.println(result.get(0));
    }

    static List<Integer> getArrayFromFile(String path){
        List<Integer> arr = new ArrayList<>();
        try(FileReader reader = new FileReader(path)) {
            Scanner scan = new Scanner(reader);
            while (scan.hasNextInt())
                arr.add(scan.nextInt());
            scan.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return arr;
    }

}