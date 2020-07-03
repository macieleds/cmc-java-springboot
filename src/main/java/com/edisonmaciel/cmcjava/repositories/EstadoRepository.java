package com.edisonmaciel.cmcjava.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edisonmaciel.cmcjava.domain.Categoria;
import com.edisonmaciel.cmcjava.domain.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer>{
	

}
