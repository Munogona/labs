import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.*;
/** Lab 10: File I/O, Lambdas and Streams — exercises 1 to 5. */
public class Main {
    record Student(String name,double marks){}
    record Learner(String name,int mark){}
    static String band(int mark){return mark>=80?"A":mark>=65?"B":"C";}
    public static void main(String[] args)throws IOException{
        Path output=Paths.get("output");Files.createDirectories(output);
        // Ex1: Supply --interactive to type five lines. Defaults allow non-interactive tests.
        List<String> lines=new ArrayList<>();
        if(args.length>0&&args[0].equals("--interactive")){
            Scanner scanner=new Scanner(System.in);
            System.out.println("1 Enter five lines:");for(int i=0;i<5;i++)lines.add(scanner.nextLine());
        }else lines.addAll(List.of("Java is object oriented","We study classes","Streams process data","Files persist information","Practice builds confidence"));
        Path fiveLines=output.resolve("five-lines.txt");Files.write(fiveLines,lines);
        System.out.println("1 File lines: "+Files.readAllLines(fiveLines));
        List<Integer> integers=new ArrayList<>(List.of(1,2,3,4,5,6,7,8));
        Predicate<Integer> even=n->n%2==0;integers.removeIf(even);System.out.println("2 Odd values: "+integers);
        String text=Files.readString(fiveLines);long lineCount=Files.lines(fiveLines).count();
        long wordCount=Arrays.stream(text.trim().split("\\s+")).filter(w->!w.isBlank()).count();
        System.out.printf("3 Lines=%d words=%d characters=%d%n",lineCount,wordCount,text.length());
        List<Student> students=List.of(new Student("Nyasha",92),new Student("Chipo",65),new Student("Amy",86),new Student("Zara",52));
        double average=students.stream().mapToDouble(Student::marks).average().orElse(0);
        List<String> above=students.stream().filter(s->s.marks()>average).map(Student::name).sorted().toList();
        System.out.printf("4 Class average=%.2f, above average: %s%n",average,above);
        Path input=Paths.get("learners.csv");
        if(!Files.exists(input)){
            Files.write(input,List.of("name,marks","Rudo,90","Chipo,70","Farai,52","Tariro,83","Simba,66"));
            System.out.println("5 Created sample learners.csv; replace with your real data as needed.");
        }
        List<Learner> learners=new ArrayList<>();
        for(String row:Files.readAllLines(input)){
            if(row.isBlank()||row.toLowerCase(Locale.ROOT).startsWith("name,"))continue;
            String[] cells=row.split(",",-1);if(cells.length!=2)throw new IllegalArgumentException("Invalid CSV row: "+row);
            learners.add(new Learner(cells[0].trim(),Integer.parseInt(cells[1].trim())));
        }
        Map<String,List<Learner>> grouped=learners.stream().collect(Collectors.groupingBy(l->band(l.mark())));
        for(String grade:List.of("A","B","C")){
            List<String> report=new ArrayList<>(List.of("Name,Marks,Grade"));
            grouped.getOrDefault(grade,List.of()).stream().sorted(Comparator.comparing(Learner::name))
                .forEach(l->report.add(l.name()+","+l.mark()+","+grade));
            Path file=output.resolve("grade-"+grade+".csv");Files.write(file,report);
            System.out.println("5 Wrote "+file+" ("+(report.size()-1)+" learners)");
        }
    }
}
