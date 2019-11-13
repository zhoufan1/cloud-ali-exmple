package com.example.foundation.serializer;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import java.util.List;

public class BeanSerializer extends BeanSerializerModifier {

    @Override
    public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc, List<BeanPropertyWriter> beanProperties) {
        // 循环所有的beanPropertyWriter
        for (BeanPropertyWriter writer : beanProperties) {
            // 判断字段的类型，如果是array，list，set则注册nullSerializer
            if (isArrayType(writer)) {
                //给writer注册一个自己的nullSerializer
                writer.assignNullSerializer(new ArrayNullJsonSerializer());
            } else {
                writer.assignNullSerializer(new BasicTypeNullSerializer());
            }
        }
        return beanProperties;
    }

    /**
     * 判断是不是数组类型
     * @param writer
     * @return
     */
    private boolean isArrayType(BeanPropertyWriter writer) {
        JavaType clazz = writer.getType();
        return clazz.isArrayType() || clazz.isCollectionLikeType();

    }

}
