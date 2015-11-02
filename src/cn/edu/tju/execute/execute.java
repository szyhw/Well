package cn.edu.tju.execute;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.WrappedPlainView;

import jgsc.GstoreConnector;
import cn.edu.tju.constructsubquery.ConstructQuery;
import cn.edu.tju.constructsubquery.Variable;
import cn.edu.tju.join.Matching;
import cn.edu.tju.prefix.Prefix;
import cn.edu.tju.rules.ConvertRules;
import cn.edu.tju.rules.GetAllString;
import cn.edu.tju.rules.GetAndString;
import cn.edu.tju.rules.GetOptionalString;
import cn.edu.tju.rules.GetResults;
import cn.edu.tju.sparqlresult.InvertedMatrix;
import cn.edu.tju.sparqlresult.GetSparqlResults;
import cn.edu.tju.sparqlresult.SparqlResult;
import cn.edu.tju.tree.AssemblyQuery;
import cn.edu.tju.tree.OptTree;
import cn.edu.tju.tree.TreeNode;
import cn.edu.tju.where.Where;

public class execute {
	public static void main(String[] args) throws IOException {
		/*
		String query = "PREFIX owl:<http://www.w3.org/2002/07/owl#>   SELECT ?x ?y ?z WHERE{	{?x <rdf:type> ?y. }		OPTIONAL {?x <rdf:isstuden> ?z. }	}";
		String prefix=Prefix.find_prefix(query);
		String where=Where.find_where(query);
		GstoreConnector gc = new GstoreConnector("127.0.0.1", 3305);
		gc.build("try", "example/test");
		String results=GetResults.getresults(where);
		TreeNode Root=OptTree.construcTree(results);
		Stack<HashMap<String, ArrayList<String>>> stack = new Stack<HashMap<String, ArrayList<String>>>();
		Stack<HashMap<String, ArrayList<String>>> line = AssemblyQuery.postOrder(Root, query, stack,gc);
		System.out.println(line);*/
		String filePath = "/home/szy/test-600";
		//String query="PREFIX owl:<http://www.w3.org/2002/07/owl#> SELECT ?x ?y WHERE{	{?x <rdf:type> ?y. } OPTIONAL {?x <rdf:isstudent> ?z}  	 }";
		String query=null;
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		GstoreConnector gc = new GstoreConnector("127.0.0.1", 3305);
		//gc.build("geo-final", "example/geo-final");
		//gc.load("geo-final");
		gc.load("LUBM10");
		fileReader = new FileReader(filePath);
		bufferedReader = new BufferedReader(fileReader);
		int count=0;
		//FileWriter fileWritter = new FileWriter("/home/szy/result-100",true);
		//BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
		long time=0;
		while ((query=bufferedReader.readLine()) != null) {
			//System.out.println(query);
			//String prefix=Prefix.find_prefix(query);
			String where=Where.find_where(query);
			String results=GetResults.getresults(where);
			//System.out.println(results);
			long time1=System.currentTimeMillis();  
			TreeNode Root=OptTree.construcTree(results);
			Stack<HashMap<String, ArrayList<String>>> stack = new Stack<HashMap<String, ArrayList<String>>>();
			Stack<HashMap<String, ArrayList<String>>> line = AssemblyQuery.postOrder(Root, query, stack,gc);
			//bufferWritter.write(++count+"			"+line+"\n");
			//System.out.println(count);
			long time2=System.currentTimeMillis(); 
			long interval=time2-time1;  
			time=time+interval;
			//fileWritter.write(++count+"			"+line+"\n");
			//System.out.println(count);
			//fileWritter.flush();
			}
				//bufferWritter.close();	
			//	fileWritter.close();
				//System.out.println("end");
				System.out.println(time);
	}

}
