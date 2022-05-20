package Personal.KPP.core.login.service;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "auth", url = "http://localhost:8099")
public interface MemberAPIService {
    @PostMapping("/api/member/join")
    Long registry(@RequestBody Map<String, String> user);

    @PostMapping("/api/member/login")
    String login(@RequestBody Map<String, String> user);

    @PostMapping("/api/member/authorized")
    String isAuthorized(@RequestBody String token);
}
