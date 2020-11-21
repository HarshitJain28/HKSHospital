package Hospital;

import java.util.Scanner;

public class Facilities extends Patient{
    static Scanner sc = new Scanner(System.in);
    public static void facHome() {
        System.out.println("DOCTORS");
        int stop = 1;

        while (stop == 1) {
            System.out.println("Choose One Of The Following: \n1)Available Rooms\n2)Cancer Care\n3)Labs in HKS\n4)Back");
            try {
                int choice;
                choice = sc.nextInt();
                switch (choice) {
                    case 1 -> availRooms();
                    case 2 -> cancerCare();
                    case 3 -> labTesting();
                    case 4 -> {
                        System.out.println("Returning Home...");
                        stop = 0;
                    }
                    default -> System.out.println("Invalid Choice");
                }
            } catch (Exception e) {
                System.out.println("Please Enter A Valid Input");
                sc.next();
            }
        }
    }
    public static void cancerCare(){

    }
    public static void labTesting(){
        int ch;
        System.out.println("YO");
    }
    public static void availRooms(){
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("At HKS Hospital You Can Choose From Three Different Types Of Rooms: ");
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("A: Private Room\nSingle Bed In A Spacious Room Providing Peace\nTo The Patient While He Recovers\nMAX 4 Family Members Can Visit At A Time");
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("A: Semi-Private Room\nDouble Bed With A Partition Giving Each Patient Utmost Privacy\nWhile He Recovers\nMAX 2 Family Members Can Visit At A Time");
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("A: General Room\nEach Room Consists Of 5 Beds\nOnly 1 Family Member Can Visit At A Time");
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("Available Rooms At This Moment");
        roomAvail();
    }
}
