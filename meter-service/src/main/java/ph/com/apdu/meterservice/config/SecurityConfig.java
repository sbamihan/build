package ph.com.apdu.meterservice.config;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.context.request.RequestContextListener;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .requestMatchers(EndpointRequest.toAnyEndpoint()).hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
            .httpBasic();
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
    	super.configure(web);
    	web.ignoring().antMatchers("/actuator/hystrix.stream", "/actuator/turbine.stream");
    }
    
    @Bean
    @Order(0)
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }
    
}