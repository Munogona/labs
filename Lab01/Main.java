import java.time.Year;

/** Lab 1: Classes and Objects — exercises 1 to 5. */
public class Main {
    static class Car {
        String brand; int year; double mileage;
        Car(String brand, int year, double mileage) { this.brand=brand; this.year=year; this.mileage=mileage; }
        void display() { System.out.printf("%s | %d | %.0f km | Antique: %b%n", brand, year, mileage, isAntique()); }
        boolean isAntique() { return Year.now().getValue()-year > 25; }
    }
    static class Rectangle {
        double width, height;
        Rectangle(double width, double height) {this.width=width;this.height=height;}
        double area() {return width*height;}
        double perimeter() {return 2*(width+height);}
    }
    static class Address { String city; Address(String city){this.city=city;} }
    static class Person {String name; Address address; Person(String name,Address address){this.name=name;this.address=address;}}
    public static void main(String[] args) {
        System.out.println("Exercise 1 & 2: Cars");
        for(Car car:new Car[]{new Car("Toyota",1995,210000),new Car("Honda",2019,50000),new Car("Mazda",2000,160000)})car.display();
        System.out.println("Exercise 3: Rectangles");
        Rectangle[] rectangles={new Rectangle(2,3),new Rectangle(4,5),new Rectangle(1,8),new Rectangle(7,3),new Rectangle(6,6)};
        Rectangle largest=rectangles[0];
        for(Rectangle r:rectangles){System.out.printf("Area %.1f; perimeter %.1f%n",r.area(),r.perimeter());if(r.area()>largest.area())largest=r;}
        System.out.printf("Largest area: %.1f%n",largest.area());
        System.out.println("Exercise 4: Aliasing");
        Rectangle r1=new Rectangle(3,4),r2=r1;r2.width=10;
        System.out.println("r1.width = "+r1.width+" because r1 and r2 refer to the same object.");
        System.out.println("Exercise 5: Composition and null");
        Person p=new Person("Tariro",new Address("Harare"));System.out.println(p.address.city);
        p.address=null;
        try{System.out.println(p.address.city);}catch(NullPointerException ex){System.out.println("Null address has no city; check for null first.");}
    }
}
