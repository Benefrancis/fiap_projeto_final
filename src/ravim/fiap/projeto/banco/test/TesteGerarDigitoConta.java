package ravim.fiap.projeto.banco.test;

import javax.swing.JOptionPane;

public class TesteGerarDigitoConta {
	public static void main(String[] args) {

		String v = String.valueOf(JOptionPane.showInputDialog("Informe um número de conta"));

		gerarDigito(v);
	}

	private static byte gerarDigito(String v) {
		long somaPares = 0, somaImpares = 0;
		
		for (int i = 0; i < v.length(); i++) {
			
			int valor = Integer.parseInt(String.valueOf(v.charAt(i)));
			
			if (valor % 2 == 0) {
				somaPares += valor;
			} else {
				somaImpares += valor;
			}
		}

		String res = String.valueOf((somaPares + (somaImpares * 3)));

		byte digito = (byte) (10 - Integer.parseInt(String.valueOf(res.charAt(res.length()-1)))) ;

		System.out.println("Soma dos dígitos impares do número da conta: " + somaImpares);
		System.out.println("Soma dos dígitos pares do número da conta: " + somaPares);
		System.out.println("Soma dos dígitos impares multiplicado por 3: " + (somaImpares * 3));
		System.out.println("Resultado: " + res);
		System.out.println("Digito é igual a 10 - o último dígito do resultado: " + digito);
		
		return digito;
	}
}
