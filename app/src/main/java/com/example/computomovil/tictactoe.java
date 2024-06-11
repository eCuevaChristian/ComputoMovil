package com.example.computomovil;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.LinkedList;

public class tictactoe extends AppCompatActivity {

    private ImageView img1 = null;
    private ImageView img2 = null;
    private ImageView img3 = null;
    private ImageView img4 = null;
    private ImageView img5 = null;
    private ImageView img6 = null;
    private ImageView img7 = null;
    private ImageView img8 = null;
    private ImageView img9 = null;
    private ImageView xturno = null;
    private ImageView oturno = null;
    private String turno = "x"; // Asumiendo que 'turno' se inicializa en "x"
    private String[][] board = new String[3][3];
    private Button resetButton = null;
    private Button exitButton = null;

    // Listas para rastrear los movimientos de cada jugador
    private LinkedList<int[]> xMoves = new LinkedList<>();
    private LinkedList<int[]> oMoves = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tictactoe);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Asignación de ImageView
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        img4 = findViewById(R.id.img4);
        img5 = findViewById(R.id.img5);
        img6 = findViewById(R.id.img6);
        img7 = findViewById(R.id.img7);
        img8 = findViewById(R.id.img8);
        img9 = findViewById(R.id.img9);
        xturno = findViewById(R.id.xturno);
        oturno = findViewById(R.id.oturno);
        resetButton = findViewById(R.id.reset_button);
        exitButton = findViewById(R.id.exit_button);

        // Inicializar el tablero
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = "";
            }
        }

        // Establecer el OnClickListener para cada ImageView
        img1.setOnClickListener(v -> clickImage(v, 0, 0));
        img2.setOnClickListener(v -> clickImage(v, 0, 1));
        img3.setOnClickListener(v -> clickImage(v, 0, 2));
        img4.setOnClickListener(v -> clickImage(v, 1, 0));
        img5.setOnClickListener(v -> clickImage(v, 1, 1));
        img6.setOnClickListener(v -> clickImage(v, 1, 2));
        img7.setOnClickListener(v -> clickImage(v, 2, 0));
        img8.setOnClickListener(v -> clickImage(v, 2, 1));
        img9.setOnClickListener(v -> clickImage(v, 2, 2));

        // Establecer el OnClickListener para el botón de reinicio
        resetButton.setOnClickListener(v -> resetGame());

        // Establecer el OnClickListener para el botón de salir
        exitButton.setOnClickListener(v -> finish());
    }

    void clickImage(View view, int row, int col) {
        if (turno.equals("x")) {
            handleMove(view, row, col, "x", xMoves);
            xturno.setBackgroundColor(Color.WHITE);
            oturno.setBackgroundColor(Color.RED);
            turno = "o";
        } else {
            handleMove(view, row, col, "o", oMoves);
            xturno.setBackgroundColor(Color.RED);
            oturno.setBackgroundColor(Color.WHITE);
            turno = "x";
        }
        checkWinner();
    }

    void handleMove(View view, int row, int col, String player, LinkedList<int[]> moves) {
        if (player.equals("x")) {
            view.setBackgroundResource(R.drawable.equis);
        } else {
            view.setBackgroundResource(R.drawable.circulo);
        }

        board[row][col] = player;
        moves.add(new int[]{row, col});
        view.setEnabled(false);

        if (moves.size() > 3) {
            int[] firstMove = moves.poll();
            int firstRow = firstMove[0];
            int firstCol = firstMove[1];

            board[firstRow][firstCol] = "";
            ImageView firstImageView = getImageView(firstRow, firstCol);
            firstImageView.setBackgroundResource(R.drawable.cuadrado);
            firstImageView.setEnabled(true);
        }
    }

    ImageView getImageView(int row, int col) {
        switch (row * 3 + col) {
            case 0:
                return img1;
            case 1:
                return img2;
            case 2:
                return img3;
            case 3:
                return img4;
            case 4:
                return img5;
            case 5:
                return img6;
            case 6:
                return img7;
            case 7:
                return img8;
            case 8:
                return img9;
            default:
                return null;
        }
    }

    void resetGame() {
        img1.setBackgroundResource(R.drawable.cuadrado);
        img2.setBackgroundResource(R.drawable.cuadrado);
        img3.setBackgroundResource(R.drawable.cuadrado);
        img4.setBackgroundResource(R.drawable.cuadrado);
        img5.setBackgroundResource(R.drawable.cuadrado);
        img6.setBackgroundResource(R.drawable.cuadrado);
        img7.setBackgroundResource(R.drawable.cuadrado);
        img8.setBackgroundResource(R.drawable.cuadrado);
        img9.setBackgroundResource(R.drawable.cuadrado);

        img1.setEnabled(true);
        img2.setEnabled(true);
        img3.setEnabled(true);
        img4.setEnabled(true);
        img5.setEnabled(true);
        img6.setEnabled(true);
        img7.setEnabled(true);
        img8.setEnabled(true);
        img9.setEnabled(true);

        turno = "x";
        xturno.setBackgroundColor(Color.RED);
        oturno.setBackgroundColor(Color.WHITE);

        // Resetear el tablero
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = "";
            }
        }

        // Limpiar las listas de movimientos
        xMoves.clear();
        oMoves.clear();
    }

    void checkWinner() {
        // Comprobar filas, columnas y diagonales
        for (int i = 0; i < 3; i++) {
            if (board[i][0].equals(board[i][1]) && board[i][0].equals(board[i][2]) && !board[i][0].isEmpty()) {
                declareWinner(board[i][0]);
                return;
            }
            if (board[0][i].equals(board[1][i]) && board[0][i].equals(board[2][i]) && !board[0][i].isEmpty()) {
                declareWinner(board[0][i]);
                return;
            }
        }
        if (board[0][0].equals(board[1][1]) && board[0][0].equals(board[2][2]) && !board[0][0].isEmpty()) {
            declareWinner(board[0][0]);
            return;
        }
        if (board[0][2].equals(board[1][1]) && board[0][2].equals(board[2][0]) && !board[0][2].isEmpty()) {
            declareWinner(board[0][2]);
            return;
        }

        // Comprobar si hay empate
        boolean emptySpace = false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].isEmpty()) {
                    emptySpace = true;
                    break;
                }
            }
            if (emptySpace) break;
        }
        if (!emptySpace) {
            Toast.makeText(this, "Empate", Toast.LENGTH_SHORT).show();
            resetGame();
        }
    }

    void declareWinner(String winner) {
        Toast.makeText(this, "Ganador: " + winner.toUpperCase(), Toast.LENGTH_SHORT).show();
        disableBoard();
    }

    void disableBoard() {
        img1.setEnabled(false);
        img2.setEnabled(false);
        img3.setEnabled(false);
        img4.setEnabled(false);
        img5.setEnabled(false);
        img6.setEnabled(false);
        img7.setEnabled(false);
        img8.setEnabled(false);
        img9.setEnabled(false);
    }
}
