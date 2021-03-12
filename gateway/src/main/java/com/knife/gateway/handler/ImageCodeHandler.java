package com.knife.gateway.handler;

import com.google.code.kaptcha.Producer;
import com.google.common.base.Joiner;
import com.knife.gateway.common.SecurityConstants;
import com.knife.base.provider.within.DesAlgorithm;
import com.knife.base.utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author knife
 * @date 2019/11/6 14:28
 * @description
 */
@Slf4j
@Component
@AllArgsConstructor
public class ImageCodeHandler implements HandlerFunction<ServerResponse> {
    private final Producer producer;
    private static final String IMAGE_TYPE = "jpeg";

    @Override
    public Mono<ServerResponse> handle(ServerRequest request) {
        String code = producer.createText();
        BufferedImage imageCode = producer.createImage(code);
        String key = Joiner.on(":").join(DateUtils.time(), code);
        String verifyCodeEncrypt = DesAlgorithm.create(code).encrypt(key);
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try {
            ImageIO.write(imageCode, IMAGE_TYPE, os);
        } catch (IOException e) {
            log.error("imageIo write error ", e);
            return Mono.error(e);
        }
        return ServerResponse.status(HttpStatus.OK)
                .contentType(MediaType.IMAGE_JPEG)
                .header(SecurityConstants.VERIFY_CODE_ENCRYPT, verifyCodeEncrypt)
                .body(BodyInserters.fromResource(new ByteArrayResource(os.toByteArray())));
    }
}
