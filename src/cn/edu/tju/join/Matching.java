package cn.edu.tju.join;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Matching {
	public static int GetLength(HashMap<String, ArrayList<String>> map){;
	Iterator it =map.entrySet().iterator();
	ArrayList<String> value = new ArrayList<String>();
	while(it.hasNext()){
	    Map.Entry entry = (Map.Entry) it.next();
	    value = (ArrayList<String>) entry.getValue();
	    break;
	}
	return value.size();
}
public static ArrayList<String> GetIntersection(HashMap<String, ArrayList<String>> left,HashMap<String, ArrayList<String>> right){
	ArrayList<String> left_arr=new ArrayList<String>();
	ArrayList<String> right_arr=new ArrayList<String>();
	Iterator it =left.entrySet().iterator();
	while(it.hasNext()){
	    Map.Entry entry = (Map.Entry) it.next();
	    String left_key = (String) entry.getKey();
	    left_arr.add(left_key);
	}
	Iterator ita =right.entrySet().iterator();
	while(ita.hasNext()){
	    Map.Entry entry = (Map.Entry) ita.next();
	    String right_key = (String) entry.getKey();
	    right_arr.add(right_key);
	}
	left_arr.retainAll(right_arr);
	return left_arr;
}
public static ArrayList<String> GetUnion(HashMap<String, ArrayList<String>> left,HashMap<String, ArrayList<String>> right){
	ArrayList<String> left_arr=new ArrayList<String>();
	ArrayList<String> right_arr=new ArrayList<String>();
	Iterator it =left.entrySet().iterator();
	while(it.hasNext()){
	    Map.Entry entry = (Map.Entry) it.next();
	    String left_key = (String) entry.getKey();
	    left_arr.add(left_key);
	}
	Iterator ita =right.entrySet().iterator();
	while(ita.hasNext()){
	    Map.Entry entry = (Map.Entry) ita.next();
	    String right_key = (String) entry.getKey();
	    right_arr.add(right_key);
	}
	left_arr.removeAll(right_arr);
	left_arr.addAll(right_arr);
	Collections.sort(left_arr);
	return left_arr;
}
public static ArrayList<String> GetSpecialElements(HashMap<String, ArrayList<String>> map,ArrayList<String> intersection,int i){
	ArrayList<String> elements=new ArrayList<String>();
	Iterator it =map.entrySet().iterator();
	while(it.hasNext()){
	    Map.Entry entry = (Map.Entry) it.next();
	    String key = (String) entry.getKey();
	    if (intersection.contains(key)) {
			ArrayList<String> value=(ArrayList<String>) entry.getValue();
			String element=value.get(i);
			elements.add(element);
		}
	}
	return elements;
}
public static ArrayList<String> GetAllElements(HashMap<String, ArrayList<String>> map,int i){
	ArrayList<String> allelements=new ArrayList<String>();
	Iterator it =map.entrySet().iterator();
	while(it.hasNext()){
	    Map.Entry entry = (Map.Entry) it.next();
	    String key = (String) entry.getKey();
		ArrayList<String> value=(ArrayList<String>) entry.getValue();
		String element=value.get(i);
		allelements.add(element);
	}
	return allelements;
}
public static ArrayList<Integer> GetVariablePosition(HashMap<String, ArrayList<String>>  map,ArrayList<String> union){
	ArrayList<Integer> pos=new ArrayList<Integer>();
	Iterator it =map.entrySet().iterator();
	while(it.hasNext()){
	    Map.Entry entry = (Map.Entry) it.next();
	    String key = (String) entry.getKey();
	    int p= union.indexOf(key);
	    pos.add(p);
	}
	return pos;
}
public static ArrayList<String> ConstructSubResult(ArrayList<String> left, ArrayList<Integer> leftpos,ArrayList<String> right, ArrayList<Integer> rightpos){
	ArrayList<String> subresult=new ArrayList<String>();
	int i=0;
	int j=0;
    while(i<leftpos.size() && j<rightpos.size())  
    {  
        if(leftpos.get(i)<rightpos.get(j)){  
        	subresult.add(left.get(i++));  
        }
        else if(leftpos.get(i)==rightpos.get(j)){
        	subresult.add(left.get(i++));  
        	j++;
        }
        else{  
        	subresult.add(right.get(j++));  
        }
    }  
    while(i<leftpos.size()) {
    	subresult.add(left.get(i++));  
    }
    while(j<rightpos.size()){  
    	subresult.add(right.get(j++));  
    }
    //System.out.println("subresult:"+subresult);
	return subresult;
}
public static HashMap<String, ArrayList<String>> InsertIntoResult(ArrayList<String> allelements,HashMap<String, ArrayList<String>> result,ArrayList<String> union){
	int j=0;
	for (int i = 0; i < union.size(); i++) {
		ArrayList<String> value = result.get(union.get(i));
		value.add(allelements.get(i));
		result.put(union.get(i), value);
	}
	return result;
}
public static ArrayList<String> ConstructRightNull(HashMap<String, ArrayList<String>> right){
	ArrayList<String> rightnull=new ArrayList<String>();
	int count=right.size();
	for(int i=0;i!=count;++i){
		rightnull.add(" ");
	}
	return rightnull;
}
public static HashMap<String, ArrayList<String>> matching(HashMap<String, ArrayList<String>> left,HashMap<String, ArrayList<String>> right){
	ArrayList<String> intersection=GetIntersection(left, right);
	//System.out.println("intersection"+intersection);
	if(!intersection.isEmpty()){
		ArrayList<String> union=GetUnion(left, right);
		HashMap<String, ArrayList<String>> result=new HashMap<String,ArrayList<String>>();
		for (int i = 0; i < union.size(); i++) {
			result.put(union.get(i),new ArrayList<String>());
		}
		int i;
		int j;
		ArrayList<Integer> leftpos=GetVariablePosition(left, union);
		ArrayList<Integer> rightpos=GetVariablePosition(right, union);
		for (i = 0; i < GetLength(left); ++i) {
			ArrayList<String> lvalue=GetSpecialElements(left,intersection,i);
			boolean tag=false;
			for(j=0;j< GetLength(right);++j){
				ArrayList<String> rvalue=GetSpecialElements(right, intersection, j);
				if(lvalue.equals(rvalue)){
					ArrayList<String> leftallvalue=GetAllElements(left, i);
					ArrayList<String> rightallvalue=GetAllElements(right, j);
					ArrayList<String> subresult=ConstructSubResult(leftallvalue, leftpos, rightallvalue, rightpos);
					result=InsertIntoResult(subresult, result,union);
					tag=true;
				}
			}
			if(tag==false){
			ArrayList<String> leftallvalue=GetAllElements(left, i);
			ArrayList<String> rightnull=ConstructRightNull(right);
			ArrayList<String> subresult=ConstructSubResult(leftallvalue, leftpos, rightnull, rightpos);
			result=InsertIntoResult(subresult, result,union);
			}
		}
		return result;
	}
	else{
		return left;
	}
}
	
	
	
	
	/*
	public static int Count(ArrayList<String> right_value,String element){
		int count=0;
		for(int i=0;i!=right_value.size();++i){
			if(right_value.get(i).equals(element)){
				++count;
			}
		}
		return count;
	}
	public static HashMap<String, ArrayList<String>>  Duplication(HashMap<String, ArrayList<String>> map,int i,int count){
		Iterator it =map.entrySet().iterator();
		int j=0;
		while(it.hasNext()){//�������
		    Map.Entry entry = (Map.Entry) it.next();
		    String map_key = (String) entry.getKey();
		    ArrayList<String> map_value = (ArrayList<String>) entry.getValue();
		    String element=map_value.get(i);
		  // System.out.println("i"+i);
		    //System.out.print("beforesize:"+map_value.size());
		    for(j=0;j!=count;++j){
		    	map_value.add(i,element );
		    	//System.out.print("insert!");
		    }
		   // System.out.print("aftersize:"+map_value.size());
		}
		return map;
	}
	public static ArrayList<Integer> GetPosition(HashMap<String, ArrayList<String>> left,HashMap<String, ArrayList<String>> right){
		Iterator it =left.entrySet().iterator();
		int i=0;
		ArrayList<Integer> position = new ArrayList<Integer>();
		while(it.hasNext()){//�������
		    Map.Entry entry = (Map.Entry) it.next();
		    String left_key = (String) entry.getKey();
		    ArrayList<String> left_value = (ArrayList<String>) entry.getValue();
		    if(right.containsKey(left_key)){//�������ƥ��
		    	ArrayList<String> right_value=right.get(left_key);
		    	int length=left_value.size();
		    	for (i = 0; i < length; i++) {
		    		String element = left_value.get(i);
		    		int sum_left=Count(left_value, element);
		    		int sum_right=Count(right_value, element);
		    		//System.out.println("left:"+sum_left+"right:"+sum_right);
		    		if((sum_right>sum_left)&&sum_left!=0){
		    			left=Duplication(left, i,sum_right-sum_left);
		    			//System.out.println("left");
		    			i=i+sum_right-sum_left;
		    			length=length+sum_right-sum_left;
		    		}
		    		else if((sum_right<sum_left)&&sum_right!=0){
		    			right=Duplication(right, 0,sum_left-sum_right);
		    			//System.out.println("right");
		    			i=i+sum_left-sum_right;
		    		}
				}
		    	int count=right_value.size();
		    	int[] tag=new int[count];
		    	for(i=0;i<left_value.size();++i){
		    		String element = left_value.get(i);
		    		int pos=right_value.indexOf(element);
		    		position.add(pos);
		    		if(pos!=-1){
		    			right_value.set(pos, "*");
		    		}
		    	}
			    break;
		    }
		}
		return position;
	}
	public static ArrayList<String> ChangeSequence(ArrayList<String> right_value,ArrayList<Integer> position){
		ArrayList<String> result = new ArrayList<String>();
		for (int i = 0; i < position.size(); i++) {
			int pos=position.get(i);
			if(pos!=-1){
				result.add(right_value.get(pos));
			}
			else{
				result.add(" ");
			}
		}
		return result;
	}
	public static HashMap<String, ArrayList<String>> FinalRight(HashMap<String, ArrayList<String>> right,ArrayList<Integer> position){
		Iterator it =right.entrySet().iterator();
		HashMap<String, ArrayList<String>> finalright =  new HashMap<String, ArrayList<String>>();
		while(it.hasNext()){//�����ұ�
		    Map.Entry entry = (Map.Entry) it.next();
		    String right_key = (String) entry.getKey();
		    ArrayList<String> right_value = (ArrayList<String>) entry.getValue();
		    ArrayList<String> value_new = ChangeSequence(right_value, position);
		    finalright.put(right_key, value_new);
		}
		return finalright;
	}
	public static HashMap<String, ArrayList<String>> DeleteSet(HashMap<String, ArrayList<String>> left,HashMap<String, ArrayList<String>> right){
		Iterator it =left.entrySet().iterator();
		while(it.hasNext()){//�������
		    Map.Entry entry = (Map.Entry) it.next();
		    String left_key = (String) entry.getKey();
		    ArrayList<String> left_value = (ArrayList<String>) entry.getValue();
		    if (right.containsKey(left_key)) {
				right.remove(left_key);
			}
		}
		return right;
	}
	public static HashMap<String, ArrayList<String>> matching(HashMap<String, ArrayList<String>> left,HashMap<String, ArrayList<String>> right){
		ArrayList<Integer> position = GetPosition(left, right);
		right=FinalRight(right, position);
		right=DeleteSet(left, right);
		left.putAll(right);
		return left;
		
	}*/
	
	/*
	public static ArrayList<Integer> GetPosition(HashMap<String, ArrayList<String>> left,HashMap<String, ArrayList<String>> right){
		Iterator it =left.entrySet().iterator();
		ArrayList<Integer> position = new ArrayList<Integer>();
		while(it.hasNext()){//�������
		    Map.Entry entry = (Map.Entry) it.next();
		    String left_key = (String) entry.getKey();
		    ArrayList<String> left_value = (ArrayList<String>) entry.getValue();
		   // System.out.println(left_key+"	"+left_value);
		    if(right.containsKey(left_key)){//�������ƥ��
		    	//System.out.println(right.get(left_key));
		    	ArrayList<String> right_value=right.get(left_key);
		    	for (int i = 0; i < left_value.size(); i++) {
					//System.out.println(left_value.get(i));
					//System.out.println(right_value.indexOf(left_value.get(i)));
		    		String element = left_value.get(i);
		    		int pos = right_value.indexOf(element);
		    		position.add(pos);
				}
			    break;
		    }

		}
		return position;
	}
	public static ArrayList<String> ChangeSequence(ArrayList<String> right_value,ArrayList<Integer> position){
		ArrayList<String> result = new ArrayList<String>();
		for (int i = 0; i < position.size(); i++) {
			int pos=position.get(i);
			if(pos!=-1){
				result.add(right_value.get(pos));
			}
			else{
				result.add(" ");
			}
		}
		return result;
	}
	public static HashMap<String, ArrayList<String>> FinalRight(HashMap<String, ArrayList<String>> right,ArrayList<Integer> position){
		Iterator it =right.entrySet().iterator();
		HashMap<String, ArrayList<String>> finalright =  new HashMap<String, ArrayList<String>>();
		while(it.hasNext()){//�����ұ�
		    Map.Entry entry = (Map.Entry) it.next();
		    String right_key = (String) entry.getKey();
		    ArrayList<String> right_value = (ArrayList<String>) entry.getValue();
		    ArrayList<String> value_new = ChangeSequence(right_value, position);
		    finalright.put(right_key, value_new);
		}
		return finalright;
	}
	public static HashMap<String, ArrayList<String>> DeleteSet(HashMap<String, ArrayList<String>> left,HashMap<String, ArrayList<String>> right){
		Iterator it =left.entrySet().iterator();
		while(it.hasNext()){//�������
		    Map.Entry entry = (Map.Entry) it.next();
		    String left_key = (String) entry.getKey();
		    ArrayList<String> left_value = (ArrayList<String>) entry.getValue();
		    if (right.containsKey(left_key)) {
				right.remove(left_key);
			}
		}
		return right;
	}
	public static HashMap<String, ArrayList<String>> matching(HashMap<String, ArrayList<String>> left,HashMap<String, ArrayList<String>> right){
		ArrayList<Integer> position = GetPosition(left, right);
		right=FinalRight(right, position);
		right=DeleteSet(left, right);
		left.putAll(right);
		//System.out.println(left);
		return left;
		
	}*/

}
