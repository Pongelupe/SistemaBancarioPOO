
public class Juridica extends Pessoa {
	private String CNPJ;

	public String getCNPJ() {
		return CNPJ;
	};

	public void setCNPJ(String CNPJ) {
		this.CNPJ = CNPJ;
	};

	@Override
	public String toString() {
		return super.toString("Juridica") + ", CNPJ: " + CNPJ;
	};

	Juridica(String CNPJ) {
		super();
		this.setCNPJ(CNPJ);
	};

	Juridica(String nome, String telefone, String endereco, String CNPJ) {
		super(nome, telefone, endereco);
		this.setCNPJ(CNPJ);
	};
}
