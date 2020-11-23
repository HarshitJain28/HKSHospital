package Hospital;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.logging.Logger;

public class Patient {
    static Scanner sc = new Scanner(System.in);
    static int room[][] = new int[3][];

    public static void patientHome() {
        System.out.println("PATIENTS");
        int stop = 1;

        while (stop == 1) {
            System.out.println("Choose One Of The Following: \n1)Patient Records\n2)Admit New Patient\n3)Payment\n4)Discharge Patient\n5)Back");
            try {
                int choice;
                choice = sc.nextInt();
                switch (choice) {
                    case 1 -> readPatient();
                    case 2 -> addPatient();
                    case 3 -> payment();
                    case 4 -> discharge();
                    case 5 -> {
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

    public static void readPatient() {
        String line = "";
        String splitBy = ",";
        try {
            BufferedReader br = new BufferedReader(new FileReader("D:\\HKSHospital\\src\\Hospital\\Patients.csv"));
            System.out.println(" ______________________________________________________________________________________________________________________________________");
            System.out.println(String.format("| %-14s | | %-14s | | %-14s | | %-15s | | %-11s | | %-8s | | %-10s | | %-11s |","Unique ID", "First Name", "Last Name", "Disease", "Room Number" ,  "Amnt Due","Amnt Paid", "Status"));
            System.out.println(" --------------------------------------------------------------------------------------------------------------------------------------");
            while ((line = br.readLine()) != null) {
                String[] patient = line.split(splitBy);
                System.out.println(String.format("| %-14s | | %-14s | | %-14s | | %-15s | | %-11s | | %-8s | | %-10s | | %-11s |",patient[0], patient[1], patient[2], patient[3], patient[4] ,  patient[5],patient[6], patient[7]));
            }
            System.out.println(" --------------------------------------------------------------------------------------------------------------------------------------");
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addPatient() {
        try {
            FileWriter writer = new FileWriter("D:\\HKSHospital\\src\\Hospital\\Patients.csv", true);
            BufferedWriter bwr = new BufferedWriter(writer);
            sc.nextLine();
            int roomNum = 0;
            System.out.println("Enter First Name");
            String patientName = sc.nextLine();
            System.out.println("Enter Last Name");
            String lastName = sc.nextLine();
            System.out.println("Enter Disease");
            String disease = sc.nextLine();
            System.out.println("Which Category Of Room Do You Prefer");
            roomAvail();
            char roomchar = sc.next().charAt(0);
            if (roomchar == 'A') {
                for (int i = 0; i < room[0].length; i++) {
                    if (room[0][i] == 0) {
                        roomNum = i + 1;
                        break;
                    }
                }
            } else if (roomchar == 'B') {
                for (int i = 0; i < room[1].length; i++) {
                    if (room[1][i] == 0) {
                        roomNum = i + 1;
                        break;
                    }
                }
            } else {
                for (int i = 0; i < room[2].length; i++) {
                    if (room[2][i] == 0) {
                        roomNum = i + 1;
                        break;
                    }
                }
            }
            bwr.write(uid() + "," + patientName + "," + lastName + "," + disease + "," + roomchar + "" + roomNum + ",0,0,Admitted");
            bwr.write("\n");
            bwr.close();
            System.out.println("Patient Admitted Successfully");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void roomAvail() {
        room[0] = new int[10];
        room[1] = new int[20];
        room[2] = new int[30];

        String line = "";
        String splitBy = ",";
        try {
            BufferedReader br = new BufferedReader(new FileReader("D:\\HKSHospital\\src\\Hospital\\Patients.csv"));
            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                String[] patient = line.split(splitBy);
                if(patient[7].equals("Admitted")){
                    if (patient[4].charAt(0) == 'A') {
                        room[0][Integer.parseInt(patient[4].substring(1)) - 1] = 1;

                    } else if (patient[4].charAt(0) == 'B') {
                        room[1][Integer.parseInt(patient[4].substring(1)) - 1] = 1;
                    } else {
                        room[2][Integer.parseInt(patient[4].substring(1)) - 1] = 1;
                    }
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int countA = 0;
        int countB = 0;
        int countC = 0;
        for (int j = 0; j < room[0].length; j++) {
            if (room[0][j] == 0) {
                countA++;
            }
        }
        for (int j = 0; j < room[1].length; j++) {
            if (room[1][j] == 0) {
                countB++;
            }
        }
        for (int j = 0; j < room[2].length; j++) {
            if (room[2][j] == 0) {
                countC++;
            }
        }

        System.out.println("The Available Rooms Are: ");
        System.out.println("A: Private Rooms: " + countA + " Rs.5000 per night each");
        System.out.println("B: Semi-Private Rooms: " + countB + " Rs.3000 per night each");
        System.out.println("C: General Rooms: " + countC + " Rs.1000 per night each");
    }

    public static String uid() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyMMdd-HHmmss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public static void discharge() {
        System.out.println("Enter The UID Of The Patient To Be Discharged");
        String id = sc.next();
        money(id);
        String line = "";
        String splitBy = ",";
        try {

            FileWriter writer = new FileWriter("D:\\HKSHospital\\src\\Hospital\\Patients2.csv");
            BufferedWriter bwr = new BufferedWriter(writer);
            BufferedReader br = new BufferedReader(new FileReader("D:\\HKSHospital\\src\\Hospital\\Patients.csv"));
            while ((line = br.readLine()) != null) {
                String[] patient = line.split(splitBy);
                if(patient[0].equals(id) && patient[7].equals("Admitted")){
                    if(patient[5].equals("0")){
                        bwr.write(patient[0] + "," + patient[1] + "," + patient[2] + "," + patient[3] + "," + patient[4] + "," + patient[5] + "," + patient[6] + ",Discharged");
                    }
                    else{
                        System.out.println("Please Pay The Amount Due To Discharge The Patient");
                        bwr.write(patient[0] + "," + patient[1] + "," + patient[2] + "," + patient[3] + "," + patient[4] + "," + patient[5] + "," + patient[6] + "," + patient[7]);
                    }
                }
                else{
                    bwr.write(patient[0] + "," + patient[1] + "," + patient[2] + "," + patient[3] + "," + patient[4] + "," + patient[5] + "," + patient[6] + "," + patient[7]);
                }
                bwr.write("\n");
            }
            br.close();
            bwr.close();
            File deleteName = new File("D:\\HKSHospital\\src\\Hospital\\Patients.csv");
            if (deleteName.delete()) {
                File oldName = new File("D:\\HKSHospital\\src\\Hospital\\Patients2.csv");
                File newName = new File("D:\\HKSHospital\\src\\Hospital\\Patients.csv");
                if(oldName.renameTo(newName)) {
                    System.out.println("");
                } else {
                    System.out.println("ERROR!!!");
                }
            } else {
                System.out.println("FAILED TO ADD");
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    public static void payment(){
        System.out.println("Please Enter The UID Of The Patient");
        String id = sc.next();
        String line = "";
        String splitBy = ",";
        try {
            FileWriter writer = new FileWriter("D:\\HKSHospital\\src\\Hospital\\Patients2.csv");
            BufferedWriter bwr = new BufferedWriter(writer);
            BufferedReader br = new BufferedReader(new FileReader("D:\\HKSHospital\\src\\Hospital\\Patients.csv"));
            while ((line = br.readLine()) != null) {
                String[] patient = line.split(splitBy);
                if(patient[0].equals(id)){
                    System.out.println("Amount To Be Paid: "+ patient[5]);
                    int amnt = 0;
                    while (amnt!=Integer.parseInt(patient[5])){
                        System.out.println("Please Pay The Exact Amount");
                        amnt = sc.nextInt();
                    }
                    int amntDue= Integer.parseInt(patient[5]) - amnt;
                    int amntPaid = Integer.parseInt(patient[6]) + amnt;
                    bwr.write(patient[0] + "," + patient[1] + "," + patient[2] + "," + patient[3] + "," + patient[4] + "," + amntDue + "," + amntPaid + "," + patient[7]);
                }
                else{
                    bwr.write(patient[0] + "," + patient[1] + "," + patient[2] + "," + patient[3] + "," + patient[4] + "," + patient[5] + "," + patient[6] + "," + patient[7]);
                }
                bwr.write("\n");
            }
            br.close();
            bwr.close();
            File deleteName = new File("D:\\HKSHospital\\src\\Hospital\\Patients.csv");
            if (deleteName.delete()) {
                File oldName = new File("D:\\HKSHospital\\src\\Hospital\\Patients2.csv");
                File newName = new File("D:\\HKSHospital\\src\\Hospital\\Patients.csv");
                if(oldName.renameTo(newName)) {
                    System.out.println("");
                } else {
                    System.out.println("ERROR!!");
                }
            } else {
                System.out.println("FAILED TO ADD");
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    public static void money(String id) {
        LocalDate dt = LocalDate.parse("20"+id.substring(0,2)+"-"+id.substring(2,4)+"-"+id.substring(4,6));
        int admitdate = dt.getDayOfYear();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        LocalDate dt2 = LocalDate.parse(dtf.format(now));
        int todaydate = dt2.getDayOfYear();
        int sub = todaydate-admitdate;
        int due = 0;
        String line = "";
        String splitBy = ",";
        try {
            FileWriter writer = new FileWriter("D:\\HKSHospital\\src\\Hospital\\Patients2.csv");
            BufferedWriter bwr = new BufferedWriter(writer);
            BufferedReader br = new BufferedReader(new FileReader("D:\\HKSHospital\\src\\Hospital\\Patients.csv"));
            while ((line = br.readLine()) != null) {
                String[] patient = line.split(splitBy);
                if(patient[0].equals(id)){
                    char roomType = patient[4].charAt(0);
                    if(roomType == 'A'){
                        due = 5000*sub;
                    }
                    if(roomType == 'B'){
                        due = 3000*sub;
                    }
                    if(roomType == 'C'){due = 1000*sub;}
                    int totalDue = Integer.parseInt(patient[5])+due;
                    bwr.write(patient[0] + "," + patient[1] + "," + patient[2] + "," + patient[3] + "," + patient[4] + "," + totalDue + "," + patient[6] + "," + patient[7]);

                }
                else{
                    bwr.write(patient[0] + "," + patient[1] + "," + patient[2] + "," + patient[3] + "," + patient[4] + "," + patient[5] + "," + patient[6] + "," + patient[7]);
                }
                bwr.write("\n");
            }
            br.close();
            bwr.close();
            File deleteName = new File("D:\\HKSHospital\\src\\Hospital\\Patients.csv");
            if (deleteName.delete()) {
                File oldName = new File("D:\\HKSHospital\\src\\Hospital\\Patients2.csv");
                File newName = new File("D:\\HKSHospital\\src\\Hospital\\Patients.csv");
                if(oldName.renameTo(newName)) {
                    System.out.println("");
                } else {
                    System.out.println("ERROR!!!");
                }
            } else {
                System.out.println("FAILED TO ADD");
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        payment();
    }
}

