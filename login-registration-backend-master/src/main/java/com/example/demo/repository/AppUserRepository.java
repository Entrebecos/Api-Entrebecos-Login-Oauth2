package com.example.demo.repository;

import com.example.demo.model.AppUser;
import com.example.demo.model.AuthenticationType;
import com.example.demo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE AppUser a " + "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableAppUser(String username);

    @Query("SELECT a FROM AppUser a WHERE a.email = ?1")
    public AppUser getAppUserByEmail(@Param("email") String email);

    @Query("SELECT u FROM AppUser u WHERE u.email = :username")
    public AppUser getUserByUsername(@Param("username") String username);

    @Transactional
    @Modifying
    @Query("UPDATE AppUser u " + "SET u.authType = ?2 WHERE u.email = ?1")
    public void updateAuthenticationType(String username, AuthenticationType authType);
}