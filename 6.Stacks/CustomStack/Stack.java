// package Stacks.CustomStack;
// class StackOverFlowException extends Exception {
//     StackOverFlowException() {
//         super("Stack Full");
//     }
// }
public class Stack {
    private int[] arr;
    private int tos; //top of stack
    private int NoOfElements;    
    private int maxCapacity; 
    private final static int DEFAULT_SIZE = 15;
    
    Stack(int size) {
        initialize(size);
    }

    Stack() {
        this(DEFAULT_SIZE);
    }

    public int size() {
        return this.NoOfElements;
    }

    public int capacity() {
        return this.maxCapacity;
    }

    protected void initialize(int size) {
        this.NoOfElements = 0;
        this.maxCapacity = size;
        this.tos = -1;
        this.arr = new int[size];
    }

    private void overflowException() throws Exception{
        if(this.NoOfElements == this.maxCapacity){
            throw new Exception("StackIsOverflow");
        }
    }

    private void underflowException() throws Exception{
        if(this.NoOfElements == 0){
            throw new Exception("StackIsUnderflow");
        }
    }


    public void push(int element) throws Exception {
        this.overflowException();
        this.arr[++this.tos] = element;
        this.NoOfElements++;
    }

    public int pop() throws Exception {
        this.underflowException();
        this.NoOfElements--;
        return this.arr[this.tos--];
    }

    public int peek() throws Exception {
        this.underflowException();
        return this.arr[this.tos];
    }


}
