package com.fan.vturbo.entity.info;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "wukong")
public class Wukong {
    private String wufan;
}
