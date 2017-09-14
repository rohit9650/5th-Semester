import java.util.ArrayList;

abstract class Stack{
	abstract void push(int ele);
	
	abstract int pop();
	
	abstract void display();
	
}

class StaticStack extends Stack{
	final int capacity;
	int size=0;
	int[] array;
	
	StaticStack(int s){
		capacity = s;
		array = new int[capacity];
	}
	
	void push(int ele){
		if(size==capacity)
			System.out.println("Stack Overflow.");
		else	
			array[size++] = ele;
	}
	
	int pop(){
		if(size==0){
			System.out.println("Stack is empty.");
			return -1;	
		}	
		return (array[--size]);
	}
	
	void display(){
		for(int i=0; i<size; i++)
			System.out.print(array[i] + " ");
	}
	
}

class DynamicStack{
	
	ArrayList<Integer> array;
	
	DynamicStack(){
		array = new ArrayList<Integer>();
	}
	
	void push(int s){
		array.add(s);
	}
	
	int pop(){
		if(array.size()==0){
			System.out.println("Stack is empty.");
			return -1;	
		}
		else{
			return (array.remove(array.size()-1));
		}
		
	}
	
	void display(){
		for(int i : array)
			System.out.print(i + " ");
	}
}

public class teststack{
	public static void main(String argv[]){
		StaticStack ss = new StaticStack(5);
		DynamicStack ds = new DynamicStack();
	}
}
