package Hospital;

import java.io.*;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.Vector;

public class Pharmacy extends Patient {
    static Scanner sc = new Scanner(System.in);
    static Vector bill = new Vector();
    static int total = 0;
    static int flag = 0;

    public static void pharmaHome() {
        System.out.println("HKS PHARMACY");
        int sto = 1;
        while (sto == 1) {
            bill.clear();
            System.out.println("Choose One Of The Following: \n1)In House Patient\n2)Buy Medicines\n3)Add Medicine\n4)BACK");
            try {
                int choi;
                choi = sc.nextInt();
                switch (choi) {
                    case 1 -> inHouse();
                    case 2 -> outside();
                    case 3 -> addMed();
                    case 4 -> {
                        System.out.println("Returning Home...");
                        sto = 0;
                    }
                    default -> System.out.println("Invalid Choice");
                }
            } catch (Exception e) {
                System.out.println("Please Enter A Valid Input");
                sc.next();
            }
        }
    }

    public static void inHouse() {
        System.out.println("Enter The ID Of The Patient");
        String id = sc.next();
        String line = "";
        int check = 0;
        int notFound = 0;
        total = 0;
        flag = 0;
        String splitBy = ",";
        try {
            FileWriter writer = new FileWriter("D:\\HKSHospital\\src\\data\\Patients2.csv");
            BufferedWriter bwr = new BufferedWriter(writer);
            BufferedReader br = new BufferedReader(new FileReader("D:\\HKSHospital\\src\\data\\Patients.csv"));
            while ((line = br.readLine()) != null) {
                String[] patient = line.split(splitBy);
                if (patient[0].equals(id) && patient[7].equals("Admitted")) {
                    notFound = 1;
                    System.out.println("1)Enter The Name Of The Medicine\n2)Enter The Type Of Medicine");
                    int ch = sc.nextInt();
                    if (ch == 1) {
                        medName();
                    } else {
                        medType();
                    }
                    int dueMed = Integer.parseInt(patient[5]) + total;
                    bwr.write(patient[0] + "," + patient[1] + "," + patient[2] + "," + patient[3] + "," + patient[4] + "," + dueMed + "," + patient[6] + "," + patient[7]);
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
                    System.out.print("");
                } else {
                    System.out.println("Error");
                }
            } else {
                System.out.println("Failed to delete the file.");
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        if (notFound == 0) {
            System.out.println("No Patient With The Following ID Is Admitted");
        } else {
            Enumeration medsi = bill.elements();
            while (medsi.hasMoreElements()) {
                System.out.println(medsi.nextElement());
            }
            System.out.println("TOTAL:      " + total);
            System.out.println("When Do You Wish To Pay :1) Now 2) During Discharge");
            int h = sc.nextInt();
            if (h == 1) {
                payment();
            } else {
                System.out.println("Your Medicine Bill Has Been Added To Your Account");
            }
        }

    }

    public static void outside() {
        int ch;
        System.out.println("1)Enter The Name Of The Medicine\n2)Enter The Type Of Medicine");
        ch = sc.nextInt();
        total = 0;
        flag = 0;
        if (ch == 1) {
            medName();
        } else {
            medType();
        }
        Enumeration meds = bill.elements();
        while (meds.hasMoreElements()) {
            System.out.println(meds.nextElement());
        }
        System.out.println("TOTAL:      " + total);
        System.out.println("Amount To Be Paid: " + total);
        int amnt = 0;
        while (amnt != total) {
            System.out.println("Please Pay The Exact Amount");
            amnt = sc.nextInt();
        }
        System.out.println("THANK YOU FOR VISITING HKS PHARMACY");
    }

    public static void medName() {
        int stop = 1;
        while (stop == 1) {
            System.out.println("Enter The Name Of The Medicine");
            if (flag == 0)
                sc.nextLine();
            String name = sc.nextLine();
            String line = "";
            int notFound =0;
            int check2 = 0;
            String splitBy = ",";
            try {
                FileWriter writer = new FileWriter("D:\\HKSHospital\\src\\data\\Pharm2.csv");
                BufferedWriter bwr = new BufferedWriter(writer);
                BufferedReader br = new BufferedReader(new FileReader("D:\\HKSHospital\\src\\data\\Pharm.csv"));
                while ((line = br.readLine()) != null) {
                    String[] medicine = line.split(splitBy);
                    if (medicine[0].equals(name)) {
                        notFound=1;
                        if (medicine[2].equals("0")) {
                            check2 = 1;
                            bwr.write(medicine[0] + "," + medicine[1] + "," + medicine[2] + "," + medicine[3]);
                            bwr.write("\n");
                        } else {
                            int qty = 0;
                            System.out.println("The Available Quantity is: " + medicine[2]);
                            System.out.println("Each For Rs. " + medicine[3]);
                            System.out.println("Enter The Quantity");
                            qty = sc.nextInt();
                            while (qty > Integer.parseInt(medicine[2])) {
                                System.out.println("Enter The Appropriate Amount");
                                qty = sc.nextInt();
                            }
                            int stock = Integer.parseInt(medicine[2]) - qty;
                            bwr.write(medicine[0] + "," + medicine[1] + "," + stock + "," + medicine[3]);
                            bwr.write("\n");
                            bill.add(medicine[0] + " " + medicine[1] + " " + qty + " " + Integer.parseInt(medicine[3]) * qty);
                            total += Integer.parseInt(medicine[3]) * qty;
                        }
                        if (check2 == 1) {
                            System.out.println("Out Of Stock!!");
                        }
                    } else {
                        bwr.write(medicine[0] + "," + medicine[1] + "," + medicine[2] + "," + medicine[3]);
                        bwr.write("\n");
                    }
                }
                if (notFound == 0) {
                    System.out.println("The Given Medicine Is Not Available Right Now");
                }
                br.close();
                bwr.close();
                File deleteName = new File("D:\\HKSHospital\\src\\data\\Pharm.csv");
                if (deleteName.delete()) {
                    File oldName = new File("D:\\HKSHospital\\src\\data\\Pharm2.csv");
                    File newName = new File("D:\\HKSHospital\\src\\data\\Pharm.csv");
                    if (oldName.renameTo(newName)) {
                        System.out.print("");
                    } else {
                        System.out.println("Error");
                    }
                } else {
                    System.out.println("Failed to delete the file.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Do You Wish To Add More Medicines 1)Yes 0)No");
            stop = sc.nextInt();
            if (flag == 1 && stop == 1) {
                stop = 0;
                medType();
            }
        }
    }

    public static void medType() {
        flag = 1;
        System.out.println("Enter The Type Of Medicine You Want");
        System.out.println("Ayurvedic\nMulti-Vitamin\nAntiseptic\nNasal Spray\nPain relief\nAntipyretic\nCough and Cold");
        sc.nextLine();
        String type = sc.nextLine();
        String line = "";
        String splitBy = ",";
        int notFound = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader("D:\\HKSHospital\\src\\data\\Pharm.csv"));
            System.out.println(" __________________________________________________________________________");
            System.out.printf("| %-20s | | %-20s | | %-10s | | %-7s |%n","Medicine Name","Medicine Type","Quantity","Price");
            System.out.println(" --------------------------------------------------------------------------");
            while ((line = br.readLine()) != null) {
                String[] medicine = line.split(splitBy);
                if (medicine[1].equals(type)) {
                    notFound = 1;
                    System.out.printf("| %-20s | | %-20s | | %-10s | | %-7s |%n",medicine[0],medicine[1],medicine[2],medicine[3]);
                }
            }
            System.out.println(" --------------------------------------------------------------------------");
            br.close();
            if (notFound == 0) {
                System.out.println("No Such Type Available");
            } else {
                medName();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addMed() {
        System.out.println("Enter The Medicine Name");
        sc.nextLine();
        String name = sc.nextLine();
        String line = "";
        String splitBy = ",";
        int notFound = 0;
        try {
            FileWriter writer = new FileWriter("D:\\HKSHospital\\src\\data\\Pharm2.csv");
            BufferedWriter bwr = new BufferedWriter(writer);
            BufferedReader br = new BufferedReader(new FileReader("D:\\HKSHospital\\src\\data\\Pharm.csv"));
            while ((line = br.readLine()) != null) {
                String[] medicine = line.split(splitBy);
                if (medicine[0].equals(name)) {
                        notFound=1;
                        System.out.println("Enter The Quantity");
                        int qty = sc.nextInt();
                        int stock = Integer.parseInt(medicine[2]) + qty;
                        bwr.write(medicine[0] + "," + medicine[1] + "," + stock + "," + medicine[3]);
                        System.out.println("Medicine Restocked");
                }
                else{
                    bwr.write(medicine[0] + "," + medicine[1] + "," + medicine[2] + "," + medicine[3]);
                }
                bwr.write("\n");
            }
            if(notFound==0){
                System.out.println("Enter The Type Of Medicine");
                String type = sc.nextLine();
                System.out.println("Enter The Quantity");
                int qty = sc.nextInt();
                System.out.println("Enter The Cost Per Packet");
                int cost = sc.nextInt();
                bwr.write(name + "," + type + "," + qty + "," + cost);
                bwr.write("\n");
                System.out.println("Medicine Added To The List");
            }
            br.close();
            bwr.close();
            File deleteName = new File("D:\\HKSHospital\\src\\data\\Pharm.csv");
            if (deleteName.delete()) {
                File oldName = new File("D:\\HKSHospital\\src\\data\\Pharm2.csv");
                File newName = new File("D:\\HKSHospital\\src\\data\\Pharm.csv");
                if (oldName.renameTo(newName)) {
                    System.out.print("");
                } else {
                    System.out.println("Error");
                }
            } else {
                System.out.println("Failed to delete the file.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }
