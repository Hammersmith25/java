public class Example2
{
    public static void main(String[] args)
    {
	int        i = 0, ratio;
	int[]      numbers = {100,10,0,5,2,8,0,30};

	for (i = 0; i < numbers.length - 1; i++)
        {
            try
            {
                ratio = numbers[i] / numbers[i+1];
                System.out.println(numbers[i]+"/"+numbers[i+1]+"="+ratio);
            }
            catch (ArithmeticException ae)
            {
                System.out.println("Couldn't calculate "+
                                   numbers[i]+"/"+numbers[i+1]);
            }
        }
	}
}


//Example2.java:19: error: variable i might not have been initialized
//                                numbers[i]+"/"+numbers[i+1]);
//                                        ^
//java thinks that what if error occure before i is initialized in try block and says that it's unsafe
// after 1 iteration error occures (10/0) and stops
