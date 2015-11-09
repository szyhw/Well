package cn.edu.tju.tree;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import jgsc.GstoreConnector;
import cn.edu.tju.constructsubquery.ConstructQuery;
import cn.edu.tju.join.ConstructMap;
import cn.edu.tju.join.Matching;
import cn.edu.tju.sparqlresult.GetSparqlResults;
import cn.edu.tju.sparqlresult.SparqlResultSet;

public class AssemblyQuery {
	public static Stack<HashMap<String, ArrayList<String>>> postOrder(TreeNode currNode,String query,Stack<HashMap<String, ArrayList<String>>> stack,GstoreConnector gc) throws IOException{//�������
		if(currNode != null){
			postOrder(currNode.left,query,stack,gc);
			postOrder(currNode.right,query,stack,gc);
			//System.out.print(currNode.data+"  ");
			if(!currNode.data.equals("OPTIONAL")){
				String subquery = ConstructQuery.constructquery(query, currNode.data);
				//get result from gstore,asume it as answer
				long time1=System.currentTimeMillis(); 
				String answer=gc.query(subquery);
				long time2=System.currentTimeMillis();
				long interval=time2-time1;
				FileWriter fileWritter = new FileWriter("/home/szy/exp/time-600",true);
				fileWritter.write(interval+"\n");
				fileWritter.flush();
				//System.out.println("	answer:	"+answer);
				if((answer.length()>1)){
					ArrayList<ArrayList<String>> result = GetSparqlResults.getsparqlresults(answer);
					ArrayList<ArrayList<String>> sparqlresult = SparqlResultSet.sparqlresultset(result);
					HashMap<String, ArrayList<String>> map = ConstructMap.constructmap(subquery,sparqlresult);
					stack.push(map);
				}
			}
			else{
				HashMap<String, ArrayList<String>> right = stack.pop();
				HashMap<String, ArrayList<String>> left = stack.pop();
				HashMap<String, ArrayList<String>>	match_result = Matching.matching(left,right);
				stack.push(match_result);
			}
		}
		return stack;
	}

}
