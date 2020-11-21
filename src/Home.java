import java.util.Scanner;
import Hospital.*;
public class Home {
    public static void main(String[] args) {
        System.out.println("Welcome To HKS Hospital");
        int stop=1;
        Scanner sc = new Scanner(System.in);
        while(stop==1){
            System.out.println("Choose One Of The Following: \n1)Our Doctors\n2)Patients\n3)HKS Pharmacy\n4)Our Facilities\n5)Contacts And FAQs\n6)EXIT");
            try {
                int choice;
                choice = sc.nextInt();
                switch (choice){
                    case 1:
                       Doctor.docHome();
                        break;
                    case 2:
                        Patient.patientHome();
                        break;
                    case 3:
                        Pharmacy.pharmaHome();
                        break;
                    case 4:
                       Facilities.facHome();
                        break;
                    case 5:
                        //Contacts();
                        break;
                    case 6:
                        System.out.println("Exiting...");
                        stop=0;
                        break;
                    default:
                        System.out.println("Invalid Choice");
                }
            } catch (Exception e) {
                System.out.println("Please Enter A Valid Input");
                sc.next();
            }
        }
    }
}
