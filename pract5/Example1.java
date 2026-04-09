public class Example1
{
    public static void main(String[] args)
    {
	int     denominator, numerator, ratio = 0;


	numerator   = 5;
	denominator = 0;

	try {
		ratio = numerator / denominator;
		System.out.println("The answer is: "+ratio);
	}
	catch(ArithmeticException e){
		System.out.println("Divide by 0.");
		e.printStackTrace();
	}
	

	System.out.println("Done."); // Don't move this line
    }
}

//after we changed the denominator to 0 we got a runtime exception java.lang.ArithmeticException
//variable ratio might not have been initialized
// Now we get Divide by 0 and Done because error occures before The answer is: and it sends us to the catch immediately

// Divide by 0.
// java.lang.ArithmeticException: / by zero
//         at Example1.main(Example1.java:12)
// Done.

//yes it executes properly and show exception and it's reason
