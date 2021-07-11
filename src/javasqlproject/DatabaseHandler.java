package javasqlproject;

import java.sql.*;
import java.util.*;

public class DatabaseHandler {
	
	static Scanner sc = new Scanner(System.in);
	
	private static Connection conn;
    private static Statement st;
    private static ResultSet rs;

	public static void main(String[] args) {
		
		System.out.print("Enter Username: ");
		String username = sc.nextLine();
		
		System.out.print("Enter Password: ");
		String password = sc.nextLine();
		
		String query = "query";
		int action_no = 0;
		int flag = 1;
		
		do {
			System.out.println("Enter 1: Select customer table\nEnter 2: Select customer_order table\nEnter 3: Select product table");
			int table_no = sc.nextInt();
			
			try {
				//Connect to database
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cex", username, password);
				System.out.println("Connected to database successfully.");
				//Create Statement
				st = conn.createStatement();
				//Execute Query
				switch (table_no) {
					case 1:
						
						int customer_id;
						String first_name = "";
						String last_name = "";
						String customer_address = "";
						String customer_email = "";
						int customer_phone;
						
						query = "select * from cex.customer";
						rs = st.executeQuery(query);
						
						System.out.println("Enter 1: Add customer\nEnter 2: View all customers\nEnter 3: Update customer\nEnter 4: Delete customer");
						action_no = sc.nextInt();
						switch (action_no) {
							case 1:
								System.out.print("Enter customer id:");
								customer_id = sc.nextInt();
								sc.nextLine();
								System.out.print("Enter first name:");
								first_name = sc.nextLine();
								System.out.print("Enter last name:");
								last_name = sc.nextLine();
								System.out.print("Enter customer address:");
								customer_address = sc.nextLine();
								System.out.print("Enter customer email:");
								customer_email = sc.nextLine();
								System.out.print("Enter customer phone number:");
								customer_phone = sc.nextInt();
								query = "insert into cex.customer values ("+customer_id+",'"+first_name+"','"+last_name+"','"+customer_address+"','"+customer_email+"',"+customer_phone+");";
								st.executeUpdate(query);	//Add customer
								break;
							case 2:
								while (rs.next()) {
						            customer_id = rs.getInt("customer_id");
						            first_name = rs.getString("first_name");
						            last_name = rs.getString("last_name");
						            customer_address = rs.getString("customer_address");
						            customer_email = rs.getString("customer_email");
						            customer_phone = rs.getInt("customer_phone");
						            System.out.println(customer_id+"	"+first_name+"	"+last_name+"	"+customer_address+"	"+customer_email+"	"+customer_phone);
						         }
								break;
							case 3:
								query = "";
								st.executeUpdate(query);	//Update customer
								break;
							case 4:
								System.out.print("Enter customer id to delete: ");
								int cust_x = sc.nextInt();
								query = "delete from cex.customer where customer_id = "+cust_x+";";
								st.executeUpdate(query);	//Delete customer
								break;
						}
						break;
					case 2:
						query = "select * from cex.orders";
						rs = st.executeQuery(query);
						int oid;
						int fk_customer_id;
						int fk_product_id;
						String date = "";
						System.out.print("Enter 1: Add order\nEnter 2: View order\nEnter 3: Delete order");
						action_no = sc.nextInt();
						switch (action_no) {
							case 1: 
								System.out.print("Enter order id: ");
								oid = sc.nextInt();
								System.out.print("Enter fk customer id: ");
								fk_customer_id = sc.nextInt();
								System.out.print("Enter fk product id: ");
								fk_product_id = sc.nextInt();
								sc.nextLine();
								System.out.print("Enter release date: ");
								date = sc.nextLine();
								query = "insert into cex.orders values ("+oid+","+fk_customer_id+","+fk_product_id+",'"+date+");";
								st.executeUpdate(query);	//Add order
								break;
							case 2:
								while (rs.next()) {
						            oid = rs.getInt("oid");
						            fk_customer_id = rs.getInt("fk_customer_id");
						            fk_product_id = rs.getInt("fk_product_id");
						            date = rs.getString("date");
						            System.out.println(oid+"	"+fk_customer_id+"	"+fk_product_id+"	"+date);
						         }
									//view all order
								break;
							case 3:
								System.out.print("Enter order id to delete: ");
								int order_x = sc.nextInt();
								query = "delete from cex.orders where oid = "+order_x+";";
								st.executeUpdate(query);	//Delete order
								break;
						}
						break;
					case 3:
						query = "select * from cex.product";
						rs = st.executeQuery(query);
						
						int product_id;
						String product_name = "";
						int product_age_rating;
						String dev_studio = "";
						float price;
						String release_date = "";
						
						System.out.println("Enter 1: Add product\nEnter 2: View all products\nEnter 3: Update product\nEnter 4: Delete product");
						action_no = sc.nextInt();
						switch (action_no) {
							case 1:
								System.out.print("Enter product id: ");
								product_id = sc.nextInt();
								sc.nextLine();
								System.out.print("Enter product name: ");
								product_name = sc.nextLine();
								System.out.print("Enter product age rating: ");
								product_age_rating = sc.nextInt();
								sc.nextLine();
								System.out.print("Enter dev studio name: ");
								dev_studio = sc.nextLine();
								System.out.print("Enter price of product: ");
								price = sc.nextFloat();
								sc.nextLine();
								System.out.print("Enter release date: ");
								release_date = sc.nextLine();
								
								query = "insert into cex.product values ("+product_id+",'"+product_name+"',"+product_age_rating+",'"+dev_studio+"',"+price+",'"+release_date+"');";
								st.executeUpdate(query);	//Add product
								break;
							case 2:
								while (rs.next()) {
									product_id = rs.getInt("product_id");
									product_name = rs.getString("product_name");
									product_age_rating = rs.getInt("product_age_rating");
									dev_studio = rs.getString("dev_studio");
									price = rs.getFloat("price");
									release_date = rs.getString("release_date");
						            System.out.println(product_id+"	"+product_name+"	"+product_age_rating+"	"+dev_studio+"	"+price+"	"+release_date);
						         }	//View all products
								break;
							case 3:
								query = "";
								rs = st.executeQuery(query);	//Update product
								break;
							case 4:
								System.out.print("Enter product id to delete: ");
								int product_x = sc.nextInt();
								query = "delete from cex.product where product_id = "+product_x+";";
								st.executeUpdate(query);	//Delete product
								break;
						}
						break;
				}//end switch
			}//end try
			catch (Exception exc) {
				exc.printStackTrace();
			}
			System.out.print("Enter 1: Continue\nEnter 0: Exit");
			flag = sc.nextInt();
		}//end do
		
		while(flag == 1);
		
	}//end main
}//end class
