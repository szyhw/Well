package cn.edu.tju.constructsubquery;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import cn.edu.tju.prefix.Prefix;

public class ConstructQuery {
	public static String constructquery(String query,String subpattern) throws IOException{
		String prefix=Prefix.find_prefix(query);
		ArrayList<String> variables=Variable.get_variable(subpattern);
		String var="";
		for (int i = 0; i < variables.size(); i++) {
			var=var+variables.get(i)+" ";
		}
		String results="";
		if(subpattern.contains("{")){
			results=prefix+" "+"SELECT "+var+" "+"WHERE "+subpattern;
		}
		else{
			results=prefix+" "+"SELECT "+var+" "+"WHERE  { "+subpattern+" }  ";
		}
	//	FileWriter fileWritter = new FileWriter("/home/szy/subquery",true);
		//System.out.println("subresult:	"+results);
//		fileWritter.write(results.toString()+"\n");
	//	fileWritter.close();
		return results;
	}

}
