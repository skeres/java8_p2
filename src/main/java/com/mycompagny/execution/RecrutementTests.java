package com.mycompagny.execution;

public class RecrutementTests {

    public static void main(String[] args) {

        palindrome("kayak");
        palindrome("toto");
        palindrome("carambar");

    }

    private static void palindrome(String str) {

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
}
