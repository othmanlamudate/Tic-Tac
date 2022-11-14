package com.example.tiktac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class game extends AppCompatActivity {
    private TextView playerScore,computerScore,playerName;
    private String player,computer;
    private Button C1;
    private Button C2;
    private Button C3;
    private Button C4;
    private Button C5;
    private Button C6;
    private Button C7;
    private Button C8;
    private Button C9;
    private String lastplayer="X";
    private Button[][] XO=new Button[3][3];

    private TextView note;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        Intent in=this.getIntent();
        Bundle bundle=in.getExtras();

        HashMap<String,String> data= (HashMap<String,String>) bundle.getSerializable("data");




        try {
            note=this.findViewById(R.id.note);
            playerName=this.findViewById(R.id.playerName);
            playerScore=this.findViewById(R.id.playerScore);
            computerScore=this.findViewById(R.id.computerScore);
            playerName.setText(data.get("player").toUpperCase());
             C1=(Button) this.findViewById(R.id.C1) ;
             C2=(Button) this.findViewById(R.id.C2) ;
             C3=(Button) this.findViewById(R.id.C3) ;
             C4=(Button) this.findViewById(R.id.C4) ;
             C5=(Button) this.findViewById(R.id.C5) ;
             C6=(Button) this.findViewById(R.id.C6) ;
             C7=(Button) this.findViewById(R.id.C7) ;
             C8=(Button) this.findViewById(R.id.C8) ;
             C9=(Button) this.findViewById(R.id.C9) ;
            click(C1);
            click(C2);
            click(C3);
            click(C4);
            click(C5);
            click(C6);
            click(C7);
            click(C8);
            click(C9);
            XO[0][0]=C1;
            XO[0][1]=C2;
            XO[0][2]=C3;
            XO[1][0]=C4;
            XO[1][1]=C5;
            XO[1][2]=C6;
            XO[2][0]=C7;
            XO[2][1]=C8;
            XO[2][2]=C9;

            String[] xoro={"X","O"};
            int index=(int)(Math.random()*2);
            player=xoro[index];
            if (index==0){
                computer=xoro[1];
            }
            else {
                computer=xoro[0];
            }





        }
        catch (Exception e){
            System.out.println("*************************************************************");
            System.out.println(e.getMessage());
            System.out.println("*************************************************************");

        }

    }

    private Boolean played(Button b){
        if (b.getText().toString().equals("") && !b.getText().toString().equals(computer) ){
            b.setText(player);
            return  true;
        }

        return  false;
    }


    private  void click(Button C){
        C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (C.getText().toString().equals("")) {
                    played(C);
                    AI_easy();
                    if (checkWiner().equals(player)) {
                        playerScore.setText(String.valueOf(Integer.valueOf(playerScore.getText().toString()) + 1));
                        Win(playerName.getText().toString());

                    }
                    if (checkWiner().equals(computer)) {
                        computerScore.setText(String.valueOf(Integer.valueOf(computerScore.getText().toString()) + 1));
                        Win("Computer");
                    }

                }
            }
        });
    }

    private String checkWiner() {
        int i=0,c=0;

        String [] diag=new String[3];
        String [] diagInverse={XO[0][2].getText().toString(),XO[1][1].getText().toString(),XO[2][0].getText().toString()};

        String [] line1={XO[0][0].getText().toString(),XO[0][1].getText().toString(),XO[0][2].getText().toString()};
        String [] line2={XO[1][0].getText().toString(),XO[1][1].getText().toString(),XO[1][2].getText().toString()};
        String [] line3={XO[2][0].getText().toString(),XO[2][1].getText().toString(),XO[2][2].getText().toString()};

        String [] col1={XO[0][0].getText().toString(),XO[1][0].getText().toString(),XO[2][0].getText().toString()};
        String [] col2={XO[0][1].getText().toString(),XO[1][1].getText().toString(),XO[2][1].getText().toString()};
        String [] col3={XO[0][2].getText().toString(),XO[1][2].getText().toString(),XO[2][2].getText().toString()};



        for(int k=0;k<3;k++){
            for (int j=0;j<3;j++){
                if(k==j){
                    diag[c]=XO[k][j].getText().toString();
                    c++;
                }
            }
        }
        if(diag[0]==diag[1] && diag[1]==diag[2] && !diag[0].equals("")){
            return diag[1];
        }
        if(diagInverse[0]==diagInverse[1] && diagInverse[1]==diagInverse[2] && !diagInverse[0].equals("")){
            return diagInverse[1];
        }
        if(line1[0]==line1[1] && line1[1]==line1[2] && !line1[0].equals("")){
            return line1[1];
        }
        if(line2[0]==line2[1] && line2[1]==line2[2] && !line2[0].equals("")){
            return line2[1];
        }
        if(line3[0]==line3[1] && line3[1]==line3[2] && !line3[0].equals("")){
            return line3[1];
        }

        if(col1[0]==col1[1] && col1[1]==col1[2] && !col1[0].equals("") ){
            return col1[1];
        }
        if(col2[0]==col2[1] && col2[1]==col2[2] && !col2[0].equals("")){
            return col2[1];
        }
        if(col3[0]==col3[1] && col3[1]==col3[2] && !col3[0].equals("")){
            return col3[1];
        }



        if(Drow()){
            Win("drow");
        }
        return "";


    }

    private boolean Win(String winer){
        for(int k=0;k<3;k++){
            for (int j=0;j<3;j++){

                    XO[k][j].setText("");

            }
        }
        return true;
    }


    private boolean Drow(){
        int i=0;
        for(int k=0;k<3;k++){
            for (int j=0;j<3;j++){
                if(XO[k][j].getText().toString().equals("")){
                   return  false;
                }

            }
        }
            return true;

    }


    private  boolean AI_easy(){
        int i=-1,o=-1;

        for(int k=0;k<3;k++){
            for (int j=0;j<3;j++){
                if(XO[k][j].getText().toString().equals("")){
                    i=k;o=j;
                    break;
                }

            }
        }
        if(i!=-1 && o!=-1){
            XO[i][o].setText(computer);
        }
        return true;


    }






}