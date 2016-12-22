/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import model.Category;
import model.ConnectToDataBase;
import model.Story;
import model.User;
import org.knallgrau.utils.textcat.TextCategorizer;

/**
 *
 * @author Ngoc Cuong
 */
@WebService(serviceName = "StoryWebService")
public class StoryWebService {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "checkLogin")
    public Boolean checkLogin(@WebParam(name = "user") User user) {
        //TODO write your implementation code here:
        ConnectToDataBase conn = new ConnectToDataBase();
        String sql = "select * from user where username = '" + user.getUsername() + "' and password = '"
                + user.getPassword() + "'";
        ResultSet rs = conn.executeQuerry(sql);
        try {
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Web service operation
     * Dịch vụ lấy các danh mục truyện
     */
    @WebMethod(operationName = "getListCategory")
    public ArrayList getListCategory() {
        //TODO write your implementation code here:
        ArrayList<Category> list = new ArrayList<Category>();
        try {
            ConnectToDataBase conn = new ConnectToDataBase();
            String sql = "select * from category";
            ResultSet rs = conn.executeQuerry(sql);

            // Đẩy dữ liệu vào list
            while (rs.next()) {
                Category ca = new Category();
                ca.setId(rs.getInt(1));
                ca.setName(rs.getNString(2));
                ca.setDescription(rs.getNString(3));
                list.add(ca);
            }
            rs.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Web service operation
     * Dịch vụ lấy danh sách truyện theo danh mục
     */
    @WebMethod(operationName = "getListStory")
    public ArrayList getListStory(@WebParam(name = "idCategory") int idCategory) {
        //TODO write your implementation code here:
        ArrayList<Story> list = new ArrayList<Story>();
        try {
            ConnectToDataBase conn = new ConnectToDataBase();
            String sql = "select * from story where categoryId = '" + idCategory + "'";
            ResultSet rs = conn.executeQuerry(sql);

            //Đẩy dữ liệu vào list
            while (rs.next()) {
                Story sto = new Story();
                sto.setId(rs.getInt(1));
                sto.setIdCategory(rs.getInt(2));
                sto.setName(rs.getNString(3));
                sto.setContent(rs.getNString(4));
                sto.setLanguage(rs.getNString(5));
                sto.setNote(rs.getNString(6));

                list.add(sto);
            }
            rs.close();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Web service operation
     * Dịch vụ lấy truyện theo ID
     */
    @WebMethod(operationName = "getStory")
    public Story getStory(@WebParam(name = "idStory") int idStory) {
        //TODO write your implementation code here:
        Story sto = new Story();
        try {
            ConnectToDataBase conn = new ConnectToDataBase();
            String sql = "select * from story where storyId = '" + idStory + "'";
            ResultSet rs = conn.executeQuerry(sql);

            //Đẩy dữ liệu
            if (rs.next()) {
                sto.setId(rs.getInt(1));
                sto.setIdCategory(rs.getInt(2));
                sto.setName(rs.getNString(3));
                sto.setContent(rs.getNString(4));
                sto.setLanguage(rs.getNString(5));
                sto.setNote(rs.getNString(6));
            }
            rs.close();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return sto;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "checkLoginS")
    public String checkLoginS(@WebParam(name = "username") String username, @WebParam(name = "password") String password) {
        //TODO write your implementation code here:
        
        ConnectToDataBase conn = new ConnectToDataBase();
        String sql = "select * from user where username = '" + username + "' and password = '"
                + password + "'";
        ResultSet rs = conn.executeQuerry(sql);
        try {
            if (rs.next()) {
                return "true";
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println(e.toString());
        }
        return "false";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getListCategoryS")
    public String getListCategoryS() {
        String listCategory = "";
        try {
            ConnectToDataBase conn = new ConnectToDataBase();
            String sql = "select * from category";
            ResultSet rs = conn.executeQuerry(sql);

            // Đẩy dữ liệu vào list
            while (rs.next()) {
               listCategory += rs.getInt(1) + "_";
               listCategory += rs.getNString(2);
               if (!rs.isLast()){
                   listCategory += "#";
               } 
            }
            rs.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return listCategory;
        
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getListStoryS")
    public String getListStoryS(@WebParam(name = "idCategory") String idCategory) {
        //TODO write your implementation code here:
        String listStory = "";
        try {
            ConnectToDataBase conn = new ConnectToDataBase();
            String sql = "select * from story where categoryId = '" + idCategory + "'";
            ResultSet rs = conn.executeQuerry(sql);

            //Đẩy dữ liệu vào list
            while (rs.next()) {
                listStory += rs.getInt(1) + "_";
                listStory += rs.getNString(3);
                
                if (!rs.isLast()){
                    listStory += "#";
                }       
            }
            rs.close();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());          
        }
        return listStory;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getStoryS")
    public String getStoryS(@WebParam(name = "idStory") String idStory) {
        //TODO write your implementation code here:
        String story = "";
        try {
            ConnectToDataBase conn = new ConnectToDataBase();
            String sql = "select * from story where storyId = '" + idStory + "'";
            ResultSet rs = conn.executeQuerry(sql);

            //Đẩy dữ liệu
            if (rs.next()) {
                story += rs.getNString(3) + "_";
                story += rs.getNString(4) + "_";
                story += rs.getNString(5);
            }
            rs.close();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return story;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "signupS")
    public String signupS(@WebParam(name = "username") String username, @WebParam(name = "password") String password) {
        ConnectToDataBase conn = new ConnectToDataBase();
        String sql = "select * from user where username = '" + username + "'";
                
        ResultSet rs = conn.executeQuerry(sql);
        try {
            if (rs.next()) {
                return "false";
            }
            String sqlInsert = "Insert into user (username,password) values ('" + username + "','" + 
                     password +"')";
            if(conn.execute(sqlInsert)){
                return "true";
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println(e.toString());
        }
        return "false";
    }
    
    private ArrayList<String> data(String file){
        ArrayList<String> list = new ArrayList<String>();
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while(br.ready()){
                String temp = br.readLine();
                list.add(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Categoryzer")
    public String Categoryzer() {
        //TODO write your implementation code here:
        int tot = 0;
        int khongtot = 0;
        int trunglap = 0;
        
        ArrayList<String> list1 = data("D:/test/test.txt");
        String category = "";
        TextCategorizer tc = new TextCategorizer("D:/test/textcat.conf");
        for(int i = getiIntRandomRange(0, 150);i <getiIntRandomRange(250, 600);i++){
            category = tc.categorize(list1.get(i).toString());
            
            if(category.equals("tot")){
                tot++;
            }else{
                if(category.equals("khongtot")){
                    khongtot++;
                }else{
                    if(category.equals("trunglap")){
                        trunglap++;
                    }
                }
            }
        }
        
        return tot + "-" + khongtot + "-" + trunglap;
       
    }
    
    public static int getiIntRandomRange(int lower, int upper) {
        //tạo một số nằm giữa trong khoảng của bạn  
        int range = (upper - lower) + 1;
        Random aRandom = new Random();

        //lấy một giá trị ngẫu nhiên từ 0.0 đến 1.0  
        double aDouble = aRandom.nextDouble();
        //nhân với range  
        aDouble = aDouble * range;
        //cộng cận dưới của khoảng vào giá trị ngẫu nhiên  
        aDouble += lower;
        //làm tròn theo giá trị thập phân  
        aDouble = Math.floor(aDouble);

        //nếu vượt quá cận trên  
        if (aDouble > upper) {
            aDouble = upper;
        }
        //chuyển kiểu double sang kiểu int và trả về  
        int anInt = (int) aDouble;

        return anInt;
    }
}
