package com.narturebelle.providers.security;

import com.narturebelle.providers.exception.ProviderOnboardingException;
import com.narturebelle.providers.model.Role;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

/** Who is calling. Same shape as NB-Ecom-API's AuthorizationService, limited to what this service needs. */
@Component
public class AuthorizationService {

    public Optional<FirebaseUserPrincipal> currentPrincipalOrEmpty() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof FirebaseUserPrincipal principal) {
            return Optional.of(principal);
        }
        return Optional.empty();
    }

    public FirebaseUserPrincipal currentPrincipal() {
        return currentPrincipalOrEmpty().orElseThrow(() -> new AccessDeniedException("Not signed in."));
    }

    /** The caller's users.id. Null means they never called NB-Ecom-API's POST /auth/firebase. */
    public Long requireUserId() {
        Long userId = currentPrincipal().userId();
        if (userId == null) {
            throw new ProviderOnboardingException(409, "ACCOUNT_NOT_REGISTERED", "Please finish signing in first.");
        }
        return userId;
    }

    public void requireRole(Role role) {
        if (!currentPrincipal().roles().contains(role)) {
            throw new AccessDeniedException("Requires " + role);
        }
    }

    public boolean hasRole(Role role) {
        return currentPrincipal().roles().contains(role);
    }
}
