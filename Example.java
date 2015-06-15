package unal.datastructures;

import java.util.*;
import java.io.*;
import holamundo;
import static Hello.*;


public static class Bank extends SuperBank {

	private Methods bankMethods = new Methods();

	public static void main( String[] args ){
		String nameFile;
		try{
			nameFile = args[0];
		}catch( Exception ex ){
			nameFile = "unal.dat";
		}
		Bank internationalSoccerBank = new Bank( nameFile );
		internationalSoccerBank.bankMethods.load( );
		internationalSoccerBank.bankMethods.menu( );
		internationalSoccerBank.bankMethods.reception( );
	}

	public Bank( String nameFile ){
		super( );
		bankMethods = new Methods( nameFile );
	}
}

class Account implements Comparable<Account>, Serializable{

	int numAccount;
	String nameAccount;
	int amount;
	String nameManager;
	String countryAccount;
	String typeAccount;
	String lastTransactions[];
	int lastTransactionsSize;

	//Constants...

	public static final Comparator<Account> BY_NUM_ACCOUNT = new ByNumAccount( );
	public static final Comparator<Account> BY_NAME_ACCOUNT = new ByNameAccount( );
	public static final Comparator<Account> BY_AMOUNT = new ByAmount( );
	public static final Comparator<Account> BY_NAME_MANAGER = new ByNameManager( );
	public static final Comparator<Account> BY_COUNTRY_ACCOUNT = new ByCountryAccount( );
	public static final Comparator<Account> BY_TYPE_ACCOUNT = new ByTypeAccount( );

	//Constructors...

	public Account( int numAccount, int amount ){
		this( numAccount, "Unknow", amount, "Unknow", "Unknow", "Checking" );
	}

	public Account( int numAccount, String nameAccount, int amount ){
		this( numAccount, nameAccount, amount, "Unknow", "Unknow", "Checking" );
	}

	public Account( int numAccount, String nameAccount, int amount, String nameManager ){
		this( numAccount, nameAccount, amount, nameManager, "Unknow", "Checking" );
	}
	public Account( int numAccount, String nameAccount, int amount, String nameManager, String countryAccount ){
		this( numAccount, nameAccount, amount, nameManager, countryAccount, "Checking" );
	}

	public Account( int numAccount, String nameAccount, int amount, String nameManager, String countryAccount, String typeAccount ){
		this.numAccount = numAccount;
		this.nameAccount = nameAccount;
		this.amount = amount;
		this.nameManager = nameManager;
		this.countryAccount = countryAccount;
		this.typeAccount = typeAccount;
		this.lastTransactions = new String[10];
		this.lastTransactionsSize = 0;
	}

	//Getters...

	public int getNumAccount( ){
		return numAccount;
	}
	public String getNameAccount( ){
		return nameAccount;
	}
	public int getAmount( ){
		return amount;
	}
	public String getNameManager( ){
		return nameManager;
	}
	public String getTypeAccount( ){
		return typeAccount;
	}
	public String getCountryAccount( ){
		return countryAccount;
	}
	public int getLastTransactionsSize( ){
		return lastTransactionsSize;
	}
	public String getTransaction( int i ){
		return lastTransactions[i];
	}

	//Setters

	public void setNameAccount( String nameAccount ){
		this.nameAccount = nameAccount;
	}
	public void setNameManager( String nameManager ){
		this.nameManager = nameManager;
	}
	public void setCountryAccount( String countryAccount ){
		this.countryAccount = countryAccount;
	}
	public void setTypeAccount( String typeAccount ){
		this.typeAccount = typeAccount;
	}
	public void setTransaction( String transaction ){
		if( lastTransactionsSize == 10 ){
			for( int i = 1; i < 10; i++ )
				lastTransactions[i-1] = lastTransactions[i];
			lastTransactions[lastTransactionsSize-1] = transaction;
		}else{
			lastTransactions[lastTransactionsSize++] = transaction;
		}

	}

	//Methods...

	public void addAmount( int amount ){
		this.amount += amount;
	}

	public int compareTo( Account b ){
		return this.numAccount - b.numAccount;
	}

	public boolean equals( Object b ){
		if( b == null ) return false;
		if( b == this ) return true;
		if( !( b instanceof Account ) ) return false;
		return this.numAccount == ( ( Account )
			b ).numAccount;
	}

