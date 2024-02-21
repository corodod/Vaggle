package com.example.vagglegame.levels;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vagglegame.Game;
import com.example.vagglegame.GameLevels;
import com.example.vagglegame.R;

public class Level10 extends AppCompatActivity {
    private Game game;
    private FrameLayout gameLayout;
    private CustomView gameView;
    private TextView statusTextView;
    private CountDownTimer countDownTimer;

    private TextView scoreTextView;
    private Bitmap circleImage;
    private Bitmap markedCircleImage;
    private final int backgroundColor = Color.WHITE;

    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_10);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        game = new Game();

        gameLayout = findViewById(R.id.field_layot);
        gameView = new CustomView(this);
        gameLayout.addView(gameView);

        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_complete);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);

        Button buttonBack = findViewById(R.id.select_level_button);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Level10.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {

                }
            }
        });

        Button newGameButton = findViewById(R.id.reset_game_button);
        newGameButton.setOnClickListener(v -> resetGame());

        statusTextView = findViewById(R.id.game_info_text);

        long totalTimeInMillis = 5 * 60 * 1000;

        Button selectLevel = (Button) dialog.findViewById(R.id.button_select_level);

        selectLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Level10.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {

                }
            }
        });

        scoreTextView = findViewById(R.id.score_info);

        countDownTimer = new CountDownTimer(totalTimeInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                updateTimerText(millisUntilFinished);
            }

            @Override
            public void onFinish() {
                statusTextView.setText("Вы проиграли");
                gameView.setEnabled(false);
            }
        };

        circleImage = BitmapFactory.decodeResource(getResources(), R.drawable.circle);
        markedCircleImage = BitmapFactory.decodeResource(getResources(), R.drawable.marked_circle);
        countDownTimer.start();
        resetGame();
    }

    private class CustomView extends View {
        private Paint paint;
        private int rowCount;
        private int colCount;
        private int cellWidth;
        private int cellHeight;

        public CustomView(Level10 context) {
            super(context);
            paint = new Paint();

            setOnTouchListener(new OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    int action = motionEvent.getAction();

                    int cellWidth = gameView.getWidth() / game.getColCount();
                    int cellHeight = gameView.getHeight() / game.getRowCount();

                    int col = (int) motionEvent.getX() / cellWidth;
                    int row = (int) motionEvent.getY() / cellHeight;

                    if (row >= 0 && row < game.getColCount() && col >= 0 && col < game.getColCount()) {
                        if (action == MotionEvent.ACTION_DOWN) {
                            firstClick(row, col);
                            secondClick(row, col);
                        }
                    }
                    return true;
                }
            });
        }


        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            drawGameField(canvas);
        }

        private void drawGameField(Canvas canvas) {
            rowCount = game.getRowCount();
            colCount = game.getColCount();

            cellWidth = getWidth() / colCount;
            cellHeight = getHeight() / rowCount;

            paint.setColor(Color.BLACK);

            for (int col = 0; col <= colCount; col++) {
                int x = col * cellWidth;
                canvas.drawLine(x, 0, x, getHeight(), paint);
            }

            for (int row = 0; row <= rowCount; row++) {
                int y = row * cellHeight;
                canvas.drawLine(0, y, getWidth(), y, paint);
            }

            for (int row = 0; row < rowCount; row++) {
                for (int col = 0; col < colCount; col++) {
                    int x = col * cellWidth;
                    int y = row * cellHeight;

                    Game.VaggleCell cell = game.field[row][col];

                    paint.setColor(backgroundColor);
                    canvas.drawRect(new Rect(x + 1, y + 1, x + cellWidth - 1, y + cellHeight - 1), paint);

                    if (cell.hasCircle()) {
                        canvas.drawBitmap(circleImage, null, new RectF(x + 5, y + 5, x + cellWidth - 10, y + cellHeight - 10), paint);
                    }

                    if (cell.isMarked()) {
                        canvas.drawBitmap(markedCircleImage, null, new RectF(x + 5, y + 5, x + cellWidth - 10, y + cellHeight - 10), paint);
                    }
                }
            }
        }
    }



    protected void secondClick(int row, int col) {
        game.leftMouseClick(row, col);
        gameView.invalidate();
        updateScoreText();
        checkGameOver();
    }

    protected void firstClick(int row, int col) {
        game.rightMouseClick(row, col);
        gameView.invalidate();
        checkGameOver();
    }

    private void updateTimerText(long millisUntilFinished) {
        int minutes = (int) (millisUntilFinished / 1000) / 60;
        int seconds = (int) (millisUntilFinished / 1000) % 60;

        String timeLeftFormatted = String.format("%02d:%02d", minutes, seconds);
        statusTextView.setText("Time\n" + timeLeftFormatted);
    }


    private void checkGameOver() {
        Game.GameState gameState = game.getGameState();

        if (gameState == Game.GameState.WIN) {
            gameView.setEnabled(false);
            countDownTimer.cancel();
            dialog.show();
            GameLevels.completedLevels++;
        }
    }

    private void updateScoreText() {
        scoreTextView.setText("Score: " + game.getScore());
    }
    private void resetGame() {
        int rowCount = 8;
        int colCount = 8;
        int[][] circlePositions = {{1, 3}, {1, 4}, {1, 5},
                {2, 2}, {2, 3}, {2, 4}, {4, 2}, {4, 4}, {4, 5}, {5, 1}, {5, 3}, {5, 4}, {5, 6}, {6, 1}};

        game.newGame(rowCount, colCount, circlePositions);
        gameView.setEnabled(true);
        game.setScore(0);
        updateScoreText();
        gameView.invalidate();
    }
}
