package com.edisonmaciel.cmcjava.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edisonmaciel.cmcjava.domain.Pedido;
import com.edisonmaciel.cmcjava.repositories.PedidoRepository;
import com.edisonmaciel.cmcjava.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repository;
	
	public Pedido find (Integer id) {
		Optional<Pedido> obj = repository.findById(id);
		return obj.orElseThrow (()-> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

}
