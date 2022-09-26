package view;

import java.util.concurrent.Semaphore;
import controller.threadBilheteria;

public class MainBilheteria {

	public static void main(String[] args) {

		Semaphore valideiro = new Semaphore(1);

		for (int i = 1; i <= 300; i++) { // Ativa 300 threads!!!!!! (compradores)

			threadBilheteria t = new threadBilheteria(i, valideiro);
			t.start();

		}

	}

}