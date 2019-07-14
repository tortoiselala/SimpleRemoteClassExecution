package com.company;

import java.lang.reflect.Method;

/**
 * @author tortoiselala
 */
public class JavaClassExecuter {
    public static String execute(byte[] classByte){
        HackSystem.clearBuffer();
        ClassModifier cm = new ClassModifier(classByte);
        byte[] modiBytes = cm.modifyUtf8Constant("java/lang/System", "com/company/HackSystem");
        HotSwapClassLoader loader = new HotSwapClassLoader();
        Class clazz = loader.loadByte(modiBytes);
        try{
            Method method = clazz.getMethod("main", new Class[]{String[].class});
            method.invoke(null, new String[]{null});
        }catch (Throwable e){
            e.printStackTrace();
        }
        return HackSystem.getBufferString();
    }
}
