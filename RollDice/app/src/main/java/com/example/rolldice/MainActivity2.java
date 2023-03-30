package com.example.rolldice;




import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ThreadLocalRandom;

public class MainActivity2 extends AppCompatActivity {
    int playerHealthVal = 50;
    int enemyHealthVal = 50;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ImageView playerDice;
        Button evenButton = findViewById(R.id.even_button);
        Button oddButton = findViewById(R.id.odd_button);
        Button rButton = findViewById(R.id.restrart_button);
        playerDice = findViewById(R.id.players_dice);
        TextView infoText = findViewById(R.id.info_text);
        TextView playerHealth = findViewById(R.id.player_health_text);
        TextView enemyHealth  =findViewById(R.id.enemy_health_text);
        playerHealth.setText(String.valueOf("Canınız: " + playerHealthVal));
        enemyHealth.setText(String.valueOf("Düşmanın Canı: " +  enemyHealthVal));
        rButton.setVisibility(View.GONE);





        evenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



               if(rollDice(0,playerDice,infoText)){

                   playerHealth.setText(String.valueOf("Canınız: " +playerHealthVal));
                   enemyHealth.setText(String.valueOf("Düşmanın Canı: " +enemyHealthVal));


               }
               else{

                   playerHealth.setText(String.valueOf("Canınız: " +playerHealthVal));
                   enemyHealth.setText(String.valueOf("Düşmanın Canı: " +enemyHealthVal));


                   evenButton.setVisibility(View.GONE);
                   oddButton.setVisibility(View.GONE);
                   rButton.setVisibility(View.VISIBLE);
               }


            }
        });
        oddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rollDice(1,playerDice,infoText)){

                    playerHealth.setText(String.valueOf("Canınız: " +playerHealthVal));
                    enemyHealth.setText(String.valueOf("Düşmanın Canı: " +enemyHealthVal));


                }
                else{

                    playerHealth.setText(String.valueOf("Canınız: " +playerHealthVal));
                    enemyHealth.setText(String.valueOf("Düşmanın Canı: " +enemyHealthVal));


                    evenButton.setVisibility(View.GONE);
                    oddButton.setVisibility(View.GONE);
                    rButton.setVisibility(View.VISIBLE);
                }

            }
        });
        rButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this,MainActivity.class);
                startActivity(intent);
            }
        });





}
    public boolean rollDice(int mod, ImageView diceImage, TextView infoText){

        int randomNum = ThreadLocalRandom.current().nextInt(1, 7);
        String path = "dice" + String.valueOf(randomNum);



        diceImage.setImageResource(getResources().getIdentifier(path,"drawable", getPackageName()));
        System.out.println(randomNum);

        if(randomNum%2 == mod){
            enemyHealthVal -= randomNum;
            infoText.setText(String.valueOf(randomNum) + " kadar hasar verdiniz!");
            if(enemyHealthVal <= 0)
            {
                enemyHealthVal = 0;
                infoText.setText("Tebrikler kazandınız!");
                return false;
            }
        }
        else{
            playerHealthVal -= randomNum;
            infoText.setText(String.valueOf(randomNum) + " kadar hasar aldınız!");
            if(playerHealthVal <= 0){
                playerHealthVal = 0;
                infoText.setText("Malesef kaybettiniz :(");
                return false;
            }
        }

        return true;


    }
}