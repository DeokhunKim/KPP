package Personal.KPP.core.login.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

@Service
public class LoginService {

    private ObjectMapper objectMapper = new ObjectMapper();
    private final MemberAPIService memberService ;

    public LoginService(MemberAPIService memberService) {
        this.memberService = memberService;
    }

    public String login(String userName, String password) {
        Map<String, String> user = new HashMap<>();
        user.put("name", userName);
        user.put("password", password);

        String getToken = memberService.login(user);
        JSONParser jsonParser = new JSONParser();
        JSONObject obj = null;
        try {
            obj = (JSONObject)jsonParser.parse(getToken);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return (String)obj.get("kpp_t");
    }

    public boolean registryMember(String userName, String password) {
        Map<String, String> user = new HashMap<>();
        user.put("name", userName);
        user.put("password", password);
        Long registry = memberService.registry(user);

        return true;
    }

    public boolean isAuthorized(String token) {
        String result = memberService.isAuthorized(token);
        if (result == null) {
            return false;
        }
        if (result.equals("OK")) {
            return true;
        }
        else {
            return false;
        }
    }

    private ResponseEntity<String> requestUserInfo(String uri, HttpMethod httpMethod, String userName, String password) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        String params = null;
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("userName", userName);
            map.put("password", password);
            params = objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }

        HttpEntity entity = new HttpEntity(params, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> exchange = restTemplate.exchange(uri, httpMethod, entity, String.class);

        System.out.println("exchange.getStatusCode() = " + exchange.getStatusCode());
        System.out.println("exchange.getBody() = " + exchange.getBody());
        return exchange;
    }

}
