
class BankAccount 
{
	private int accountNumber, accountCredit;
	
	public BankAccount(int accountNumber, int initialCredit)
	{
		this.accountNumber = accountNumber;
		this.accountCredit = initialCredit;
	}
	
	public void deposit(int value)
	{
		accountCredit += value;
	}
	
	public boolean withdraw(int value)
	{
		if (value <= accountCredit)
		{
			accountCredit -= value;
			return true;
		}
		return false;
	}
	
	public double checkCredit()
	{
		return accountCredit;
	}
	
	@Override
	public String toString()
	{
		return "Bank Account [Number: " + accountNumber + ", Credit: " + "]";
	}
	
	
}
