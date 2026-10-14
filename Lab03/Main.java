import java.util.*;

/** Lab 3: Encapsulation — exercises 1 to 5. */
public class Main {
    static class Student {
        private String name;
        private int marks;

        Student(String n, int m) {
            setName(n);
            setMarks(m);
        }

        void setName(String n) {
            if (n == null || n.trim().isEmpty())
                throw new IllegalArgumentException("name");
            name = n;
        }

        void setMarks(int m) {
            if (m < 0 || m > 100)
                throw new IllegalArgumentException("marks must be 0-100");
            marks = m;
        }

        String getGrade() {
            return marks >= 80 ? "A" : marks >= 65 ? "B" : marks >= 50 ? "C" : "F";
        }

        String getName() {
            return name;
        }
    }

    static class BankAccount {
        private double balance;
        private final List<String> transactions = new ArrayList<>();

        BankAccount(double initial) {
            if (initial < 0)
                throw new IllegalArgumentException();
            balance = initial;
            transactions.add("Opening " + initial);
        }

        void deposit(double sum) {
            if (sum <= 0)
                throw new IllegalArgumentException();
            balance += sum;
            transactions.add("Deposit " + sum);
        }

        boolean withdraw(double sum) {
            if (sum <= 0)
                throw new IllegalArgumentException();
            if (sum > balance)
                return false;
            balance -= sum;
            transactions.add("Withdrawal " + sum);
            return true;
        }

        List<String> getTransactions() {
            return Collections.unmodifiableList(new ArrayList<>(transactions));
        }
    }

    static final class ImmutablePerson {
        private final String name;
        private final int age;

        ImmutablePerson(String name, int age) {
            if (name == null || name.trim().isEmpty() || age < 0)
                throw new IllegalArgumentException();
            this.name = name;
            this.age = age;
        }

        String getName() {
            return name;
        }

        int getAge() {
            return age;
        }
    }

    static class Temperature {
        private final double kelvin;

        Temperature(double kelvin) {
            if (kelvin < 0)
                throw new IllegalArgumentException("below absolute zero");
            this.kelvin = kelvin;
        }

        static Temperature fromCelsius(double c) {
            return new Temperature(c + 273.15);
        }

        double getCelsius() {
            return kelvin - 273.15;
        }

        double getFahrenheit() {
            return getCelsius() * 9 / 5 + 32;
        }
    }

    public static void main(String[] args) {
        Student s = new Student("Chipo", 85);
        System.out.println("1-2 " + s.getName() + " grade " + s.getGrade());
        try {
            s.setMarks(120);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid marks rejected: " + e.getMessage());
        }
        BankAccount a = new BankAccount(100);
        a.deposit(30);
        a.withdraw(20);
        System.out.println("3 " + a.getTransactions());
        try {
            a.getTransactions().add("fake");
        } catch (UnsupportedOperationException e) {
            System.out.println("Transaction history protected.");
        }
        ImmutablePerson p = new ImmutablePerson("Nyasha", 23);
        System.out.println("4 " + p.getName() + ", age " + p.getAge());
        System.out.println(
                "final class prevents extension; private final fields prevent reassignment; no setters prevent modification.");
        Temperature t = Temperature.fromCelsius(25);
        System.out.printf("5 %.2fC = %.2fF%n", t.getCelsius(), t.getFahrenheit());
        try {
            new Temperature(-0.01);
        } catch (IllegalArgumentException e) {
            System.out.println("Below absolute zero rejected.");
        }
        System.out.println(
                "Clients use temperature methods, so its internal storage unit can change without changing client code.");
    }
}
