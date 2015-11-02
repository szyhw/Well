package cn.edu.tju.rules;

public class Caculate {
	public static int calculate(String str, String substr) {
        String temp = str;
        int count = 0;
        int index = temp.indexOf(substr);
        while (index != -1) {
            temp = temp.substring(index + 1);
            index = temp.indexOf(substr);
            count++;
        }
        return count;
    }
}
