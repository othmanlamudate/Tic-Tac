package com.example.tiktac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private EditText name;
    private Button play;
    private RadioButton Easy,Hard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name =findViewById(R.id.name);
        play=findViewById(R.id.play);
        Easy=findViewById(R.id.Easy);
        Hard=findViewById(R.id.Hard);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!name.getText().toString().equals("")){
                    Intent in=new Intent(MainActivity.this,game.class);
                    HashMap<String,String> info=new HashMap<String,String>();
                    info.put("player",name.getText().toString());
                    if(Hard.isChecked()){
                        info.put("IA","Hard");
                    }
                    if(Easy.isChecked()){
                        info.put("IA","Easy");
                    }
                    in.putExtra("data",info);
                    startActivity(in);

                }
                else {
                    Toast.makeText(MainActivity.this, "we need your name", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}