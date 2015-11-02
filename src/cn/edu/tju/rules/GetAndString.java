package cn.edu.tju.rules;

import java.util.ArrayList;

public class GetAndString {
	public static ArrayList<String> getandstring(ArrayList<String> all,ArrayList<String> opt){
		ArrayList<String> result=new ArrayList<String>();
		ArrayList<String> copy=(ArrayList<String>) opt.clone();
		int opt_length=copy.size();
		int all_length=all.size();
		int i,j,k;
		for(i=0;i!=all_length;++i){
			for(j=0;j!=opt_length;++j){
			if((all.get(i).equals(copy.get(j)))&&(!copy.get(j).equals("NULL"))){
				all.set(i, "NULL");
				copy.set(j, "NULL");
				}
			}
		}
		for(k=0;k!=all_length;++k){
			if(!all.get(k).equals("NULL")){
				result.add(all.get(k).trim());
			}
		}
		return result;
	}

}
