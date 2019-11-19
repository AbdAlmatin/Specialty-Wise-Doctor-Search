package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class loadDatabase {
    public static List< String > districts = new ArrayList< String >();
    public static List< String > thana = new ArrayList< String >();
    public static List< String > hospitals = new ArrayList< String >();
    public static  List<String> doctors = new ArrayList<>();
    public static Map<String, Integer> hospitals_reg_num = new HashMap<>();
    public static Map<String, Integer> doctor_reg_num = new HashMap<>();


    public static void LoadDatabase() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection mycon = DriverManager.getConnection("jdbc:mysql://localhost:3306/swds", "root", "amishipon");
        Statement stmt = mycon.createStatement();
        ResultSet res = stmt.executeQuery("SELECT distinct name from districts ORDER BY name");
        int indx = 0;
        while(res.next()){
            districts.add(res.getString("name"));
        }
        res = stmt.executeQuery("select name from upazilas");
        while (res.next()) {
            thana.add(res.getString("name"));
        }

        res = stmt.executeQuery("select reg_num, name from hospitals");
        while (res.next()) {
            hospitals.add(res.getString("name"));
            hospitals_reg_num.put(res.getString("name"), res.getInt("reg_num"));

//            System.out.println(hospitals_reg_num.get(res.getString("name")));
        }
        Statement stm3 = mycon.createStatement();
        ResultSet res2 = stm3.executeQuery("select concat(fname, ' ', lname) as name, reg_num from doctors");

        while(res2.next()) {
            doctors.add(res2.getString("name"));
            doctor_reg_num.put(res2.getString("name"), res2.getInt("reg_num"));
            System.out.println(res2.getString("name"));
        }


    }
}
