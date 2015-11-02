package cn.edu.tju.sparqlresult;

import java.util.ArrayList;
import java.util.HashSet;

public class InvertedMatrix {
	public static ArrayList<ArrayList<String>> invertedmatrix(ArrayList<ArrayList<String>> results){
		//System.out.println("results:   "+results);
		ArrayList<ArrayList<String>> ash=new ArrayList<ArrayList<String>>();
		int sum_variables=results.get(0).size();
		int sum_result=results.size();
		for (int i = 0; i < sum_variables; i++) {
			ArrayList<String> hs=new ArrayList<String>();
			for (int j = 0; j < sum_result; j++) {
				hs.add(results.get(j).get(i));
			}
			ash.add(hs);
		}
		return ash;
	}

}
