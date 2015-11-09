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
		String filePath = "/home/szy/data/test-600";
		String query=null;
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		GstoreConnector gc = new GstoreConnector("127.0.0.1", 3305);
		gc.load("LUBM10");
		fileReader = new FileReader(filePath);
		bufferedReader = new BufferedReader( fileReader);
		int count=0;
	//	FileWriter fileWritter = new FileWriter("/home/szy/exp/result-100",true);
		FileWriter countWriter = new FileWriter("/home/szy/exp/count-600",true);
		long time=0;
		while ((query=bufferedReader.readLine()) != null) {
			String where=Where.find_where(query);
			String results=GetResults.getresults(where);
			//System.out.println(results);
			long time1=System.currentTimeMillis();  
			TreeNode Root=OptTree.construcTree(results);
			Stack<HashMap<String, ArrayList<String>>> stack = new Stack<HashMap<String, ArrayList<String>>>();
			Stack<HashMap<String, ArrayList<String>>> line = AssemblyQuery.postOrder(Root, query, stack,gc);
			//bufferWritter.write(++count+"			"+line+"\n");
			//System.out.println(++count);
			//System.out.println(line);
			long time2=System.currentTimeMillis(); 
			long interval=time2-time1;  
			time=time+interval;
			//fileWritter.write(++count+"			"+line+"\n");
			countWriter.write(Matching.GetLength(line.get(0))+"\n");
		//	System.out.println();
			//System.out.println(++count);
			//fileWritter.flush();
			countWriter.flush();
			}
			countWriter.close();
			//fileWritter.close();
				//System.out.println("end");
				System.out.println("sum_time"+time);
				FileReader fileReader_time = new FileReader("/home/szy/exp/time-600");
				BufferedReader bufferedReader_time = new BufferedReader(fileReader_time);
				String time_line=null;
				int sum=0;
				while ((time_line=bufferedReader_time.readLine()) != null) {
							int t=Integer.parseInt(time_line);
							sum=sum+t;
				}
				System.out.println("gstore_time:"+sum);
				FileReader fileReader_count = new FileReader("/home/szy/exp/count-600");
				BufferedReader bufferedReader_count = new BufferedReader(fileReader_count);
				String count_line=null;
				int s=0;
				while ((count_line=bufferedReader_count.readLine()) != null) {
							int t=Integer.parseInt(count_line);
							s=s+t;
							
				} 
				System.out.println("count:	"+s);
	}

}
