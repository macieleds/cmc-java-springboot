package com.edisonmaciel.cmcjava.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edisonmaciel.cmcjava.domain.Categoria;
import com.edisonmaciel.cmcjava.repositories.CategoriaRepository;
import com.edisonmaciel.cmcjava.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repository;
	
	public Categoria find (Integer id) {
		Optional<Categoria> obj = repository.findById(id);
		return obj.orElseThrow (()-> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public Categoria insert (Categoria obj) {
		obj.setId(null);
		return repository.save(obj);
	}
	
	public Categoria update (Categoria obj) {
		find(obj.getId());
		return repository.save(obj);
	}

}
