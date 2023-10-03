package com.HotelService.MicroService.RepositoyInterfcae;

import com.HotelService.MicroService.Entities.Hotel;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Hotelrepo extends JpaRepository<Hotel,Long> {



}
