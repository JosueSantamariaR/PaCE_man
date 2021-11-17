#include "Game.h"
#include <stdio.h>
#include <stdbool.h>
#include <unistd.h>
#include <pthread.h>
#include <stdlib.h>


int gameBoard[31][28] = {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                         {1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1},
                         {1, 3, 1, 1, 1, 1, 3, 1, 1, 1, 1, 1, 3, 1, 1, 3, 1, 1, 1, 1, 1, 3, 1, 1, 1, 1, 3, 1},
                         {1, 3, 1, 0, 0, 1, 3, 1, 0, 0, 0, 1, 3, 1, 1, 3, 1, 0, 0, 0, 1, 3, 1, 0, 0, 1, 3, 1},
                         {1, 3, 1, 1, 1, 1, 3, 1, 1, 1, 1, 1, 3, 1, 1, 3, 1, 1, 1, 1, 1, 3, 1, 1, 1, 1, 3, 1},
                         {1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1},
                         {1, 3, 1, 1, 1, 1, 3, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 3, 1, 1, 1, 1, 3, 1},
                         {1, 3, 1, 1, 1, 1, 3, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 3, 1, 1, 1, 1, 3, 1},
                         {1, 3, 3, 3, 3, 3, 3, 1, 3, 3, 3, 3, 3, 1, 1, 3, 3, 3, 3, 3, 1, 3, 3, 3, 3, 3, 3, 1},
                         {1, 1, 1, 3, 1, 1, 3, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 3, 1, 3, 1, 1, 1, 1},
                         {1, 1, 1, 3, 1, 1, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 3, 1, 3, 3, 1, 0, 1},
                         {1, 3, 3, 3, 1, 1, 3, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 3, 1, 3, 1, 1, 3, 1, 1, 1},
                         {1, 3, 1, 1, 1, 1, 3, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 1, 3, 1, 3, 1, 1, 3, 3, 3, 1},
                         {1, 3, 3, 3, 3, 3, 3, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 3, 1, 3, 1, 1, 1, 1, 3, 1},
                         {1, 1, 1, 1, 1, 1, 3, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 3, 3, 3, 3, 3, 3, 1},
                         {1, 3, 3, 3, 3, 3, 3, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 3, 1, 3, 1, 3, 1, 1, 1, 1},
                         {1, 3, 1, 1, 1, 1, 3, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 1, 3, 1, 3, 1, 3, 3, 1, 1, 1},
                         {1, 3, 1, 0, 0, 1, 3, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 3, 1, 3, 1, 1, 3, 3, 1, 1},
                         {1, 3, 1, 0, 0, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 1, 0, 1, 3, 1, 1, 1},
                         {1, 3, 1, 1, 1, 1, 3, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 3, 1, 1, 1, 3, 1, 1},
                         {1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1},
                         {1, 1, 1, 3, 1, 1, 3, 1, 1, 1, 1, 1, 3, 1, 1, 3, 1, 1, 1, 1, 1, 3, 1, 1, 1, 1, 3, 1},
                         {1, 1, 1, 3, 1, 1, 3, 1, 1, 1, 1, 1, 3, 1, 1, 3, 1, 1, 1, 1, 1, 3, 1, 1, 1, 1, 3, 1},
                         {1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 1, 3, 3, 3, 1},
                         {1, 3, 1, 1, 1, 1, 3, 1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 3, 1, 1, 3, 1, 1, 1},
                         {1, 3, 1, 1, 1, 1, 3, 1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 3, 1, 1, 3, 1, 1, 1},
                         {1, 3, 3, 3, 3, 3, 3, 1, 1, 3, 3, 3, 3, 1, 1, 3, 3, 3, 3, 3, 1, 3, 3, 3, 3, 3, 3, 1},
                         {1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1},
                         {1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1},
                         {1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1},
                         {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};
struct Player player;
struct Fruit mapFruits;
int ghotsList[4] = {5, 6, 7, 8};
bool candyMode = false;
int newLevelBoard[31][28];

struct Ghost blinkyGhost;
struct Ghost pinkyGhost;
struct Ghost inkyGhost;
struct Ghost clyndeGhost;

pthread_t pacman;
pthread_t candyModeThread;
pthread_t blinky;
pthread_t pinky;
pthread_t inky;
pthread_t clynde;


struct Ghost {
    int type;
    int speed;
    int x;
    int y;
    int colision;
};
struct Fruit {
    int type;
    int score;
};

struct Player;


void startGame(struct Player player1) {
    showBoard(gameBoard);
    copyBoards();
    player = player1;
    char move = getMovement();
    startGameAux(move);
    return;
};

void startGameAux(char move) {
    printf("Se preciona tecla : %c \n", move);
    if (move == 'p') {
        printf("Game Stop");
        return;
    }
    if (move == 'i') {
        showBoard(gameBoard);
    }
    if (move == 'f') {
        addObjetsBoard();
    }
    if (move == 'e') {
        printf("---------------------------------------- \n");
        printf("------------------Game Data------------- \n");
        printf("---------------------------------------- \n");

        printf("Score: %d \n", player.score);
        printf("Lives: %d \n", player.lives);
        printf("Level: %d \n", player.level);
        printf("---------------------------------------- \n");
    }
    movePacman(move);
    movePacman(move);

    if (nextLevel() == true) {
        player.level++;
        resetBoard();
        ghotsList[0] = 5;
        ghotsList[1] = 6;
        ghotsList[2] = 7;
        ghotsList[3] = 8;

    }
    char move2 = getMovement();
    startGameAux(move2);
};

void createGhost(int type, int speed, int x, int y) {
    if (type == 5 || type == 6 || type == 7 || type == 8) {
        int e = entityOnBoard(x, y);
        printf("Se Encontro: %d \n", e);
        bool checkEntity = searchGhostTypes(type);
        if (e != 1 && checkEntity == true) {
            struct Ghost ghost = {type, speed, x, y, 0};
            gameBoard[x][y] = ghost.type;
            if (ghost.type == 5) {
                blinkyGhost = ghost;
            }
            if (ghost.type == 6) {
                pinkyGhost = ghost;
            }
            if (ghost.type == 7) {
                inkyGhost = ghost;

            }
            if (ghost.type == 8) {
                clyndeGhost = ghost;
            }
        } else {
            printf("Error en la posicicon del Ghost ");
        }
    } else {
        printf("Error en el tipo de Ghost ");
    }
};

void createFruit(int type, int score) {
    if (type == 9 || type == 10 || type == 11 || type == 12 || type == 13) {
        struct Fruit fruit = {type, score};
        mapFruits = fruit;
        gameBoard[17][14] = type;
        return;
    }
};

void showBoard(int **board) {
    for (int i = 0; i < 31; i++) {
        for (int j = 0; j < 28; j++) {
            printf("%d ", board[i][j]);
        }
        printf("\n");
    }
}

char getMovement() {
    char c = getchar();
    if (c == 'w') return c;
    if (c == 'a') return c;
    if (c == 's') return c;
    if (c == 'd') return c;
    if (c == 'p') return c;
    if (c == 'i') return c;
    if (c == 'e') return c;
    if (c == 'f') return c;
    else {
        getMovement();
    }
};

void movePacman(char move) {
    if (move == 'i') {
        return;
    }
    if (move == 'e') {
        return;
    }
    if (move == 'f') {
        return;
    }
    struct Pos position = searchEntity(2);
    int ix = 0;
    int jx = 0;
    int ii = position.i;
    int ji = position.j;
    if (move == 'w') ix = -1;
    if (move == 's') ix = 1;
    if (move == 'a') jx = -1;
    if (move == 'd') jx = 1;
    position.i += ix;
    position.j += jx;
    while (gameBoard[position.i][position.j] != 1) {
        int entTable = gameBoard[position.i][position.j];
        if (entTable == 5 || entTable == 6 || entTable == 7 || entTable == 8) {
            if (candyMode == false) {
                gameOver(position.i, position.j);
                break;
            } else {
                break;
            }
        }
        if (entTable == 3) player.score += 10;
        if (entTable == 9 || entTable == 10 || entTable == 11 || entTable == 12 || entTable == 13) {
            player.score += mapFruits.score;
            mapFruits.score = 0;
            mapFruits.type = 0;
        }
        gameBoard[ii][ji] = 0;
        gameBoard[position.i][position.j] = 2;
        ii = position.i;
        ji = position.j;
        position.i += ix;
        position.j += jx;
    }
};



struct Pos searchEntity(int type) {
    for (int i = 0; i < 31; i++) {
        for (int j = 0; j < 28; j++) {
            if (gameBoard[i][j] == type) {
                printf("(%d , %d) \n", i, j);
                struct Pos pos1 = {i, j};
                return pos1;
            }
        }
    }
};

int entityOnBoard(int i, int j) {
    return gameBoard[i][j];
};

bool searchGhostTypes(int type) {
    for (int i = 0; i < 4; i++) {
        if (ghotsList[i] == type) {
            ghotsList[i] = 0;
            return true;
        }
    }
    return false;
}

void gameOver(int i, int j) {
    if (player.lives == 0) {
        return;
    } else {
        player.lives -= 1;
        struct Pos ubicacion = searchEntity(2);
        gameBoard[ubicacion.i][ubicacion.j] = 0;
        gameBoard[23][14] = 2;
    }

}

void addObjetsBoard() {
    printf("\n");
    printf("------------------------------------------------------\n");
    printf("--------------------OBJECTS MENU----------------------\n");
    printf("------------------------------------------------------\n");
    printf("------------------------------------------------------\n");
    printf("1.\tPara ingresar una fruta:\n");
    printf("2.\tPara ingresar un fantasma:\n");
    char f = getchar();
    f = getchar();
    if (f == '1') {

        printf("1.\tCereza:\n");
        printf("2.\tFresa:\n");
        printf("3.\tNaranja:\n");
        printf("4.\tManzana:\n");
        printf("5.\tUvas:\n");
        printf("  \tIngrese la fruta a escoger: ");
        int t;
        scanf("%d", &t);
        printf("  \tIngrese el puntaje de la fruta: ");
        int p;
        scanf("%d", &p);
        createFruit(t, p);

        return;
    }
    if (f == '2') {
        printf("5.\tBlinky:\n");
        printf("6.\tPinky:\n");
        printf("7.\tInky:\n");
        printf("8.\tClynde:\n");
        printf("  \tIngrese el fantasma a crear: ");
        int type;
        scanf("%d", &type);
        printf("\n\t Ingrese velocidad:");
        int speed;
        scanf("%d", &speed);
        printf("\n\t Ingrese La fila");
        int row;
        scanf("%d", &row);
        printf("\n\t Ingrese La Columna");
        int colum;
        scanf("%d", &colum);
        createGhost(type, speed, row, colum);
        return;
    }
}

void copyBoards() {
    for (int i = 0; i < 31; i++) {
        for (int j = 0; j < 28; j++) {
            newLevelBoard[i][j] = gameBoard[i][j];
        }
    }

}

void resetBoard() {
    for (int i = 0; i < 31; i++) {
        for (int j = 0; j < 28; j++) {
            gameBoard[i][j] = newLevelBoard[i][j];
        }
    }

}

bool nextLevel() {
    if (scoreCount() == 0) return true;
    return false;
}

int scoreCount() {
    int cant = 0;
    for (int i = 0; i < 31; i++) {
        for (int j = 0; j < 28; j++) {
            if (gameBoard[i][j] == 3 || gameBoard[i][j] == 4) cant++;
        }
    }
    return cant;
}

void blinkyMovement() {
    int move = rand() % 4;
    struct Pos blinkyPosition = searchEntity(5);
    int ix = 0;
    int jx = 0;
    int ii = blinkyPosition.i;
    int ji = blinkyPosition.j;
    if (move == 0) ix = -1;
    if (move == 1) ix = 1;
    if (move == 2) jx = -1;
    if (move == 3) jx = 1;
    blinkyPosition.i += ix;
    blinkyPosition.j += jx;
    while (gameBoard[blinkyPosition.i][blinkyPosition.j] != 1) {
        gameBoard[ii][ji] = blinkyGhost.colision;
        blinkyGhost.colision = gameBoard[blinkyPosition.i][blinkyPosition.j];
        gameBoard[blinkyPosition.i][blinkyPosition.j] = 5;
        ii = blinkyPosition.i;
        ji = blinkyPosition.j;
        blinkyPosition.i += ix;
        blinkyPosition.j += jx;
    }
}

