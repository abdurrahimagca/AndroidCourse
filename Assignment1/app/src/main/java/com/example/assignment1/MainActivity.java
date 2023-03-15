package com.example.assignment1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView eqText = findViewById(R.id.solution_tv);
        TextView resText = findViewById(R.id.result_tv);
        updateText(resText, "");
        updateText(eqText, "");

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

        button0.setOnClickListener(view -> buttonPressed(eqText, expr, "0"));
        button1.setOnClickListener(view -> buttonPressed(eqText, expr, "1"));

        button2.setOnClickListener(view -> buttonPressed(eqText, expr, "2"));

        button3.setOnClickListener(view -> buttonPressed(eqText, expr, "3"));
        button4.setOnClickListener(view -> buttonPressed(eqText, expr, "4"));
        button5.setOnClickListener(view -> buttonPressed(eqText, expr, "5"));

        button6.setOnClickListener(view -> buttonPressed(eqText, expr, "6"));
        button7.setOnClickListener(view -> buttonPressed(eqText, expr, "7"));
        button8.setOnClickListener(view -> buttonPressed(eqText, expr, "8"));
        button9.setOnClickListener(view -> buttonPressed(eqText, expr, "9"));
        buttonPlus.setOnClickListener(view -> buttonPressed(eqText, expr, "+"));
        buttonDivide.setOnClickListener(view -> buttonPressed(eqText, expr, "/"));
        buttonMinus.setOnClickListener(view -> buttonPressed(eqText, expr, "-"));
        buttonOpenBracket.setOnClickListener(view -> buttonPressed(eqText, expr, "("));
        buttonDot.setOnClickListener(view -> buttonPressed(eqText, expr, "."));

        buttonCloseBracket.setOnClickListener(view -> buttonPressed(eqText, expr, ")"));
        buttonAC.setOnClickListener(view -> {
            expr[0] = "";
            buttonPressed(eqText, expr, "");
            buttonPressed(resText, expr, "");
        });
        buttonMultiply.setOnClickListener(view -> buttonPressed(eqText, expr, "*"));
        buttonEq.setOnClickListener(view -> {
            double result;

            ExecutorService exec = Executors.newFixedThreadPool(1);
            Expression e = new ExpressionBuilder(expr[0])
                    .build();
            Future<Double> future = ((Expression) e).evaluateAsync(exec);
            try {
                result = future.get();

                updateText(resText, String.format("%.3f", result));
                expr[0] = "";
                updateText(eqText, expr[0]);
            } catch (ExecutionException | InterruptedException ex) {
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("Hata");
                alertDialog.setMessage("Hatali matematiksel ifade!");
                updateText(resText, "");
                expr[0] = "";
                updateText(eqText, expr[0]);
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        (dialog, which) -> dialog.dismiss());
                alertDialog.show();
            }

        });


    }


    public void updateText(TextView textView, String string) {
        textView.setText(string);
    }

    public void buttonPressed(TextView textView, String[] string, String substring) {
        string[0] = string[0] + substring;
        textView.setText(string[0]);
    }

}