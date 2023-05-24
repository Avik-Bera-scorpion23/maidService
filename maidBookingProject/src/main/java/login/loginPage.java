package login;

import static com.mongodb.client.model.Filters.eq;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * Servlet implementation class loginPage
 */


public class loginPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String uri = "mongodb+srv://beraavik23:beraavik@cluster0.9hc1eyc.mongodb.net/?retryWrites=true&w=majority"; 
	
       
    
    public loginPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		Cookie[] cookies = request.getCookies();
		
		 
		
		 PrintWriter pw= response.getWriter();
	    
	     String password=  request.getParameter("pass");
	    
	     String email=  request.getParameter("email");

	   
	   
	     if(  matchPassword(email, password)  && matchEmail(email) )
	     {
	        String nameCookie= loginFuncData(email);
	    	//System.out.println(  "Login successfully......." );
	    	
	    	 Cookie c = new Cookie("recentCredntial", email);
	    	 Cookie nameDetailsOfUser = new Cookie("nameCredntial", nameCookie);
	    	 //Cookie lname = new Cookie("recentCredntial", email);
	         response.addCookie(c);
	         response.addCookie(nameDetailsOfUser);
	    
	    	response.sendRedirect("index.html");
	    	
	    	
	    	
	     
	     }else 
	     {
	    	 
	    	 response.sendRedirect("errorPage.html");
	    	 
	     }
		
	     //pw.println("<h1> User name get from html :- <br> rest here.......</h1>");
	     pw.close();

		
		
		
	}
	
	
	public static String loginFuncData(String email ) 
	{
		
		
		  
	     try (MongoClient mongoClient = MongoClients.create(uri))
		 {
	         
			 MongoDatabase database = mongoClient.getDatabase("javaServlet");
	            MongoCollection<Document> collection = database.getCollection("clientDetail");
	           
			        
	           
				  Document doc =  collection.find(eq( "email" , email ) ).first(); 
				  
				  if( doc !=  null ) {
				  
//				  System.out.println("Doc data ,,,,  "+ doc.toJson() );
		          String firstName=  (String) doc.get("First_name");
//		          String newStr= str.replaceAll("\\s", "");
		          String lastName=  (String) doc.get("Last_name");
		          lastName= lastName.replaceAll("\\s", "");
		          String databaseMail=  (String) doc.get("email");
//		          System.out.println(" last name is   "+lastName);
				  if ( databaseMail.equals(email) )
				  {
					
					return firstName+"_"+lastName;
			      } 
				  
				  
		            
		          }   }  return null;

		
	}
	
	
	public static Boolean matchEmail( String data) 
	{
		  
	     try (MongoClient mongoClient = MongoClients.create(uri))
		 {
	         
	    	
	    	 MongoDatabase database = mongoClient.getDatabase("javaServlet");
	    	 MongoCollection<Document> collection = database.getCollection("clientDetail");
			
	    	 //System.out.println("Doc data ,,,,  "+ data );
			  Document doc =  collection.find(eq( "email" , data ) ).first(); 
			  
			  if( doc !=  null ) {
			  
			  //System.out.println("Doc data ,,,,  "+ doc.toJson() );
	          String databaseMail=  (String) doc.get("email");
			  if (databaseMail.equals(data) )
			  {
				return true;
				
		      } 
			  
			  
	            
	     }   }
	     
		return false;   }
	
	

	
	public static Boolean matchPassword( String data, String pass) 
	{
		  
	     try (MongoClient mongoClient = MongoClients.create(uri))
		 {
	         
	    	
	    	 MongoDatabase database = mongoClient.getDatabase("javaServlet");
	    	 MongoCollection<Document> collection = database.getCollection("clientDetail");
			
	    	 //System.out.println("Doc data ,,,,  "+ data );
			  Document doc =  collection.find(eq( "email" , data ) ).first(); 
			  
			  if( doc !=  null ) {
			  
			  //System.out.println("Doc data ,,,,  "+ doc.toJson() );
	          String databaseMail=  (String) doc.get("password");
			  if (databaseMail.equals(pass) )
			  {
				return true;				
		      } 
			  
			  }
			  
	     
		 }
	     
		return false;   }


}
