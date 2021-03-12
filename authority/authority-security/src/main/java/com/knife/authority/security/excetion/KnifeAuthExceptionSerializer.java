package com.knife.authority.security.excetion;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.knife.base.dto.BaseCode;
import com.knife.base.dto.Response;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;


@Slf4j
public class KnifeAuthExceptionSerializer extends StdSerializer<KnifeAuthException> {

    protected KnifeAuthExceptionSerializer() {
        super(KnifeAuthException.class);
    }

    @Override
    public void serialize(KnifeAuthException value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        BaseCode code = value.getCode();
        Response<Void> failed = Response.failed(code);
        gen.writeString(JSON.toJSONString(failed));
    }
}
