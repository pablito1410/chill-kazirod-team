package com.chill.table.football.infrastructure.repository.user;

import com.chill.table.football.application.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataUserRepository extends JpaRepository<User, Long> {

    User findOneByUserName(String userName);
}
