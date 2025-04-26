package org.fubabz.mcp.llm;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class LLMApiSampleApplication {

    public static void main(String[] args) {

        new SpringApplicationBuilder(LLMApiSampleApplication.class)
                .run(args);
    }
}
