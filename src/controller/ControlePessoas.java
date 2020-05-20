package controller;

import java.util.concurrent.Semaphore;

import javax.swing.JLabel;

public class ControlePessoas extends Thread {

	private int idPessoa;
	private Semaphore semaforo;
	private JLabel lblPessoa;
	private static int chegou;
	private static int saiu;
	
	public ControlePessoas(int idPessoa, Semaphore semaforo, JLabel lblPessoa) {
		super();
		this.idPessoa = idPessoa;
		this.semaforo = semaforo;
		this.lblPessoa = lblPessoa;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		// super.run();
		this.avancaPessoa(200, 4, 6);
		try {
			semaforo.acquire();
			this.portaPessoa(1, 2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			semaforo.release();
			this.passaPessoa();
		}
	}

	private void avancaPessoa(int distancia, int velocidadeMin, int velocidadeMax) {
		// TODO Auto-generated method stub
		int posicao = distancia;
		int velocidade;
		
		while(posicao > 0) {
			velocidade = sorteio(velocidadeMin, velocidadeMax);
			System.out.println("Pessoa " + idPessoa + " está a " + posicao + "m da porta.");
			posicao -= velocidade;
			lblPessoa.setLocation(2*(200 - posicao), 70+30*idPessoa);
			try {
				sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		posicao = (posicao < 0) ? 0 : posicao;
		lblPessoa.setLocation((200 - posicao) * 2, 70+30*idPessoa);
		System.out.println("Pessoa " + idPessoa + " foi a " + (++chegou) + "ª a chegar à porta.");
	}
	
	private void portaPessoa(int tempoMin, int tempoMax) {
		int tempo = this.sorteio(tempoMin, tempoMax);
		System.out.println("Pessoa " + idPessoa + " abriu a porta para passar.");
		lblPessoa.setLocation(410, 145);
		try {
			sleep(tempo*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Pessoa " + idPessoa + " demora " + tempo + "s para passar pela porta.");
	}
	
	private void passaPessoa() {
		System.out.println("Pessoa " + idPessoa + " saiu da porta.");
		lblPessoa.setLocation(420, 70 + 30 * (++saiu));
	}
	
	private int sorteio(int min, int max) {
		int sorteio = (int) Math.round(Math.random() * (max - min) + min);
		return(sorteio);
	}
}
