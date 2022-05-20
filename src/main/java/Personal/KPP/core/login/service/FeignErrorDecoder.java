package Personal.KPP.core.login.service;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class FeignErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        switch (response.status()) {
            case HttpServletResponse.SC_UNAUTHORIZED:
                log.info("SC_UNAUTHORIZED");
                if (methodKey.contains("login")) {
                    log.info("login method key");
                    return new ResponseStatusException(HttpStatus.valueOf(response.status()));
                }
                if (methodKey.contains("registry")) {
                    log.info("registry method key");
                    return new ResponseStatusException(HttpStatus.valueOf(response.status()));
                }
                break;
            case HttpServletResponse.SC_OK:
                log.info("SC_OK");
                break;
            case HttpServletResponse.SC_BAD_REQUEST:
                log.info("BAD_REQUEST");
                if (methodKey.contains("isAuthorized")) {
                    log.info("login method key");
                    return new ResponseStatusException(HttpStatus.valueOf(response.status()));
                }
                break;
            default:
                log.info("default : {}", response.status());
                break;
        }
        return null;
    }

    private void resetCookie(Response response) {

    }

}
