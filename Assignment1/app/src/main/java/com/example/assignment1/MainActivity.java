package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView eqText = findViewById(R.id.solution_tv);
        TextView resText = findViewById(R.id.result_tv);
        updateText(resText,"");
        updateText(eqText,"");

        final String[] expr = {""};

        Button button0 = findViewById(R.id.button_0);
        Button button1 = findViewById(R.id.button_1);
        Button button2 = findViewById(R.id.button_2);
        Button button3 = findViewById(R.id.button_3);
        Button button4 = findViewById(R.id.button_4);
        Button button5 = findViewById(R.id.button_5);
        Button button6 = findViewById(R.id.button_6);
        Button button7 = findViewById(R.id.button_7);
        Button button8 = findViewById(R.id.button_8);
        Button button9 = findViewById(R.id.button_9);
        Button buttonDot = findViewById(R.id.button_dot);
        Button buttonAC = findViewById(R.id.button_ac);
        Button buttonOpenBracket = findViewById(R.id.button_open_bracket);
        Button buttonCloseBracket = findViewById(R.id.button_close_bracket);
        Button buttonEq = findViewById(R.id.button_equals);
        Button buttonDivide = findViewById(R.id.button_divide);
        Button buttonMultiply = findViewById(R.id.button_multiply);
        Button buttonPlus = findViewById(R.id.button_plus);
        Button buttonMinus = findViewById(R.id.button_minus);

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expr[0] = expr[0] + "0";
                updateText(eqText, expr[0]);

            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expr[0] = expr[0] + "1";
                updateText(eqText, expr[0]);

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expr[0] = expr[0] + "2";
                updateText(eqText, expr[0]);

            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expr[0] = expr[0] + "3";
                updateText(eqText, expr[0]);

            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expr[0] = expr[0] + "4";
                updateText(eqText, expr[0]);

            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expr[0] = expr[0] + "5";
                updateText(eqText, expr[0]);

            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expr[0] = expr[0] + "6";
                updateText(eqText, expr[0]);

            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expr[0] = expr[0] + "7";
                updateText(eqText, expr[0]);

            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expr[0] = expr[0] + "8";
                updateText(eqText, expr[0]);

            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expr[0] = expr[0] + "9";
                updateText(eqText, expr[0]);

            }
        });
        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expr[0] = expr[0] + "+";
                updateText(eqText, expr[0]);
            }
        });
        buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expr[0] = expr[0] + "/";
                updateText(eqText, expr[0]);
            }
        });
        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expr[0] = expr[0] + "-";
                updateText(eqText, expr[0]);
            }
        });
        buttonOpenBracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expr[0] = expr[0] + "(";
                updateText(eqText, expr[0]);
            }
        });

        buttonCloseBracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expr[0] = expr[0] + ")";
                updateText(eqText, expr[0]);
            }
        });
        buttonAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateText(resText,"");
                updateText(eqText,"");
            }
        });
        buttonMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expr[0] = expr[0] + "*";
                updateText(eqText, expr[0]);
            }
        });
        buttonEq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExecutorService exec = Executors.newFixedThreadPool(1);
                Expression e = new ExpressionBuilder(expr[0])

                        .build();

                Future<Double> future = ((net.objecthunter.exp4j.Expression) e).evaluateAsync(exec);
                double result = 0;
                try {
                    result = future.get();
                } catch (ExecutionException ex) {
                    throw new RuntimeException(ex);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                updateText(resText,String.valueOf(result));
                expr[0] = "";
                updateText(eqText,expr[0]);
            }
        });





    }
    public void updateText(TextView textView, String string){
        textView.setText(string);
    }

}