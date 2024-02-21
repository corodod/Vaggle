package com.example.vagglegame;

public class Game {
    public enum GameState {
        NOT_STARTED,
        PLAYING,
        WIN
    }


    public static class VaggleCell {
        private boolean circle;
        private boolean marked;

        public VaggleCell(boolean circle) {
            this.circle = circle;
            this.marked = false;
        }

        public boolean hasCircle() {
            return circle;
        }

        public void setCircle(boolean circle) {
            this.circle = circle;
        }

        public boolean isMarked() {
            return marked;
        }

        public void setMarked(boolean marked) {
            this.marked = marked;
        }
    }

    public VaggleCell[][] field;
    private int circleCount;
    private GameState gameState;

    private int score = 0;

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public Game() {
        field = null;
        circleCount = 0;
        gameState = GameState.NOT_STARTED;
    }

    public void newGame(int rowCount, int colCount, int[][] circlePositions) {
        field = new VaggleCell[rowCount][colCount];
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                field[row][col] = new VaggleCell(false);
            }
        }

        for (int[] position : circlePositions) {
            int row = position[0];
            int col = position[1];
            field[row][col].setCircle(true);
        }

        this.circleCount = circlePositions.length;
        gameState = GameState.PLAYING;
    }

    public void leftMouseClick(int row, int col) {
        if (gameState == GameState.PLAYING) {
            if (row >= 0 && row < field.length && col >= 0 && col < field[0].length) {
                VaggleCell clickedCell = field[row][col];
                if (!clickedCell.hasCircle()) {
                    if (row > 1 && field[row - 1][col].hasCircle() && field[row - 2][col].isMarked()) {
                        clickedCell.setCircle(true);
                        clickedCell.setMarked(true);
                        field[row - 1][col].setCircle(false);
                        field[row - 2][col].setCircle(false);
                        field[row - 2][col].setMarked(false);
                        circleCount--;
                        score+=10;
                        if (circleCount == 1) {
                            gameState = GameState.WIN;
                        }
                    } else if (row < field.length - 2 && field[row + 1][col].hasCircle() && field[row + 2][col].isMarked()) {
                        clickedCell.setCircle(true);
                        clickedCell.setMarked(true);
                        field[row + 1][col].setCircle(false);
                        field[row + 2][col].setCircle(false);
                        field[row + 2][col].setMarked(false);
                        circleCount--;
                        score+=10;

                        if (circleCount == 1) {
                            gameState = GameState.WIN;
                        }
                    } else if (col > 1 && field[row][col - 1].hasCircle() && field[row][col - 2].isMarked()) {
                        clickedCell.setCircle(true);
                        clickedCell.setMarked(true);
                        field[row][col - 1].setCircle(false);
                        field[row][col - 2].setCircle(false);
                        field[row][col - 2].setMarked(false);
                        circleCount--;
                        score+=10;
                        if (circleCount == 1) {
                            gameState = GameState.WIN;
                        }
                    } else if (col < field[0].length - 2 && field[row][col + 1].hasCircle() && field[row][col + 2].isMarked()) {
                        clickedCell.setCircle(true);
                        clickedCell.setMarked(true);
                        field[row][col + 1].setCircle(false);
                        field[row][col + 2].setCircle(false);
                        field[row][col + 2].setMarked(false);
                        circleCount--;
                        score+=10;
                        if (circleCount == 1) {
                            gameState = GameState.WIN;
                        }
                    }
                }
            }
        }
    }

    public void rightMouseClick(int row, int col) {
        if (gameState == GameState.PLAYING) {
            if (row >= 0 && row < field.length && col >= 0 && col < field[0].length) {
                VaggleCell clickedCell = field[row][col];
                if (clickedCell.hasCircle()) {
                    if (clickedCell.isMarked()) {
                        clickedCell.setMarked(false);
                    } else {
                        for (int i = 0; i < field.length; i++) {
                            for (int j = 0; j < field[i].length; j++) {
                                if (field[i][j].hasCircle() && field[i][j].isMarked()) {
                                    field[i][j].setMarked(false);
                                }
                            }
                        }
                        clickedCell.setMarked(true);
                    }
                }
            }
        }
    }

    public int getRowCount() {
        return field == null ? 0 : field.length;
    }

    public int getColCount() {
        return field == null ? 0 : field[0].length;
    }

    public GameState getGameState() {
        return gameState;
    }
}
