package view;

import java.util.concurrent.Semaphore;

import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.ControlePessoas;

public class Principal {

	public static void main(String[] args) {
		int quant = 4;
		Semaphore semaforo = new Semaphore(1);
		JFrame janela = new JFrame();
		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		janela.setLayout(null);
		janela.setSize(800, 600);
		JLabel lblPessoa[] = new JLabel[quant];
		
		for(int i = 0; i < quant; i++) {
			lblPessoa[i] = new JLabel("Pessoa " + (i + 1));
			lblPessoa[i].setBounds(0, 100+30*i, 100, 20);
			janela.add(lblPessoa[i]);
		}
		janela.setVisible(true);
		
		for(int i=1; i<=quant; i++) {
			Thread t = new ControlePessoas(i, semaforo, lblPessoa[i-1]);
			t.start();
		}
	}
}