	@Override
	public String toString( ){
		return "| " + numAccount + " | " + nameAccount + " | " + amount + " | " + nameManager + " | " + countryAccount + " | " + typeAccount + " |";
	}

	//Comparators...

	private static class ByNumAccount implements Comparator<Account>{
		public int compare( Account a, Account b ){
			return a.getNumAccount( ) - b.getNumAccount( );
		}
	}
	private static class ByNameAccount implements Comparator<Account>{
		public int compare( Account a, Account b ){
			return a.getNameAccount( ).compareTo( b.getNameAccount( ) );
		}
	}
	private static class ByAmount implements Comparator<Account>{
		public int compare( Account a, Account b ){
			return a.getAmount( ) - b.getAmount( );
		}
	}
	private static class ByNameManager implements Comparator<Account>{
		public int compare( Account a, Account b ){
			return a.getNameManager( ).compareTo( b.getNameManager( ) );
		}
	}
	private static class ByCountryAccount implements Comparator<Account>{
		public int compare( Account a, Account b ){
			return a.getCountryAccount( ).compareTo( b.getCountryAccount( ) );
		}
	}
	private static class ByTypeAccount implements Comparator<Account>{
		public int compare( Account a, Account b ){
			return a.getTypeAccount( ).compareTo( b.getTypeAccount( ) );
		}
	}
}

class Methods{

	ArrayLinearListTaller1<Account> accounts;
	String nameFile;
	Scanner input = new Scanner( System.in );
	String command =  "";

	public Methods( String nameFile ){
		accounts = new ArrayLinearListTaller1<Account>( );
		this.nameFile = nameFile;
	}

	public void reception( ){
		while( !command.equals( "quit" ) && !command.equals( "Q" ) ){
			System.out.print( "command > " );
			command = input.nextLine( );
			String parameters[] = command.split( " " );
			switch( parameters[0] ){

				case "a" : case "apply_monthly_fee" : {
					applyMonthlyFee( );
					break;
				}

				case "A" : case "apply_monthly_interest" : {
					applyMonthlyInterest( );
					break;
				}

				case "c" : case "create" : {
					try{
						if( exist( Integer.parseInt( parameters[1] ) ) ){
							System.out.println( "ERROR - Number account " + parameters[1] + " already in use..." );
							break;
						} else if( parameters[parameters.length - 1].equals( "*" ) ){
							create( Integer.parseInt( parameters[1] ) );
						}else if( parameters.length == 2 ){
							System.out.println( "ERROR - Can't create this account without an amount..." );
							break;
						}else if( parameters.length == 3 ){
							try{
								Integer.parseInt( parameters[2] );
								create( Integer.parseInt( parameters[1] ), Integer.parseInt( parameters[2] ) );
							}catch( Exception ex ){
								create( Integer.parseInt( parameters[1] ), parameters[2] );
							}
						}
						else if( parameters.length == 4 )
							create( Integer.parseInt( parameters[1] ), parameters[2], Integer.parseInt( parameters[3] ) );
						else if( parameters.length == 5 )
							create( Integer.parseInt( parameters[1] ), parameters[2], Integer.parseInt( parameters[3] ), parameters[4] );
						System.out.println( "The account " + parameters[1] + " was saving successfully..." );
					}catch( Exception ex ){
						System.out.println( "Please, use the command CREATE with a number account..." );
					}
					break;
				}

				case "d" : case "deposit" : {
					try{
						deposit( Integer.parseInt( parameters[1] ), Integer.parseInt( parameters[2] ) );
					}catch( Exception ex ){
						System.out.println( "Please, use the command DEPOSIT with a number account and an amount..." );
					}
					break;
				}

				case "e" : case "edit" : {
					try{
						edit( Integer.parseInt( parameters[1] ) );
					}catch( Exception ex ){
						System.out.println( "Please, use the command EDIT with a number account..." );
					}
					break;
				}

				case "q" : case "query" : {
					try{
						query( Integer.parseInt( parameters[1] ) );
					}catch( Exception ex ){
						System.out.println( "Please, use the command QUERY with a number account..." );
					}
					break;
				}

				case "m" : case "menu" : {
					menu( );
					break;
				}

				case "p" :  case "print" : {
					print( );
					break;
				}

				case "r" : case "remove" : {
					try{
						remove( Integer.parseInt( parameters[1] ) );
					}catch( Exception ex ){
						ex.printStackTrace();
						System.out.println( "Please, use the command REMOVE with a number account..." );
					}
					break;
				}

				case "w" : case "withdrawal" : {
					try{
						withdrawal( Integer.parseInt( parameters[1] ), Integer.parseInt( parameters[2] ) );
					}catch( Exception ex ){
						System.out.println( "Please, use the command WITHDRAWAL with a number account..." );
					}
					break;
				}

				case "l" : case "last_transactions" : {
					try{
						printLastTransactions( Integer.parseInt( parameters[1] ) );
					}catch( Exception ex ){
						System.out.println( "Please, use the command LAST_TRANSACTIONS with a number account..." );
					}
					break;
				}

				case "s" : case "sort" : {
					try{
						sort( parameters[1] );
					}catch( Exception ex ){
						accounts.sort( Account.BY_NUM_ACCOUNT );
					}
					break;
				}

				case "t" : case "total" : {
					getTotal( );
					break;
				}

				case "?" : {
					try{
						help( parameters[1] );
					}catch( Exception ex ){
						System.out.println( "Please, the command HELP must be used with a name command..." );
					}
					break;
				}

				case "clear" : {
					clear( );
					break;
				}

				case "Q" : case "quit" : {
					quit( );
					break;
				}
				case "" : {
					break;
				}
				default : {
					System.out.println( "Command not found. Try Again." );
				}
			}
		}
	}

