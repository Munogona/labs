import java.util.*;

/** Lab 6: Abstraction and Interfaces — exercises 1 to 5. */
public class Main {
    abstract static class Shape {
        abstract double area();

        abstract double perimeter();
    }

    static class Circle extends Shape {
        double r;

        Circle(double r) {
            this.r = r;
        }

        double area() {
            return Math.PI * r * r;
        }

        double perimeter() {
            return 2 * Math.PI * r;
        }
    }

    static class Rectangle extends Shape {
        double w, h;

        Rectangle(double w, double h) {
            this.w = w;
            this.h = h;
        }

        double area() {
            return w * h;
        }

        double perimeter() {
            return 2 * (w + h);
        }
    }

    static class Square extends Rectangle {
        Square(double side) {
            super(side, side);
        }
    }

    interface Flyable {
        void fly();
    }

    static class Bird implements Flyable {
        public void fly() {
            System.out.println("Bird flying");
        }
    }

    static class Aeroplane implements Flyable {
        public void fly() {
            System.out.println("Aeroplane flying");
        }
    }

    abstract static class Account {
        double balance;

        void deposit(double amount) {
            if (amount <= 0)
                throw new IllegalArgumentException();
            balance += amount;
        }

        abstract double monthlyFee();
    }

    static class SavingsAccount extends Account {
        double monthlyFee() {
            return 2;
        }
    }

    static class CurrentAccount extends Account {
        double monthlyFee() {
            return 10;
        }
    }

    static class Student implements Comparable<Student> {
        String name;
        int marks;

        Student(String n, int m) {
            name = n;
            marks = m;
        }

        public int compareTo(Student s) {
            return name.compareTo(s.name);
        }

        public String toString() {
            return name + "=" + marks;
        }
    }

    interface GreetingA {
        default void hello() {
            System.out.println("Hello A");
        }
    }

    interface GreetingB {
        default void hello() {
            System.out.println("Hello B");
        }
    }

    static class Both implements GreetingA, GreetingB {
        public void hello() {
            GreetingA.super.hello();
            GreetingB.super.hello();
        }
    }

    public static void main(String[] args) {
        for (Shape s : new Shape[] { new Circle(2), new Rectangle(3, 4), new Square(5) })
            System.out.printf("1 %s area %.2f perimeter %.2f%n", s.getClass().getSimpleName(), s.area(), s.perimeter());
        for (Flyable f : new Flyable[] { new Bird(), new Aeroplane() })
            f.fly();
        Account savings = new SavingsAccount(), current = new CurrentAccount();
        savings.deposit(100);
        current.deposit(100);
        System.out.println("3 Fees: " + savings.monthlyFee() + ", " + current.monthlyFee());
        List<Student> students = new ArrayList<>(
                Arrays.asList(
                        new Student("Zara", 75),
                        new Student("Amy", 90),
                        new Student("Ben", 55)));
        Collections.sort(students);
        System.out.println("4 By name: " + students);
        students.sort(Comparator.comparingInt((Student s) -> s.marks).reversed());
        System.out.println("By marks: " + students);
        System.out.println("5 Resolved default-method clash:");
        new Both().hello();
        System.out.println("Two default methods with the same signature must be overridden to resolve the conflict.");
    }
}
