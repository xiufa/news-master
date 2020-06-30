package o2oboot.config.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import o2oboot.util.EncryptPropertyPlaceholderConfigurer;

@Configuration
public class EncryptPropertyPlaceholderConfigurerConfiguration {

    @Bean(name="EncryptPropertyPlaceholderConfigurer")
    public EncryptPropertyPlaceholderConfigurer getE(){
        EncryptPropertyPlaceholderConfigurer encryptPropertyPlaceholderConfigurer=new EncryptPropertyPlaceholderConfigurer();
        encryptPropertyPlaceholderConfigurer.setLocations(new ClassPathResource("application.properties"));
        return encryptPropertyPlaceholderConfigurer;
    }


}
