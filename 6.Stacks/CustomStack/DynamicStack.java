// package 6.Stacks.CustomStack;

public class DynamicStack extends Stack{
    DynamicStack() {
        super();
    }

    DynamicStack(int size){
        super(size);
    }

    @Override
    public void push(int element) throws Exception {
        if(super.capacity() == super.size()) {
            int[] temp = new int[super.size()];
            int idx = super.size() - 1;
            while(super.size() != 0) {
                temp[idx--] = super.pop();
            }
            initialize(temp.length * 2);

            for(int ele : temp) {
                super.push(ele);
            }
        }
        super.push(element);
    }
}
