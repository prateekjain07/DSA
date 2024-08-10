// package 6.Stacks.CustomStack;

public class User {
    public static void main(String[] args) throws Exception {
        Stack st = new DynamicStack();
        // st.initialize(10); // Protected members can be accessed by Classes in the same folder - flaw but true
        st.push(1);
        System.out.println(st.peek());

    }
}
