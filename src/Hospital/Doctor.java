package Hospital;
import java.io.*;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;


public class Doctor {
    static int currTime, flag=0;
    static Scanner sc = new Scanner(System.in);
    public static void docHome() {
        System.out.println("DOCTORS");
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
//parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader("D:\\HospitalManagementSystem\\src\\Hospital\\Doctors.csv"));
           if(flag==0){
               while ((line = br.readLine()) != null)   //returns a Boolean value
               {
                   String[] doctor = line.split(splitBy);    // use comma as separator
                   System.out.println(doctor[0] + " "  + doctor[1] + " " + doctor[2] + " " + doctor[3] + " " + doctor[4] + " " + doctor[5]+ " " + doctor[6]+ " " + doctor[7]);
               }
           }
           else{
               int count =0;
               while ((line = br.readLine()) != null)   //returns a Boolean value
               {

                   String[] doctor = line.split(splitBy);    // use comma as separator
                   if(count == 0){
                       count++;
                       continue;}
                   if(Integer.parseInt(doctor[5]) <= currTime && Integer.parseInt(doctor[6]) >= currTime) {
                       System.out.println(doctor[0] + " " + doctor[1] + " " + doctor[2] + " " + doctor[3] + " " + doctor[4] + " " + doctor[5] + " " + doctor[6] + " " + doctor[7]);
                   }
               }
               flag=0;
           }

            br.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public static void addDoc() {
        try {
            FileWriter writer = new FileWriter("D:\\HospitalManagementSystem\\src\\Hospital\\Doctors.csv", true);
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
            int contact = sc.nextInt();
            bwr.write(docName + "," + lastName + "," + spec + "," + qual + "," + cabin + "," + inhrs + "," + outhrs + "," + contact);
            bwr.write("\n");
            bwr.close();
            System.out.println("succesfully written to a file");
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




