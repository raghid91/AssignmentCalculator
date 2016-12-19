package com.example.acer.assignmentcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LandscapeActivity extends AppCompatActivity {

    private TextView mNumberDisplay;
    private SimpleExpression mExpression;
    private SimpleExpression cExpression;
    private Memory memory;
    private Boolean opSet;
    private Boolean bracket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landscape);

        mNumberDisplay = (TextView) findViewById(R.id.outputView);
        mExpression = new SimpleExpression();
        cExpression = new SimpleExpression();
        memory = new Memory();
        opSet = false;
        bracket = false;
    }

    public void goC (View view){
        mExpression.clearAll();
        mNumberDisplay.setText("0");
    }

    public void goCE(View view){
        mExpression.clearLast();
        mNumberDisplay.setText("0");
    }

    public void goMP(View view){
        String val = (String) mNumberDisplay.getText();
        mNumberDisplay.setText(memory.addToMemory(val));
    }

    public void goMS(View view){
        String val = (String) mNumberDisplay.getText();
        mNumberDisplay.setText(memory.saveToMemory(val));
    }

    public void goMC(View view){
        mNumberDisplay.setText(memory.clearMemory());
    }

    public void goOperand (View view) {
        String val = (String) mNumberDisplay.getText();
        String digit = (String) view.getContentDescription();

        if(bracket) goBracketOperand(Integer.parseInt(digit));

        if(opSet && val.charAt(0)!='0'){
            mNumberDisplay.setText(digit);
            opSet=false;
        }
        else if (val.charAt(0) == '0')
            mNumberDisplay.setText(digit);
        else
            mNumberDisplay.setText((String) mNumberDisplay.getText()
                    + digit.charAt(0));
    }

    public void goOperand (Integer val) {
        mNumberDisplay.setText(val);
    }

    public void goOperator (View view) {
        String operator = (String) view.getContentDescription();
        if(bracket) goBracketOperator(operator);
        try {
            String val = (String) mNumberDisplay.getText();
            mExpression.setOperand1((int) Integer.parseInt(val.toString()));
        }
        catch (NumberFormatException e){
            mExpression.setOperand1(0);
        }
        mExpression.setOperator(operator);
        opSet = true;
    }

    //WHEN THE = BUTTON IS CLICKED, COMPUTE AND DISPLAY THE VALUE
    public void goCompute (View view){
        try {
            String val = (String) mNumberDisplay.getText();
            mExpression.setOperand2((int) Integer.parseInt(val.toString()));
        }
        catch (NumberFormatException e){
            mExpression.setOperand2(0);
        }
        mNumberDisplay.setText(mExpression.getValue().toString());
    }

    public void goLeftBracket(View view){
        String val = (String) mNumberDisplay.getText();
        String digit = (String) view.getContentDescription();

        mNumberDisplay.setText('(');
        bracket = true;
        opSet=false;
    }

    public void goBracketOperand(Integer number){
        if(!opSet){
            Integer o1 = cExpression.getOperand1();
            String nb = "" + o1 + number;
            cExpression.setOperand1(Integer.parseInt(nb));
            String nbout = mNumberDisplay.getText() + nb;
            mNumberDisplay.setText(nbout);
        }

        else{
            Integer o2 = cExpression.getOperand2();
            String nb = "" + o2 + number;
            cExpression.setOperand2(Integer.parseInt(nb));
            String nbout = mNumberDisplay.getText() + nb;
            mNumberDisplay.setText(nbout);
        }
    }

    public void goBracketOperator(String symbol){
        if(!opSet){
            cExpression.setOperator(symbol);
            String symout = mNumberDisplay.getText() + symbol;
            mNumberDisplay.setText(symout);
            opSet = true;
        }
    }

    public void goRightBracket(View view){
        mNumberDisplay.setText(cExpression.getValue().toString());
        bracket = false;
    }

    public void goPi(View view){
        mNumberDisplay.setText("3");
    }


    public void goSqrt(View view){
        String val = (String) mNumberDisplay.getText();
        mNumberDisplay.setText(mExpression.sqrt(val));
    }

    public void goX2(View view){
        String val = (String) mNumberDisplay.getText();
        mNumberDisplay.setText(mExpression.powerTwo(val));
    }

    public void go10X(View view){
        String val = (String) mNumberDisplay.getText();
        mNumberDisplay.setText(mExpression.tenPower(val));
    }

    public void goHelp(View view) {
        Intent helpIntent = new Intent(LandscapeActivity.this,LandscapeActivityHelp.class);
        startActivity(helpIntent);
    }

    public void goToStandard(View view){
        Intent modeIntent = new Intent(LandscapeActivity.this,PortraitActivity.class);
        startActivity(modeIntent);
    }
}
