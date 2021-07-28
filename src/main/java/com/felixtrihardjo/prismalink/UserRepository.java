package com.felixtrihardjo.prismalink;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
	public Optional<User> findByEmail(String email);
	@Query(value = "select * from users u where u.created_date = :created_date", nativeQuery = true)
	public Optional<List<User>> findByDate(@Param("created_date") Date created_date);
}
