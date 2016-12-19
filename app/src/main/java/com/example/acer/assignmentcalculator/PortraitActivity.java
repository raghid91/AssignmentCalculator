package com.example.acer.assignmentcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PortraitActivity extends AppCompatActivity {

    private TextView mNumberDisplay;
    private SimpleExpression mExpression;
    private Memory memory;
    private Boolean opSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portrait);

        mNumberDisplay = (TextView) findViewById(R.id.outputView);
        mExpression = new SimpleExpression();
        memory = new Memory();
        opSet = false;
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

    public void goSqrt(View view){
        String val = (String) mNumberDisplay.getText();
        mNumberDisplay.setText(mExpression.sqrt(val));
    }

    public void goOperand (View view) {
        String val = (String) mNumberDisplay.getText();
        String digit = (String) view.getContentDescription();
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

    public void goOperator (View view) {
        String operator = (String) view.getContentDescription();
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

    public void goHelp(View view) {
        Intent helpIntent = new Intent(PortraitActivity.this,PortraitHelpActivity.class);
        startActivity(helpIntent);
    }

    public void goToScientific(View view){
        Intent modeIntent = new Intent(PortraitActivity.this,LandscapeActivity.class);
        startActivity(modeIntent);
    }


}
