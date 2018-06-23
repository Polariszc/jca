package com.github.ofofs.jca.model;

import com.github.ofofs.jca.util.JcaUtil;
import com.sun.tools.javac.code.Symbol;

/**
 * @author kangyonggan
 * @since 6/22/18
 */
public class JcaClass {

    /**
     * 类
     */
    private Symbol.ClassSymbol clazz;

    public JcaClass(Symbol.ClassSymbol clazz) {
        this.clazz = clazz;
    }

    /**
     * 插入一个字段
     *
     * @param jcaField 字段
     * @return 返回当前类
     */
    public JcaClass insert(JcaField jcaField) {
        JcaUtil.createField(this, jcaField);
        return this;
    }

    public Symbol.ClassSymbol getClazz() {
        return clazz;
    }

    /**
     * 获取类的包名
     *
     * @return 返回类的包名
     */
    public String getPackageName() {
        String fullName = clazz.fullname.toString();
        return fullName.substring(0, fullName.lastIndexOf("."));
    }

    public String getClassName() {
        String fullName = clazz.fullname.toString();
        return fullName.substring(fullName.lastIndexOf(".") + 1);
    }

    /**
     * 添加接口
     *
     * @param interfaceClass 接口类
     * @return 返回当前类
     */
    public JcaClass addInterface(Class<?> interfaceClass) {
        JcaUtil.addInterface(this, interfaceClass);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        JcaClass jcaClass = (JcaClass) o;

        return clazz != null ? clazz.equals(jcaClass.clazz) : jcaClass.clazz == null;
    }

    @Override
    public int hashCode() {
        return clazz != null ? clazz.hashCode() : 0;
    }
}