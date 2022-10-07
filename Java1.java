import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Java1
{
public static void main(String []args) 
{
	Scanner sc=new Scanner(System.in);
try 
{
 Class.forName("oracle.jdbc.driver.OracleDriver");
 String url="jdbc:oracle:thin:@localhost:1521:xe";
 String username="system";
 String password="20F246";
 Connection conn=DriverManager.getConnection(url,username,password);
 Statement st=conn.createStatement();
 ResultSet rs;
 st.executeUpdate("CREATE TABLE ATM (Denomination Number,Num_1 Number,Value Number)");
 st.executeUpdate("CREATE TABLE Customer(Acc_No Number,Acc_Holder varchar(20),Pin_Number Number,Acc_Balance Number)");
 st.executeUpdate("INSERT INTO Customer values(101,'Suresh',2343,25234)");
 st.executeUpdate("INSERT INTO Customer values(102,'Ganesh',5432,34123)");
 st.executeUpdate("INSERT INTO Customer values(103,'Magesh',7854,26100)");
 st.executeUpdate("INSERT INTO Customer values(104,'Naresh',2345,80000)");
 st.executeUpdate("INSERT INTO Customer values(105,'Harish',1907,103400)");
 System.out.println("Enter Your Choice 1.Load Cash to ATM 2.Show Customer Details 3.Show ATM operations");
 int a=sc.nextInt();
 switch(a)
 {
 case 1:
	 System.out.println("Enter Denomination");
	 int deno=sc.nextInt();
	 System.out.println("Enter Number");
	 int count=sc.nextInt();
	 int val1=deno*count;
	 st.executeUpdate("INSERT INTO ATM values(deno,count,val1)");
	 break;
 case 2:
	 rs=st.executeQuery("SELECT * FROM Customer");
	 	while(rs.next()) {
	 		System.out.println(rs.getInt("Acc_No")+" "+ rs.getString("Acc_Holder")+" " + rs.getInt("Pin_Number")+" "+ rs.getInt("Acc_Balance"));
	 	}
	 	break;
 case 3:
	 System.out.println("Enter your choice 1.Check Balance 2.Withdraw Money 3.Transfer Money 4.Check ATM Balance");
	 int b=sc.nextInt();
	 switch(b) {
	 case 1:
		 System.out.println("Enter Account No");
		 int account_no=sc.nextInt();
		 int balance=st.executeUpdate("SELECT Acc_Balance FROM Customer WHERE Acc_No=account_no");
		 System.out.println(balance);
		 break;
	 case 2:
		 System.out.println("Enter amount to be withdraw");
		 int amount=sc.nextInt();
		 System.out.println("Enter your pin");
		 int pin=sc.nextInt();
		 if(amount >=100 && amount<=10000) {
			 if(amount<=st.executeUpdate("SELECT Acc_Balance FROM Customer WHERE Pin_Number=pin")) 
			 {
				 st.executeUpdate("UPDATE Customer SET Acc_Balance=Acc_Balance-amount WHERE Pin_Number=pin");
			 }
			 else {
				 System.out.println("ALERT");
			 }
		 }
		 else {
			 System.out.println("ALERT");
		 }
		 break;
	 case 3: 
		 System.out.println("Enter AccountNo");
		 int acc_no1=sc.nextInt();
		 System.out.println("Enter Money for Transfer");
		 int money=sc.nextInt();
		 if(money>1000 && money<10000) {
			 System.out.println("Enter AccountNo of Receiver");
			 int acc_no2=sc.nextInt();
			 st.executeUpdate("UPDATE Customer SET Acc_Balance=Acc_Balance-money WHERE Acc_No=acc_no1");
			 st.executeUpdate("UPDATE Customer SET Acc_Balance=Acc_Balance+money WHERE Acc_No=acc_no2");
		 }
		 else {
			 System.out.println("ALERT");
		 }
		 break;
	 case 4:
		 rs=st.executeQuery("SELECT * FROM ATM");
		 while(rs.next()) {
			 System.out.println(rs.getInt("Denomination")+ " "+ rs.getInt("Num_1") +" " + rs.getInt("Value"));
		 }
		 break;
	 default: 
		 System.out.println("Invalid Choice");
		 
	 }
	 break;
	 default:
		 System.out.println("Invalid Choice");

 }
 
 }

catch(Exception e)
{
	System.out.print(e);
}

}






}