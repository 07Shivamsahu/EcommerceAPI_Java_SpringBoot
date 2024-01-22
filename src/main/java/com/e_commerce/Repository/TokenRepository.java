package com.e_commerce.Repository;

import com.e_commerce.Entity.AuthenticationToken;
import com.e_commerce.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<AuthenticationToken, Integer> {
    AuthenticationToken findByUser(User user);
}
