/** Lab 2: Constructors, this and static — exercises 1 to 5. */
public class Main {
    static class Circle {
        private static int count; final double radius;
        Circle(){this(1.0);} Circle(double radius){if(radius<0)throw new IllegalArgumentException("radius");this.radius=radius;count++;}
        static int getCount(){return count;}
    }
    static class Employee {
        private static int nextId=1; final int employeeId; String name; double salary;
        Employee(){this("Unknown",0);}Employee(String name){this(name,0);}Employee(String name,double salary){this.employeeId=nextId++;this.name=name;this.salary=salary;}
        Employee(Employee other){this(other.name,other.salary);} // unique ID for the copy
        public String toString(){return employeeId+": "+name+" ($"+salary+")";}
    }
    static class Initialisation {
        static {System.out.println("static block one");} static {System.out.println("static block two");}
        {System.out.println("instance block one");}{System.out.println("instance block two");}
        Initialisation(){System.out.println("constructor");}
    }
    static class Temperature {
        private final double celsius;
        private Temperature(double celsius){this.celsius=celsius;}
        static Temperature fromCelsius(double c){return new Temperature(c);}
        static Temperature fromFahrenheit(double f){return new Temperature((f-32)*5/9);}
        double getCelsius(){return celsius;}
    }
    public static void main(String[] args){
        Circle c1=new Circle(),c2=new Circle(3);System.out.printf("1-2 Circles: %.1f, %.1f; count=%d%n",c1.radius,c2.radius,Circle.getCount());
        Employee e=new Employee("Rudo",700),copy=new Employee(e);System.out.println("3 Employees: "+new Employee()+" | "+new Employee("Farai")+" | "+e+" | copy: "+copy);
        System.out.println("4 First object:");new Initialisation();System.out.println("4 Second object:");new Initialisation();
        System.out.printf("5 32F = %.1fC; 100C = %.1fC%n",Temperature.fromFahrenheit(32).getCelsius(),Temperature.fromCelsius(100).getCelsius());
        System.out.println("Static factories clarify whether a value is Fahrenheit or Celsius.");
    }
}
