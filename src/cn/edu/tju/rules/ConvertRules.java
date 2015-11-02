package cn.edu.tju.rules;

import java.util.ArrayList;

public class ConvertRules {
	public static String GetRules(ArrayList<String> and,ArrayList<String> opt){
		String result="{";
		for (int i = 0; i < and.size(); i++) {
			result=result+and.get(i)+" ";
		}
		result=result+"}";
		for (int i = 0; i < opt.size(); i++) {
			//System.out.println(opt.get(i));
			result=result+" OPTIONAL { "+opt.get(i)+" } ";
		}
		return result;
	}

}
