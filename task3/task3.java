
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class task3 {
    public static void main(String[] args){
        File directory = new File(args[0]);
        if (directory.isDirectory()){
            try{
                if (directory.listFiles().length!=5) throw new Exception("Number of files must be 5!!!");
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        File file1 = new File(directory, "Cash1.txt");
        File file2 = new File(directory, "Cash2.txt");
        File file3 = new File(directory, "Cash3.txt");
        File file4 = new File(directory, "Cash4.txt");
        File file5 = new File(directory, "Cash5.txt");
        double[] cash1 = GetParams(file1);
        double[] cash2 = GetParams(file2);
        double[] cash3 = GetParams(file3);
        double[] cash4 = GetParams(file4);
        double[] cash5 = GetParams(file5);
        double[] SumCash=GetSumCash(cash1,cash2,cash3,cash4,cash5);

        double max=0;
        int n=0;
        for (int i=0;i<16;i++){
            if (SumCash[i]>max) {
                max = SumCash[i];
                n=i+1;
            }
        }
        System.out.println(n);
    }
    static double[] GetParams (File file ){
        double[] params = new double[16];
        int i=0;
        try(FileReader reader = new FileReader(file)){
            Scanner scan = new Scanner(reader);
            while (scan.hasNextDouble()){
                params[i]=scan.nextDouble();
                i++;
            }
        }
        catch (IOException ex){
            System.out.println("Файл не найден");
        }
        return params;
    }
    static double[] GetSumCash(double[] cash1, double[] cash2, double[] cash3, double[] cash4, double[] cash5){
        double[] SumCash = new double[16];
        for (int i=0;i<16;i++){
            SumCash[i] = cash1[i]+cash2[i]+cash3[i]+cash4[i]+cash5[i];
        }
        return SumCash;
    }
}
