package org.exmaple;

import java.util.Scanner;

public class Task1 {
    
    public static void task1() {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите строку: ");
        String str;
        str=in.nextLine();
        String numberOnlyString = str.replaceAll("[^0-9]", "");
        if (numberOnlyString.length() != 0) {
            int onlyNumbers = Integer.parseInt(numberOnlyString);
            int sum = 0;
            while(onlyNumbers != 0){
                sum += (onlyNumbers % 10);
                onlyNumbers /= 10;
            }
            System.out.print("Сумма всех цифр в строке: ");
            System.out.println(sum);
            System.out.print("\n");
        } else {
            System.out.println("В строке не было чисел\n");
        }
    }
}
