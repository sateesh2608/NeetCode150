package com.neetcode150.arrays_and_hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q8EncodeAndDecodeString {

	public static void main(String[] args) {

		List<String> s = List.of("Hello","World");
		String encodedVal = encode(s);
		System.out.println("Before Encoding Value is: "+s.toString());
		System.out.println("Encoded Value is: "+encodedVal);
		System.out.println("After Encoding Value is: "+decode(encodedVal));		
		
	}
	
	 // Encodes a list of strings to a single string.
    public static String encode(List<String> strs) {
    	
    	if(strs.size() == 0)
    		return Character.toString((char)258);
    	
    	String separate = Character.toString((char)257);
    	StringBuilder sb= new StringBuilder();
    	
    	for (String string : strs) {
			sb.append(string);
			sb.append(separate);
		}
    	
    	sb.deleteCharAt(sb.length()-1);
    	
    	return sb.toString();
    }
    
    // Decodes a single string to a list of strings.
    public static List<String> decode(String s) {
    	
    	if(s.equals(Character.toString((char)258)))
    		return new ArrayList<String>();
    	
    	String separate = Character.toString((char)257);
  
      	return Arrays.asList(s.split(separate));
    }

}
