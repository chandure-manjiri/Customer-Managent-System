package CustomerManagementSystem.demo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
import java.util.UUID;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Customer Management System",
				version = "1.0.0",
				description = "Implemented Rest API using Spring Boot",
				contact = @Contact(
						name = "Manjiri Chandure",
						email = "manjiri.chandure@techprescient.com"
				),
				license = @License(
						name = "Licence",
						url = "manjiri"
				)
		)
)
public class CustomerApplication {



	public static void main(String[] args) throws IOException, InterruptedException {

		SpringApplication.run(CustomerApplication.class, args);
       System.out.println("Wants create customer using HTTPCleint (y/n)?:");
	   Scanner sc = new Scanner(System.in);
	   String c = sc.next();
	   char cc = c.charAt(0);
	   if(cc == 'y'){
		   int val = 0;
		   while (val != 8){
			   System.out.println("Read Customer by Id Enter 1 \n " +
					   "Read All Customer Enter 2 \n " +
					   "Create One Customer Enter 3\n " +
					   "Create Multiple Customers Enter 4 \n " +
					   "Update Customer by Id Enter 5 \n " +
					   "Delete Customer by Id Enter 6\n " +
					   "Delete All Customer Enter 7 \n " +
					   "8 for Stop\n");
			   int v = sc.nextInt();
			   if(v==1){ // read by id
				   System.out.println("Enter ID");
				   String uuid = sc.next();
				   String url = "http://localhost:8080/customer-mang-sys/customers/"+ uuid;
				   //System.out.println("Get Customers");
				   var request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
				   var client = HttpClient.newBuilder().build();

				   var response =  client.send(request, HttpResponse.BodyHandlers.ofString());

				   System.out.println(response.statusCode());
				   System.out.println(response.body());
			   }
			   else if(v == 2){ // read all
				   var url = "http://localhost:8080/customer-mang-sys/customers";
				   System.out.println("Get All Customers");
				   var request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
				   var client = HttpClient.newBuilder().build();

				   var response =  client.send(request, HttpResponse.BodyHandlers.ofString());

				   System.out.println(response.statusCode());
				   System.out.println(response.body());
			   }
			   else if(v == 3){ // create one
				   System.out.println("Add New Customer");
				   var url = "http://localhost:8080/customer-mang-sys/customer";
				   System.out.println("Enter Fisrt name: ");
				   String fname = sc.next();
				   System.out.println("Enter Last name: ");
				   String lname = sc.next();
				   System.out.println("Enter gender: ");
				   String gender = sc.next();
				   System.out.println("Enter phonenumber: ");
				   String pn = sc.next();
				   System.out.println("Enter age: ");
				   int age = sc.nextInt();

				   String jsonPayload = "{\n" +
						   "\"firstname\": \"" + fname + "\",\n" +
						   "\"lastname\": \"" + lname + "\",\n" +
						   "\"gender\": \"" + gender + "\",\n" +
						   "\"age\": " + age + ",\n" +
						   "\"phoneNumber\": \"" + pn + "\"\n" +
						   "}";

				   System.out.println(jsonPayload);

				   var request = HttpRequest.newBuilder()
						   .uri(URI.create(url))
						   .header("Content-Type", "application/json")
						   .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
						   .build();
				   var client = HttpClient.newBuilder().build();

				   try {
					   // Send the POST request
					   var response = client.send(request, HttpResponse.BodyHandlers.ofString());

					   // Print the response status code and body
					   System.out.println("Status Code: " + response.statusCode());
					   System.out.println("Response Body: " + response.body());

				   } catch (Exception e) {
					   e.printStackTrace();
				   }
			   }


			   else if(v == 4){ // create multiple

				   System.out.println("Add Multiple Customers");
				   var url = "http://localhost:8080/customer-mang-sys/customers";
				   int vv = 1;
				   String jsonPayload = "[";
				   while (vv == 1){
					   System.out.println("Enter Fisrt name: ");
					   String fname = sc.next();
					   System.out.println("Enter Last name: ");
					   String lname = sc.next();
					   System.out.println("Enter gender: ");
					   String gender = sc.next();
					   System.out.println("Enter phonenumber: ");
					   String pn = sc.next();
					   System.out.println("Enter age: ");
					   int age = sc.nextInt();
					   System.out.println("continue to add customers 1 for yes & 0 for no");
					   vv = sc.nextInt();


					    jsonPayload += "\n{\n" +
							   "\"firstname\": \"" + fname + "\",\n" +
							   "\"lastname\": \"" + lname + "\",\n" +
							   "\"gender\": \"" + gender + "\",\n" +
							   "\"age\": " + age + ",\n" +
							   "\"phoneNumber\": \"" + pn + "\"\n" +
							   "}";
						if(vv == 1)
							jsonPayload += ",";
				   }

				   jsonPayload += "\n]";

				   var request = HttpRequest.newBuilder()
						   .uri(URI.create(url))
						   .header("Content-Type", "application/json")
						   .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
						   .build();
				   var client = HttpClient.newBuilder().build();

				   try {
					   // Send the POST request
					   var response = client.send(request, HttpResponse.BodyHandlers.ofString());

					   // Print the response status code and body
					   System.out.println("Status Code: " + response.statusCode());
					   System.out.println("Response Body: " + response.body());

				   } catch (Exception e) {
					   e.printStackTrace();
				   }


			   }
			   else if(v == 5){ // update by id
				   System.out.println("Update customer by UUID");
				   System.out.println("Enter ID");
				   String uuid = sc.next();
				   String url = "http://localhost:8080/customer-mang-sys/customers/"+uuid;
				   System.out.println("Enter Fisrt name: ");
				   String fname = sc.next();
				   System.out.println("Enter Last name: ");
				   String lname = sc.next();
				   System.out.println("Enter gender: ");
				   String gender = sc.next();
				   System.out.println("Enter phonenumber: ");
				   String pn = sc.next();
				   System.out.println("Enter age: ");
				   int age = sc.nextInt();

				   String jsonPayload = "{\n" +
						   "\"firstname\": \"" + fname + "\",\n" +
						   "\"lastname\": \"" + lname + "\",\n" +
						   "\"gender\": \"" + gender + "\",\n" +
						   "\"age\": " + age + ",\n" +
						   "\"phoneNumber\": \"" + pn + "\"\n" +
						   "}";

				   // Build the PUT request
				   var request = HttpRequest.newBuilder()
						   .uri(URI.create(url))
						   .header("Content-Type", "application/json")
						   .PUT(HttpRequest.BodyPublishers.ofString(jsonPayload))
						   .build();

				   var client = HttpClient.newBuilder().build();

				   try {
					   // Send the PUT request
					   var response = client.send(request, HttpResponse.BodyHandlers.ofString());

					   // Print the response status code and body
					   System.out.println("Status Code: " + response.statusCode());
					   System.out.println("Response Body: " + response.body());

				   } catch (Exception e) {
					   e.printStackTrace();
				   }

			   }
			   else if(v == 6){ // delete by id
				   System.out.println("Delete customer by UUID");
				   System.out.println("Enter ID");
				   String uuid = sc.next();
				   String url = "http://localhost:8080/customer-mang-sys/customers/"+uuid;

				   String jsonPayload = "{\n" +
						   "\"id\": " +uuid+"\n" +
						   "}";

				   var request = HttpRequest.newBuilder()
						   .uri(URI.create(url))
						   .header("Content-Type", "application/json")
						   .DELETE()
						   .header("Accept", "application/json") // Optional: Set the Accept header if needed
						   .method("DELETE", HttpRequest.BodyPublishers.ofString(jsonPayload))
						   .build();

				   var client = HttpClient.newBuilder().build();
				   try {
					   // Send the DELETE request
					   var response = client.send(request, HttpResponse.BodyHandlers.ofString());

					   // Print the response status code and body
					   System.out.println("Status Code: " + response.statusCode());
					   System.out.println("Response Body: " + response.body());

				   } catch (Exception e) {
					   e.printStackTrace();
				   }

			   }
			   else if(v == 7){ // delete all
				   System.out.println("Delete all customers");
				   String url = "http://localhost:8080/customer-mang-sys/customers";
				   var request = HttpRequest.newBuilder().DELETE().uri(URI.create(url)).build();

				   var client = HttpClient.newBuilder().build();

				   var response =  client.send(request, HttpResponse.BodyHandlers.ofString());

				   System.out.println(response.statusCode());
				   System.out.println(response.body());

			   }
			   else if(v == 8){
				   System.exit(0);
			   }

		   }

	   }
	   else{
		   System.out.println("continue with postman, localhost or swagger");
	   }


	}

}
