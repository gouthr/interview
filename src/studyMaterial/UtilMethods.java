// Utils methods

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Codechef
{
	public static void main (String[] args) throws java.lang.Exception
	{
	    // Display utils
		String[] arr = {"Spiderman", "Hulk", "Batman", "Aquaman"};
		List<String> list = Arrays.asList("Robin", "Wonderwoman", "Superman");
		
		int[] nums = {1, 2, 3, 4, 5};
		List<Integer> numsList = Arrays.asList(6, 7, 8, 9, 10);
		
		Map<String, Integer> map = new HashMap<>();
		map.put("a", 1);
		map.put("b", 2);
		map.put("c", 3);
		map.put("d", 4);
		map.put("e", 5);
		
		System.out.println(Arrays.toString(arr));
		System.out.println(list);
		
		System.out.println(Arrays.toString(nums));
		System.out.println(numsList);
		
		System.out.println(map);
		
		// Convertion utils
		String[] strArr = {"one", "two", "three"};
		List<String> strList = Arrays.asList(strArr);
		System.out.println(Arrays.toString(strArr));
		System.out.println(strList);
		
		List<String> strList2 = Arrays.asList("four", "five", "six");
		String[] strArr2 = strList2.toArray(new String[0]);
		System.out.println(Arrays.toString(strArr2));
		
		int[] numsArr = {11, 12, 13 , 14};
		// List<Integer> numsList2 = Arrays.asList(numsArr); // This fails - compiler error
		List<Integer> numsList3 = Arrays.asList(11, 12, 13, 14, 15);
		Integer[] convertedArr = numsList3.toArray(new Integer[0]);
		System.out.println(Arrays.toString(convertedArr));
		List<Integer> convertedList = Arrays.stream(numsArr).boxed().toList();
		System.out.println(convertedList);
		
		int[] convertedArr2 = numsList3.stream().mapToInt(i->i).toArray();
		System.out.println(Arrays.toString(convertedArr2));
		
	}
}
