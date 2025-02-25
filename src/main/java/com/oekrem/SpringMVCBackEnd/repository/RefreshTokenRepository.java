package com.oekrem.SpringMVCBackEnd.repository;

import com.oekrem.SpringMVCBackEnd.models.RefreshToken;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {

    @Transactional
    @Modifying
    RefreshToken save(RefreshToken refreshToken);

    Optional<RefreshToken> findByRefreshToken(String refreshToken);
    Optional<RefreshToken> findByUser_Id(Long userId);
    Optional<RefreshToken>findByUser_Email(String email);

    @Transactional
    @Modifying
    @Query("Delete from RefreshToken t where t.user.id = :userId")
    void deleteAllByUser_Id(@Param("userId") Long userId);

    @Transactional
    @Modifying
    void deleteByRefreshToken(String refreshToken);

}
