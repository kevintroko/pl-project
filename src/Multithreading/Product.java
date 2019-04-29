package Multithreading;

import java.util.Random;

/** @Author Valentin Ochoa */

public class Product {
   final char[] SCHEME_OPERATIONS = {'+', '-', '*', '/'};

   private int lowerBound = 0;
   private int upperBound = 9;
   Random random;
   
   Product() {
       random = new Random();
   }

   public int generateRandomFactor() {
       int randNumber = random.nextInt((upperBound - lowerBound) + 1) + lowerBound;
       return randNumber;
   }

    public char generateRandomOperation() {

        int randomOperation = random.nextInt(4);
        return SCHEME_OPERATIONS[randomOperation];
    }
   public String createOperation() {
	   return generateRandomOperation()+ " "+ generateRandomFactor()+" "+ generateRandomFactor();
   }
   
}
