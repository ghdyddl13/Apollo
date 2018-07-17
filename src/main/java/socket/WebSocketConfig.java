/**
 프로젝트 : Apollo
 파일이름 : WebSocketConfig.java 
 날      짜 : 2018. 7. 17.
 작 성  자 : 이 진 우
*/

package socket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
 
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer{
 
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // 모든사이트는 "*" -> "특정사이트 주소"로 변경
        registry.addHandler(socket(), "/socket.htm").setAllowedOrigins("/*");
    }
 
    @Bean
    public WebSocketHandler socket() {
        return new ChatWebSocketHandler();
    }
 
}
