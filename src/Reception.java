import java.io.Console;
import java.util.Scanner;
import Hospital.*;
public class Reception {
    public static void main(String[] args) {
        System.out.println("Welcome To HKS Hospital");
        int stop=1;
        Scanner sc = new Scanner(System.in);
        while(stop==1){
            System.out.println("Choose One Of The Following: \n1)Our Doctors And Staff\n2)Patients\n3)HKS Pharmacy\n4)Our Facilities\n5)EXIT");
            try {
                int choice;
                choice = sc.nextInt();
                switch (choice) {
                    case 1 -> Doctor.docHome();
                    case 2 -> Patient.patientHome();
                    case 3 -> Pharmacy.pharmaHome();
                    case 4 -> Facilities.facHome();
                    case 5 -> {
                        System.out.println("Thank You For Visiting HKS Hospital");
                        System.out.println("Exiting...");
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
}
