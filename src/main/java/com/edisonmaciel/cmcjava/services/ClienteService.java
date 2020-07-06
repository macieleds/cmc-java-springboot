package com.edisonmaciel.cmcjava.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edisonmaciel.cmcjava.domain.Cliente;
import com.edisonmaciel.cmcjava.repositories.ClienteRepository;
import com.edisonmaciel.cmcjava.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	public Cliente find (Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow (()-> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

}
