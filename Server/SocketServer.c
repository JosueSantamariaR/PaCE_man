#include "SocketServer.h"
#include <unistd.h>
#include <stdio.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <string.h>
#include <stdbool.h>
#include <arpa/inet.h>
#include <stdbool.h>
char message[1024];
bool authenticated = true;

/**
 * Metodo principal de la creacion de los sockets con la comprobacion de erores
 * dependiendo de la etapa de ejecucion en la que se encuentre
 */
void server() {
    int serverConection, clienConection, port;
    socklen_t longc;
    struct sockaddr_in serverAddr, clientAddr;
    char buffer[100];

    port = 25558;
    serverConection = socket(AF_INET, SOCK_STREAM, 0);
    bzero((char *)&serverAddr, sizeof(serverAddr));
    serverAddr.sin_family = AF_INET;
    serverAddr.sin_port = htons(port);
    serverAddr.sin_addr.s_addr = INADDR_ANY;

    // Se comprueba si hay error al asociar el puerto a la conexion
    if (bind(serverConection, (struct sockaddr *) &serverAddr, sizeof(serverAddr)) < 0) {
        close(serverConection);
        return;
    }
    while (authenticated) {
        sprintf(message, "");
        listen(serverConection, 3);
        printf("Port waiting %d\n", ntohs(serverAddr.sin_port));
        longc = sizeof(clientAddr);
        clienConection = accept(serverConection, (struct sockaddr *) &clientAddr, &longc);

        if (clienConection < 0) {
            while (clienConection < 0) {
                clienConection = accept(serverConection, (struct sockaddr *) &clientAddr, &longc);
            }
        }

        printf("Conectando con el client\n");

        //Se comprueba si se ha cerrado la conexion recv =0
        //Se comprueba si ha ocurrido un error recv < 0
        if (recv(clienConection, buffer, 100, 0) < 0) {
            close(serverConection);
            return;
        } else {
            printf("%s\n", buffer);
            bzero((char *) &buffer, sizeof(buffer));
            joinToGame();
            if(authenticated) {
                send(clienConection, message, 1024, 0);
            }
        }
        close(clienConection);
    }
    close(serverConection);
    return;

}

/**
 * Metodo encargado de mostrar las opciones al moderador
 * sobre la creacion de objetos y acciones ha realizar
 */

