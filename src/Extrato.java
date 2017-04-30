
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public abstract class Extrato {
	private List<Operacoes> operacoes;
	private double saldo;
	private LocalDateTime dataCriacao;
	static private int instancias = -1;
	private int id;

	public List<Operacoes> getOperacoes() {
		return operacoes;
	};

	public double getSaldo() {
		return saldo;
	};

	private void setSaldo(double saldo) {
		this.saldo = saldo;
	};

	public static int getInstancias() {
		return instancias;
	};

	public void setId(int id) {
		this.id = id;
	};

	public int getId() {
		return id;
	};

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	};

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	};

	public abstract double investimentoTotal();

	public void imprimeExtrato() {
		for (int i = 0; i < this.getOperacoes().size(); i++)
			System.out.println(this.getOperacoes().get(i).toString());
	};

	public void depositar(double valor) throws ExcecaoValorNegativo {
		if (valor > 0) {
			this.setSaldo(this.getSaldo() + valor);
			this.getOperacoes().add(new Operacoes("Depósito", valor));
		} else {
			throw new ExcecaoValorNegativo(valor);
		}
	};

	public void sacar(double valor) throws ExcecaoValorNegativo, ExcecaoSaldoInsuficiente {
		if (valor < this.getSaldo() && valor > 0) {
			this.setSaldo(getSaldo() - valor);
			this.getOperacoes().add(new Operacoes("Saque", valor));
		} else if (valor <= 0) {
			throw new ExcecaoValorNegativo(saldo);
		} else {
			throw new ExcecaoSaldoInsuficiente(this.getSaldo(), valor);
		}

	};

	public void taxar(String tipo, double valor) throws ExcecaoSaldoInsuficiente {
		if (valor < this.getSaldo() && valor > 0) {
			this.setSaldo(getSaldo() - valor);
			this.getOperacoes().add(new Operacoes(tipo, valor));
		}
	};

	public void rendimento(String tipo, double rendimento) throws ExcecaoValorNegativo {
		if (rendimento > 0) {
			this.setSaldo(getSaldo() * rendimento);
			this.getOperacoes().add(new Operacoes(tipo, rendimento));
		} else
			throw new ExcecaoValorNegativo(saldo);
	};

	Extrato() throws ExcecaoValorNegativo {
		// 0.01 é o valor default para se começar seu extrato
		this(0.01);
	};

	Extrato(double saldo) throws ExcecaoValorNegativo {
		if (saldo <= 0)
			throw new ExcecaoValorNegativo(saldo);
		else {
			this.operacoes = new LinkedList<Operacoes>();
			this.setSaldo(saldo);
			this.setId(++instancias);
			this.setDataCriacao(LocalDateTime.now());
		}
	};
}
