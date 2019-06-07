#include<stdio.h>
#include<stdlib.h>
#include<dlfcn.h>

/**
 * make shared
 * gcc demo.c -o a.out -Wall -std=c11 -ldl
 */

typedef int (*pFunc)(int a, int b);

int main(int argc, char* argv[])
{
    void* handle = NULL;
    handle = dlopen("/tmp/libdemo.so", RTLD_LAZY);
    if (!handle)
    {
        printf("%s", dlerror());
    }

    pFunc p;
    p = dlsym(handle, "sum");
    printf("%d\n", p(20, 5));

    p = dlsym(handle, "sub");
    printf("%d\n", p(20, 5));

    dlclose(handle);

    return 0;
}
