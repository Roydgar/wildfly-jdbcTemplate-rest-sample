package tk.roydgar.wildfly.security.provider;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import tk.roydgar.wildfly.model.entity.Token;
import tk.roydgar.wildfly.model.repository.TokenRepository;
import tk.roydgar.wildfly.security.token.TokenAuthentication;

import java.util.Optional;
import java.util.logging.Logger;

@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        TokenAuthentication tokenAuthentication = (TokenAuthentication) authentication;

        Optional<Token> optionalToken = tokenRepository.findByValue(tokenAuthentication.getName());

        if (optionalToken.isPresent()) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(optionalToken.get().getUser().getLogin());
            tokenAuthentication.setUserDetails(userDetails);
            tokenAuthentication.setAuthenticated(true);

            return  tokenAuthentication;
        }

        throw new IllegalStateException("Wrong security token " + authentication.getName());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return TokenAuthentication.class.equals(authentication);
    }
}
