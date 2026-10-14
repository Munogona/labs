/** Lab 5: Polymorphism — exercises 1 to 5. */
public class Main {
    static class Printer {void print(int n){System.out.println(n);}void print(double n){System.out.println(n);}void print(String s){System.out.println(s);}void print(int[] a){System.out.println(java.util.Arrays.toString(a));}}
    static class Animal {void sound(){System.out.println("Animal sound");}}
    static class Cat extends Animal {@Override void sound(){System.out.println("Meow");}}
    static class Cow extends Animal {@Override void sound(){System.out.println("Moo");}}
    static class Duck extends Animal {@Override void sound(){System.out.println("Quack");}}
    static class Employee {double calculatePay(){return 1000;}}
    static class Manager extends Employee {@Override double calculatePay(){return 1500;}}
    static class Developer extends Employee {@Override double calculatePay(){return 1300;}}
    static class Intern extends Employee {@Override double calculatePay(){return 400;}}
    static class Base {String label="Base";String getLabel(){return "Base method";}}
    static class Child extends Base {String label="Child";@Override String getLabel(){return "Child method";}}
    interface Payment {void process();}
    static class CardPayment implements Payment {public void process(){System.out.println("Card charged");}}
    static class MobileMoneyPayment implements Payment {public void process(){System.out.println("Mobile money charged");}}
    static class PaymentProcessor {
        void payWithTypeCheck(Payment p){if(p instanceof CardPayment){System.out.println("Type-check: card");}else if(p instanceof MobileMoneyPayment){System.out.println("Type-check: mobile money");}}
        void pay(Payment p){p.process();}
    }
    public static void main(String[] args){
        System.out.println("1 Printer overloads:");Printer printer=new Printer();printer.print(4);printer.print(2.5);printer.print("Hello");printer.print(new int[]{1,2,3});
        System.out.println("2 Animal array:");for(Animal a:new Animal[]{new Cat(),new Cow(),new Duck()})a.sound();
        double payroll=0;for(Employee e:new Employee[]{new Employee(),new Manager(),new Developer(),new Intern()})payroll+=e.calculatePay();System.out.println("3 Total payroll: "+payroll);
        Base b=new Child();System.out.println("4 Field: "+b.label+"; method: "+b.getLabel());System.out.println("Fields use declared reference type; overridden instance methods use runtime object type.");
        PaymentProcessor processor=new PaymentProcessor();for(Payment p:new Payment[]{new CardPayment(),new MobileMoneyPayment()}){processor.payWithTypeCheck(p);processor.pay(p);}
        System.out.println("5 Type checks require edits for each new payment; polymorphic process() supports new payment classes without modifying the processor.");
    }
}
