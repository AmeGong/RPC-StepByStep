package com.ame.extension;

/**
 * FileName: Holder
 * Author:   AmeGong
 * Date:     2020/12/30 20:30
 */
public class Holder<T> {
    private volatile T value;

    public T get() {return value;}

    public void set(T value) {
        this.value = value;
    }
}
