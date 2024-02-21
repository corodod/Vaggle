package com.example.vagglegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.vagglegame.levels.Level1;
import com.example.vagglegame.levels.Level10;
import com.example.vagglegame.levels.Level2;
import com.example.vagglegame.levels.Level3;
import com.example.vagglegame.levels.Level4;
import com.example.vagglegame.levels.Level5;
import com.example.vagglegame.levels.Level6;
import com.example.vagglegame.levels.Level7;
import com.example.vagglegame.levels.Level8;
import com.example.vagglegame.levels.Level9;

public class GameLevels extends AppCompatActivity {

    public static int completedLevels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_levels);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button buttonLevel1 = findViewById(R.id.button1);
        Button buttonLevel2 = findViewById(R.id.button2);
        Button buttonLevel3 = findViewById(R.id.button3);
        Button buttonLevel4 = findViewById(R.id.button4);
        Button buttonLevel5 = findViewById(R.id.button5);
        Button buttonLevel6 = findViewById(R.id.button6);
        Button buttonLevel7 = findViewById(R.id.button7);
        Button buttonLevel8 = findViewById(R.id.button8);
        Button buttonLevel9 = findViewById(R.id.button9);
        Button buttonLevel10 = findViewById(R.id.button10);

        buttonLevel1.setSelected(completedLevels >= 1);
        buttonLevel2.setSelected(completedLevels >= 2);
        buttonLevel3.setSelected(completedLevels >= 3);
        buttonLevel4.setSelected(completedLevels >= 4);
        buttonLevel5.setSelected(completedLevels >= 5);
        buttonLevel6.setSelected(completedLevels >= 6);
        buttonLevel7.setSelected(completedLevels >= 7);
        buttonLevel8.setSelected(completedLevels >= 8);
        buttonLevel9.setSelected(completedLevels >= 9);
        buttonLevel10.setSelected(completedLevels >= 10);

        buttonLevel1.setEnabled(true);
        buttonLevel2.setEnabled(completedLevels >= 1);
        buttonLevel3.setEnabled(completedLevels >= 2);
        buttonLevel4.setEnabled(completedLevels >= 3);
        buttonLevel5.setEnabled(completedLevels >= 4);
        buttonLevel6.setEnabled(completedLevels >= 6);
        buttonLevel7.setEnabled(completedLevels >= 7);
        buttonLevel8.setEnabled(completedLevels >= 8);
        buttonLevel9.setEnabled(completedLevels >= 9);
        buttonLevel10.setEnabled(completedLevels >= 10);


        buttonLevel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(GameLevels.this, Level1.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {

                }
            }
        });

        buttonLevel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(GameLevels.this, Level2.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                }
            }
        });

        buttonLevel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(GameLevels.this, Level3.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {

                }
            }
        });

        buttonLevel4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(GameLevels.this, Level4.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                }
            }
        });

        buttonLevel5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(GameLevels.this, Level5.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {

                }
            }
        });

        buttonLevel6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(GameLevels.this, Level6.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {

                }
            }
        });

        buttonLevel7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(GameLevels.this, Level7.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {

                }
            }
        });

        buttonLevel8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(GameLevels.this, Level8.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {

                }
            }
        });

        buttonLevel9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(GameLevels.this, Level9.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {

                }
            }
        });

        buttonLevel10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(GameLevels.this, Level10.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {

                }
            }
        });
    }
}