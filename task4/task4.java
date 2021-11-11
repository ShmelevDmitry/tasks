

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class task4 {

    public static void main(String[] args) {
        List<Integer> TimesList = new ArrayList<>();
        try {
            TimesList = TimesFromFileToArrayList(args[0]);
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        List<Integer> InTime = GetInTime(TimesList);//Время входа в минутах
        List<Integer> OutTime = GetOutTime(TimesList);//Время выхода в минутах

        //Отсортируем массивы по времени входа
        for (int i = InTime.size()-1; i>0; i--){
            for (int j = 0; j < i; j++) {
                if (InTime.get(j) > InTime.get(j+1)){
                    int tmpIn = InTime.get(j);
                    InTime.set(j,InTime.get(j+1));
                    InTime.set(j+1, tmpIn);
                    int tmpOut = OutTime.get(j);
                    OutTime.set(j,OutTime.get(j+1));
                    OutTime.set(j+1, tmpOut);
                }
            }
        }

        //Объединим интервалы, если один является продолжением другого
        for (int i =0; i< InTime.size(); i++){
            for (int j = i+1; j < InTime.size(); j++) {
                if (InTime.get(j).equals(OutTime.get(i))){
                    OutTime.set(i, OutTime.get(j));
                    OutTime.remove(j);
                    InTime.remove(j);
                }
            }
        }

        /* В условии задачи сказано, что время входа и выхода посетителей округляется до минут.
         Создадим массив, содержащий все варианты времени с 8:00 до 20:00 с интервалов в 1 минуту*/
        List<Integer> AllTimesInDay = new ArrayList<>();
        for (int i=8;i<20;i++){
            for (int j=0;j<60;j++){
                AllTimesInDay.add(i*60 + j);
            }
        }

        /* Теперь проверим каждую минуту в течение дня на вхождение в заданные временные интервалы,
        чтобы определить, сколько посетителей было в каждую отдельную минуту. Количество интервалов,
        в которые попадает сравниваемая минута, запишем в отдельный массив.
         */
        int[] NumberOfPeopleInCurrentMinute = new int[AllTimesInDay.size()];
        for (int i =0; i< AllTimesInDay.size();i++){
            for (int j=0; j< InTime.size();j++){
                if (AllTimesInDay.get(i)>=InTime.get(j) && AllTimesInDay.get(i)<= OutTime.get(j))
                    NumberOfPeopleInCurrentMinute[i]++;
            }
        }
        //Определим максимальное количество людей, присутствоваших одномоментно
        int max = 0;
        for (int i=0; i<NumberOfPeopleInCurrentMinute.length; i++){
            if (NumberOfPeopleInCurrentMinute[i]>max)
                max=NumberOfPeopleInCurrentMinute[i];
        }
        //Определим минуты, в которые было наибольшее число посетителей, перенесем в отдельный массив
        List<Integer> Periods = new ArrayList<>();
        for (int i=0; i< AllTimesInDay.size();i++) {
            if (NumberOfPeopleInCurrentMinute[i] == max) {
                Periods.add(AllTimesInDay.get(i));
            }
        }

        //"Свернем" полученные минуты во временные интервалы
        List<Integer> FinalPeriods = new ArrayList<>();
        FinalPeriods.add(Periods.get(0));
        for (int i=1; i< Periods.size()-1; i++){
            if (!(Periods.get(i+1).equals(Periods.get(i)+1)))
            {
                FinalPeriods.add(Periods.get(i));
                FinalPeriods.add(Periods.get(i+1));
            }
        }
        FinalPeriods.add(Periods.get(Periods.size()-1 ));

        //Выводим результат
        for (int i =0;i< FinalPeriods.size()-1;i+=2) {
            if (FinalPeriods.get(i)%60 < 10){
                if (FinalPeriods.get(i+1)%60 < 10){
                    System.out.printf("%d:0%d %d:0%d\n",FinalPeriods.get(i)/60, FinalPeriods.get(i)%60, FinalPeriods.get(i+1)/60, FinalPeriods.get(i+1)%60);
                }
                else System.out.printf("%d:0%d %d:%d\n",FinalPeriods.get(i)/60, FinalPeriods.get(i)%60, FinalPeriods.get(i+1)/60, FinalPeriods.get(i+1)%60);
            }
            else {
                if (FinalPeriods.get(i+1)%60 < 10){
                    System.out.printf("%d:%d %d:0%d\n",FinalPeriods.get(i)/60, FinalPeriods.get(i)%60, FinalPeriods.get(i+1)/60, FinalPeriods.get(i+1)%60);
                }
                else System.out.printf("%d:%d %d:%d\n",FinalPeriods.get(i)/60, FinalPeriods.get(i)%60, FinalPeriods.get(i+1)/60, FinalPeriods.get(i+1)%60);
            }
        }
    }
    static List<Integer> TimesFromFileToArrayList(String path) throws Exception{
        //Переводим временные интервалы из текстового файла в массив
        List<Integer> TimesList = new ArrayList<>();
        try(FileReader reader = new FileReader(path)){
            Scanner scanner = new Scanner(reader);
            scanner.useDelimiter("[:\r\n\s]+");
            while (scanner.hasNextInt())
                TimesList.add(scanner.nextInt());
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
        if (TimesList.size() == 0 || (TimesList.size() % 4 != 0)) throw new Exception("Invalid input parameters!");
        return TimesList;
    }
    static List<Integer> GetInTime(List<Integer> TimesList){
        //Получаем массив времен входа посетителей, для удобства вычислений переводим время в минуты
        List<Integer> InTime = new ArrayList<>();
        for (int i=0; i< (TimesList.size());i+=4)
        {
            InTime.add(TimesList.get(i)*60 + TimesList.get(i+1));
        }
        return InTime;
    }
    static List<Integer> GetOutTime(List<Integer> TimesList){
        //Получаем массив времен выхода посетителей, для удобства вычислений переводим время в минуты
        List<Integer> OutTime = new ArrayList<>();
        for (int i=0; i< (TimesList.size());i+=4)
        {
            OutTime.add(TimesList.get(i+2)*60 + TimesList.get(i+3));
        }
        return OutTime;
    }

}
