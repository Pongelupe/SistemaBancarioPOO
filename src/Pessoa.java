
public abstract class Pessoa {
	private String nome, telefone, endereco;
	private ContaCorrente contaCorrente;
	private Poupanca poupanca;
	private FundosdeInvestimento fundosdeInvestimento;

	public String getNome() {
		return nome;
	};

	public void setNome(String nome) {
		this.nome = nome;
	};

	public String getTelefone() {
		return telefone;
	};

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	};

	public String getEndereco() {
		return endereco;
	};

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	};

	public ContaCorrente getContaCorrente() {
		return contaCorrente;
	};

	public void setContaCorrente(ContaCorrente contaCorrente) {
		this.contaCorrente = contaCorrente;
	};

	public Poupanca getPoupanca() {
		return poupanca;
	};

	public void setPoupanca(Poupanca poupanca) {
		this.poupanca = poupanca;
	};

	public FundosdeInvestimento getFundosdeInvestimento() {
		return fundosdeInvestimento;
	};

	public void setFundosdeInvestimento(FundosdeInvestimento fundosdeInvestimento) {
		this.fundosdeInvestimento = fundosdeInvestimento;
	};

	public String toString(String tipo) {
		return "Pessoa " + tipo + "\nNome: " + nome + ", Telefone: " + telefone + ", Endereco: " + endereco;
	};

	Pessoa() {
		this("Shulambs", "32360111", "Rua dos Bobos, nº 0");
	};

	Pessoa(String nome, String telefone, String endereco) {
		this.setNome(nome);
		this.setTelefone(telefone);
		this.setEndereco(endereco);
		this.setContaCorrente(null);
		this.setPoupanca(null);
		this.setFundosdeInvestimento(null);
	};

}
