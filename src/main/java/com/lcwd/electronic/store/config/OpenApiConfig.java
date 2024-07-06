package com.lcwd.electronic.store.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
        info = @Info(
                title = "Electronic Store Project",
                description = "This project contain all functionalities of electronic store",
                contact = @Contact(
                        name = "Shobhit Bansal",
                        email = "shobhit.bansal.150202@gmail.com"
                ),
                license = @License(
                        name = "SHOBHIT1502"
                ),
                version = "v1"
        )
)
public class OpenApiConfig {

}
