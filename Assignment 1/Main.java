import java.util.Scanner;

public class Main {
    public static void T1(){
        String name = "Furqan";
        int age = 19;
        double weight = 187.5;

        System.out.println(name);
        System.out.println(age);
        System.out.println(weight + "lbs");
        System.out.println();

    }

    public static void T2(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your age: ");
        int age = scanner.nextInt();
        if(age >= 18){
            System.out.println();
            System.out.println("You are an adult.");
        }
        else{
            System.out.println("You are a minor.");
        }

    }
    public static void T3(){
        int x = 0;
        int y = 20;

        while(x < y+1){
            if(x%2 == 0){
                System.out.println(x);
                x+=1;
            }
            else{
                x+=1;
            }
        }

        x = 0;
        y = 50;
        int sum = 0;

        while(x<y+1){
            if(x%2 == 0){
                x+=1;
            }
            else{
                sum = sum + x;
                x+=1;
            }
        }
        System.out.println(sum);


    }
    public static void T4(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter length: ");
        int length = scanner.nextInt();
        System.out.println("Enter width: ");
        int width = scanner.nextInt();
        int area = length * width;
        System.out.println("Area = " + area);


    }

    public static long calculateFactorial(int n){
        System.out.println();
        long result;

        if (n < 0){
            throw new IllegalArgumentException("Factorial is not defined for negative numbers.");}
        else if (n == 0) {
            result = 1;
            System.out.print("The factorial of " + n + " is: ");
            return result;
        }
        else {
            result = 1;
            for (int i = 1; i <= n; i++) {
                result *= i;
            }
            System.out.print("The factorial of " + n + " is: ");
            return result;
        }
    }
    public static void main(String[] args){
        T1();
        T2();
        T3();
        T4();
        calculateFactorial(5);
    }
}