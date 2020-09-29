package com.mycompagny.execution;



import javax.xml.transform.sax.SAXSource;

public class RecrutementTests {

    enum Jour {
        LUNDI, MARDI, MERCREDI, JEUDI, VENDREDI, SAMEDI, DIMANCHE;
    }

    public static void main(String[] args) {

        RecrutementTests recrutementTests = new RecrutementTests();
        recrutementTests.palindrome("kayak");
        recrutementTests.palindrome("toto");
        recrutementTests.palindrome("carambar");
        recrutementTests.out();
        recrutementTests.enumStoryPart1();


    }

    private void palindrome(String str) {

        System.out.println("running palindrome");

        StringBuilder input1 = new StringBuilder();
        StringBuilder output1;

        // append a string into StringBuilder input1
        input1.append(str);

        // reverse StringBuilder input1
        output1 = input1.reverse();


        if ( (output1.toString().equalsIgnoreCase(str)) )
        {
            System.out.println("true : "+str+" is a palindrome");
        } else {
            System.out.println("false : "+str+" is a NOT palindrome");
        }

    }

    public void out() {
        //histoire d'heritage de classes
        Tests tests = new Tests(); tests.exec();

        //histoires de non compilation
        int x=10;
        int y=2;
        int res=x/y;
        System.out.println(res+" " );
        try {
            for (int z=2;z>=0;z--) {
                int ans=x/z;
                System.out.println(ans+ " ");
            }
        } catch (Exception e) {
            System.out.println("bong");
        }
        // catch below gets compilation Error  :already caught above
        //with general exception
        //catch (ArithmeticException e2) {
        //    System.out.println("paf");
        //}

        //histoire de stringBuilder.
        StringBuilder sb= new StringBuilder();
        sb.append("java");
        sb.append("love");
        System.out.println(sb.toString());
        sb.substring(4,7);    // piege : sb n'est pas modifié
        System.out.println(sb.toString());
        int foundAt=sb.indexOf("love");
        System.out.println(foundAt);

    }


    public void enumStoryPart1() {

        String[] arg=new String[1];
        arg[0]=Jour.SAMEDI.toString();
        Jour jour = Jour.valueOf(arg[0]);
        if (jour == Jour.SAMEDI) System.out.print("fin de semaine : ");
        switch(jour)	{
            case SAMEDI : System.out.println("passe dans le case Samedi mais ne fait rien et passe à dimanche car pas de 'break'");
            case DIMANCHE :
                System.out.println("se reposer");
                break;
            default :
                System.out.println("travailler");
                break;
        }
    }


}
