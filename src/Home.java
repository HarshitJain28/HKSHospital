import java.util.Scanner;

public class Home {
    public static void main(String[] args) {
        System.out.println("Welcome To HKS Hospital");
        int stop=1;
        Scanner sc = new Scanner(System.in);
        while(stop==1){
            System.out.println("Choose One Of The Following: \n1)Doctor Records\n2)Patient Records\n3)Pharmacy\n4)Facilities\n5)Contacts And FAQs\n6)EXIT");
            try {
                int choice;
                choice = sc.nextInt();
                switch (choice){
                    case 1:
                        //Doctors();
                        break;
                    case 2:
                        //Patients();
                        break;
                    case 3:
                        //Pharmacy();
                        break;
                    case 4:
                       // Facilities();
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
