package controller;

import java.util.concurrent.Semaphore;

public class threadBilheteria extends Thread {

	static private int ingressos = 100;
	private int idUser;
	private Semaphore val;

	public threadBilheteria(int idUser, Semaphore val) {
		this.idUser = idUser;
		this.val = val;
	}

	@Override
	public void run() {

		try {

			int qtdCompra = (int) (Math.random() * 4) + 1;
			boolean erroLogin = false;
			boolean erroCompra = false;

			erroLogin = login();
			if (erroLogin == false) {
				erroCompra = comprar();
				if (erroCompra == false) {
					val.acquire();
					validar(qtdCompra);
				}
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			val.release();
		}

	}

	private boolean login() {

		int tempoL = (int) (Math.random() * 1951) + 50;

		try {
			sleep(tempoL);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (tempoL > 1000) {
			System.err.println("Usuário " + idUser + " tempo esgotado no login | " + tempoL);
			return true;
		} else {
			System.out.println("Usuário " + idUser + " logou");
			return false;
		}

	}

	private boolean comprar() {

		int tempoC = (int) (Math.random() * 2001) + 1000;

		try {
			sleep(tempoC);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (tempoC > 2500) {
			System.err.println("Usuário " + idUser + " tempo esgotado na compra | " + tempoC);
			return true;
		} else {
			System.out.println("Usuário " + idUser + " marcou a compra");
			return false;
		}

	}

	private void validar(int qC) {

		if (qC > ingressos) {
			System.err.println("Usuário " + idUser + " tentou comprar mais do que podia");
			System.exit(0);
		} else {
			ingressos -= qC;
			System.out.println("Usuário " + idUser + " comprou " + qC + " ingressos, restam " + ingressos);
		}

	}

}