import java.util.Arrays;

public class ArraySort {

	public static void main(String [] args){
		
		int [] testArray={3,9,50,15,99,7,98,65};
		String [] stringArray={"Java","Cat"};
		
		distClosest(stringArray);
		
		for (String i:stringArray){
			System.out.println(i);
		}
	
	}
	
	static String [] distClosest(String [] tArray){
	
	Arrays.sort(tArray);
		
		return tArray;
		
	}
	
}
