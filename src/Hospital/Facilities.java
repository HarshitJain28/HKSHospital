package Hospital;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Facilities extends Patient{
    static Scanner sc = new Scanner(System.in);
    public static void facHome() {
        System.out.println("OUR FACILITIES");
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
        System.out.println("We Have An Outstanding Cancer Care Facility Available");
        System.out.println("Our Team Of Doctors Consist Of Exceptional Individuals Who Have A Path Breaking Record In The Field Of Cancer Cure");
        System.out.println("Some Of Them Are: ");
        String line = "";
        String splitBy = ",";
        try{
            BufferedReader br = new BufferedReader(new FileReader("D:\\HKSHospital\\src\\Hospital\\Doctors.csv"));
                System.out.println(" ______________________________________________________________________________________________________________________________________________");
                System.out.printf("| %-15s | | %-15s | | %-15s | | %-15s | | %-15s | | %-10s | | %-10s | | %-10s |%n","First Name","Surname","Specialization","Qualification","Cabin Number","In Time","Out Time","Contacts");
                System.out.println(" ----------------------------------------------------------------------------------------------------------------------------------------------");
                while ((line = br.readLine()) != null) {
                    String[] doctor = line.split(splitBy);
                    if(doctor[2].equals("Oncologist")){
                        System.out.printf("| %-15s | | %-15s | | %-15s | | %-15s | | %-15s | | %-10s | | %-10s | | %-10s |%n",doctor[0], doctor[1], doctor[2], doctor[3], doctor[4] ,  doctor[5],doctor[6], doctor[7]);
                    }
                }
                System.out.println(" ----------------------------------------------------------------------------------------------------------------------------------------------");
            br.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Some Of Our Strong-willed Patients Who Have Won The War Against Cancer Are: ");
        try {
            BufferedReader br = new BufferedReader(new FileReader("D:\\HKSHospital\\src\\Hospital\\Patients.csv"));
            System.out.println(" __________________________________________________________________________________________");
            System.out.printf("| %-14s | | %-14s | | %-14s | | %-15s | | %-11s |%n","Unique ID", "First Name", "Last Name", "Reason Of Admit", "Status");
            System.out.println(" ------------------------------------------------------------------------------------------");
            while ((line = br.readLine()) != null) {
                String[] patient = line.split(splitBy);
                if(patient[3].equals("Cancer")&&patient[7].equals("Discharged"))
                System.out.printf("| %-14s | | %-14s | | %-14s | | %-15s | | %-11s |%n",patient[0], patient[1], patient[2], patient[3], patient[7]);
            }
            System.out.println(" ------------------------------------------------------------------------------------------");
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("**************For More Information Call On : 08850095645 **************");
    }
    public static void labTesting(){
        System.out.println("Choose One Of The Following: \n1)Book An Appointment\n2)Have An Appointment\n3)Back");
       try {
           int c;
           c = sc.nextInt();
           switch (c) {
               case 1 -> Testing.bookappointment();
               case 2 -> Testing.haveappointment();
               case 3 -> {
                   System.out.println("Returning Home...");
                   facHome();
               }
               default -> System.out.println("Invalid Choice");
           }
       } catch (Exception e) {
           e.printStackTrace();
       }

    }
    public static void availRooms(){
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("At HKS Hospital You Can Choose From Three Different Types Of Rooms: ");
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("A: Private Room\nSingle Bed In A Spacious Room Providing Peace\nTo The Patient While He Recovers\nMAX 4 Family Members Can Visit At A Time");
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("B: Semi-Private Room\nDouble Bed With A Partition Giving Each Patient Utmost Privacy\nWhile He Recovers\nMAX 2 Family Members Can Visit At A Time");
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("C: General Room\nEach Room Consists Of 5 Beds\nOnly 1 Family Member Can Visit At A Time");
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("Available Rooms At This Moment");
        roomAvail();
    }
}
