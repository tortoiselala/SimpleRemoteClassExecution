package com.company;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

/**
 * @author tortoiselala
 */
public class HackSystem {
    public final static InputStream IN = java.lang.System.in;

    private static ByteArrayOutputStream buffer = new ByteArrayOutputStream();

    private final static PrintStream OUT = new PrintStream(buffer);

    public final static PrintStream ERR = OUT;

    public static String getBufferString(){
        return buffer.toString();
    }

    public static void clearBuffer(){
        buffer.reset();
    }

    public static void setSecurityManager(final SecurityManager s){
        System.setSecurityManager(s);
    }

    public static SecurityManager getSecurityManager() {
        return System.getSecurityManager();
    }

    public static long currentTimeMillis(){
        return System.currentTimeMillis();
    }

    public static void arraycopy(Object src, int srcPos, Object dest, int destPos, int length){
        System.arraycopy(src, srcPos, dest, destPos, length);
    }
}
