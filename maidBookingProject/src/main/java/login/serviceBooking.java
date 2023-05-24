package login;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mongodb.client.model.geojson.GeoJsonObjectType;

import registration.registrationPage;

/**
 * Servlet implementation class serviceBooking
 */
public class serviceBooking extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	
    public serviceBooking() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("________________________________");


	  
		
		
//		System.out.println("________________________________");
		
		
		
		 StringBuffer jb = new StringBuffer();
		  String line = null;
		  try {
		    BufferedReader reader = request.getReader();
		    while ((line = reader.readLine()) != null)
		      jb.append(line);
		  } catch (Exception e) { /*report an error*/ }

		  String temp=jb.toString() ;
		  String t1="";
		  String[] words = temp.split("para=");

	        //using java loop to print elements of string array
	        for (int i = 0; i < words.length; i++) {
	            System.out.println(words[i]);
	            t1+=words[i];
	        }
//		  System.out.println( temp );
//		  System.out.println( jb.toString());
		  
		  try {
		   JSONParser jp= new JSONParser();
		   
		   Object obj= jp.parse(t1);
		   
		   JSONObject jobj= (JSONObject)obj;
		   
//		   System.out.println(  jobj.get("email") );  
//		   System.out.println(  jobj.get("FirstName") );  
//		   System.out.println(  jobj.get("LastName") );  
//		   System.out.println(  jobj.get("Ordered_Service") );
		   
		     String tmp1, tmp2, tmp3 ,tmp4;
		   tmp1= (String) jobj.get("FirstName");
		   tmp2	= (String)jobj.get("LastName"); 
		   tmp3 = (String)jobj.get("email");
		   tmp4= (String)jobj.get("Ordered_Service");
		   
		   registrationPage otherClassObj= new registrationPage();
		   otherClassObj.OrderSave( tmp1 , tmp2  , tmp3 , tmp4 );
		   
		   
//		    JSONObject jsonObject =  (JSONObject)temp;  registrationFunc()
		    
		  } catch (Exception e) {  System.out.println(  "Opps..." );    }


	
		
//		System.out.println( request.getParameter("para")); 
//		System.out.println( t1 ); 
//
//		
//		System.out.println("________________________________");
		
		PrintWriter out = response.getWriter();

	  

		    // finally output the json string       
		    out.print("Okay 1");
		    
	    
		
	}

}
