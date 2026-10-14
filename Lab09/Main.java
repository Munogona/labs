import java.util.*;
/** Lab 9: Nested types, enums, records and Object contract — exercises 1 to 5. */
public class Main {
    enum Level {LOW,MEDIUM,HIGH}
    record Student(String name,int age){}
    static class Book {
        private final String isbn,title;Book(String isbn,String title){this.isbn=isbn;this.title=title;}
        @Override public boolean equals(Object other){return this==other || (other instanceof Book b && Objects.equals(isbn,b.isbn));}
        @Override public int hashCode(){return Objects.hashCode(isbn);}
        @Override public String toString(){return title+" ("+isbn+")";}
    }
    static class LinkedList<T> implements Iterable<T> {
        private static class Node<T>{T value;Node<T> next;Node(T v){value=v;}}
        private Node<T> head,tail;
        void add(T value){Node<T> n=new Node<>(value);if(tail==null)head=n;else tail.next=n;tail=n;}
        // Non-static inner iterator can access outer list's private nodes.
        private class ListIterator implements Iterator<T>{Node<T> current=head;
            public boolean hasNext(){return current!=null;}
            public T next(){if(!hasNext())throw new NoSuchElementException();T val=current.value;current=current.next;return val;}
        }
        public Iterator<T> iterator(){return new ListIterator();}
    }
    enum Coin {PENNY(1),NICKEL(5),DIME(10),QUARTER(25);
        final int cents;Coin(int c){cents=c;}
        static Map<Coin,Integer> fewestCoins(int amount){
            if(amount<0)throw new IllegalArgumentException("negative cents");
            Map<Coin,Integer> result=new LinkedHashMap<>();
            for(Coin coin:new Coin[]{QUARTER,DIME,NICKEL,PENNY}){int n=amount/coin.cents;if(n>0)result.put(coin,n);amount%=coin.cents;}
            return result;
        }
    }
    public static void main(String[] args){
        for(Level level:Level.values()){
            String advice=switch(level){case LOW->"Keep monitoring";case MEDIUM->"Take precautions";case HIGH->"Act immediately";};
            System.out.println("1 "+level+": "+advice);
        }
        Student a=new Student("Tariro",21),b=new Student("Tariro",21);
        System.out.println("2 Record: "+a+"; equal? "+a.equals(b)+"; component: "+a.name());
        Set<Book> books=new HashSet<>();books.add(new Book("123","Java Basics"));books.add(new Book("123","Same ISBN"));System.out.println("3 HashSet size "+books.size());
        LinkedList<Integer> linked=new LinkedList<>();linked.add(1);linked.add(2);linked.add(3);System.out.print("4 Linked list: ");for(int n:linked)System.out.print(n+" ");System.out.println();
        System.out.println("5 Fewest US coins for 87 cents: "+Coin.fewestCoins(87));
    }
}
