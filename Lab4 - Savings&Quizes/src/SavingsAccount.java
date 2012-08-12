
public class SavingsAccount {

	private double balance;
	private double interest;
	
	public SavingsAccount(double interest) {
		this(0.0, interest);
	}
	
	
	public SavingsAccount(double balance, double interest) {
		this.balance = balance;
		this.interest = interest;
	}


	public double getBalance() {
		return balance;
	}


	public double getInterest() {
		return interest;
	}
	
	public double deposit(double amount){
		balance += amount;
		return balance;
	}

	public double withdraw(double amount){
		balance -= amount;
		return balance;
	}
	
	public double addInterest(){
		double interestAmount = balance*(interest/12.0);
		balance += interestAmount;
		
		return interestAmount;
	}
}
