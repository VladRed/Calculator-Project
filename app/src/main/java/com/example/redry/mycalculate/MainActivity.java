package com.example.redry.mycalculate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int LAYOUT = R.layout.activity_main;
    private View.OnClickListener onClickListener;

    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0;
    private Button btnPlus, btnMinus, btnUmnoj, btnDivision, btnPoint, btnEquals, btnDelete, btnPi, btnDegree;
    private TextView fullMathExpression, inputValue, textAnswer;
    private String value = "";
    private String full_value = "";
    private String answer = "";
    private List<String> numbers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        initElements();

        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn1:
                        addValue("1");
                        break;
                    case R.id.btn2:
                        addValue("2");
                        break;
                    case R.id.btn3:
                        addValue("3");
                        break;
                    case R.id.btn4:
                        addValue("4");
                        break;
                    case R.id.btn5:
                        addValue("5");
                        break;
                    case R.id.btn6:
                        addValue("6");
                        break;
                    case R.id.btn7:
                        addValue("7");
                        break;
                    case R.id.btn8:
                        addValue("8");
                        break;
                    case R.id.btn9:
                        addValue("9");
                        break;
                    case R.id.btn0:
                        addValue("0");
                        break;
                    case R.id.point:
                        addValue(".");
                        break;
                    case R.id.delete:
                        delete();
                        break;
                    case R.id.plus:
                        operation("+");

                        break;
                    case R.id.minus:
                        if(!value.equals("")){
                            operation("-");

                        }else addValue("-");
                        break;
                    case R.id.umnoj:
                        operation("x");

                        break;
                    case R.id.division:
                        operation("/");

                        break;
                    case R.id.pi:
                        if(!value.equals("")){
                            full_value += value + " x " + "π";
                            addNumb();
                            numbers.add("x");
                            numbers.add("3.14");
                            fullMathExpression.setText(full_value);
                        }else{
                            full_value += "π";
                            numbers.add("3.14");
                            fullMathExpression.setText(full_value);
                        }
                        break;
                    case R.id.degree:
                        operation("^");
                        break;
                    case R.id.equals:
                        try{
                        full_value += value;
                        if(!value.equals(""))addNumb();
                        fullMathExpression.setText(full_value);
                        calcul("^", "");
                        calcul("x", "/");
                        calcul("+", "-");
                        answer = numbers.get(0);
                        textAnswer.setText(answer);
                        }
                        catch (Exception e){
                            delete();
                            full_delete();
                            textAnswer.setText(R.string.Error);
                        }
                        break;
                }
            }
        };
        setListeners();
    }
    //Метод добавляющий знаки математических операций
    public void operation(String s){
        full_value += value + " " + s + " ";
        if(!value.equals(""))
            addNumb();
            numbers.add(s);
            fullMathExpression.setText(full_value);


    }
    //Добавление введеного значения в массив
    public void addNumb(){
        numbers.add(value);
        value = "";
        inputValue.setText(value);
    }
    //Ввод значения
    public void addValue(String s){
        value += s;
        inputValue.setText(value);
    }

    //Выполнение математических операций
    public void calcul(String f, String s) {
        int y = 0;
        while(y < numbers.size()-1 ) {
            if((numbers.get(y)).equals(f) || (numbers.get(y)).equals(s)) {
                double d = Double.parseDouble(numbers.get(y-1));
                double d1 = Double.parseDouble(numbers.get(y+1));
                double value = 0;
                if((numbers.get(y)).equals(f)) {
                    if(f.equals("^")){
                        value = Math.pow(d, d1);
                    }
                }
                if((numbers.get(y)).equals(f)) {
                    if(f.equals("x")) {
                        value = d*d1;
                    }
                    if(f.equals("+")) {
                        value = d+d1;
                    }
                }
                if((numbers.get(y)).equals(s)) {
                    if(s.equals("/")) {
                        value = d/d1;
                    }
                    if(s.equals("-")) {
                        value = d-d1;
                    }
                }
                numbers.remove(y+1);
                numbers.remove(y);
                numbers.remove(y-1);
                String c = Double.toString(value);
                numbers.add(y-1, c);
                y = 0;
            }else y++;
        }
    }
    //Удаление последнего символа в водимом значении
    public void delete(){
        try{
            int chars = value.length();
            int answer_lenth = answer.length();
            if(answer_lenth > 0){
                full_delete();
            }
            if(chars>0){
                value = value.substring(0, chars-1);
                 //= s;
                inputValue.setText(value);
            }
            if((chars == 0) && (!full_value.equals(""))){
                int last_value = numbers.size()-2;
                value = numbers.get(last_value);
                int last_number_size = value.length();
                int char_del2 = full_value.length();
                String s = full_value.substring(0, char_del2 - last_number_size - 3);
                numbers.remove(numbers.size()-1);
                numbers.remove(last_value);
                full_value = s;
                fullMathExpression.setText(full_value);
                inputValue.setText(value);
            }
        }catch (Exception e){
            textAnswer.setText(R.string.Error);
        }
        }
    //Полная очистка всех введеныхзначений
    public void full_delete(){
            answer = "";
            value = "";
            full_value = "";
            textAnswer.setText(answer);
            inputValue.setText(value);
            fullMathExpression.setText(full_value);
            numbers.clear();

        }
    private void initElements() {
        fullMathExpression = (TextView)findViewById(R.id.textView);
        inputValue = (TextView)findViewById(R.id.textView2);
        textAnswer = (TextView)findViewById(R.id.textView3);
        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        btn3 = (Button)findViewById(R.id.btn3);
        btn4 = (Button)findViewById(R.id.btn4);
        btn5 = (Button)findViewById(R.id.btn5);
        btn6 = (Button)findViewById(R.id.btn6);
        btn7 = (Button)findViewById(R.id.btn7);
        btn8 = (Button)findViewById(R.id.btn8);
        btn9 = (Button)findViewById(R.id.btn9);
        btn0 = (Button)findViewById(R.id.btn0);
        btnDelete = (Button)findViewById(R.id.delete);
        btnPoint = (Button)findViewById(R.id.point);
        btnPlus = (Button)findViewById(R.id.plus);
        btnMinus = (Button)findViewById(R.id.minus);
        btnUmnoj = (Button)findViewById(R.id.umnoj);
        btnDivision = (Button)findViewById(R.id.division);
        btnEquals = (Button)findViewById(R.id.equals);
        btnPi = (Button)findViewById(R.id.pi);
        btnDegree = (Button)findViewById(R.id.degree);

    }
    private void setListeners() {
        btn1.setOnClickListener(onClickListener);
        btn2.setOnClickListener(onClickListener);
        btn3.setOnClickListener(onClickListener);
        btn4.setOnClickListener(onClickListener);
        btn5.setOnClickListener(onClickListener);
        btn6.setOnClickListener(onClickListener);
        btn7.setOnClickListener(onClickListener);
        btn8.setOnClickListener(onClickListener);
        btn9.setOnClickListener(onClickListener);
        btn0.setOnClickListener(onClickListener);
        btnPoint.setOnClickListener(onClickListener);
        btnDelete.setOnClickListener(onClickListener);
        btnPlus.setOnClickListener(onClickListener);
        btnMinus.setOnClickListener(onClickListener);
        btnUmnoj.setOnClickListener(onClickListener);
        btnDivision.setOnClickListener(onClickListener);
        btnEquals.setOnClickListener(onClickListener);
        btnPi.setOnClickListener(onClickListener);
        btnDegree.setOnClickListener(onClickListener);
    }
}


