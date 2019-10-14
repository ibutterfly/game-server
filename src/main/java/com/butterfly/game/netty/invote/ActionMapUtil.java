package com.butterfly.game.netty.invote;

import com.butterfly.game.netty.message.Message;
import io.netty.channel.ChannelHandlerContext;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 杨国 on 2019/10/14.
 *  请求分发器
 * @author yangguo
 */
public class ActionMapUtil {
    private static Map<Integer, Action> map = new HashMap<>();

    public static Object invote(Integer key, Object... args) throws Exception {
        Action action = map.get(key);
        if (action != null) {
            Method method = action.getMethod();
            try {
                return method.invoke(action.getObject(), args);
            } catch (Exception e) {
                throw e;
            }
        }
        return null;
    }

    public static void put(Integer key, Action action) {
        map.put(key, action);
    }
}
