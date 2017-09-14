import java.util.ArrayList;
import java.util.Scanner;

class BankAccount{

	public int acc_no;
	public float balance;
	
	BankAccount(int a){
		acc_no  = a;
		balance = 0;
	}
	
	float deposit(float x){
		balance += x;
		return balance;
	}
	
	float withdraw(float x){
		balance -= x;
		return balance;
	}
	
	int get_acc_no(){
		return acc_no;
	}
	
	float getBalance(){
		return balance;
	}
	
	void taxDeduction(){
		//see Documentation on oracle
	}
}

class Bank{
	public ArrayList<BankAccount> accounts;
	
	Bank(){
		 accounts = new ArrayList<>();
	}
	
	void addAccount(int acno){
		accounts.add(new BankAccount(acno));
		System.out.println("Account Added Successfuly.");
	}
	
	float totalBal(){
		float t=0;
		for(BankAccount a : accounts){
			t += a.getBalance();
		}
		return t;
	}
	
	int maxBal(){
		BankAccount a = accounts.get(0);
		for(int i=1; i<accounts.size(); i++){
			if(a.balance > accounts.get(i).balance)
				a = accounts.get(i);
		}
		return a.get_acc_no();
	}
	
	int minBal(){
		BankAccount a = accounts.get(0);
		for(int i=1; i<accounts.size(); i++){
			if(a.balance < accounts.get(i).balance)
				a = accounts.get(i);
		}
		return a.get_acc_no();
	}
	
	int minThreshold(float x){
		int num=0;
		for(BankAccount a : accounts){
			if(a.getBalance()>=x)
				num++;
		}
		return num;
	}	
	
	void findAndDeposit(int acc,float bal){
		for(BankAccount b : accounts){
			if(b.get_acc_no() == acc)
				System.out.println("New Balance : " + b.deposit(bal));
		}
	}
	
	void findAndWithdraw(int acc,float bal){
		for(BankAccount b : accounts){
			if(b.get_acc_no() == acc)
				System.out.println("New Balance : " + b.withdraw(bal));
		}
	}
}

class testbank{
	public static void main(String argv[]){
		Bank bk = new Bank();
		bk.addAccount(10);
		bk.addAccount(12);
		System.out.println();
	}
}
