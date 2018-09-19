package tk.roydgar.wildfly.security.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import tk.roydgar.wildfly.exception.WrongAuthenticationTokenException;
import tk.roydgar.wildfly.model.entity.Token;
import tk.roydgar.wildfly.model.repository.TokenRepository;
import tk.roydgar.wildfly.security.details.UserDetailsImpl;
import tk.roydgar.wildfly.security.token.TokenAuthentication;

import java.util.Optional;

@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        TokenAuthentication tokenAuthentication = (TokenAuthentication) authentication;

        Optional<Token> optionalToken = tokenRepository.findByValue(tokenAuthentication.getName());

        if (optionalToken.isPresent()) {
            UserDetails userDetails = new UserDetailsImpl(optionalToken.get().getUser());
            tokenAuthentication.setUserDetails(userDetails);
            tokenAuthentication.setAuthenticated(true);
            return  tokenAuthentication;
        } else {
            throw new WrongAuthenticationTokenException("Incorrect token " + tokenAuthentication.getName());
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return TokenAuthentication.class.equals(authentication);
    }

}
