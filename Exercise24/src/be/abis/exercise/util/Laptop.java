package be.abis.exercise.util;

import java.util.List;

public class Laptop {

   /* public static <T> void callPrinter(List<T> list){
      // for (T t : list) new Printer<T>().print(t);
      //OR
        Printer<T> printer = new Printer<>();
        list.forEach(printer::print);
    }*/


    public static void callPrinter(List<? extends Number> list){
        // for (T t : list) new Printer<T>().print(t);
        //OR
       list.forEach(System.out::println);
       //list.add(new Object());
    }

}
