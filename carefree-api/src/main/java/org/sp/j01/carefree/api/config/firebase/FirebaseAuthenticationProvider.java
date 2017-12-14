package org.sp.j01.carefree.api.config.firebase;

import com.google.api.core.ApiFuture;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sp.j01.carefree.api.config.firebase.model.FirebaseAuthenticationToken;
import org.sp.j01.carefree.api.config.firebase.model.FirebaseUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

/**
 * Thanks to https://github.com/awaters1/ for this contribution.
 */
public class FirebaseAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    private static final Logger logger = LogManager.getLogger(FirebaseAuthenticationProvider.class);

    @Autowired
    private FirebaseAuth firebaseAuth;

    @Override
    public boolean supports(Class<?> authentication) {
        return (FirebaseAuthenticationTokenFilter.class.isAssignableFrom(authentication));
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        final FirebaseAuthenticationToken authenticationToken = (FirebaseAuthenticationToken) authentication;
        Executor executor = new Executor() {
            @Override
            public void execute(Runnable command) {
                command.run();
            }
        };
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                logger.info("I'm runnin', I'm runnin' real quick.");
            }
        };
        ApiFuture<FirebaseToken> apiFuture = firebaseAuth.verifyIdTokenAsync(authenticationToken.getToken());
        apiFuture.addListener(runnable, executor);
        try {
            final FirebaseToken token = apiFuture.get();
            return new FirebaseUserDetails(token.getEmail(), token.getUid());
        } catch (InterruptedException | ExecutionException e) {
            throw new SessionAuthenticationException(e.getMessage());
        }
    }
}