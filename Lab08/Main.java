import java.util.*;
import java.util.stream.*;

/** Lab 8: Generics and Collections — all five manual exercises. */
public class Main {
    // Exercise 3: stack is backed by ArrayList, with no inappropriate List methods
    // exposed.
    static class Stack<T> {
        private final ArrayList<T> values = new ArrayList<>();

        void push(T item) {
            values.add(item);
        }

        T pop() {
            if (isEmpty())
                throw new EmptyStackException();
            return values.remove(values.size() - 1);
        }

        T peek() {
            if (isEmpty())
                throw new EmptyStackException();
            return values.get(values.size() - 1);
        }

        boolean isEmpty() {
            return values.isEmpty();
        }
    }

    // Exercise 5: bubble sort works for reference types implementing Comparable.
    static <T extends Comparable<T>> void bubbleSort(T[] arr) {
        for (int i = 0; i < arr.length - 1; i++)
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    T tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
    }

    public static void main(String[] args) {
        // Exercise 1
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(8, 2, 5, 10, 4, 7, 1, 6, 3, 9));
        int sum = numbers.stream().mapToInt(Integer::intValue).sum();
        int max = Collections.max(numbers);
        List<Integer> reversed = new ArrayList<>(numbers);
        Collections.reverse(reversed);
        System.out.printf("1 Sum=%d max=%d reversed=%s%n", sum, max, reversed);
        // Exercise 2
        List<String> names = Arrays.asList("Chipo", "Rudo", "Chipo", "Farai", "Rudo");
        Set<String> unique = new LinkedHashSet<>(names);
        System.out.println("2 Unique names: " + unique);
        // Exercise 3
        Stack<String> stack = new Stack<>();
        stack.push("First");
        stack.push("Second");
        System.out.printf("3 peek=%s pop=%s empty=%b%n", stack.peek(), stack.pop(), stack.isEmpty());
        // Exercise 4: optional paragraph on command line; default is reproducible.
        String paragraph = args.length > 0 ? String.join(" ", args)
                : "Java is fun and Java is useful. Java collections are useful.";
        Map<String, Long> frequencies = Arrays
                .stream(paragraph.toLowerCase(Locale.ROOT).replaceAll("[^a-z0-9\\s]", " ").trim().split("\\s+"))
                .filter(w -> !w.isEmpty()).collect(Collectors.groupingBy(w -> w, Collectors.counting()));
        System.out.println("4 Word frequency, descending:");
        frequencies.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed().thenComparing(Map.Entry.comparingByKey()))
                .forEach(e -> System.out.println(e.getKey() + " -> " + e.getValue()));
        // Exercise 5
        Integer[] integers = { 4, 1, 3, 2 };
        String[] words = { "pear", "apple", "orange" };
        bubbleSort(integers);
        bubbleSort(words);
        System.out.println("5 Integers: " + Arrays.toString(integers));
        System.out.println("5 Strings: " + Arrays.toString(words));
        System.out.println("int[] cannot be used: generics require reference types; Integer[] works.");
    }
}
