
@SuppressWarnings("serial")
public class ExcecaoValorNegativo extends Exception {
	public double valor;

	ExcecaoValorNegativo(double valor) {
		System.out.println("Este valor n�o pode ser " + valor + ", o valor deve ser maior que 0");
		this.valor = valor;
	};
}
