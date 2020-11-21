package Hospital;

import java.io.*;
import java.util.Scanner;

public class Nurse {
    static Scanner sc = new Scanner(System.in);
    public static void nurseHome() {
        System.out.println("NURSES");
        int stop = 1;
        while (stop == 1) {
            System.out.println("Choose One Of The Following: \n1)Nurse Records\n2)Available Doctors\n3)Add New Entry\n4)Back");
            try {
                int choice;
                choice = sc.nextInt();
                switch (choice) {
                    case 1 -> readNurseData();
                    case 2 -> readNurseData();
                    case 3 -> readNurseData();
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
    public static void readNurseData(){
        String line = "";
        String splitBy = ",";
        try{
            BufferedReader br = new BufferedReader(new FileReader("D:\\HKSHospital\\src\\Hospital\\Nurses.csv"));
                while ((line = br.readLine()) != null) {
                    String[] nurse = line.split(splitBy);
                    System.out.println(String.format("| %-15s | | %-15s | | %-15s | | %-15s | ",nurse[0], nurse[1], nurse[2], nurse[3]));
                }

            br.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
