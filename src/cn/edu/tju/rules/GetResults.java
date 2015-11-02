package cn.edu.tju.rules;

import java.util.ArrayList;

public class GetResults {
	public static String getresults(String query){
		ArrayList<String> all=GetAllString.getallstring(query);
		//System.out.println("all:	"+all.get(0));
		ArrayList<String> opt=GetOptionalString.getoptionalstring(query);
		//System.out.println("opt:	"+opt);
		ArrayList<String> and=GetAndString.getandstring(all,opt);
		//System.out.println("and:	"+and);
		String results=ConvertRules.GetRules(and,opt);
		return results;
	}
	
}
