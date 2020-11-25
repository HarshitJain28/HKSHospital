package Hospital;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class Testing extends Facilities {
    static Scanner sc = new Scanner(System.in);
    static int flag=0;
    static String pId = "";
    static String fname = "";
    static String surname = "";
    public static void bookappointment(){
        System.out.println("Are You An In-House Patient 1) Yes 0) No");
        int inHouse = sc.nextInt();
        if(inHouse == 1){
            System.out.println("Enter The UID Of The Patient");
            sc.nextLine();
            pId = sc.nextLine();
            String line = "";
            String splitBy = ",";
            try {
                BufferedReader br = new BufferedReader(new FileReader("D:\\HKSHospital\\src\\data\\Patients.csv"));
                while ((line = br.readLine()) != null) {
                    String[] patient = line.split(splitBy);
                    if(patient[0].equals(pId) && patient[7].equals("Admitted")){
                        fname = patient[1];
                        surname = patient[2];
                        flag=1;
                        break;
                    }
                }
                if(flag==0){System.out.println("No Patient Exist With The Given ID OR The Patient Has Been Discharged");}
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else{flag=0;}
        if(flag==1 || inHouse!=1){
            System.out.println("There Are Three Types Of Test Available");
            System.out.println("1)Blood Test\n2)CT Scan\n3)X-Ray");
            try {
                int c;
                c = sc.nextInt();
                switch (c) {
                    case 1 -> test(1000,"Blood Test");
                    case 2 -> test(3000, "CT Scan");
                    case 3 -> test(2000,"X Ray");
                    case 4 -> {
                        System.out.println("Returning Home....");
                        labTesting();
                    }

                    default -> System.out.println("Invalid Choice");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    public static void haveappointment(){
        System.out.println("Enter Your Lab-ID");
        String lid = sc.nextLine();
        String line = "";
        String splitBy = ",";
        int notFound = 1;
        try {
            FileWriter writer = new FileWriter("D:\\HKSHospital\\src\\data\\Testings2.csv");
            BufferedWriter bwr = new BufferedWriter(writer);
            BufferedReader br = new BufferedReader(new FileReader("D:\\HKSHospital\\src\\data\\Testings.csv"));
            while ((line = br.readLine()) != null) {
                String[] testing = line.split(splitBy);
                if(testing[2].equals(lid) && testing[7].equals("Pending")){
                    notFound=0;
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    LocalDateTime now = LocalDateTime.now();
                    if(testing[6].equals(dtf.format(now))){
                        int timeToWait = 5;
                        System.out.print("Testing");
                        try {
                            for (int i=0; i<timeToWait ; i++) {
                                Thread.sleep(1000);
                                System.out.print(".");
                            }
                        } catch (InterruptedException ie)
                        {
                            Thread.currentThread().interrupt();
                        }
                        System.out.println("\nTest Successful");
                        bwr.write(testing[0] + "," + testing[1]  + "," + testing[2]  + "," + testing[3]  + "," + testing[4]  + "," + testing[5]  + "," + testing[6] +","+"Done");
                    }
                    else{
                        bwr.write(testing[0] + "," + testing[1]  + "," + testing[2]  + "," + testing[3]  + "," + testing[4]  + "," + testing[5]  + "," + testing[6] +","+testing[7]);
                        System.out.println("Your Date Of Appointment Is :" + testing[6] + " Please Come On That Day");
                    }
                }
                else{
                    bwr.write(testing[0] + "," + testing[1]  + "," + testing[2]  + "," + testing[3]  + "," + testing[4]  + "," + testing[5]  + "," + testing[6] +","+testing[7]);
                }
                bwr.write("\n");
            }
            if(notFound==1){
                System.out.println("The Given Lab ID Doesn't Exist in Pending List");
            }
            br.close();
            bwr.close();
            File deleteName = new File("D:\\HKSHospital\\src\\data\\Testings.csv");
            if (deleteName.delete()) {
                File oldName = new File("D:\\HKSHospital\\src\\data\\Testings2.csv");
                File newName = new File("D:\\HKSHospital\\src\\data\\Testings.csv");
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


    public static String id() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HHmmss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public static void updatePatient(int amnt) {
        String line = "";
        String splitBy = ",";
        try {
            FileWriter writer = new FileWriter("D:\\HKSHospital\\src\\data\\Patients2.csv");
            BufferedWriter bwr = new BufferedWriter(writer);
            BufferedReader br = new BufferedReader(new FileReader("D:\\HKSHospital\\src\\data\\Patients.csv"));
            while ((line = br.readLine()) != null) {
                String[] patient = line.split(splitBy);
                if (patient[0].equals(pId) && patient[7].equals("Admitted")) {
                    int dueAmnt = Integer.parseInt(patient[5]) + amnt;
                    bwr.write(patient[0] + "," + patient[1] + "," + patient[2] + "," + patient[3] + "," + patient[4] + "," + dueAmnt + "," + patient[6] + "," + patient[7]);
                } else {
                    bwr.write(patient[0] + "," + patient[1] + "," + patient[2] + "," + patient[3] + "," + patient[4] + "," + patient[5] + "," + patient[6] + "," + patient[7]);
                }
                bwr.write("\n");
            }
            br.close();
            bwr.close();
            File deleteName = new File("D:\\HKSHospital\\src\\data\\Patients.csv");
            if (deleteName.delete()) {
                File oldName = new File("D:\\HKSHospital\\src\\data\\Patients2.csv");
                File newName = new File("D:\\HKSHospital\\src\\data\\Patients.csv");
                if (oldName.renameTo(newName)) {
                    System.out.println("");
                } else {
                    System.out.println("Error");
                }
            } else {
                System.out.println("Failed to delete the file.");
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    public static void updateInTesting(int cat, String id, String lid, String test, String apDate){
        try {
            FileWriter writer = new FileWriter("D:\\HKSHospital\\src\\data\\Testings.csv", true);
            BufferedWriter bwr = new BufferedWriter(writer);
            bwr.write(cat + "," + id + "," + lid + "," + fname + "," + surname + "," + test + "," + apDate.substring(0,2)+"-"+apDate.substring(2,4)+"-"+apDate.substring(4,8)+","+"Pending");
            bwr.write("\n");
            bwr.close();
            System.out.println("Appointment Created");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    public static void test(int x,String type){

        if(flag==1){
            System.out.println("Lab Timings: 9 A.M To 3 P.M");
            System.out.println("Price: Rs "+x);
            System.out.println("Which Date is Feasible For You (ddmmyyyy)");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyy");
            LocalDateTime now = LocalDateTime.now();
            sc.nextLine();
            String apDate = sc.nextLine();
            while (Integer.parseInt(apDate) <= Integer.parseInt(dtf.format(now))){
                System.out.println("Please Enter A Date Starting From Tomorrow");
                apDate = sc.nextLine();
            }
            String labId = apDate+ "-" +id();
            updatePatient(x);
            updateInTesting(1,pId,labId,type,apDate);
            System.out.println("Your Appointment Has Been Booked Successfully For: " + apDate.substring(0,2)+"-"+apDate.substring(2,4)+"-"+apDate.substring(4,8) + " And Your Lab Id Is: " + labId);
        }
        else{
            System.out.println("Lab Timings: 9 A.M To 3 P.M");
            System.out.println("Price: Rs "+(x+1000));
            System.out.println("Enter Your First Name");
            fname = sc.next();
            System.out.println("Enter Your Last Name");
            surname = sc.next();
            System.out.println("Which Date is Feasible For You (ddmmyyyy)");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyy");
            LocalDateTime now = LocalDateTime.now();
            sc.nextLine();
            String apDate = sc.nextLine();
            while (Integer.parseInt(apDate) <= Integer.parseInt(dtf.format(now))){
                System.out.println("Please Enter A Date Starting From Tomorrow");
                apDate = sc.nextLine();
            }
            String labId = apDate+ "-" +id();
            System.out.println("Amount To Be Paid: " + (x+1000));
            int amnt = 0;
            while (amnt!=(x+1000)){
                System.out.println("Please Pay The Exact Amount");
                amnt = sc.nextInt();
            }
            System.out.println("Payment Receieved");
            updateInTesting(0,"NA",labId,type,apDate);
            System.out.println("Your Appointment Has Been Booked Successfully For: " + apDate.substring(0,2)+"-"+apDate.substring(2,4)+"-"+apDate.substring(4,8) + " And Your Lab Id Is: " + labId);

        }
    }
}
