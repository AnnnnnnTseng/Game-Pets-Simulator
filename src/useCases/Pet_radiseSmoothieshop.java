package useCases;

import globalEntities.Parameters;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

//lambda practice
public class Pet_radiseSmoothieshop {
    public static void main(String[] args) {

        // Get the produce map from the Parameter class
        Map<String, List<Integer>> produce = Parameters.getSmoothieMenu();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Welcome to Pet-radise Smoothie Shop.");
            // Print menu with available items and their energy level information
            System.out.println("Menu:");
            for (Map.Entry<String, List<Integer>> entry : produce.entrySet()) {
                String item = entry.getKey();
                List<Integer> values = entry.getValue();
                System.out.println(item + " - Price: " + values.get(0) + ", Calories: " + values.get(1) + ", Sugar: " + values.get(2));
            }
            System.out.println();
            System.out.println("What do you wanna get to boost your pet's energy level?");
            System.out.println("Enter command (checkprice, checkcalorie, checksugar, makeorder) followed by three ingredients:");
            String inputLine = scanner.nextLine();

            // Check for exit command
            if (inputLine.trim().equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");
                break;
            }


            //parse the input
            String[] parts = inputLine.split(", ");
            if (parts.length != 4) {
                System.out.println("Invalid input. Should be: command, ingredient1, ingredient2, ingredient3");
                continue;
            }

            //put input into variables
            String command = parts[0].trim();
            String ingredient1 = parts[1].trim();
            String ingredient2 = parts[2].trim();
            String ingredient3 = parts[3].trim();

            //validate userinput
            if (!produce.containsKey(ingredient1) || !produce.containsKey(ingredient2) || !produce.containsKey(ingredient3)) {
                System.out.println("One or more ingredients are not available.");
                continue;
            }

            //define lambda expression
            Function<List<Integer>, Integer> getPrice = values -> values.get(0);
            Function<List<Integer>, Integer> getCalorie = values -> values.get(1);
            Function<List<Integer>, Integer> getSugar = values -> values.get(2);

            // Get the values from the map
            List<Integer> values1 = produce.get(ingredient1);
            List<Integer> values2 = produce.get(ingredient2);
            List<Integer> values3 = produce.get(ingredient3);

            //Caculate result based on use input
            switch (command) {
                case "checkprice":
                    int totalPrice = getPrice.apply(values1) + getPrice.apply(values2) + getPrice.apply(values3);
                    System.out.println("Total price: " + totalPrice);
                    break;
                case "checkcalorie":
                    int totalCalorie = getCalorie.apply(values1) + getCalorie.apply(values2) + getCalorie.apply(values3);
                    System.out.println("Total calorie: " + totalCalorie);
                    break;
                case "checksugar":
                    int totalSugar = getSugar.apply(values1) + getSugar.apply(values2) + getSugar.apply(values3);
                    System.out.println("Total sugar level: " + totalSugar);
                    break;
                case "makeorder":
                    totalPrice = getPrice.apply(values1) + getPrice.apply(values2) + getPrice.apply(values3);
                    totalCalorie = getCalorie.apply(values1) + getCalorie.apply(values2) + getCalorie.apply(values3);
                    totalSugar = getSugar.apply(values1) + getSugar.apply(values2) + getSugar.apply(values3);
                    System.out.println("Thank you for your order! Here is the information:");
                    System.out.println("Total price: " + totalPrice);
                    System.out.println("Total calorie: " + totalCalorie);
                    System.out.println("Total sugar level: " + totalSugar);
                    break;
                default:
                    System.out.println("Invalid command.");
            }
        }
        scanner.close();
    }
}





//        //create some attribute for calculation
//        String operator_ = parts[0];
//        double num1, num2;
//
//        //parse input value "parts" into desired type double and catch error
//        try {
//            num1 = Double.parseDouble(parts[1]);
//            num2 = Double.parseDouble(parts[2]);
//        } catch (NumberFormatException e) {
//            System.out.println("Invalid numbers.");
//            return;
//        }
//
//        //Perform operation and print result
//        BiFunction<Double, Double, Double> operation = binaryOps.get(operator_);
//        BiFunction<Double, Double, Double> operation = binaryOps.get(operator_);
//        if (operation == null) {
//            System.out.println("Invalid operator.");
//            return;
//        }
//        double result = operation.apply(num1, num2);
//        System.out.println("Result: " + result);