	public void create( int numAccount ){
		String nameAccount, nameManager, countryAccount, typeAccount;
		int amount = 0;
		System.out.print( "Name Account?: " );
		nameAccount = input.nextLine( );
		if( nameAccount.equals( "" ) )
			nameAccount = "Unknow";
		do{
			System.out.print( "Amount? [Min = 5000]: " );
			amount = input.nextInt( );
		}while( amount < 5000 );
		input.nextLine();
		System.out.print( "Name Manager?: " );
		nameManager = input.nextLine( );
		System.out.print( "Country Account?: " );
		countryAccount = input.nextLine( );
		do{
			System.out.print( "Type Account? [Checking = C / Saving = S]: " );
			typeAccount = input.nextLine( );
		}while( !typeAccount.equals( "S" ) && !typeAccount.equals( "C" ) );
		if( typeAccount.equals( "S" ) )
			typeAccount = "Saving";
		else
			typeAccount = "Checking";
		accounts.add( accounts.size, new Account( numAccount, nameAccount, amount, nameManager, countryAccount, typeAccount ) );
	}
	public void create( int numAccount, String nameAccount ){
		accounts.add( accounts.size, new Account( numAccount, nameAccount, 0 ) );
	}
	public void create( int numAccount, int amount ){
		accounts.add( accounts.size, new Account( numAccount, amount ) );
	}
	public void create( int numAccount, String nameAccount, int amount ){
		accounts.add( accounts.size, new Account( numAccount, nameAccount, amount ) );
	}
	public void create( int numAccount, String nameAccount, int amount, String nameManager ){
		accounts.add( accounts.size, new Account( numAccount, nameAccount, amount, nameManager ) );
	}
	public void create( int numAccount, String nameAccount, int amount, String nameManager, String countryAccount ){
		accounts.add( accounts.size, new Account( numAccount, nameAccount, amount, nameManager, countryAccount ) );
	}
	public void create( int numAccount, String nameAccount, int amount, String nameManager, String countryAccount, String typeAccount ){
		accounts.add( accounts.size, new Account( numAccount, nameAccount, amount, nameManager, countryAccount, typeAccount ) );
	}

	public void clear( ){
		for( int i = 0; i < 56; i++ ){
			System.out.println( "" );
		}
	}

	public void applyMonthlyFee( ){
		System.out.print( "Applying Monthly Fee... " );
		if( accounts.size == 0 )
			System.out.println( "INFO - The accounts list is empty..." );
		else{
			for( int  i = 0; i < accounts.size; i++ ){
				if( accounts.get( i ).getTypeAccount( ).equals( "Saving" ) ){
					accounts.get( i ).addAmount( -10000 );
					accounts.get( i ).setTransaction( "Apply monthly fee for $10.000" );
				}else if( accounts.get( i ).getTypeAccount( ).equals( "Checking" ) ){
					accounts.get( i ).addAmount( -5000 );
					accounts.get( i ).setTransaction( "Apply monthly fee for $5.000" );
				}
			}
			System.out.println( "Process completed successfully..." );
		}
	}

