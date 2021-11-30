public class NewTask1 {
    public static void main(String[] args){
        int n=0;
        int m=0;
        int k=0; //�������������� �������. ������������ ��� ����, ����� �������� � step ���������� ��������, ������ ����� ����
        int i=0;
        try {
             n = Integer.parseInt(args[0]);
             m = Integer.parseInt(args[1]);
        }
        catch (NumberFormatException e){
            System.err.println("Invalid input data!");
            return;
        }
        catch (ArrayIndexOutOfBoundsException e){
            System.err.println("No input data!");
            return;
        }

        if (n<=0 || m<=0){
            System.out.println("Input params must be >0!");
            return;
        }

        int[] arr = getArray(n);
        int[] step = new int[m];
        do {
            if (n==1 || m == 1){       //���� ������ ������� �� ������ ��������, ��, ���������� �� ���������� �����,
                System.out.print("1"); //�� ������ ������ ���� ��������� 1. �� �� ����� ��� ���� 1 � ������� ����� �����.
                break;
            }
            if (i > n-1)
                i=0;
            if (k < m-1){
                step[k] = arr[i];
                i++;
                k++;
            }
            else {
                System.out.print(step[0]);
                step[k] = arr[i];
                k=0;
            }
        } while (step[m-1] != arr[0]);
    }

    static int[] getArray(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i+1;
        }
        return arr;
    }
}
