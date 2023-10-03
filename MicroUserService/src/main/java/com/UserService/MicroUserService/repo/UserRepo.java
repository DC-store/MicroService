package com.UserService.MicroUserService.repo;

import com.UserService.MicroUserService.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {



    @Query(value ="SELECT * FROM user_micro WHERE user_id  = :userId  ;" , nativeQuery = true)
    public User findUserByQuery(@Param("userId") long userId);
}
