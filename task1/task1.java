

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class task1 {
    public static void main(String[] args){
        short[] numbers = GetNumbers(args[0]);
        float max = GetMax(numbers); //Можно обойтись и без этих методов,
        float min = GetMin(numbers); //так как массив уже отсортирован
        float avg = GetAvg(numbers);
        float perc90 = Percentile(numbers,90);
        float med = Percentile(numbers, 50);
        System.out.printf("%.2f\n%.2f\n%.2f\n%.2f\n%.2f\n",perc90,med,max,min,avg);
        /*System.out.printf("%.2f\n%.2f\n%.2f\n%.2f\n%.2f\n",perc90,med,(float)numbers[numbers.length-1],(float)numbers[0],avg);
        Альтернативный вариант вывода без вычисления max и min
         */
    }
    static short[] GetNumbers(String path){
        short[] numbers = new short[1000];
        int i =0;
        try(FileReader reader = new FileReader(path)){
            Scanner scan = new Scanner(reader);

            while (scan.hasNextShort())
            {
                numbers[i]=scan.nextShort();
                i++;
            }
            scan.close();
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }
        if (i<1000)
            numbers = Arrays.copyOf(numbers,i);
        Arrays.sort(numbers);
        return numbers;
    }
    static float GetMax(short[] nums){
        float max = nums[0];
        for(int n =1; n< nums.length; n++)
        {
            if (nums[n]>max)
                max=nums[n];
        }
        return max;
    }
    static float GetMin(short[] nums) {
        float min = nums[0];
        for (int n = 1; n < nums.length; n++)
        {
            if (nums[n] < min)
                min = nums[n];
        }
        return min;
    }
    static float GetAvg(short[] nums){
        float sum=0;
        for (int n=0; n<nums.length;n++)
            sum+=nums[n];
        float avg = sum/ nums.length;
        return avg;
    }
    static float Percentile(short[] nums, float p){
        float n=p/100*(nums.length-1)+1; //Получаем порядковый номер числа в массиве
        float perc = nums[(int)n-1] + (n-(int)n)*(nums[(int)n]-nums[(int)n-1]);
        return perc;
    }
}

