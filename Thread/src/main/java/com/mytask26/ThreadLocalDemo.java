package com.mytask26;

/**
 * @author apktool
 * @package com.mytask26
 * @class ThreadLocalDemo
 * @description TODO
 * @date 2020-07-26 10:50
 */
public class ThreadLocalDemo {
    public static void main(String[] args) {
        Task task = new Task();
        task.set("demo");
        new Thread(() -> task.set("Hello Word").display()).start();
        new Thread(() -> task.display()).start();
    }

    public static class Task {
        private ThreadLocal<String> local = new ThreadLocal<>();

        public Task set(String name) {
            this.local.set(name);
            return this;
        }

        public void display() {
            System.out.println(Thread.currentThread().getName() + " -> " + local.get());
        }
    }
}
