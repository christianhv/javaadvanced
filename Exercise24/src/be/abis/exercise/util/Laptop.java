package be.abis.exercise.util;

import java.util.List;

public class Laptop {

   public static <T> void callPrinter(List<T> list){
      // for (T t : list) new Printer<T>().print(t);
      //OR
        Printer<T> printer = new Printer<>();
        list.forEach(printer::print);
    }

}
