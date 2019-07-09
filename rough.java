package com.bofa.bofalogin;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class rough {
	public static void main(String[] args) throws IOException {
		String d = "123456789";
		char[] c = {'1','2','3','4','5','6','7','8','9','0',};
		 System.out.println(c);
		System.out.println(d.substring(4,6));
		 System.out.println(d.charAt(3));
		 System.out.println(d.compareTo("test"));
		 d.getChars(4,8,c, 1);
		 d.subSequence(4, 6);
		 char[] r = d.toCharArray();
		 String s = "";
		 for(char t:r) {
			s  = t+s;
			System.out.println(s);
		 }
		 System.out.println(s);
		 System.out.println(s);
		 System.out.println();
		 
	  }
	
	}
	

