package com.butterfly.game.netty.core;

import java.lang.annotation.*;

/**
 * Created by 杨国 on 2019/10/14.
 * 类似于mvc的@RequestMapping
 * @author yangguo
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface ActionMap {
    int key();
}