	public void applyMonthlyInterest( ){
		System.out.print( "Applying monthly interest... " );
		if( accounts.size == 0 )
			System.out.println( "INFO - The accounts list is empty..." );
		else{
			for( int  i = 0; i < accounts.size; i++ ){
				if( accounts.get( i ).getTypeAccount( ).equals( "Saving" ) ){
					accounts.get( i ).addAmount( ( int ) ( accounts.get( i ).getAmount( ) * 0.5 ) );
					accounts.get( i ).setTransaction( "Apply monthly interest for " + ( ( int ) ( accounts.get( i ).getAmount( ) * 0.5 ) ) );
				}else if( accounts.get( i ).getTypeAccount( ).equals( "Checking" ) ){
					accounts.get( i ).addAmount( ( int ) ( accounts.get( i ).getAmount( ) * 0.3 ) );
					accounts.get( i ).setTransaction( "Apply monthly interest for " + ( ( int ) ( accounts.get( i ).getAmount( ) * 0.3 ) ) );
				}
			}
			System.out.println( "Process completed successfully..." );
		}
	}

	public Account getAccount( int numAccount ){
		Account x = new Account( 0, 0 );
		for( int i = 0; i < accounts.size; i++ )
			if( accounts.get( i ).getNumAccount( ) == numAccount )
				x = accounts.get( i );
		return x;
	}

	public boolean exist( int numAccount ){
		for( int i = 0; i < accounts.size; i++ )
			if( accounts.get( i ).getNumAccount( ) == numAccount )
				return true;
		return false;
	}

	public void printLastTransactions( int numAccount ){
		if( exist( numAccount ) == false )
			System.out.println( "The number Account " + numAccount + " does not exist..." );
		else{
			Account x = getAccount( numAccount );
			if( x.getLastTransactionsSize( ) == 0 )
				System.out.println( "The number Account " + numAccount + " has not transactions..." );
			else{
				System.out.println( "Transactions of account " + numAccount + " : " );
				for( int j = 0; j < x.getLastTransactionsSize( ); j++ )
					System.out.println( (j+1) + " => " + x.getTransaction( j ) );
			}
		}
	}

	public void print( ){
		System.out.println( "+---------------------------------------+" );
		System.out.println( "|              FIFA PES BANK            |" );
		System.out.println( "+---------------------------------------+" );
		if( accounts.size == 0 ){
			System.out.println( "|      The accounts list is empty       |" );
			System.out.println( "+---------------------------------------+" );
		}
		for( int i = 0; i < accounts.size; i++ ){
			Account x = accounts.get( i );
			System.out.println( "   Account number : " + x.getNumAccount( ) );
			System.out.println( "   Account name : " + x.getNameAccount( ) );
			System.out.println( "   Amount : $ " + x.getAmount( ) );
			System.out.println( "   Account Manager : " + x.getNameManager( ) );
			System.out.println( "   Account Country : " + x.getCountryAccount( ) );
			System.out.println( "   Account Type : " + x.getTypeAccount( ) );
			System.out.println( "+---------------------------------------+" );
		}
	}

	public void deposit( int numAccount, int amount ){
		if( !exist( numAccount ) )
			System.out.println( "The number account " + numAccount + " does not exist..." );
		else if( amount < 0 ){
			System.out.println( "The deposit can't be less than 0..." );
			return ;
		}else{
			Account x = getAccount( numAccount );
			x.addAmount( amount );
			x.setTransaction( "Deposit $" + amount );
			System.out.println( "Deposit completed successfully. The new balance in the account " + numAccount + " is : " + x.getAmount( ) );
		}
	}

	public void menu( ){
		System.out.println( "------------------------------ MAIN MENU ----------------------------------- " );
		System.out.println( "apply_monthly_fee - Apply monthly fee / $10.000 for Saving, $5.000 for Checking" );
		System.out.println( "create account_number name deposit - Create a new account with all data" );
		System.out.println( "create account_number * - Create an account and prompt for each field" );
		System.out.println( "deposit account_number amount - Deposit an amount" );
		System.out.println( "edit account_number - Allows to modify all account fields" );
		System.out.println( "query account_number - Print account information" );
		System.out.println( "menu - Print main menu" );
		System.out.println( "print - Print all bank accounts" );
		System.out.println( "sort parameter - Sort all accounts by a parameter" );
		System.out.println( "remove account_nubmer - Remove an account" );
		System.out.println( "withdrawal account_number amount - Withdraw an amount" );
		System.out.println( "? command - Print info about a command" );
		System.out.println( "last_transactions account_number - Print the last transactions of an account");
		System.out.println( "apply_monthly_interest - pays a interest / 5% for Saving - 3% for Checking");
		System.out.println( "quit - Quit the system" );
		System.out.println( "------------------------------------------------------------------------------");
	}

