package com.mycompagny.execution;

public class Tests {

    abstract static class Writer { public  void write() { System.out.println("Writing...");} };

    public class Programmer extends Writer { public  void write() { System.out.println("Writing code"); } };

    public void exec() { Writer w = new Programmer(); w.write(); }

}
