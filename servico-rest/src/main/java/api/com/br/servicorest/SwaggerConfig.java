package api.com.br.servicorest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.WebAsyncTask;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    @Bean
    public Docket api() {
        Parameter parameter = new ParameterBuilder()
                .name("Authorization")
                .description("Header Autorization (Basic para obter e Bearer para utilizar)")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false).build();
        List<Parameter> parameters = new ArrayList<Parameter>();
        parameters.add(parameter);
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .genericModelSubstitutes(WebAsyncTask.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage("api.com.br.com.servicorest"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo("API teste", "Conexao Swagger", "1.0."+simpleDateFormat.format(new Date()),
                "", "Teste" , "", ""

        );
        return apiInfo;
    }

}
