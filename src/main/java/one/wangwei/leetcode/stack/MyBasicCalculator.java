package one.wangwei.leetcode.stack;

import one.wangwei.algorithms.datastructures.stack.IStack;
import one.wangwei.algorithms.datastructures.stack.impl.LinkedStack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * 简单计算器实现
 *
 * @author https://wangwei.one
 * @date 2019/1/18
 */
public class MyBasicCalculator {

    private IStack<Integer> operand;
    private IStack<String> operator;
    private Set<String> highOperator;
    private Set<String> lowOperator;
    private Set<String> parentheses;
    private Set<String> operatorSet;

    public MyBasicCalculator() {
        this.operand = new LinkedStack<>();
        this.operator = new LinkedStack<>();

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

    /**
     * 运算表达式
     *
     * @param s
     * @return
     */
    public int calculate(String s) {
        if (s == null || s.isEmpty()) {
            throw new RuntimeException("Expression Invalid! expr=" + s);
        }

        ArrayList<String> express = convertExpr(s);
        for (String str : express) {
            if (!operatorSet.contains(str)) {
                operand.push(Integer.valueOf(str));
            } else {
                pushOperator(str);
            }
        }

        // 对余下的操作数进行计算，得到最后的结果
        operandCalcu();

        return operand.pop();
    }

    /**
     * 转换表达式
     * <p>
     * 1. 去除空格
     * 2. 拆分出有效的数字
     *
     * @param expr
     * @return
     */
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

    /**
     * 运算符入栈
     *
     * @param operatorSign
     */
    private void pushOperator(String operatorSign) {
        String prevOperator = null;
        if (!operator.empty()) {
            prevOperator = operator.peek();
        }
        // 操作符栈为空的情况下，直接入栈
        if (prevOperator == null) {
            operator.push(operatorSign);
        } else {
            if (")".equals(operatorSign) && "(".equals(prevOperator)) {
                operator.pop();
                return;
            }
            // 第一次以后入栈，先比较优先级，高优先级，则入栈
            if (priority(operatorSign, prevOperator)) {
                operator.push(operatorSign);
            } else {
                // 否则先对前面的表达式进行计算
                operandCalcu();
                pushOperator(operatorSign);
            }
        }
    }

    /**
     * 从操作数栈取出两个操作数进行计算
     */
    private void operandCalcu() {
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
        operandCalcu();
    }

    /**
     * 比较优先级
     *
     * @param next
     * @param prev
     * @return
     */
    private boolean priority(String next, String prev) {
        return (highOperator.contains(next)
                && lowOperator.contains(prev))
                || "(".equals(prev)
                || "(".equals(next);
    }

    /**
     * 对两个数字进行计算
     *
     * @param front
     * @param after
     * @param sign
     * @return
     */
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
        long startTime = System.currentTimeMillis();
        MyBasicCalculator solution = new MyBasicCalculator();
        System.out.println(solution.calculate("1 + 1 - 3 + 4 - (8 + 2) - 4 + 3 - 1 - 4 + 6 - 9 + 1"));
        System.out.println(solution.calculate("(1+(4+5+2)-3)+(6+8)"));
        System.out.println(solution.calculate("1-(5)"));
        System.out.println(solution.calculate("2-4-(8+2-6+(8+4-(1)+8-10))"));
        System.out.println(System.currentTimeMillis() - startTime);
    }
}
