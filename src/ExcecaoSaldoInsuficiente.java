
@SuppressWarnings("serial")
public class ExcecaoSaldoInsuficiente extends Exception {
	public double saldo, valor;
	
	ExcecaoSaldoInsuficiente(double saldo, double valor){
		System.out.println("Seu saldo de " + saldo + " � insuficiente para essa transa��o de " + valor + " reais");
		this.saldo = saldo;
		this.valor = valor;
	};
}
