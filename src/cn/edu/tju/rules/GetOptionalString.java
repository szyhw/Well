package cn.edu.tju.rules;

import java.util.ArrayList;
import java.util.Stack;

public class GetOptionalString {
	public static ArrayList<String> getoptionalstring(String query){
		Stack<String> stack_symbol=new Stack<String>();
		Stack<String> stack_triple=new Stack<String>();
		ArrayList<String> triple_array=new ArrayList<String>();
		ArrayList<String> results=new ArrayList<String>();
		for (int i = 0; i < query.length(); i++) {
			String s=String.valueOf(query.charAt(i));
			//System.out.println("s:	"+s);
			if((s.equals("O"))&&(i<=query.length()-8)){
				String judge=query.toUpperCase().substring(i+1,i+8);
				//System.out.println("judge:	"+judge);
				if(judge.equals("PTIONAL")){
					stack_symbol.push("OPTIONAL");
					i=i+7;
				}
				else{
					if((!stack_symbol.isEmpty())&&(stack_symbol.get(0).equals("OPTIONAL"))){
						stack_triple.push(s);
					}
				}
			}
			else if(s.equals("{")){
				if((!stack_symbol.isEmpty())&&(stack_symbol.get(0).equals("OPTIONAL"))){
					stack_symbol.push(s);
				}
			}
			else if(s.equals("}")){
				if(!stack_symbol.isEmpty()){
				stack_symbol.pop();
				String result=GetAllString.getAllElements(stack_triple).trim();
				if(result.length()>0){
					triple_array.add(result.trim());
				}
				if((!stack_symbol.isEmpty())&&(stack_symbol.peek().equals("OPTIONAL"))){
					stack_symbol.pop();/*
					String re="";
					for (int j = 0; j < triple_array.size(); j++) {
						re=re+triple_array.get(j);
					}
					results.add(re);
					triple_array.clear();*/
				}
				}
			}
			else{
				if((!stack_symbol.isEmpty())&&(stack_symbol.get(0).equals("OPTIONAL"))){
					stack_triple.push(s);
				}
			}
			//System.out.println("symbol:	"+stack_symbol);
			//System.out.println("triple:	"+stack_triple);
			//System.out.println("end");
		}
		return triple_array;
	}

}
