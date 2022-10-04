//
// >>>>>>>>>>>>>>>>>>>>>>>>>   source ::https://winterbe.com/posts/2014/07/31/java8-stream-tutorial-examples/
//


package com.mycompagny.execution;



        import com.mycompagny.interfaces.Instrument;

        import java.io.*;
        import java.nio.file.Files;
        import java.nio.file.Paths;
        import java.util.*;
        import java.util.function.Consumer;
        import java.util.function.Function;
        import java.util.function.Predicate;
        import java.util.stream.Collectors;
        import java.util.stream.IntStream;
        import java.util.stream.Stream;

public class Execution001 {

    public class Toto {

        public Toto() {
            System.out.println("in Toto constructor");
        }
    }

    public interface Calcul {
        public int mutlitplyByX( int x);
    }




    public static void main(String args[]) {
        System.out.println("hello world");
        Execution001 execution = new Execution001();
        execution.doWork1();
        execution.doWork2();
        execution.doWork3();
        try {
            execution.doWork4();
        } catch (IOException e) {
            e.printStackTrace();
        }
        execution.doWork5();
        execution.doWork6();
        execution.doWork7();
        execution.doWork8();
        execution.doWorkBidon();
        execution.doWorkBidon2();
        execution.doWorkBidon3();

    }

    public void doWorkBidon3(){
        System.out.println("méthode 3 qui ne fait rien, pour test");
    }

    public void doWorkBidon2(){
        System.out.println("méthode 2 qui ne fait rien, pour test");
    }

    public void doWorkBidon(){
        System.out.println("méthode 1 qui ne fait rien, pour test");
    }

