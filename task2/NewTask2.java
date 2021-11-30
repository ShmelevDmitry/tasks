import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NewTask2 {
    public static void main(String[] args) {
        /*  Необходимо вычислить длину отрезка, концами которого являются
            центр окружности и точка из файла, а затем сравнить длину отрезка
            с радиусом окружности  */
        List<Float> circle = new ArrayList<>();
        List<Float> points = new ArrayList<>();

        try {
            circle = getCircle(args[0]);
                   }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        try {
            points = getPoints(args[1]);
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        float radius = circle.get(2);

        for (int i = 0; i < points.size(); i+=2) {
            if (radius > getLength(circle.get(0),circle.get(1),points.get(i),points.get(i+1) ))
                System.out.println(1);
            if (radius == getLength(circle.get(0),circle.get(1),points.get(i),points.get(i+1) ))
                System.out.println(0);
            if (radius < getLength(circle.get(0),circle.get(1),points.get(i),points.get(i+1) ))
                System.out.println(2);
        }
    }

    static List<Float> getPoints(String path) throws Exception {
        List<Float> points = new ArrayList<>();
        try (FileReader reader = new FileReader(path)) {
            Scanner scan = new Scanner(reader);
            while (scan.hasNextFloat()) {
                points.add(scan.nextFloat());
            }
            scan.close();
        } catch (IOException ex) {
            System.out.println("Файл не найден");
        }
        if (points.size() % 2 != 0)
            throw new Exception("Заданы неверные координаты точек");
        return points;
    }

    static List<Float> getCircle(String path) throws Exception {
        List<Float> circle = new ArrayList<>();
        try (FileReader reader = new FileReader(path)) {
            Scanner scan = new Scanner(reader);
            while (scan.hasNextFloat()) {
                circle.add(scan.nextFloat());
            }
            scan.close();
        } catch (IOException ex) {
            System.out.println("Файл не найден");
        }
        if (circle.size() != 3) throw new Exception("Неверные параметры окружности!");
        return circle;
    }

    static double getLength(float x1, float y1, float x2, float y2){
        double length = Math.sqrt(Math.pow(x2-x1,2)+Math.pow(y2-y1,2));
        return length;
    }
}