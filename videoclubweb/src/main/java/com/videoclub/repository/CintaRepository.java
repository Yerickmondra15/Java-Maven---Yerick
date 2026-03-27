package com.videoclub.repository;

import com.videoclub.entity.Cinta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CintaRepository extends JpaRepository<Cinta, Integer> {

}
