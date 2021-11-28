

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        /*  Необходимо вычислить длину отрезка, концами которого являются
            центр окружности и точка из файла, а затем сравнить длину отрезка
            с радиусом окружности  */
        float radius;
        float[] circle = new float[3];
        List<Float> points = new ArrayList<>();

        try {
            circle = GetCircle(args[0]);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        try {
            points = GetPoints(args[1]);
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        radius = circle[2];

        for (int i = 0; i < points.size(); i+=2) {
            if (radius > GetLength(circle[0],circle[1],points.get(i),points.get(i+1) ))
                System.out.println(1);
            if (radius == GetLength(circle[0],circle[1],points.get(i),points.get(i+1) ))
                System.out.println(0);
            if (radius < GetLength(circle[0],circle[1],points.get(i),points.get(i+1) ))
                System.out.println(2);
        }
    }

    static List<Float> GetPoints(String path) throws Exception {
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

    static float[] GetCircle(String path) throws Exception {
        float[] circle = new float[3];
        int i = 0;
        try (FileReader reader = new FileReader(path)) {
            Scanner scan = new Scanner(reader);
            while (scan.hasNextFloat()) {
                circle[i] = scan.nextFloat();
                i++;
            }
            scan.close();
        } catch (IOException ex) {
            System.out.println("Файл не найден");
        }
        if (circle.length != 3) throw new Exception("Неверные параметры окружности!");
        return circle;
    }

    static double GetLength(float x1, float y1, float x2, float y2){
        double length = Math.sqrt(Math.pow(x2-x1,2)+Math.pow(y2-y1,2));
        return length;
    }
}
