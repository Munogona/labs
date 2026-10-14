import java.util.*;
/** Lab 4: Inheritance — exercises 1 to 5. */
public class Main {
    static class Person {String name;int age;Person(String n,int a){name=n;age=a;}void display(){System.out.println(name+" ("+age+")");}}
    static class Student extends Person {String course;Student(String n,int a,String c){super(n,a);course=c;}}
    static class Teacher extends Person {String subject;Teacher(String n,int a,String s){super(n,a);subject=s;}}
    static class Vehicle {Vehicle(){System.out.println("Vehicle constructor");}}
    static class Car extends Vehicle {Car(){System.out.println("Car constructor");}}
    static class ElectricCar extends Car {ElectricCar(){System.out.println("ElectricCar constructor");}}
    static class Animal {Animal(String name){System.out.println("Animal: "+name);}}
    static class Dog extends Animal {Dog(){super("Dog");}} // implicit super() would fail: none exists
    static class Stack<T> {
        private final ArrayList<T> values=new ArrayList<>();
        void push(T value){values.add(value);}T pop(){if(values.isEmpty())throw new EmptyStackException();return values.remove(values.size()-1);}
        int size(){return values.size();}
    }
    public static void main(String[] args){
        System.out.println("1-2 Student and Teacher:");new Student("Rudo",20,"Software Engineering").display();new Teacher("Moyo",41,"Java").display();
        System.out.println("3 Constructor chain:");new ElectricCar();
        System.out.println("4 Explicit super argument:");new Dog();
        System.out.println("Animal defines only Animal(String); Java would insert super() in Dog without explicit super(\"Dog\"), causing compilation failure.");
        Stack<Integer> stack=new Stack<>();stack.push(10);stack.push(20);System.out.println("5 Stack popped "+stack.pop()+"; size "+stack.size());
        System.out.println("Composition hides ArrayList operations such as adding at arbitrary indexes or clearing the stack unexpectedly.");
    }
}
