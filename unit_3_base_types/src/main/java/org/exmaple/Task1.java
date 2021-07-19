package org.exmaple;

import java.util.Scanner;

public class Task1 {
    public static void task1(){
        Scanner in = new Scanner(System.in);
        System.out.print("Введите строку: ");
        String str;
        str=in.nextLine();
        String NumberOnlyString= str.replaceAll("[^0-9]", "");
        if(NumberOnlyString.length()!=0){
            int OnlyNumbers = Integer.parseInt(NumberOnlyString);
            int sum=0;
            while(OnlyNumbers != 0){
                sum += (OnlyNumbers % 10);
                OnlyNumbers/=10;
            }
            System.out.print("Сумма всех цифр в строке: ");
            System.out.println(sum);
            System.out.print("\n");
        }else{
            System.out.println("В строке не было чисел\n");
        }
    }
}
