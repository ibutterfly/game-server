package com.butterfly.game.netty.core;

import com.butterfly.game.netty.invote.Action;
import com.butterfly.game.netty.invote.ActionMapUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by 杨国 on 2019/10/14.
 *
 * @author yangguo
 */
@Component
public class ActionBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Method[] methods = bean.getClass().getMethods();
        for (Method method : methods) {
            ActionMap actionMap = method.getAnnotation(ActionMap.class);
            if (actionMap != null) {
                Action action = new Action();
                action.setMethod(method);
                action.setObject(bean);
                ActionMapUtil.put(actionMap.key(), action);
            }
        }
        return bean;
    }
}
