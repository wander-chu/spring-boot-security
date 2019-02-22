package com.example.springbootsecurity.config;

import com.example.springbootsecurity.security.CustomUserService;
import com.example.springbootsecurity.util.MD5Utils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.ForwardAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    UserDetailsService customUserService() {
        return new CustomUserService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService()).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return MD5Utils.encode((String) rawPassword);
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return encodedPassword.equals(MD5Utils.encode((String) rawPassword));
            }
        }); //user Details Service验证;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                //设置静态的资源允许所有访问
                .antMatchers("/static/**").permitAll()
                //其他所有资源都需要登陆后才能访问
                .anyRequest().authenticated()
                .and()
                //设置默认登陆页面/login
                .formLogin().loginPage("/login")
                //强制指定登陆成功后跳转的路径
                .successHandler(new ForwardAuthenticationSuccessHandler("/"))
                .failureUrl("/login?error").permitAll()
                //设置缓存，默认2周有效
                .and().rememberMe().tokenValiditySeconds(1209600).key("mykey")
                .and().logout().permitAll()
                //禁用csrf
                .and().csrf().disable();
    }
}