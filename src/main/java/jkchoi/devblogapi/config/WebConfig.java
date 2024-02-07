package jkchoi.devblogapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // CORS를 적용할 URL 패턴 정의, 와일드카드 사용
                .allowedOrigins("http://localhost:3000")            // 자원 공유를 허락할 Origin을 지정, *로 모든 Origin 허락
                .allowedMethods("*") // 허용할 HTTP Method 지정
                .allowedHeaders("Authorization", "Content-Type") // 클라이언트측의 CORS요청에 허용되는 헤더 지정
                .exposedHeaders("Custom-Header")    // 클라이언트측 응답에서 노출되는 헤더를 지정
                .allowCredentials(true) // 클라이언트 측에 대한 응답에 credentials(쿠키,인증헤더)를 포함할 수 있는지 여부
                .maxAge(3600);  // 원하는 시간만큼 pre-flight 리퀘스트를 캐싱 해 둘 수 있음
    }
}
