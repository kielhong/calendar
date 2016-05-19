package com.widehouse.user;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by kiel on 2016. 5. 19..
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
