package cn.edu.tju.sparqlresult;

import java.util.ArrayList;
import java.util.HashSet;

public class SparqlResultSet {
	public static ArrayList<ArrayList<String>> sparqlresultset(ArrayList<ArrayList<String>> results){
		ArrayList<ArrayList<String>> ahs = new ArrayList<ArrayList<String>>();
		if(!results.get(0).isEmpty()){
			ahs=InvertedMatrix.invertedmatrix(results);
		}
		else{
			ahs.clear();
		}
		return ahs;
	}

}
