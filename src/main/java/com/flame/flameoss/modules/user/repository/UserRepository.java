package com.flame.flameoss.modules.user.repository;

import com.flame.flameoss.modules.user.entiry.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserAccount(String userAccount);

}
