package br.ufpb.dcx.aps.carcassone;

import java.util.ArrayList;

import br.ufpb.dcx.aps.carcassone.tabuleiro.TabuleiroFlexivel;
import br.ufpb.dcx.aps.carcassone.tabuleiro.Tile;


public class Jogo {
	
	private Tile proximoTile;
	private BolsaDeTiles tiles;
	private TabuleiroFlexivel tabuleiro = new TabuleiroFlexivel("  ");
	private boolean isIniciada = false;
	private String status = "InÃ­cio";
	private Cor[] pecaDoJogador;
	private Cor proximaPecaDoJogador;
	static int indiceDePecas = 0;
	ArrayList<Tile> tilesUsar = new ArrayList<Tile>();
	
	public Jogo(BolsaDeTiles tiles) {
		this.tiles = tiles;
	}

	public Jogo iniciarPartida(Cor... sequencia) {
		
		if(sequencia.length<2) {
			throw new ExcecaoJogo("Cada partida deve ter uma sequÃªncia de pelo menos dois jogadores");
		}
		for(int k=0;k < sequencia.length;k++) {
			for(int i=0;i< sequencia.length;i++) {
				if(k!=i && sequencia[i]==sequencia[k]) {
					throw new ExcecaoJogo("NÃ£o pode haver repetiÃ§Ã£o de cores na sequÃªncia de jogadores");
					}
			}
		}
		
		if(isIniciada){
			throw new ExcecaoJogo("NÃ£o pode iniciar uma partida enquanto a partida anterior nÃ£o for finalizada");
		}
		isIniciada = true;
		pegarProximoTile();
		pecaDoJogador = sequencia;
		proximoTile = tilesUsar.get(tilesUsar.size()-1);
		indiceDePecas= 0;
		proximaPecaDoJogador = pecaDoJogador[indiceDePecas % pecaDoJogador.length];
		return this;
	}

	public String relatorioPartida() {
		if (isIniciada == false) {
			throw new ExcecaoJogo("Partida nÃ£o iniciada");
		}

		String sequencia = "";
		
		for (int i = 0; i < pecaDoJogador.length - 1; i++) {
			sequencia += pecaDoJogador[i].toString() + ", ";
		}
		
		sequencia += pecaDoJogador[pecaDoJogador.length - 1];
		if (status.equals("Fim")) {
			pecaDoJogador[0] = null;
		}

		String relatorio = "Status: " + status + "\nJogadores: " + sequencia + "\nTabuleiro: " + tabuleiro
				+ "\nJogador da rodada: " + pecaDoJogador[(indiceDePecas % pecaDoJogador.length)] + "\nPrÃ³ximo tile: "
				+ tilesUsar.get(tilesUsar.size() - 1);

		return relatorio;	
	}
	
	public Jogo girarTile() {	
		if(proximoTile != null);
			proximoTile.girar();
		return this;
	}


	public Jogo posicionarInicial() {
		status = "Tile";
		
		if(isIniciada == false){
			throw new ExcecaoJogo("O tile inicial nÃ£o pode ser posicionado antes de iniciar a partida");
		}
		tabuleiro.adicionarPrimeiroTile(proximoTile);
		pegarProximoTile();
		return this;
	}

	public Jogo pegarProximoTile() {
		Tile t = tiles.pegar();
		if(t != null) {
			t.reset();
		}
		tilesUsar.add(t);
		return this;
	}

	public Jogo finalizarRodada() {
		proximoTile = tilesUsar.get(tilesUsar.size() - 1);
		if (proximoTile == null) {
			status = "Fim";
			pegarProximoTile();
		} else {
			status="InÃ­cio";
			++indiceDePecas;
			proximaPecaDoJogador = pecaDoJogador[indiceDePecas % pecaDoJogador.length];
		}
		return this;
	}


	public Jogo posicionarTile(Tile tileReferencia, Lado ladoTileReferencia) {
		status = "Tile";
		proximoTile = tilesUsar.get(tilesUsar.size() - 1);
		tabuleiro.posicionar(tileReferencia, ladoTileReferencia, proximoTile);
		pegarProximoTile();
		return this;		
	}
	public Jogo posicionarMeepleEstrada(Lado lado) {
		return this;
	}
	
	public Jogo posicionarMeepleCampo(Vertice vertice) {
		return this;
	}
	
	public Jogo posicionarMeepleCidade(Lado lado) {
		return this;
	}
	
	public Jogo posicionarMeepleMosteiro() {
		return this;
	}
	
	public String getEstradas() {
		return null;
	}
	
	public String getCampos() {
		return null;
	}
	
	public String getCidades() {
		return null;
	}

	public String getMosteiros() {
		return null;
	}

	public String getRio() {
		// TODO Auto-generated method stub
		return null;
	}
}