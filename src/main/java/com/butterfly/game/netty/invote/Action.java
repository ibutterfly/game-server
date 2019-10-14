package com.butterfly.game.netty.invote;

import java.io.ObjectOutput;
import java.lang.reflect.Method;

/**
 * Created by 杨国 on 2019/10/14.
 *
 * @author yangguo
 */
public class Action {
    private Method method;
    private Object object;

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
