public class Example3
{
    public static void main(String[] args)
    {
	int              i;
	int[]            data = {50, 320, 97, 12, 2000};


	for (i=0; i < data.length; i++)
        {
		System.out.println(data[i]);
	    }
    }
}

//before we were checking if the error occures 10 times, now we set the correct condition in the for loop and exception can't occure here
