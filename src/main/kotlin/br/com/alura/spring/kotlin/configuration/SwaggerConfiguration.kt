package br.com.alura.spring.kotlin.configuration

import br.com.alura.spring.kotlin.repository.orm.Usuario
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ParameterBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.schema.ModelRef
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.util.*

@Configuration
@EnableSwagger2
class SwaggerConfiguration {

    @Bean
    fun forumApi(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("br.com.alura.spring.kotlin"))
            .paths(PathSelectors.ant("/**"))
            .build()
            .ignoredParameterTypes(Usuario::class.java)
            .globalOperationParameters(
                Collections.singletonList(
                    ParameterBuilder()
                        .name("Authorization")
                        .description("Header para token JWT")
                        .modelRef(ModelRef("string"))
                        .parameterType("header")
                        .required(false)
                        .build()
                )
            )
    }

}