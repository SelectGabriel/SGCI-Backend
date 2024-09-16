package br.tads.ufpr.sgci_backend.authentication.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JwtBlacklistService {

    private Map<String, Long> blacklist = new HashMap<>(); // Map<Token, ExpirationTime>

    // Add a token to the blacklist with its expiration time
    public void blacklistToken(String token, long expirationTime) {
        blacklist.put(token, expirationTime);
    }

    // Check if the token is blacklisted (and hasn't expired)
    public boolean isTokenBlacklisted(String token) {
        Long expirationTime = blacklist.get(token);
        if (expirationTime == null || expirationTime < System.currentTimeMillis()) {
            return false; // Token is not blacklisted or has expired
        }
        return true;
    }

    // Remove expired tokens from the blacklist
    public void removeExpiredTokens() {
        Iterator<Map.Entry<String, Long>> iterator = blacklist.entrySet().iterator();
        long now = System.currentTimeMillis();
        while (iterator.hasNext()) {
            Map.Entry<String, Long> entry = iterator.next();
            if (entry.getValue() < now) {
                iterator.remove(); // Remove the token if it has expired
            }
        }
    }
}
