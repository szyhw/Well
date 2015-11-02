package cn.edu.tju.sparqlresult;

import java.util.ArrayList;

public class GetSparqlResults {
	public static ArrayList<ArrayList<String>> getsparqlresults(String answer){
		String[] result=answer.split("\n");
		ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();
		for (int i = 0; i < result.length; i++) {
			ArrayList<String> temp =  new ArrayList<String>();
			String s = result[i];
			temp=SparqlResult.get_result(s);
			results.add(temp);
		}
		//System.out.println(result.length+"	"+results);
		return results;
	}

}
