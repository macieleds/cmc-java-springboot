package com.edisonmaciel.cmcjava.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edisonmaciel.cmcjava.domain.ItemPedido;
import com.edisonmaciel.cmcjava.domain.PagamentoComBoleto;
import com.edisonmaciel.cmcjava.domain.Pedido;
import com.edisonmaciel.cmcjava.domain.enums.EstadoPagamento;
import com.edisonmaciel.cmcjava.repositories.ItemPedidoRepository;
import com.edisonmaciel.cmcjava.repositories.PagamentoRepository;
import com.edisonmaciel.cmcjava.repositories.PedidoRepository;
import com.edisonmaciel.cmcjava.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repository;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public Pedido find (Integer id) {
		Optional<Pedido> obj = repository.findById(id);
		return obj.orElseThrow (()-> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
	
	public Pedido insert (Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		obj = repository.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.find(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		return obj;
	
	}

}
