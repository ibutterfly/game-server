package com.butterfly.game.netty.core;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * Created by 杨国 on 2019/10/14.
 * 类似于mvc的Controller注解
 * @author yangguo
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Component
public @interface NettyController {
}
