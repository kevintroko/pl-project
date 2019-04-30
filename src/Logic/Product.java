package Logic;

import java.util.Random;

/** @Author Valentin Ochoa */

public class Product {
   final char[] SCHEME_OPERATIONS = {'+', '-', '*', '/'};

   private final int LOWER_BOUND = 0;
   private int UPPER_BOUND = 9;
   Random random;
   
   Product() {
       random = new Random();
   }


    public char generateRandomOperation() {

        int randomOperation = random.nextInt(4);
        return SCHEME_OPERATIONS[randomOperation];
    }
    public int generateRandomFactor() {
        int randNumber = random.nextInt((UPPER_BOUND - LOWER_BOUND) + 1) + LOWER_BOUND;
        return randNumber;
    }
   public String createOperation() {
	   return generateRandomOperation()+ " "+ generateRandomFactor()+" "+ generateRandomFactor();
   }

}
