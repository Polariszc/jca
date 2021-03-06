package com.github.ofofs.jca.processor;

import com.github.ofofs.jca.annotation.Handler;
import com.github.ofofs.jca.model.JcaClass;
import com.github.ofofs.jca.util.PropertiesUtil;

import javax.annotation.processing.RoundEnvironment;
import java.util.Set;

/**
 * @author kangyonggan
 * @since 6/26/18
 */
public abstract class AbstractJcaProcessor extends BaseProcessor {

    protected AbstractJcaProcessor(RoundEnvironment env) {
        super(env);
    }

    /**
     * 获取注解句柄
     *
     * @param type 注解类型
     * @return 返回注解句柄
     */
    protected JcaClass getHandler(Handler.Type type) {
        Set<JcaClass> handlers = getJcaClasses(Handler.class);
        for (JcaClass handler : handlers) {
            Handler anno = handler.getClassSym().getAnnotation(Handler.class);
            if (anno.value() == type) {
                return handler;
            }
        }

        return null;
    }

    /**
     * 判断注解是否可用
     *
     * @param type 注解句柄类型
     * @return 返回true代表可用，返回false代表不可用(默认可用)
     */
    protected boolean isEnable(Handler.Type type) {
        // 优先使用@Handler注解
        Set<JcaClass> handlers = getJcaClasses(Handler.class);
        for (JcaClass handler : handlers) {
            Handler anno = handler.getClassSym().getAnnotation(Handler.class);
            if (anno.value() == type) {
                return anno.enable();
            }
        }

        // 没有@Handler的情况，以配置文件jca.properties为准， 缺省为true
        return PropertiesUtil.isEnable(type);
    }
}
