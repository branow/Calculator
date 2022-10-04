package calcul;

import java.util.ArrayList;
import java.util.Stack;

public class Logic {

    private static Stack<Double> nums = new Stack<>();
    private static ArrayList<Operation> o;
    private static Stack<Operation> operations = new Stack<>();

    static {
        o = new ArrayList<>();
        o.add(Operation.ADD);
        o.add(Operation.SUB);
        o.add(Operation.MUL);
        o.add(Operation.DIV);
        o.add(Operation.ROOT);
        o.add(Operation.SQUARED);
        o.add(Operation.CUBED);
        o.add(Operation.EQUALS);
    }

    private static Operation find (String str) {
        for (Operation e: o) {
            if (str.equals(e.getName())) {
                return e;
            }
        }
        return null;
    }

    public static void action (String str) {
        Operation operation = find(str);
        if (operation==null)
            System.exit(2);
        while (!operations.isEmpty() && operations.peek().getValue() >= operation.getValue()) {
            nums.push(calculator(operations.pop()));
        }
        operations.push(operation);
    }

    private static double calculator (Operation operation) {
        switch (operation.getName()) {
            case ("+"):
                return nums.pop()+nums.pop();
            case ("-"):
                return -(nums.pop()-nums.pop());
            case ("\u00D7"):
                return nums.pop()*nums.pop();
            case ("\u00F7"):
                return (1/nums.pop())*nums.pop();
            case ("\u00B2"):
                return Math.pow(nums.pop(),2);
            case ("\u00B3"):
                return Math.pow(nums.pop(),3);
            default :
                return Math.sqrt(nums.pop());
        }
    }

    public static boolean check () {
        if (operations.isEmpty()) {
            return true;
        } else if (operations.peek().equals(Operation.ROOT) || operations.peek().equals(Operation.SQUARED)
                || operations.peek().equals(Operation.CUBED))  {
            return false;
        }
        return true;
    }

    public static Stack<Operation> getOperations() {
        return operations;
    }

    public static void setOperations(Stack<Operation> operations) {
        Logic.operations = operations;
    }

    public static Stack<Double> getNums() {
        return nums;
    }

    public static void setNums(Stack<Double> nums) {
        Logic.nums = nums;
    }
}
