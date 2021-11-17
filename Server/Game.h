#include <stdbool.h>

struct Ghost;
struct Fruit;
struct Player{
    int lives;
    int level;
    int score;
};
struct Pos{
    int i;
    int j;
};


void createGhost(int type, int speed, int x, int y);
void createFruit(int type, int score);
void startGame(struct Player player1);
void startGameAux(char move);
void showBoard(int **board);
char getMovement();
void movePacman(char move);
struct Pos searchEntity(int type);
void gameOver(int i, int j);
void addObjetsBoard();
int entityOnBoard(int i, int j);
bool searchGhostTypes(int type);
void copyBoards();
void resetBoard();
bool nextLevel();
int scoreCount();
void blinkyMovement();



