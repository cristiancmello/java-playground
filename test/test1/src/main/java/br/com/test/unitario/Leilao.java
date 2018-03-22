package br.com.test.unitario;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leilao {

	private String descricao;
	private List<Lance> lances;
	
	public Leilao(String descricao) {
		this.descricao = descricao;
		this.lances = new ArrayList<Lance>();
	}

	public void propoe(Lance lance) {
		if(lances.isEmpty() || podeDarLance(lance.getUsuario())) {
			lances.add(lance);
		}
	}

	private boolean podeDarLance(Usuario usuario) {
		return !ultimoLanceDado().getUsuario().equals(usuario)
				&& qtdDelancesDo(usuario) < 5;
	}

	private Lance ultimoLanceDado() {
		return lances.get(lances.size()-1);
	}

	private int qtdDelancesDo(Usuario usuario) {
		int total = 0;
		for(Lance lance : lances) {
			if(lance.getUsuario().equals(usuario)) total++;
		}
		return total;
	}

	public String getDescricao() {
		return descricao;
	}

	public List<Lance> getLances() {
		return Collections.unmodifiableList(lances);
	}


	public void dobraLance(Usuario usuario) {
		Lance ultimoLance = ultimoLanceDo(usuario);
		if(ultimoLance!=null) {
			propoe(new Lance(usuario, ultimoLance.getValor()*2));
		}
	}

	private Lance ultimoLanceDo(Usuario usuario) {
		Lance ultimo = null;
		for(Lance lance : lances) {
			if(lance.getUsuario().equals(usuario)) ultimo = lance;
		}

		return ultimo;
	}
}