//
// >>>>>>>>>>>>>>>>>>>>>>>>>   source ::https://winterbe.com/posts/2014/07/31/java8-stream-tutorial-examples/
//


package com.mycompagny.execution;



import com.mycompagny.interfaces.Instrument;
import com.mycompagny.utils.CommonService;
import com.mycompagny.utils.LocalDateTimeRange;
import com.mycompagny.utils.MyBigDecimal;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Execution001
{

    public class Test {
        Boolean aBooleanObject;
        boolean aBooleanPrimitive;
    }
    public static class BooleanHolder {
        public Boolean valeur;
    }
    public static class BooleanWrapper {
        public boolean valeurWrapper;
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

        execution.doWork9();
        execution.doWork10();
        execution.doWork11();
        execution.doWork12();

        boolean one = false;
        Boolean TWO = false;
        BooleanHolder b= new BooleanHolder();
        System.out.println("BooleanHolder avant set ="+b.valeur);
        b.valeur=false;
        BooleanWrapper z= new BooleanWrapper();
        System.out.println("BooleanWrapper avant set ="+z.valeurWrapper);
        z.valeurWrapper=false;
        execution.doWork14(one, TWO, b, z);
        System.out.println("one="+one);
        System.out.println("TWO="+TWO);
        System.out.println("b="+b.valeur);
        System.out.println("z="+z.valeurWrapper);

        String a="a";
        StringBuilder sb= new StringBuilder("sb");
        execution.doWork15(a,sb);
        System.out.println("a="+a);
        System.out.println("sb="+sb);

        boolean r=false;
        execution.doWork16(r);
        System.out.println("r="+r);

        execution.doWork17();
        execution.doWork18();
        execution.doWork19();
        execution.doWork20();
        execution.doWork21();
    }

    void doWork21() {
        int a=7;
        if (a !=7 ) {
            System.out.println("pas 7");
        } else {
            System.out.println("c'est 7");
        }
    }
    void doWork20() {
        LocalDateTime ldt1 = LocalDateTime.of(2022,9,1,12,00);
        LocalDateTime ldt2 = LocalDateTime.of(2022,9,1,17,00);

        System.out.println("ldt1="+ldt1);

        System.out.println("ldt2 is after ldt1="+ldt2.isAfter(ldt1));
        System.out.println("ldt1 is after ldt1="+ldt1.isAfter(ldt1));
        System.out.println("ldt1 is equal ldt1="+ldt1.isEqual(ldt1));
        System.out.println("ldt2 compareTo ldt1="+ldt2.compareTo(ldt1));
        System.out.println("ldt1 compareTo ldt2="+ldt1.compareTo(ldt2));
        System.out.println("ldt1 compareTo ldt1="+ldt1.compareTo(ldt1));

    }
    void doWork19() {
        Test t = new Test();

        System.out.println("aBooleanObject init="+t.aBooleanObject);
        System.out.println("aBooleanPrimitive init="+t.aBooleanPrimitive);
    }

    void doWork18() {
        MyBigDecimal init = new MyBigDecimal();
        System.out.println("init="+init.valeur);
        BigDecimal bd= new BigDecimal(37);
        BigDecimal pvei= new BigDecimal(7);
        System.out.println("bd="+bd+" pvei=2 : "+ bd.divide(pvei,3, RoundingMode.HALF_UP));
    }
    void doWork17() {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        String myDateDepart = "2021-12-14 10:39";
        LocalDateTime ldtDepart=LocalDateTime.parse(myDateDepart,dateTimeFormatter);
        System.out.println("myDateDepart="+ldtDepart);

        String myDateArrivee = "2021-12-14 11:52";
        LocalDateTime ldtArrivee=LocalDateTime.parse(myDateArrivee,dateTimeFormatter);
        System.out.println("myDateArrivee="+myDateArrivee);

        LocalDateTimeRange r1=LocalDateTimeRange.ofLocalTime(ldtDepart.toLocalTime(), ldtArrivee.toLocalTime());
        System.out.println("plageTSVP depart="+r1.getDepart());
        System.out.println("plageTSVP fin="+r1.getFin());

        LocalTime debutPlage=LocalTime.of(11,00);;
        LocalTime finPlage=LocalTime.of(15,00);;

        CommonService commonService=new CommonService();
        Long l=commonService.intersection(r1, debutPlage, finPlage);

        System.out.println("l="+l);

    }

    void doWork16(boolean zzb) {
        zzb=true;
    }

    void doWork15(String x, StringBuilder y) {
        x="aa";
        y.append("sb");
    }

    void doWork14(boolean un, Boolean DEUX, BooleanHolder bh, BooleanWrapper truc) {
        un=true;
        DEUX=true;
        bh.valeur=true;
        truc.valeurWrapper=true;
    }

    void doWork12() {
        List<Integer> list = new ArrayList<>();
        list.add(202201);
        list.add(202202);
        list.add(202204);
        list.add(202203);

        List<Integer> listSorted = list.stream().sorted((
                (t1,t2) -> {
                    if (t1.compareTo(t2) == 0) {
                        System.out.println(t1.toString() +" | "+ t2.toString() + " t1.compareTo(t2)=0"+(t1.compareTo(t2) == 0));
                        return 0;
                    }
                    if (t1.compareTo(t2) == 1) {
                        System.out.println(t1.toString() +" | "+ t2.toString() + " t1.compareTo(t2)=1"+(t1.compareTo(t2) == 1));
                        return 1;
                    } else {
                        System.out.println(t1.toString() +" | "+ t2.toString() + " t1.compareTo(t2)=-1"+(t1.compareTo(t2) == 1));
                        return -1;
                    }
                }
        )).collect(Collectors.toList());
        listSorted.stream().forEach(System.out::println);

    }

    void doWork11() {
        List<String> list = new ArrayList<>();
        list.add("202201");
        list.add("202202");
        list.add("202204");
        list.add("202203");

        List<String> listSorted = list.stream().sorted((
                (t1,t2) -> {
                    int c=t1.compareTo(t2);
                    /*
                    if (t1.compareTo(t2) == 0) {
                        System.out.println(t1.toString() +" | "+ t2.toString() + " t1.compareTo(t2)=0 "+(t1.compareTo(t2) == 0));
                        return 0;
                    }
                    if (t1.compareTo(t2) == 1) {
                        System.out.println(t1.toString() +" | "+ t2.toString() + " t1.compareTo(t2)=1 "+(t1.compareTo(t2) == 1));
                        return 1;
                    } else {
                        System.out.println(t1.toString() +" | "+ t2.toString() + " t1.compareTo(t2)=-1 "+(t1.compareTo(t2) == -1));
                        return -1;
                    }
                    */
                    return c;
                }
                )).collect(Collectors.toList());
        listSorted.stream().forEach(System.out::println);

    }

    void doWork10(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(4);
        list.add(4);
        boolean resultAnd = list.stream().map(e-> e.compareTo(4) == 0).reduce(Boolean::logicalAnd).orElse(false);
        boolean resultOr = list.stream().map(e-> e.compareTo(4) == 0).reduce(Boolean::logicalOr).orElse(false);

        System.out.println("resultAnd="+resultAnd);
        System.out.println("resultOr="+resultOr);
    }
    void doWork9() {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        String myDateDepart = "2021-12-14 10:30";
        LocalDateTime ldtDepart=LocalDateTime.parse(myDateDepart,dateTimeFormatter);
        System.out.println("myDateDepart="+ldtDepart);

        LocalDateTime date01Minuit = LocalDate.of(ldtDepart
                                .plusDays(1)
                                .getYear(),
                        1,
                        1)
                .atStartOfDay();
        System.out.println("date01Minuit="+date01Minuit);

        String myDateArrivee = "2021-12-16 08:30";
        LocalDateTime ldtArrivee=LocalDateTime.parse(myDateArrivee,dateTimeFormatter);
        System.out.println("myDateArrivee="+myDateArrivee);

        if (myDateArrivee.compareTo(myDateDepart) >= 0) {
            System.out.println(myDateArrivee +" est plus grande que " + myDateDepart);
        } else {
            System.out.println(myDateArrivee +" est plus petite que " + myDateDepart);
        }

        LocalDateTimeRange plageTSVP=LocalDateTimeRange.ofLocalTime(ldtDepart.toLocalTime(), ldtArrivee.toLocalTime());
        System.out.println("plageTSVP depart="+plageTSVP.getDepart());
        System.out.println("plageTSVP fin="+plageTSVP.getFin());


        LocalDateTimeRange ldtr= LocalDateTimeRange.of(ldtDepart,ldtArrivee);
        LocalDateTime inDep=LocalDateTime.parse("2021-12-14 00:00",dateTimeFormatter);
        LocalDateTime inArr=LocalDateTime.parse("2021-12-14 01:30",dateTimeFormatter);
        LocalDateTimeRange in= LocalDateTimeRange.of(inDep,inArr);
        LocalDateTimeRange intersection=ldtr.intersection(in);
        System.out.println("intersection="+intersection.getDepart() + "/"+intersection.getFin());


        //test getLocalDateTimeFromLocalTime obtention d'un intervalle d'après des heures
        LocalTime debut=LocalTime.of(12,30);
        LocalTime fin=LocalTime.of(13,30);
        LocalDateTimeRange intervalle=LocalDateTimeRange.getLocalDateTimeFromLocalTime(debut,fin);
        System.out.println("debut="+debut);
        System.out.println("fin="+fin);
        System.out.println("intervalle="+intervalle.getDepart() + " / "+intervalle.getFin());


    };

    void doWork8() {

        List<Integer> listint = new ArrayList<Integer>();
        listint.add(1);
        listint.add(3);
        listint.add(5);
        listint.add(7);

        Integer r=listint.stream().reduce((a,b)->b).orElse(null);
        System.out.println("r="+r);

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
        System.out.println("doWork7");

    }



}