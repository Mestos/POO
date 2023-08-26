
public class Bank 
{
	public static void main(String[] args)
	{
		BankAccount account1 = new BankAccount(321, 2000);
		//BankAccount account2 = new BankAccount(999, 4000);
		
		account1.deposit(2000);
		//account2.deposit(9000);
		
		account1.withdraw(4001);
		//account2.withdraw(1000);
		
		System.out.println("Final credit of the account 321: " + account1.checkCredit());
		//System.out.println("Final credit of the account 999: " + account2.checkCredit());
	}
}
