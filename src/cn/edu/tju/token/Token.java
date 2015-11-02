package cn.edu.tju.token;

import java.util.ArrayList;

public class Token {
	public static ArrayList<String> GetToken(String string){
		String[] fragment= string.split("OPTIONAL");
	       int sum=fragment.length;
	       int i,j,k;
	       ArrayList<String> token = new ArrayList<String>();
	       for(i=0;i!=sum;++i){
	    	   String subString=fragment[i];
	    	   int count_dot=0;
	    	   int count_symbol=0;
	    	   for(k=0;k!=subString.length();++k){
	    		   if(subString.charAt(k)=='.'){
	    			   ++count_dot;
	    		   }
	    		   if(subString.charAt(k)=='{'){
	    			   ++count_symbol;
	    		   }
	    	   }
	    	   //System.out.println("hello"+count_dot+"  "+count_symbol);
	    	   //if(count_dot==count_symbol){
	    		   subString=subString.replace("{", "");
	    		   subString=subString.replace("}", "");
	    	//   }
			   String s="";
	    	   for(j=0;j!=subString.length();++j){
	    		   String t=String.valueOf(subString.charAt(j));
	    		  // System.out.println(t);
	    		   if((t.equals("{"))||(t.equals("}"))||(t.equals("("))||(t.equals(")"))||(j==subString.length()-1)){
	    			   if((!s.isEmpty())&&(!s.equals(" "))){
	    				   token.add(s);
	    				   s="";
	    			   }
	    			   token.add(t);
	    		   }
	    		   else {
					s=s+t;
				}
	    	   }
	    	   if(i!=sum-1){
	    		   token.add("OPTIONAL");
	    	   }
	       }
	      // System.out.println(token);
	       return token;
		}
}
