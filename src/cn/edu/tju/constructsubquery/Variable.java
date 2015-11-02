package cn.edu.tju.constructsubquery;

import java.util.ArrayList;

public class Variable {
	public static ArrayList<String> get_variable(String pattern){
		String[] subpattern=pattern.split(" ");
		ArrayList<String> variables=new ArrayList<String>();
		for (int i = 0; i < subpattern.length; i++) {
			String s=subpattern[i].trim();
			if(s.startsWith("?")){
				if(s.endsWith(".")){
					s=s.substring(0, s.length()-1);
				}
				if(!variables.contains(s)){
					variables.add(s);
				}
			}
		}
		return variables;
	}

}
