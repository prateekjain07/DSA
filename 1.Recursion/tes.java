import java.util.Scanner;
 
class Account{
	private String name;
	private String accountNo;
	private double balance;
	Account(String name, String accountNo, double balance){
		this.name = name;
		this.accountNo = accountNo;
		this.balance = balance;
	}
	String deposit(double amt){
		this.balance += amt;
		return "deposted:" + amt;
	}
	String withdraw(double amt){
		if(this.balance - amt >0){
			this.balance -= amt;
			return "withdrawal:" + amt;
		}
		else return "Insufficient balance";
	}
	String getData(){
		return this.accountNo  + " " + this.name + " " + this.balance; 
	}
	double getBalance(){
		return this.balance;
	}
}
class tes
{
	public static void main (String[] args)
	{
        Scanner scn = new Scanner(System.in);
		String accountNo = "12345";
		String name = "Ankit";
 
		double balance = 1000;
		double depAmt = 1500;
		double wthAmt = 2000;
        Account user = new Account(name, accountNo, balance);
		System.out.println(user.getData());
		System.out.println("Balance is:" + user.getBalance());
 
		System.out.println(user.deposit(depAmt));
 
		System.out.println(user.withdraw(wthAmt));
 
		System.out.println("Balance is:" + user.getBalance());
 
	}
}