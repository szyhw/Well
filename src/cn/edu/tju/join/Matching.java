package cn.edu.tju.join;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Matching {
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
		
	}

}