	public void sort( String comp ){
		switch( comp ){
			case "numberAccount" : {
				accounts.sort( Account.BY_NUM_ACCOUNT );
				break;
			}
			case "nameAccount" : {
				accounts.sort( Account.BY_NAME_ACCOUNT );
				break;
			}
			case "amount" : {
				accounts.sort( Account.BY_AMOUNT );
				break;
			}
			case "nameManager" : {
				accounts.sort( Account.BY_NAME_MANAGER );
				break;
			}
			case "countryAccount" : {
				accounts.sort( Account.BY_COUNTRY_ACCOUNT );
				break;
			}
			case "typeAccount" : {
				accounts.sort( Account.BY_TYPE_ACCOUNT );
				break;
			}
			default : {
				System.out.println( "ERROR - The sort parameter does not exist..." );
			}
		}
	}

	public void query( int accountNumber ){
		if( !exist( accountNumber ) ){
			System.out.println( "\nERROR - The number account " + accountNumber + " does not exist...\n" );
		}else{
			Account x = getAccount( accountNumber );
			System.out.println( "\nNumber Account : " + accountNumber );
			System.out.println( "Name Account : " + x.getNameAccount( ) );
			System.out.println( "Balance : " + x.getAmount( ) );
			System.out.println( "Account Manager : " + x.getNameManager( ) );
			System.out.println( "Account Country : " + x.getCountryAccount( ) );
			System.out.println( "Type Account : " + x.getTypeAccount( ) + "\n");
		}
	}

	public void remove( int numAccount ){
		if( !exist( numAccount ) ){
			System.out.println( "ERROR - The number account " + numAccount + " does not exist..." );
		}else{
			Account x = new Account( 0, 0 );
			for( int i = 0; i < accounts.size; i++ )
				if( accounts.get( i ).getNumAccount( ) == numAccount )
					x = accounts.remove( i );
			System.out.println( "The number account " + x.getNumAccount( ) + ", with Balance $" + x.getAmount( ) + " was deleted successfully!" );
		}
	}

	public void quit( ){
		System.out.println( "Saving file to '" + nameFile + "'... " );
		accounts.save( nameFile );
		System.out.println( "Thank you. Come again soon..." );
	}

	public void load( ){
		accounts.load( nameFile );
	}

	public void withdrawal( int numAccount, int amount ){
		Account x = new Account( 0, 0 );
		for( int i = 0; i < accounts.size; i++ )
			x = accounts.get( i );
			if( x.getNumAccount( ) == numAccount ){
				if( x.getAmount( ) - amount < 5000 ){
					System.out.println( "ERROR - Insufficent founds..." );
					return ;
				}else{
					x.addAmount( -1 * amount );
					x.setTransaction( "Withdraw " + amount );
					System.out.println( "Withdraw completed successfully..." );
					return ;
				}
			}
		System.out.println( "ERROR - The number account " + numAccount + " does not exist..." );
	}

	public void edit( int numAccount ){
		String parameter;
		if( exist( numAccount ) ){
			Account x = getAccount( numAccount );
			System.out.print( "Account Titular [" + x.getNameAccount( ) + "]: "  );
			parameter = input.nextLine( );
			if( !parameter.equals( "" ) )
				x.setNameAccount( parameter );
			System.out.print( "Account Manager [" + x.getNameManager( ) + "]: "  );
			parameter = input.nextLine( );
			if( !parameter.equals( "" ) )
				x.setNameManager( parameter );
			System.out.print( "Account Country [" + x.getCountryAccount( ) + "]: "  );
			parameter = input.nextLine( );
			if( !parameter.equals( "" ) )
				x.setCountryAccount( parameter );
			do{
				System.out.print( "Account Type ( Checking = C / Saving = S ) [" + x.getTypeAccount( ) + "]: "  );
				parameter = input.nextLine( );
				if( parameter.equals( "" ) )
					break;
			}while( !parameter.equals( "S" ) && !parameter.equals( "C" ) );
			if( parameter.equals( "S" ) ) x.setTypeAccount( "Saving" );
			else if( parameter.equals( "C" ) ) x.setTypeAccount( "Checking" );
		}else{
			System.out.println( "The account " + numAccount + " does not exist..." );
		}
	}

