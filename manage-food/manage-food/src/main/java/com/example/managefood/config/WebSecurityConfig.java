package com.example.managefood.config;

import com.example.managefood.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {



    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;


    @Autowired
    public void configGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {


        //CSRF cross site req forgery là kỹ thuật tấn công bằng cách sử dụng quyền chứng thực của người sử dụng đối với 1 website khác
        httpSecurity.csrf().disable();

        //các trang không yêu cầu login
        httpSecurity.authorizeRequests().antMatchers("/", "/login");

        //trang employee yêu cầu phải login với vai trò ROLE_USER, ROLE_ADMIN
        //Nếu chưa login , nó sẽ redirect tới trang /login , dùng hasAnyRole để cho phép ai có quyền vào
        // ROLE user và admin lấy từ database ra cái mà mình chèn vô ở bước 1
        httpSecurity.authorizeRequests().antMatchers("/list-product","/accountInfo").access("hasAnyRole('ROLE_MEMBER', 'ROLE_ADMIN')");


//        Trang chỉ dành cho ADMIN
        httpSecurity.authorizeRequests().antMatchers("/list-account").access("hasAnyRole('ROLE_ADMIN')");


        //khi người dùng đã login , với vai trò là user
        //nhưng cố ý truy cập vào trang admin
        // Ngoại lệ AccessDeniedException được ném ra
        // => 403.html
        httpSecurity.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

        // cấu hình cho login form
        httpSecurity.authorizeRequests().and().formLogin()
                .loginProcessingUrl("/j_spring_security_check")
                .loginPage("/login")
                .defaultSuccessUrl("/") // khi đăng nhập thành công thì vào trang này
                .failureUrl("/login?error=true") //Khi đăng nhập sai username và password thì nhập lại
                .usernameParameter("username")
                .passwordParameter("password")
                //cau hinh cho logout
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/");

        //cau hình cho remember me
        httpSecurity.authorizeRequests().and()
                .rememberMe().tokenRepository(this.persistentTokenRepository())
                .tokenValiditySeconds(3*24*60*60); // 3 days

    }
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        InMemoryTokenRepositoryImpl memory = new InMemoryTokenRepositoryImpl(); // Ta lưu tạm remember me trong memory (RAM). Nếu cần mình có thể lưu trong database
        return memory;
    }

}