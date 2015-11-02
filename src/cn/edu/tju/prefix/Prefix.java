package cn.edu.tju.prefix;

public class Prefix {
	public static String find_prefix(String query){
		String str_standard=query.toUpperCase();
		int start;
		int end;
		start=str_standard.indexOf("PREFIX");
		end=str_standard.indexOf("SELECT");
		if(start!=-1){
			return query.substring(start, end);
		}
		else{
			return "";
		}
	}

}
