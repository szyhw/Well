package cn.edu.tju.sparqlresult;

import java.util.ArrayList;

import cn.edu.tju.rules.Caculate;

public class SparqlResult {
	public static ArrayList<String> get_result(String result){

		ArrayList<String> results = new ArrayList<String>();
		if(!result.contains("empty result")){
		String[] temp=result.split(" ");
		//System.out.println("temp     "+temp);
		for (int i = 0; i < temp.length; i++) {
			results.add(temp[i]);
		}
		
		/*
		int length=result.length();
		for (int i = 0; i < temp.length; i++) {
			String str=temp[i];
				if(!str.contains("\"")){
					results.add(str);
				}
				else if(str.contains("\"")){
					if(Caculate.calculate(str, "\"")%2!=0){
						String collection=str;
						for (int j = i+1; j < length; j++) {
							if(!temp[j].contains("\"")){
								collection=collection+" "+temp[j];
							}
							else{
								collection=collection+" "+temp[j];
								results.add(collection);
								i=j;
								break;
							}
						}
					}
					else{
						results.add(str);
					}
				}
		}*/
		return results;
		}
		else{
			results.clear();
			return results;
		}
	}

}
