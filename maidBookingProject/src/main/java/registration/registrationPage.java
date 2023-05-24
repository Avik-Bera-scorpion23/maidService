package registration;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static com.mongodb.client.model.Filters.eq;
import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * Servlet implementation class registrationPage
 */


public class registrationPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String uri = "mongodb+srv://beraavik23:beraavik@cluster0.9hc1eyc.mongodb.net/?retryWrites=true&w=majority"; 
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registrationPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		 PrintWriter pw= response.getWriter();
	     String fname=  request.getParameter("firstNameOfClient");
	     String lname=  request.getParameter("lastNameOfClient");
	     String password=  request.getParameter("pass");
	     String confirm_password=  request.getParameter("cpass");
	     String email=  request.getParameter("email");
	     String address=  request.getParameter("addressOfClient");
	     String mobileNum=  request.getParameter("mobileNumber");
	     String requirement=  request.getParameter("requirement");
	   
	     if( matchEmail(email)== false )
	     {
	     
	    		response.sendRedirect("index.html");
	     registrationFunc( fname ,lname, password, email, address, mobileNum, requirement );
	 //pw.println("<h1> User name get from html :- <br>"+requirement +" --- "+email +" "+fname+" "+lname+"  "+address+" "+password+" -- "+confirm_password +" rest here.......</h1>");
	 	 
//	 System.out.println(email );
//	 System.out.println(requirement );
//	 System.out.println(address );
//	 System.out.println(confirm_password );
//	 System.out.println(password );
//	 System.out.println(fname );
	 
//	 System.out.println("--------------------------" );
	    	 
	     }else if(  matchEmail(email)== true ) {
	    	 
	    	 response.sendRedirect("errorPage.html");
	 
	    	 
	     }else {
	    	 
//	    	 pw.println("<h1> password and confirm password mismatch </h1>"); 
	    	 response.sendRedirect("errorPage.html");
	    	 
	     }
		
	   pw.close();


		
		
		
	}
	
	public static void registrationFunc( String fname ,String lname,String password,String email,String address,String mobileNum,String requirement  ) 
	{
		
		
		  
	     try (MongoClient mongoClient = MongoClients.create(uri))
		 {
	         
	    	
	    	 MongoDatabase database = mongoClient.getDatabase("javaServlet");
	    	 MongoCollection<Document> collection = database.getCollection("clientDetail");
			
	            Document doc= new Document( "First_name", fname  );
	               
			        doc.append("Last_name", lname);
			        doc.append("address", address );
			        doc.append("email", email );
			        doc.append("contact_number", mobileNum );			       
			        doc.append("requirement", requirement );			       
			        doc.append("password", password );
			        collection.insertOne(doc);
			        
	            FindIterable<Document> data = collection.find();
	            for (Document base : data)
	            {
	            	
	            /*   System.out.println("-------------------");	              
	           	 
	           	   System.out.println( base.get("First_name") );
	               System.out.println( base.get("Last_name") );
	               System.out.println( base.get("age") );
	               System.out.println( base.get("address") );
	               System.out.println( base.get("requirement") );
	               System.out.println( base.get("mobile_number") );
	               System.out.println( base.get("adhar") );
	               System.out.println( base.get("id") );
	               
	               System.out.println("-------------------");*/
	              
	            }
	            
	        }  

	     
	    
		
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
		
		
	public void OrderSave(String fname, String lname , String email, String order ) {
		
		
		
		 try (MongoClient mongoClient = MongoClients.create(uri))
		 {
	         
	    	
	    	 MongoDatabase database = mongoClient.getDatabase("javaServlet");
	    	 MongoCollection<Document> collection = database.getCollection("OrderedDetail");
			
	            Document doc= new Document( "First_name", fname  );
	               
			        doc.append("Last_name", lname);
			        doc.append("email", email );
			        doc.append("order_service", order );
			        
			        collection.insertOne(doc);
			        
	            FindIterable<Document> data = collection.find();
	            for (Document base : data)
	            {
	            	
	             /*  System.out.println("-----Save--------------");	              	           	 
	           	   System.out.println( base.get("First_name") );
	               System.out.println( base.get("Last_name") );
	               System.out.println( base.get("order_service") );
	               System.out.println( base.get("email") );              
	               System.out.println("-----------------Save--");*/
	              
	            }
	            
	        }  

		
	}

}
