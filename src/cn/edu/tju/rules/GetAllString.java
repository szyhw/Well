package cn.edu.tju.rules;

import java.security.AllPermission;
import java.util.ArrayList;
import java.util.Stack;

public class GetAllString {
	public static String getAllElements(Stack<String> s){
			String result="";
			for (int i = 0; i < s.size(); ++i) {
				result=result+s.get(i);
			}
			s.clear();
			//System.out.println("getelements:	"+result);
			return result;
	}
	public static ArrayList<String> getallstring(String query){
		Stack<String> symbol = new Stack<String>();
		Stack<String> string = new Stack<String>();
		query=query.replaceAll("OPTIONAL", "");
		ArrayList<String> result=new ArrayList<String>();
		int length=query.length();
		int i;
		for(i=0;i!=length;++i){
			char c=query.charAt(i);
			String s=String.valueOf(c);
			if(s.equals("{")){
				symbol.push(s);
			}
			else if(s.equals("}")){
				symbol.pop();
				String r = getAllElements(string);
				if((r.length()!=0)){
					result.add(r.trim());
				}
			}
			else 
			{
					string.push(s);
			}
		}
		//System.out.println("all.size	"+result.size());
		//System.out.println("all:	"+result);
		return result;
	}

}
