package ru.gb.gbshopproject.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAwareBean")
@RequiredArgsConstructor
public class ShopConfig {


    @Bean
    public AuditorAware<String> auditorAwareBean() {
        return () -> Optional.of("User");
    }


}
