package com.example.btcalculator;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import org.mariuszgromada.math.mxparser.Expression;

public class MainActivity extends AppCompatActivity {
    private EditText display;
    private EditText displayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = findViewById(R.id.input);

        displayout = findViewById(R.id.output);
        displayout.setFocusable(false);
        displayout.setBackground(null);

        displayout.setFocusableInTouchMode(false);
        display.setShowSoftInputOnFocus(false);
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getString(R.string.display).equals(display.getText().toString())) {
                    display.setText("");
                }

            }
        });


    }

    private void updateText(String strToAdd) {
        String oldstr = display.getText().toString();
        int cursorpos = display.getSelectionStart();
        String leftstr = oldstr.substring(0, cursorpos);
        String rightstr = oldstr.substring(cursorpos);


        if (getString(R.string.display).equals(display.getText().toString())) {
            display.setText(strToAdd);
            display.setSelection(cursorpos + 1);
        } else {
            display.setText(String.format("%s%s%s", leftstr, strToAdd, rightstr));
            display.setSelection(cursorpos + 1);
            displayout.setText("");

        }


    }

    public void zeroBTn(View view) {
        updateText("0");
    }

    public void oneBTN(View view) {
        updateText("1");

    }

    public void twoBTN(View view) {
        updateText("2");
    }

    public void threeBTN(View view) {
        updateText("3");
    }

    public void fourBTN(View view) {
        updateText("4");
    }

    public void fiveBTN(View view) {
        updateText("5");
    }

    public void sixBTN(View view) {
        updateText("6");
    }

    public void sevenBTN(View view) {
        updateText("7");
    }

    public void eightBTN(View view) {
        updateText("8");
    }

    public void nineBTN(View view) {
        updateText("9");
    }

    public void clearBTN(View view) {
        display.setText("");
        displayout.setText("");
    }

    public void equalsBTN(View view) {
        String userExp = display.getText().toString();
        Expression exp = new Expression(userExp);
        userExp = userExp.replaceAll("×", "*");
        userExp = userExp.replaceAll("÷", "/");
        String result = String.valueOf((double)exp.calculate());
        displayout.setText(result);   display.setText("");
        displayout.setSelection(result.length());





    }

    public void pointBTN(View view) {
        updateText(".");
    }


    public void subractBTN(View view) {
        updateText("-");
    }

    public void addBTN(View view) {
        updateText("+");
    }

    public void divideBTN(View view) {
        updateText("÷");
    }

    public void plusminesBTN(View view) {
        updateText("-");
    }

    public void multiplyBTN(View view) {
        updateText("×");
    }

    public void parentheseBTN(View view) {
        int cursorPos = display.getSelectionStart();
        int openPar = 0;
        int closedPar = 0;
        int textLen = display.getText().length();
        for (int i = 0; i < cursorPos; i++) {
            if (display.getText().toString().substring(i, i + 1).equals("(")) {
                openPar += 1;
            }
            if (display.getText().toString().substring(i, i + 1).equals(")")) {
                closedPar += 1;
            }


        }
        if (openPar == closedPar || display.getText().toString().substring(textLen - 1, textLen).equals("(")) {
            updateText("(");

        } else if (closedPar < openPar && !display.getText().toString().substring(textLen - 1, textLen).equals("(")) {
            updateText(")");

        }
        display.setSelection(cursorPos + 1);
    }

    public void exponentBTN(View view) {
        updateText("^");
    }

    public void backspceBTN(View view) {
        displayout.setText("");
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();
        if (cursorPos != 0 && textLen != 0) {
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos - 1, cursorPos, "");
            display.setText(selection);
            display.setSelection(cursorPos - 1);


        }

    }

}