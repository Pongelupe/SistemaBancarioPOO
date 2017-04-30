
public class FundosdeInvestimento extends Extrato {
	private int cotas;
	private double indexador;
	private static final double taxaAdministracao = 15.10;

	public int getCotas() {
		return cotas;
	};

	public void setCotas(int cotas) {
		this.cotas = cotas;
	};

	public double getIndexador() {
		return indexador;
	};

	public void setIndexador(double indexador) {
		this.indexador = indexador;
	};

	public void depositarFundos(double valor) throws ExcecaoSaldoInsuficiente, ExcecaoValorNegativo {
		this.taxar("Taxa de administração", taxaAdministracao);
		super.depositar(valor);
	};

	@Override
	public void sacar(double valor) throws ExcecaoValorNegativo, ExcecaoSaldoInsuficiente {
		this.taxar("Taxa de administração", taxaAdministracao);
		super.sacar(valor);
	};

	@Override
	public double investimentoTotal() {
		return this.getCotas() * this.getIndexador();
	};

	FundosdeInvestimento(int cotas, double indexador) throws ExcecaoValorNegativo {
		super(cotas * indexador);
	};

}
