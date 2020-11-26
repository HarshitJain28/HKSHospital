package Hospital;
import java.io.*;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Doctor {
    static int currTime, flag=0;
    static Scanner sc = new Scanner(System.in);
    public static void docHome() {
        System.out.println("DOCTORS AND STAFF");
        int stop = 1;
        while (stop == 1) {
            System.out.println("Choose One Of The Following: \n1)Doctor Records\n2)Available Doctors\n3)Add New Entry\n4)Back");
            try {
                int choice;
                choice = sc.nextInt();
                switch (choice) {
                    case 1 -> readDocData();
                    case 2 -> availableDoc();
                    case 3 -> addDoc();
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
    public static void readDocData(){
        String line = "";
        String splitBy = ",";
        try{
            BufferedReader br = new BufferedReader(new FileReader("D:\\HKSHospital\\src\\data\\Doctors.csv"));
           if(flag==0){
               System.out.println(" ______________________________________________________________________________________________________________________________________________");
               System.out.printf("| %-15s | | %-15s | | %-15s | | %-15s | | %-15s | | %-10s | | %-10s | | %-10s |%n","First Name","Surname","Specialization","Qualification","Cabin Number","In Time","Out Time","Contacts");
               System.out.println(" ----------------------------------------------------------------------------------------------------------------------------------------------");
               while ((line = br.readLine()) != null)
               {
                   String[] doctor = line.split(splitBy);
                   System.out.printf("| %-15s | | %-15s | | %-15s | | %-15s | | %-15s | | %-10s | | %-10s | | %-10s |%n",doctor[0], doctor[1], doctor[2], doctor[3], doctor[4] ,  doctor[5],doctor[6], doctor[7]);
               }
               System.out.println(" ----------------------------------------------------------------------------------------------------------------------------------------------");

           }
           else{
               System.out.println(" ______________________________________________________________________________________________________________________________________________");
               System.out.printf("| %-15s | | %-15s | | %-15s | | %-15s | | %-15s | | %-10s | | %-10s | | %-10s |%n","First Name","Surname","Specialization","Qualification","Cabin Number","In Time","Out Time","Contacts");
               System.out.println(" ----------------------------------------------------------------------------------------------------------------------------------------------");
               while ((line = br.readLine()) != null){
                   String[] doctor = line.split(splitBy);
                   if(Integer.parseInt(doctor[5]) <= currTime && Integer.parseInt(doctor[6]) >= currTime) {
                       System.out.printf("| %-15s | | %-15s | | %-15s | | %-15s | | %-15s | | %-10s | | %-10s | | %-10s |%n",doctor[0], doctor[1], doctor[2], doctor[3], doctor[4] ,  doctor[5],doctor[6], doctor[7]);
                   }
               }
               System.out.println(" ----------------------------------------------------------------------------------------------------------------------------------------------");
               flag=0;
           }

            br.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void addDoc() {
        try {
            FileWriter writer = new FileWriter("D:\\HKSHospital\\src\\data\\Doctors.csv", true);
            BufferedWriter bwr = new BufferedWriter(writer);
            sc.nextLine();
            System.out.println("Enter First Name");
            String docName = sc.nextLine();
            System.out.println("Enter Last Name");
            String lastName = sc.nextLine();
            System.out.println("Enter Specialization");
            String spec = sc.nextLine();
            System.out.println("Enter Qualifications");
            String qual = sc.nextLine();
            System.out.println("Enter Cabin");
            int cabin = sc.nextInt();
            System.out.println("Enter In Time");
            int inhrs = sc.nextInt();
            System.out.println("Enter Out Time");
            int outhrs = sc.nextInt();
            System.out.println("Enter Contact");
            String contact = sc.next();
            while(contact.length()!=10){
                System.out.println("Enter A Valid Number");
                contact = sc.next();
            }
            bwr.write(docName + "," + lastName + "," + spec + "," + qual + "," + cabin + "," + inhrs + "," + outhrs + "," + contact);
            bwr.write("\n");
            bwr.close();
            System.out.println("Successfully Added To The List");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    public static void availableDoc(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HHmm");
        LocalDateTime now = LocalDateTime.now();
        currTime = Integer.parseInt(dtf.format(now));
        flag=1;
        readDocData();
    }
}




