

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class task2 {
    public static void main(String[] args){
        /* Алгоритм основан на вычислении псевдоскалярного произведения векторов АВ=ах*by - bx*ay;
            Если АВ > 0, вектор В находится правее вектора А, если АВ < 0, В левее А.
            Метод подходит для любого выпуклого четырехугольника
         */
        float FC[] = GetFigureCoord(args[0]); //Получаем координаты четырехугольника из файла
        List<Float> PC = GetPointsCoord(args[1]); //Получаем массив координат точек из файла
        float ax=(Float)FC[0]; //Для удобства переводим координаты вершин четырехугольника
        float ay=(Float)FC[1]; //из массива в числовые переменные
        float bx=(Float)FC[2];
        float by=(Float)FC[3];
        float cx=(Float)FC[4];
        float cy=(Float)FC[5];
        float dx=(Float)FC[6];
        float dy=(Float)FC[7];
        Vector ab=new Vector(FC[0],FC[1],FC[2],FC[3]); //Получаем координаты векторов
        Vector ad=new Vector(FC[0],FC[1],FC[6],FC[7]);
        Vector ba=new Vector(FC[2],FC[3],FC[0],FC[1]);
        Vector bc=new Vector(FC[2],FC[3],FC[4],FC[5]);
        Vector cb=new Vector(FC[4],FC[5],FC[2],FC[3]);
        Vector cd=new Vector(FC[4],FC[5],FC[6],FC[7]);
        Vector dc=new Vector(FC[6],FC[7],FC[4],FC[5]);
        Vector da=new Vector(FC[6],FC[7],FC[0],FC[1]);
        for(int i=0;i< PC.size();i+=2){
            Vector ap = new Vector(FC[0],FC[1],PC.get(i),PC.get(i+1));
            Vector bp = new Vector(FC[2],FC[3],PC.get(i),PC.get(i+1));
            Vector cp = new Vector(FC[4],FC[5],PC.get(i),PC.get(i+1));
            Vector dp = new Vector(FC[6],FC[7],PC.get(i),PC.get(i+1));
            if((PC.get(i)==ax && PC.get(i+1)==ay)||(PC.get(i)==bx && PC.get(i+1)==by)||(PC.get(i)==cx && PC.get(i+1)==cy)||(PC.get(i)==dx && PC.get(i+1)==dy)){
              System.out.println(0);
            }
            else if ((Vector.VectorMultiply(ab, ap)==0 && ay<PC.get(i+1) && PC.get(i+1)<by)||(Vector.VectorMultiply(bc, bp)==0 && bx<PC.get(i) && PC.get(i)<cx)||(Vector.VectorMultiply(cd, cp)==0 && dy<PC.get(i+1) && PC.get(i+1)<cy)||(Vector.VectorMultiply(da, dp)==0 && ax<PC.get(i) && PC.get(i+1)<dx)){
                System.out.println(1);
            }
            else if (Vector.VectorMultiply(ab,ap)>0 || Vector.VectorMultiply(bc,bp)>0 || Vector.VectorMultiply(cd,cp)>0 || Vector.VectorMultiply(da,dp)>0){
                System.out.println(3);
            }
            else if (Vector.VectorMultiply(ab,ap)<0 && Vector.VectorMultiply(bc,bp)<0 && Vector.VectorMultiply(cd,cp)<0 && Vector.VectorMultiply(da,dp)<0){
                System.out.println(2);
            }
        }
    }
static float[] GetFigureCoord(String path){
    float[] FC = new float[8];
    int i=0;
    try(FileReader reader = new FileReader(path)){
        Scanner scan = new Scanner(reader);
        while (scan.hasNextFloat())
        {
            FC[i]=scan.nextFloat();
            i++;
        }
        scan.close();
    }
    catch (IOException ex){
        System.out.println("Файл не найден");
    }
    return FC;
}
static List<Float> GetPointsCoord(String path){
        List<Float> PC = new ArrayList<>();
    try(FileReader reader = new FileReader(path)){
        Scanner scan = new Scanner(reader);
        while (scan.hasNextFloat())
        {
            PC.add(scan.nextFloat());
        }
        scan.close();
    }
    catch (IOException ex){
        System.out.println("Файл не найден");
    }
    return PC;
}
}
class Vector{
  float x, y;
  Vector(float x1, float y1, float x2, float y2){
      this.x=x2-x1;
      this.y=y2-y1;
  }
  public static float VectorMultiply(Vector a, Vector b){
      return a.x*b.y-b.x*a.y;
  }
}
