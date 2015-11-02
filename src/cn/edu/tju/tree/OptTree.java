package cn.edu.tju.tree;

import java.util.ArrayList;

import cn.edu.tju.token.Token;

public class OptTree {
static TreeNode root = null;
	
	 static boolean hasOper(String checkOper){
		if(checkOper.indexOf("OPTIONAL") == -1){
			return false;
		}
		else{
			return true;
		}
	}
	
	static TreeNode buildTree(String expression){
		if(expression.indexOf("{") == 0){
			for(int i = 0;i < expression.length();i++){
				if(expression.startsWith("{") == true){
					if(expression.endsWith("}") == true){
						expression = expression.substring(1,expression.length() - 1);
					}
					else{
						/*
						 * 抛出异常   括号不比配
						 */
					}
					
				}
			}
		}
	    ArrayList<String> expre = new ArrayList<String>();
	    expre=Token.GetToken(expression);
		TreeNode newNode = new TreeNode();
		String leftString = new String();
		String rightString = new String();
		String stack = new String();             
		if(hasOper(expression) == true){
			int index = 0;    
			for(int i = expre.size()-1;i>=0;i--){
				if(expre.get(i).equals("}")){
					stack = stack + expre.get(i);
				}
				else if(expre.get(i).equals("{")){
					if(stack.length() > 0){
						stack = stack.substring(0, stack.length() - 1);
					}
				}
				else if(expre.get(i).equals("OPTIONAL")&&stack.length() == 0){//||expre.charAt(i) == '-'&&stack.length() == 0){
					index = i;
					break;
				}
			}
			if(stack.length() != 0){
				/*
				 * 抛出异常    括号不匹配
				 */
			}
			int separator;
			if(index != 0){            
				separator = index;
			}
			else{
				if(expre.contains("{")){
					separator = 1;
				}
				else{
					separator=0;
				}
			}
			newNode.data = expre.get(separator);
			int pos = 0;
			for(;pos < separator;pos++){
				leftString = leftString + expre.get(pos);
			}
			pos++;
			for(;pos < expre.size();pos++){
				rightString = rightString + expre.get(pos);
			}
			if(root == null){
				root = newNode;
			}
			newNode.left = buildTree(leftString);
			newNode.right = buildTree(rightString);
		}
		else{
			String temp = expression;
			newNode.data = temp;
		}
		return newNode;
	}
	
	public static void postOrder(TreeNode currNode){//后序遍历
		if(currNode != null){
			postOrder(currNode.left);
			postOrder(currNode.right);
			System.out.print(currNode.data+"  ");
			
		}
	}
	public static void preOrder(TreeNode currNode){//先序遍历
		if(currNode != null){
			System.out.print(currNode.data+"  ");
			preOrder(currNode.left);
			preOrder(currNode.right);
		}
	}
	public static void inOrder(TreeNode currNode){//中序遍历
		if(currNode != null){
			inOrder(currNode.left);
			System.out.print(currNode.data+"  ");
			inOrder(currNode.right);
		}
	}
	public static TreeNode construcTree(String str){
	       TreeNode Root = null;
	       Root = buildTree(str);
	       return Root;
	}
}
