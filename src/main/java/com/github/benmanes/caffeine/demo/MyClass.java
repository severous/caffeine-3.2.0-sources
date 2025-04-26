package com.github.benmanes.caffeine.demo;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

public class MyClass {

    private volatile Object refValue;

    private static final VarHandle REF_VALUE_HANDLE;

    static {
        try {
            REF_VALUE_HANDLE = MethodHandles.lookup()
                    .findVarHandle(MyClass.class, "refValue", Object.class);
        } catch (ReflectiveOperationException e) {
            throw new Error(e);
        }
    }

    public Object get1() {
        return this.refValue;
    }

    public Object get2() {
        return REF_VALUE_HANDLE.get(this);
    }

    public Object get3() {
        return REF_VALUE_HANDLE.getVolatile(this);
    }

    public void set1(Object newValue) {
        this.refValue = newValue;
    }

    public void set2(Object newValue) {
        REF_VALUE_HANDLE.set(this, newValue);
    }

    public void set3(Object newValue) {
        REF_VALUE_HANDLE.setVolatile(this, newValue);
    }

}
