import java.time.LocalDateTime;
import java.util.ArrayList;

public class ContaCorrente extends Extrato {
	private ArrayList<Cheque> talaoCheque;
	private LocalDateTime dataEmisaoTalao;
	private int quantidadeCheques;
	private static final double taxaTalao = 7.90;

	private ArrayList<Cheque> getTalaoCheque() {
		return this.talaoCheque;
	};

	private void setDataEmissaoTalao(LocalDateTime data) {
		this.dataEmisaoTalao = data;
	};

	public LocalDateTime getdataEmisaoTalao() {
		return dataEmisaoTalao;
	};

	public int getQuantidadeCheques() {
		return quantidadeCheques;
	};

	private void setQuantidadeCheques(int quantidadeCheques) {
		this.quantidadeCheques = quantidadeCheques;
	};

	public Boolean emitirCheque(double valor) {
		Boolean emitido = true;
		if (this.getTalaoCheque().size() == this.getQuantidadeCheques()) {
			System.out.println("Você emitiu todos os cheques, por favor solicite mais para proseguir");
			emitido = false;
		} else {
			this.getTalaoCheque().add(new Cheque(valor, LocalDateTime.now()));
			this.getOperacoes().add(new Operacoes("Emissão de cheque", valor));
		}
		return emitido;
	};

	public void imprimeChequesUtilizados() {
		if (this.getTalaoCheque().isEmpty())
			return;
		for (int i = 0; i < this.getTalaoCheque().size(); i++) {
			System.out.println(this.getTalaoCheque().get(i));
		}
	};

	public Boolean solicitarNovoTalao() throws ExcecaoSaldoInsuficiente {
		Boolean solicitacao = true;
		if (LocalDateTime.now().isAfter(getdataEmisaoTalao().plusMonths(1))) {
			this.taxar("Emissão de talão novo", taxaTalao);
			this.getTalaoCheque().ensureCapacity(this.getQuantidadeCheques());
			this.setDataEmissaoTalao(LocalDateTime.now());
		} else
			solicitacao = false;
		return solicitacao;
	};

	@Override
	public double investimentoTotal() {
		return this.getSaldo();
	};

	ContaCorrente() throws ExcecaoValorNegativo {
		// 10 cheques por talão default
		this(0.01, 10);
	};

	ContaCorrente(double saldo) throws ExcecaoValorNegativo {
		this(saldo, 10);
	};

	ContaCorrente(double saldo, int quantidadeCheques) throws ExcecaoValorNegativo {
		super(saldo);
		this.setQuantidadeCheques(quantidadeCheques);
		this.talaoCheque = new ArrayList<>(this.getQuantidadeCheques());
		this.dataEmisaoTalao = LocalDateTime.now();
	};
}
