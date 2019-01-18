package one.wangwei.leetcode.stack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * @author https://wangwei.one
 * @date 2019/1/18
 */
public class MyBasicCalculator {

    private Stack<Integer> operand;
    private Stack<String> operator;
    private Set<String> highOperator;
    private Set<String> lowOperator;
    private Set<String> parentheses;
    private Set<String> operatorSet;

    public MyBasicCalculator() {
        this.operand = new Stack<>();
        this.operator = new Stack<>();

        this.parentheses = new HashSet<>();
        this.parentheses.add("(");
        this.parentheses.add(")");

        this.highOperator = new HashSet<>();
        this.highOperator.add("*");
        this.highOperator.add("/");

        this.lowOperator = new HashSet<>();
        this.lowOperator.add("+");
        this.lowOperator.add("-");

        this.operatorSet = new HashSet<>();
        this.operatorSet.addAll(highOperator);
        this.operatorSet.addAll(lowOperator);
        this.operatorSet.addAll(parentheses);
    }

    public int calculate(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        // remove empty spaces
        ArrayList<String> express = convertExpr(s);
        for (String str : express) {
            if (!isOperator(str)) {
                operand.push(Integer.valueOf(str));
            } else {
                pushOperator(str);
            }
        }

        lastCalcu();
        return operand.pop();
    }

    private ArrayList<String> convertExpr(String expr) {
        ArrayList<String> result = new ArrayList<>();
        // remove empty spaces
        String trimExpr = expr.replaceAll("\\s+", "");

        String tmpIntStr = "";
        for (Character ch : trimExpr.toCharArray()) {
            String str = ch.toString();
            if (operatorSet.contains(str)) {
                if (!tmpIntStr.isEmpty()) {
                    result.add(tmpIntStr);
                    tmpIntStr = "";
                }
                result.add(str);
            } else {
                tmpIntStr = tmpIntStr + str;
            }
        }
        if (!tmpIntStr.isEmpty()) {
            result.add(tmpIntStr);
        }
        return result;
    }

    private void pushOperator(String operSign) {
        String prevOperator = null;
        if (!operator.empty()) {
            prevOperator = operator.peek();
        }
        // 第一次入栈
        if (prevOperator == null) {
            operator.push(operSign);
        } else {
            if (")".equals(operSign) && "(".equals(prevOperator)) {
                operator.pop();
                return;
            }
            // 第一次以后入栈，先比较优先级，高优先级，则入栈
            if (priority(operSign, prevOperator)) {
                operator.push(operSign);
            } else {
                // 否则先对前面的表达式进行计算
                lastCalcu();
                pushOperator(operSign);
            }
        }
    }

    private void lastCalcu() {
        // 从操作数栈取出两个操作数进行计算
        if (operator.empty()) {
            return;
        }
        String sign = operator.peek();
        if ("(".equals(sign)) {
            return;
        }

        sign = operator.pop();
        int after = operand.pop();
        int front = operand.pop();

        int value = calcIntegers(front, after, sign);
        operand.push(value);
        lastCalcu();
    }

    private boolean isOperator(String sign) {
        return operatorSet.contains(sign);
    }

    private boolean priority(String next, String prev) {
        return (highOperator.contains(next)
                && lowOperator.contains(prev))
                || "(".equals(prev)
                || "(".equals(next);
    }

    private int calcIntegers(int front, int after, String sign) {
        switch (sign) {
            case "+":
                return front + after;
            case "-":
                return front - after;
            case "*":
                return front * after;
            case "/":
                return front / after;
            default:
                throw new RuntimeException("Sign Invalid! sign=" + sign);
        }
    }

    public static void main(String[] args) {
        MyBasicCalculator solution = new MyBasicCalculator();
        System.out.println(solution.calculate("1 + 1 - 3 + 4 - (8 + 2) - 4 + 3 - 1 - 4 + 6 - 9 + 1"));
        System.out.println(solution.calculate("(1+(4+5+2)-3)+(6+8)"));
        System.out.println(solution.calculate(null));
        System.out.println(solution.calculate("1-(5)"));
        System.out.println(solution.calculate("2-4-(8+2-6+(8+4-(1)+8-10))"));
//        System.out.println(solution.calculate("1+2*5/3+6/4*2"));

    }
}
