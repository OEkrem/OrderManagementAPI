package com.oekrem.SpringMVCBackEnd.services;

import com.oekrem.SpringMVCBackEnd.models.RefreshToken;
import com.oekrem.SpringMVCBackEnd.models.User;


public interface RefreshTokenService {

    RefreshToken createRefreshToken(User user, String refreshToken);

    RefreshToken getRefreshTokenByToken(String refreshToken);
    RefreshToken getRefreshTokenByUserId(Long userId);
    RefreshToken getRefreshTokenByUserEmail(String email);
    void deleteRefreshTokensByUserId(Long userId);
    void deleteRefreshToken(String refreshToken);

    boolean validateRefreshTokenByUserId(Long userId);
}