    /**
     * Stream operations are either intermediate or terminal. Intermediate operations return a stream so we can chain multiple intermediate operations without using semicolons.
     * Terminal operations are either void or return a non-stream result.
     * ==> In the above example filter, map and sorted are intermediate operations
     * ==> whereas forEach is a terminal operation.
     *
     * Stream operations are either intermediate or terminal.
     * Intermediate operations return a stream so we can chain multiple intermediate operations without using semicolons.
     * Terminal operations are either void or return a non-stream result. In the above example filter, map and sorted are intermediate operations whereas forEach is a terminal operation
     *
     *  For a full list of all available stream operations see the Stream Javadoc. Such a chain of stream operations as seen in the example above is also known as operation pipeline.
     *
     *  Most stream operations accept some kind of lambda expression parameter, a functional interface specifying the exact behavior of the operation.
     *  Most of those operations must be both non-interfering and stateless. What does that mean?
     *
     *  A function is non-interfering when it does not modify the underlying data source of the stream,
     *  e.g. in the above example no lambda expression does modify myList by adding or removing elements from the collection.
     *
     *  A function is stateless when the execution of the operation is deterministic,
     *  e.g. in the above example no lambda expression depends on any mutable variables or states from the outer scope which might change during execution.
     */
    void doWork1() {
        System.out.println("doWork1");
        List<String> myList =
                Arrays.asList("a1", "a2", "b1", "c2", "c1");

        myList
                .stream()
                .filter(s -> s.startsWith("c"))
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);

    }

    /**
     * We focus on sequential streams for now
     * Calling the method stream() on a list of objects returns a regular object stream.
     * But we don't have to create collections in order to work with streams as we see in the next code sample:
     * ==> Just use Stream.of() to create a stream from a bunch of object references.
     */
    void doWork2(){
        System.out.println("doWork2");
        Stream.of("a1", "a2", "a3")
                .findFirst()
                .ifPresent(System.out::println);  // a1

        Stream.of(3,2,1)
                .sorted()
                .findFirst()
                .ifPresent(System.out::println);  // ?
    }


    /**
     * Besides regular object streams Java 8 ships with special kinds of streams for working with the primitive data types int, long and double.
     * As you might have guessed it's IntStream, LongStream and DoubleStream.
     *
     * IntStreams can replace the regular for-loop utilizing IntStream.range():
     *
     *
     * All those primitive streams work just like regular object streams with the following differences:
     * Primitive streams use specialized lambda expressions, e.g. IntFunction instead of Function or IntPredicate instead of Predicate.
     * And primitive streams support the additional terminal aggregate operations sum() and average():
     *
     *
     */
    void doWork3(){
        System.out.println("doWork3");
        IntStream.range(1, 4)
                .forEach(System.out::println);

        Arrays.stream(new int[] {1, 2, 3})
                .map(n -> 2 * n + 1)
                .average()
                .ifPresent(System.out::println);  // 5.0

        //Sometimes it's useful to transform a regular object stream to a primitive stream or vice versa.
        //For that purpose object streams support the special mapping operations mapToInt(), mapToLong() and mapToDouble:
        Stream.of("a1", "a2", "a3","b8")
                .map(s -> s.substring(1))
                //.mapToInt(Integer::parseInt) // "::" is a Double Colon Operator. It gives the same result as below
                .mapToInt(value -> Integer.parseInt(value))
                .max()
                .ifPresent(System.out::println);  // 3

        //Primitive streams can be transformed to object streams via mapToObj():
        IntStream.range(1, 4)
                .mapToObj(i -> "a" + i)
                .forEach(System.out::println);

        //Here's a combined example: the stream of doubles is first mapped to an int stream and than mapped to an object stream of strings:
        Stream.of(1.0, 2.0, 3.0)
                .mapToInt(Double::intValue)
                .mapToObj(i -> "a" + i)
                .forEach(System.out::println);

        /**** Converting a Primitive 'int' Array to List ****/
        int intArray[] = {1, 2, 3, 4, 5};
        List<Integer> integerList1 = Arrays.stream(intArray).boxed().collect(Collectors.toList());
        System.out.println("List #1: " + integerList1);

        /**** 'IntStream.of' or 'Arrays.stream' Gives The Same Output ****/
        List<Integer> integerList2 = IntStream.of(intArray).boxed().collect(Collectors.toList());
        System.out.println("List #2: " + integerList2);

        /**** Converting an 'Integer' Array to List ****/
        Integer integerArray[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        List<Integer> integerList3 = Arrays.stream(integerArray).collect(Collectors.toList());
        System.out.println("List #3: " + integerList3);

    }

    void doWork4() throws IOException {
        System.out.println("doWork4 read file as a stream");

        //create file
        File f = new File(".//fileTOstream.txt");
        BufferedWriter bufferedWriter = null;

        try {
            bufferedWriter = new BufferedWriter(new FileWriter(f));
            bufferedWriter.write("line1"+"\n");
            bufferedWriter.write("line2"+"\n");
            bufferedWriter.write("line3"+"\n");
            bufferedWriter.write("line4"+"\n");
            bufferedWriter.write("line5"+"\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            bufferedWriter.close();
        }
        String fileName = ".//fileTOstream.txt";
        List<String> list = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

            //1. filter line 3
            //2. convert all content to upper case
            //3. convert it into a List
            list = stream
                    .filter(line -> !line.startsWith("line3"))
                    .map(String::toUpperCase)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }

        list.forEach(System.out::println);
    }

    void doWork5(){

        System.out.println("doWork5 palindrome");
            String str = "kayak";
            //String str = "bla";

            StringBuilder input1 = new StringBuilder();
            StringBuilder output1;

            // append a string into StringBuilder input1
            input1.append(str);

            // reverse StringBuilder input1
            output1 = input1.reverse();

            System.out.println(input1);
            System.out.println(output1);

            if ( (output1.toString().equalsIgnoreCase(str)) )
            {
                System.out.println("true");
            } else {
                System.out.println("false");
            }



    }


    void doWork6(){
        System.out.println("doWork6 lambda expressions");


        //coding the old way : here we must describe Saxophone classs to be used in the old way without lambda
        class Saxophone implements Instrument {
            @Override
            public String jouerMorceauMusique(String morceau, int dureeDecoute) {
                System.out.println("oldWay : lancement de "+morceau+ " pour une durée de "+dureeDecoute);
                return "fin de lecture pour "+morceau;
            }
        }
        //old way
        Instrument instrument;
        instrument = new Saxophone();
        System.out.println(instrument.jouerMorceauMusique("mozart", 22));



        // ---------> coding the new way : 5 lines of code !
        Instrument instrumentNew= (morceau,dureeDecoute) -> {
            System.out.println("NewWay : lancement de "+morceau+ " pour une durée de "+dureeDecoute);
            return "fin de lecture pour "+morceau;
        };
        System.out.println(instrumentNew.jouerMorceauMusique("beethoven", 43));


    }

    void doWork7(){
        System.out.println("doWork7 interface et Lambda");
        System.out.println("SAM = single abstract method");

        //implementation de calcul avec Lambda
        Calcul resultat = x -> { return x*2; };
        System.out.println("avec Lambda "+ resultat.mutlitplyByX(4));

        //implementation de calcul SANS Lambda
        class Obtenir implements Calcul {

            @Override
            public int mutlitplyByX(int x) {
                return x*2;
            }
        }

        System.out.println("sans Lambda "+ new Obtenir().mutlitplyByX(4));

    }

    void doWork8() {
        System.out.println("doWork8 interfaces embarquées JAVA 8 ");

        Consumer<String> addThatsGreat = (a) -> System.out.println(a+" ,that's great");
        addThatsGreat.accept("je suis un champion");

        Consumer<String> addwhy = x -> System.out.println(x+" ,why ?");
        addwhy.accept("vous avez l'heure s'il vous plait ? ");

        Consumer<String> addChaud = (String x) -> System.out.println(x+" chaud chaud chaud !");
        addChaud.accept("chaud pour un foot ? ");

        Function<Integer, Boolean> isPositif = valeur -> {
            if (valeur >= 0) {
                return true;
            } else {
                return false;
            }
        };

        int e=4;
        System.out.println("l'entier présenté "+e+" est positif est une affirmation "+isPositif.apply(e) );
        e=-1;
        System.out.println("l'entier présenté "+e+" est positif est une affirmation "+isPositif.apply(e) );

        Function<Integer, Integer> fois2 = x -> x*2;
        int x=7;
        System.out.println(x+" fois 2 est égal à "+fois2.apply(x));


        int repetition=3;
        String message="je suis un message";
        Runnable r = () -> {
            for (int i = 0; i < repetition; i++) {
                System.out.println(message);
            }
        };
        new Thread(r).start();


        Predicate<String> contientX = s -> s.contains("X");
        Predicate<String> possedeTailleTrois = s -> s.length() == 8;
        Predicate<String> contientLeMotJoli = s -> s.toLowerCase().contains("joli");

        System.out.println(contientX.test("X"));
        Boolean b=contientX.and(possedeTailleTrois).and(contientLeMotJoli).test("joli XXX");
        System.out.println("retour combinaison predicat = "+b);


        ArrayList<String> mine = new ArrayList<>();
        mine.add("le 1 ");
        mine.add("le 2");
        System.out.println("taille initiale collection = "+mine.size());
        Consumer<Collection<String>> videLaCollection = c-> c.clear();
        videLaCollection.accept(mine);
        System.out.println("taille collection apres call consumer = "+mine.size());

        String z="kamoulox";
        Objects.requireNonNull(z);  //thow null pointer exception si z=null


        Function<String,String> fi_function_trimDataIfPossible = in -> {
            String ret=null;
            if (in != null ) {
                ret=in.trim();
            }
            return ret;
        };

        String cdRayon="   4 2    ";
        String cdCatCode=null;

        cdRayon=(null != cdRayon) ? cdRayon.trim() : cdRayon;
        cdCatCode=(null!=cdCatCode) ? cdCatCode.trim() : cdCatCode;

        //ou bien
        cdRayon=fi_function_trimDataIfPossible.apply(cdRayon);
        cdCatCode=fi_function_trimDataIfPossible.apply(cdCatCode);

        System.out.println(cdRayon);
        System.out.println(cdCatCode);

    }


}