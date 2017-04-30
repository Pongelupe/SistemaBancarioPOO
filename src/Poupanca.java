import java.time.LocalDateTime;

public class Poupanca extends Extrato {

	private static final double coeficienteRendimento = 0.005;
	private LocalDateTime dataRendimento;

	public double getCoeficienteRendimento() {
		return coeficienteRendimento;
	};

	public LocalDateTime getDataRendimento() {
		return dataRendimento;
	};

	public void setDataRendimento(LocalDateTime dataRendimento) {
		this.dataRendimento = dataRendimento;
	};

	public void rendimentoMensal() throws ExcecaoValorNegativo {
		if (LocalDateTime.now().isAfter(getDataRendimento().plusMonths(1))) {
			this.rendimento("Rendimento mensal da conta Poupança", (this.getSaldo() * getCoeficienteRendimento()));
			this.setDataRendimento(LocalDateTime.now());
		}
	};

	@Override
	public double investimentoTotal() {
		return this.getSaldo();
	};

	Poupanca() throws ExcecaoValorNegativo {
		this(0.01);
	};

	Poupanca(double saldo) throws ExcecaoValorNegativo {
		super(saldo);
		this.setDataRendimento(getDataCriacao());
	};

}
