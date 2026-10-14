import java.util.*;
/** Lab 7: Exception Handling — exercises 1 to 5. */
public class Main {
    static class InvalidAgeException extends Exception {InvalidAgeException(String message){super(message);}}
    static class InsufficientFundsException extends Exception {InsufficientFundsException(String message){super(message);}}
    static class BankAccount {
        private double balance;BankAccount(double amount){balance=amount;}
        void withdraw(double amount)throws InsufficientFundsException {
            if(amount<=0)throw new IllegalArgumentException("amount must be positive");
            if(amount>balance)throw new InsufficientFundsException("Insufficient funds: balance="+balance);
            balance-=amount;
        }
    }
    static void validateAge(int age)throws InvalidAgeException {if(age<0||age>120)throw new InvalidAgeException("Age must be 0-120: "+age);}
    static void readIndex(int[] arr,int index){try{System.out.println("Array item: "+arr[index]);}catch(ArrayIndexOutOfBoundsException e){System.out.println("Index out of bounds: "+index);}}
    static int returnWithFinally(){try{return 42;}finally{System.out.println("5 finally runs before the caller receives the return value");}}
    public static void main(String[] args){
        System.out.println("1 Integer division: enter two integers (or press Enter for defaults 10 and 2).");
        Scanner scanner=new Scanner(System.in);
        try {
            String first=scanner.hasNextLine()?scanner.nextLine().trim():"";
            String second=scanner.hasNextLine()?scanner.nextLine().trim():"";
            int a=first.isEmpty()?10:Integer.parseInt(first);
            int b=second.isEmpty()?2:Integer.parseInt(second);
            System.out.println("Result: "+(a/b));
        }catch(NumberFormatException e){System.out.println("Non-numeric input rejected.");}
         catch(ArithmeticException e){System.out.println("Division by zero rejected.");}
        System.out.println("2 Array access:");readIndex(new int[]{5,10},7);
        try{validateAge(150);}catch(InvalidAgeException e){System.out.println("3 "+e.getMessage());}
        BankAccount account=new BankAccount(50);
        try{account.withdraw(100);}catch(InsufficientFundsException e){System.out.println("4 "+e.getMessage());}
        System.out.println("A checked exception requires callers to handle insufficient funds; returning false is simpler for expected failures but gives less information.");
        System.out.println("5 Returned: "+returnWithFinally());
    }
}