void joinToGame() {
    printf("\n");
    printf("------------------------------------------------------\n");
    printf("--------------------OBJECTS MENU----------------------\n");
    printf("------------------------------------------------------\n");
    printf("------------------------------------------------------\n");
    printf("1.\tPara ingresar una fruta\n");
    printf("2.\tPara ingresar un fantasma\n");
    printf("3.\tPara ingresar una pastilla\n");
    printf("4.\tModificar la velocidad de los fantasmas\n");
    printf("5.\tRegresar\n");
    printf("6.\tIngresar una vidas \n");
    printf("7.\tSalir \n");
    printf("  \tIngrese la accion a realizar: ");

    int option=0;
    scanf("%u", &option);

    if(option==1) {
        printf("\n");
        printf("1.\tCereza\n");
        printf("2.\tFresa\n");
        printf("3.\tNaranja\n");
        printf("4.\tUvas\n");
        int fruitType;
        printf("  \tIngrese la fruta a escoger: ");
        scanf("%d", &fruitType);
        int fruitScore;
        printf("  \tIngrese el puntaje de la fruta: ");
        scanf("%d", &fruitScore);
        int fruitRow;
        printf("  \tIngrese la fila: ");
        scanf("%d", &fruitRow);
        int fruitCol;
        printf("  \tIngrese la columna: ");
        scanf("%d", &fruitCol);

        /*Envía la información de la fruta*/
        sprintf(message, "fruta,%d,%d,%d,%d", fruitType, fruitScore, fruitRow, fruitCol);

    }
    if(option==2) {
        printf("\n");
        printf("1.\tBlinky\n");
        printf("2.\tPinky\n");
        printf("3.\tInky\n");
        printf("4.\tClynde\n");
        printf("  \tIngrese el fantasma a crear: ");

        int ghostType;
        scanf("%d", &ghostType);

        printf("\n\tIngrese velocidad:");
        int speed;
        scanf("%d", &speed);

        sprintf(message, "fantasma,%d,%d", ghostType, speed);


    }
    if(option==3) {

        int candyScore;
        printf("  \tIngrese el puntaje de la pastilla: ");
        scanf("%u", &candyScore);

        printf("\n\tIngrese la fila");
        int rowp;
        scanf("%d", &rowp);
        printf("\n\tIngrese la columna");
        int colump;
        scanf("%d", &colump);
        sprintf(message, "pastilla,%u,%u,%u",candyScore, rowp, colump);
    }
    if(option==4) {
        printf("\n\t Cambiar velocidad del fantasma\n");
        printf("1.\tBlinky\n");
        printf("2.\tPinky\n");
        printf("3.\tInky\n");
        printf("4.\tClynde\n");
        printf("  \tIngrese el fantasma a cambiar: ");
        int typeG;
        scanf("%d", &typeG);
        printf("\n\tNueva velocidad:");
        int speedG;
        scanf("%d", &speedG);
        sprintf(message, "velocidad,%d,%d", typeG, speedG);

    }
    if(option==5) {
        printf("No agrega nada\n");
        return;
    }
    if(option==6) {
        printf("  \tIngrese la cantidad de vidas: ");
        int lives;
        scanf("%d", &lives);
        sprintf(message, "vidas,%d",lives);
    }
    if(option==7) {
        authenticated= false;
    }
    printf("Mensaje enviado: \"%s\". \n", message);
    return;

    }




/**
 * Se inicia una vez que se elija en el menu principal
 * Se encarga de la conexion con el cliente por medio de sockets en el puerto 25578
 * Tiene las comprobaciones de errores dependiendo del estado
 *
 */
void client() {
    int socketValue = 0;
    struct sockaddr_in socketServerAddr;
    char buffer[1024];
    int PORT = 25578;

    //Comprobación de un error en la creación del socket
    if ((socketValue = socket(AF_INET, SOCK_STREAM, 0)) < 0){
        return;
    }
    socketServerAddr.sin_family = AF_INET;
    socketServerAddr.sin_port = htons(PORT);

    //Comprobación de un error en el puerto o ip incorrecta
    if(inet_pton(AF_INET, "127.0.0.1", &socketServerAddr.sin_addr)<=0)
    {
        return;
    }

    //Comprobación de un error en la conexión
    if (connect(socketValue, (struct sockaddr *)&socketServerAddr, sizeof(socketServerAddr)) < 0)
    {
        return;
    }



/**
 * Una vez establecida la coneccion se inicia el juego preguntando
 * al moderador los objetos y opciones ha agregar
 */

    joinToGame();
    send(socketValue , message , (strlen(message)+1), 0 );
    printf("\n");
    printf("1.\tPara ingresar objetos\n");
    printf("2.\tPara desconectarse\n");
    printf("  \tIngrese la accion a realizar: ");
    close(socketValue);
}




/**
 * Metodo encargado de la parte inicial en consola
 * Comprueba la autenticacion con el client y tambien
 * se desconecta del cliente dependiendo de la seleccion.
 */
void startClient() {
    printf("------------------------------------------------------\n");
    printf("--------------------PACMAN MENU-----------------------\n");
    printf("------------------------------------------------------\n");
    printf("------------------------------------------------------\n");
    printf("1.\tPara ingresar objetos\n");
    printf("2.\tPara desconectarse\n");
    printf("  \tIngrese la accion a realizar: ");


    while (authenticated) {
        int option=0;
        scanf("%u", &option);
        if(option == 2) {
            authenticated= false;
            break;
        }
        if(option == 1) {
            client();
        }
    }

}
