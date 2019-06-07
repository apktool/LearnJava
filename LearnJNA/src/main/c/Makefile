
CC = gcc
CFLAGS := -Wall -std=c11

OBJS := main.o fun.o

MAINOUTPUT = a.out
SHAREDOUTPUT = b.out
LIBOUTPUT = libdemo.so

all: main demo

# ----- 生成main.c 和 demo.c 的可执行文件 -----

main: main.o fun.o
	$(CC) $(CFLAGS) $^ -o $(MAINOUTPUT)

demo: shared
	$(CC) $(CFLAGS) demo.c -o $(SHAREDOUTPUT) -ldl

# ------------- 生成动态库 --------------------

shared: fun.o
	@$(CC) $(CFLAGS) $^ -o $(LIBOUTPUT) -fPIC -shared
	@cp $(LIBOUTPUT) /tmp

# ------------- 生成目标文件 ------------------

main.o: main.c
	@$(CC) $(CFLAGS) -c $< -o $@

fun.o: fun.c
	@$(CC) $(CFLAGS) -c $< -o $@

# ------------- 显示帮助文档 ------------------

help:
	@echo "-------------------------------------------"
	@echo "Generate execute file   | make main"
	@echo "Generate shared library | make shared"
	@echo "Find help documents     | make help"
	@echo "-------------------------------------------"

.PHONY: clean
clean:
	@rm -f $(OBJS) $(LIBOUTPUT) $(MAINOUTPUT) $(SHAREDOUTPUT)
