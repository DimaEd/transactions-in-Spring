package com.ednach.app.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "com.ednach")
@Import({WebConfiguration.class, DatabaseConfiguration.class})
public class AppConfiguration {

}