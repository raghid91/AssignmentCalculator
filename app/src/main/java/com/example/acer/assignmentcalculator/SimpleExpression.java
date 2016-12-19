package com.example.acer.assignmentcalculator;

/**
 * Created by Acer on 10/10/2016.
 */

public class SimpleExpression {
    private Integer mOperand1;
    private Integer mOperand2;
    private String mOperator;
    private Integer mValue;

    public SimpleExpression() {
        mOperand1 = 0;
        mOperand2 = 0;
        mOperator = "+";
        mValue = 0;
    }

    public void setOperand1(int v) {
        mOperand1 = v;
    }

    public int getOperand1() {
        return mOperand1;
    }

    public void setOperand2(int v) {
        mOperand2 = v;
    }

    public int getOperand2() {
        return mOperand2;
    }

    public void setOperator(String s) {
        mOperator = s;
    }

    public String getOperator() {
        return mOperator;
    }

    public Integer getValue() {
        computeValue();
        return mValue;
    }


    /*
     * Clears the operands within an expression
     */
    public void clearAll() {
        mOperand1 = 0;
        mOperand2 = 0;
        mOperator = "+";
        mValue = 0;
    }

    public void clearLast(){
        if(mOperand2!=0) mOperand2=0;
        else if(mOperand1!=0 && mOperand2==0 && mOperator.contentEquals("+")) mOperand1=0;
    }

    /*
     * Computes the integer value of the expression.
     */
    private void computeValue() {
        mValue = 0;
        if (mOperator.contentEquals("+"))
            mValue = mOperand1 + mOperand2;
        else if (mOperator.contentEquals("-"))
            mValue = mOperand1 - mOperand2;
        else if (mOperator.contentEquals("x"))
            mValue = mOperand1 * mOperand2;
        else if (mOperator.contentEquals("/") && mOperand2 != 0)
            mValue = mOperand1 / mOperand2;
        else if (mOperator.contentEquals("mod") && mOperand2 !=0)
            mValue = mOperand1 % mOperand2;
        else{
            Double m = Math.pow(mOperand1,mOperand2);
            mValue = m.intValue();
        }
    }

    public Integer sqrt(String val) {
        Integer value = Integer.parseInt(val);
        Double result = Math.sqrt(value);
        Integer opResult = result.intValue();
        return opResult;
    }

    public Integer powerTwo(String val){
        Integer value = Integer.parseInt(val);
        Integer power = value*value;
        return power;
    }

    public Integer tenPower(String val){
        Integer value = Integer.parseInt(val);
        Double power = Math.pow(10,value);
        Integer opResult = power.intValue();
        return opResult;
    }

}
