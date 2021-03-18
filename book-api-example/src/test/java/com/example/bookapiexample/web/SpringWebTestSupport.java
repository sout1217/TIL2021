package com.example.bookapiexample.web;

import com.example.bookapiexample.domain.SpringTestSupport;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ResourceLoader;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

@AutoConfigureMockMvc
@Import(RestDocsConfiguration.class)
@ExtendWith(RestDocumentationExtension.class) // spring-restdocs-mockmvc
public class SpringWebTestSupport extends SpringTestSupport {

    protected final String CLASSPATH = "classpath:";
    @Autowired
    protected ObjectMapper mapper;
    @Autowired
    protected MockMvc mvc;
    @Autowired
    protected RestDocsConfiguration restdocs;
    @Autowired
    protected RestDocumentationResultHandler write;
    @Autowired
    protected ResourceLoader resourceLoader;

    @BeforeEach
    void setUp(WebApplicationContext context, RestDocumentationContextProvider provider) {


        this.mvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(MockMvcRestDocumentation.documentationConfiguration(provider))
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(MockMvcResultHandlers.print())
                .alwaysDo(restdocs.write())
                .build()
        ;
    }
}

@Configuration
class RestDocsConfiguration {

    @Bean
    public RestDocumentationResultHandler write() {
        return MockMvcRestDocumentation.document(
                String.format(
                        "%s/%s",
                        Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                        Preprocessors.preprocessResponse(Preprocessors.prettyPrint())
                )
        );
    }
}

