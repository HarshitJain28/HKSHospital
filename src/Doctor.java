import java.io.*;


public class Doctor {
    public static void main(String[] args) {
        String line = "";
        String splitBy = ",";
        try
        {
//parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader("D:\\HospitalManagementSystem\\src\\Doctors.csv"));
            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                String[] doctor = line.split(splitBy);    // use comma as separator
                System.out.println(doctor[0] + " "  + doctor[1] + " " + doctor[2] + " " + doctor[3] + " " + doctor[4] + " " + doctor[5]);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}





