#include<stdio.h>
#include<stdlib.h>
#include"fun.h"

int (*fun)(int, int);

int main(int argc, char* argv[]) {
    int a = 10;
    int b = 2;

    fun = sum;
    printf("%d\n", fun(a, b));

    fun = sub;
    printf("%d\n", fun(a, b));
    return 0;
}
