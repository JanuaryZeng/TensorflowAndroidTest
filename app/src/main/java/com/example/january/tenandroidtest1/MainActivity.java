package com.example.january.tenandroidtest1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.nio.FloatBuffer;

public class MainActivity extends AppCompatActivity {

    private float[] number = new float[1];
    private float[] result = new float[1];
    private EditText et;
    private float text;
    private float number1;
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et = findViewById(R.id.editText);
                text = Float.parseFloat(et.getText().toString());
                System.out.println(text);
                System.out.println("****************************************************************************************");
                TensorAndroid td = new TensorAndroid(getAssets());
                number[0] = text;
                result=td.getAddResult(number);
                number1 = result[0];
                tv = findViewById(R.id.textView);
                tv.setText(new String(String.valueOf(number1)));
            }
        });
    }
}
