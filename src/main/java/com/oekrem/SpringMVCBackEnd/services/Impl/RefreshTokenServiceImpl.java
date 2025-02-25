package com.oekrem.SpringMVCBackEnd.services.Impl;

import com.oekrem.SpringMVCBackEnd.exceptions.SecurityExceptions.TokenNotFoundException;
import com.oekrem.SpringMVCBackEnd.models.RefreshToken;
import com.oekrem.SpringMVCBackEnd.models.User;
import com.oekrem.SpringMVCBackEnd.repository.RefreshTokenRepository;
import com.oekrem.SpringMVCBackEnd.services.RefreshTokenService;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    @Transactional
    public RefreshToken createRefreshToken(User user, String refreshToken) {
        // validate etmek lazım
        RefreshToken token = RefreshToken.builder()
                .refreshToken(refreshToken)
                .user(user)
                .build();
        return refreshTokenRepository.save(token);
    }

    @Override
    @Transactional(readOnly = true)
    public RefreshToken getRefreshTokenByToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new TokenNotFoundException("Token not found for refresh token: " + refreshToken));
    }

    @Override
    @Transactional(readOnly = true)
    public RefreshToken getRefreshTokenByUserId(Long userId) {
        return refreshTokenRepository.findByUser_Id(userId)
                .orElseThrow( ()-> new TokenNotFoundException("Token not found for userId: " + userId));
    }

    @Override
    @Transactional(readOnly = true)
    public RefreshToken getRefreshTokenByUserEmail(String email) {
        return refreshTokenRepository.findByUser_Email(email)
                .orElseThrow( ()-> new TokenNotFoundException("Token not found for userEmail: " + email));
    }

    @Override
    @Transactional
    public void deleteRefreshTokensByUserId(Long userId) {
        if(validateRefreshTokenByUserId(userId))
            refreshTokenRepository.deleteAllByUser_Id(userId);
    }

    @Override
    @Transactional
    public void deleteRefreshToken(String refreshToken) {
        Optional<RefreshToken> token = refreshTokenRepository.findByRefreshToken(refreshToken);
        token.ifPresent( p-> refreshTokenRepository.deleteByRefreshToken(refreshToken));
    }

    @Override
    public boolean validateRefreshTokenByUserId(Long userId) {
        return refreshTokenRepository.findByUser_Id(userId).isPresent();
    }

}
