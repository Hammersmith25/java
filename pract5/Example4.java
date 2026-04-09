import java.util.*;

public class Example4
{
    public static void main(String[] args)
    {
       double                 leftOperand, result, rightOperand;
       String                 leftString, operator, rightString;
       StringTokenizer        tokenizer;
       Scanner in = new Scanner(System.in);
       
       String arr[] = in.nextLine().split("\\s+");
       for(String el : arr){

       
         tokenizer = new StringTokenizer(el, "+-*/", true);

         try
         {
            leftString   = tokenizer.nextToken();
            operator     = tokenizer.nextToken();
            rightString  = tokenizer.nextToken();
            
            try{
               leftOperand  = Double.parseDouble(leftString);
            }
            catch(NumberFormatException nfe){
               System.out.println("Left operand is not a number!");
               continue;
            }
            try{
               rightOperand = Double.parseDouble(rightString);
            }
            catch(NumberFormatException nfe){
               System.out.println("right operand is not a number!");
               continue;
            }

            if (operator.equals("+"))
               result = leftOperand + rightOperand;
            else if(operator.equals("-"))
               result = leftOperand - rightOperand;
            else if(operator.equals("*"))
               result = leftOperand * rightOperand;
            else if(operator.equals("/"))
               result = leftOperand / rightOperand;
            else
               result = 0.0;

            System.out.println("Result: " + result);
         }
         catch (NoSuchElementException nsee)
         {
            System.out.println("Invalid syntax");
         }
         catch (NumberFormatException nfe)
         {
            System.out.println("One or more operands is not a number");
         }

      }
    }
}

//A StringTokenizer splits a string into smaller parts called tokens based on delimiters.
//StringTokenizer st = new StringTokenizer("5+6", "+");
// while (st.hasMoreTokens()) {
//     System.out.println(st.nextToken());
// }
//Output: 
// 5 
// 6

//StringTokenizer(String str, String delim, boolean returnDelims)
//StringTokenizer st = new StringTokenizer("5+6", "+", true);
//Output: 
//5
//+
//6

//5.3+9.2
//Result: 14.5

//5.3+
//Invalid syntax
//NoSuchElementException is occured because rightString  = tokenizer.nextToken(); is empty(There is no nextToken())

//5.3+a
//One or more operands is not a number
//because "a" is not a number and it can't be parsed to double