	public void getTotal( ){
		int total = 0;
		for( int i = 0; i < accounts.size; i++ )
			total += accounts.get( i ).getAmount( );
		if( accounts.size == 0 )
			System.out.println( "INFO - The accounts list is empty..." );
		else
			System.out.println( "Total : $" + total );
	}

	public void help( String command ){
		switch( command ){
			case "create" : {
				System.out.println( "command CREATE : Create a new account.\n" );
				System.out.println( "=> to use: c number_account *" );
				System.out.println( "=> to use: create number_account amount" );
				System.out.println( "=> to use: c number_account name_account" );
				System.out.println( "=> to use: create number_account name_account amount" );
				System.out.println( "=> to use: c number_account name_account amount name_manager" );
				System.out.println( "=> to use: create number_account name_account amount name_manager country_account" );
				System.out.println( "=> to use: c number_account name_account amount name_manager country_account type_account\n" );
				break;
			}
			case "query" : {
				System.out.println( "command QUERY : query all data of an account.\n" );
				System.out.println( "=> to use: query number_account" );
				System.out.println( "=> to use: q number_account\n" );
				break;
			}
			case "remove" : {
				System.out.println( "command REMOVE : remove an account.\n" );
				System.out.println( "=> to use: remove number_account" );
				System.out.println( "=> to use: r number_account\n" );
				break;
			}
			case "edit" : {
				System.out.println( "command EDIT : edit an account.\n" );
				System.out.println( "=> to use: edit number_account" );
				System.out.println( "=> to use: e number_account\n" );
				break;
			}
			case "print" : {
				System.out.println( "command PRINT : print all accounts.\n" );
				System.out.println( "=> to use: print" );
				System.out.println( "=> to use: p\n" );
				break;
			}

			case "last_transactions" : {
				System.out.println( "command LAST_TRANSACTIONS : print last 10 transactions of an account.\n" );
				System.out.println( "=> to use: last_transactions" );
				System.out.println( "=> to use: l\n" );
				break;
			}
			case "quit" : {
				System.out.println( "command QUIT : save the accounts and close the program.\n" );
				System.out.println( "=> to use: quit" );
				System.out.println( "=> to use: Q\n" );
				break;
			}
			case "total" : {
				System.out.println( "command TOTAL : print the total balance accounts.\n" );
				System.out.println( "=> to use: total" );
				System.out.println( "=> to use: t\n" );
				break;
			}
			case "withdrawal" : {
				System.out.println( "command WITHDRAWAL : withdrawal of an account.\n" );
				System.out.println( "=> to use: withdrawal number_account amount" );
				System.out.println( "=> to use: w number_account amount" );
				break;
			}
			case "deposit" : {
				System.out.println( "command DEPOSIT : deposit an amount to an account.\n" );
				System.out.println( "=> to use: deposit number_account amount" );
				System.out.println( "=> to use: d number_account amount\n" );
				break;
			}
			case "menu" : {
				System.out.println( "command MENU : print menu.\n" );
				System.out.println( "=> to use: menu" );
				System.out.println( "=> to use: m\n" );
				break;
			}
			case "apply_monthly_fee" : {
				System.out.println( "command APPLY_MONTHLY_FEE : apply monthly fee.\n" );
				System.out.println( "=> to use: apply_monthly_fee" );
				System.out.println( "=> to use: a\n" );
				break;
			}
			case "apply_monthly_interest" : {
				System.out.println( "command APPLY_MONTHLY_INTEREST : apply monthly interest.\n" );
				System.out.println( "=> to use: apply_monthly_interest" );
				System.out.println( "=> to use: A\n" );
				break;
			}
			case "sort" : {
				System.out.println( "command SORT : sort the account list.\n" );
				System.out.println( "=> to use: s numAccount" );
				System.out.println( "=> to use: sort nameAccount" );
				System.out.println( "=> to use: sort amount" );
				System.out.println( "=> to use: sort nameManager" );
				System.out.println( "=> to use: sort countryAccount" );
				System.out.println( "=> to use: sort typeAccount\n" );
				break;
			}

			default : {
				System.out.println( "ERROR - Helper not recognize the command...\n" );
			}
		}
	}
}


