
public class Fisica extends Pessoa {
	private String CPF;

	public String getCPF() {
		return CPF;
	};

	public void setCPF(String CPF) {
		this.CPF = CPF;
	};

	@Override
	public String toString() {
		return super.toString("Física") + ", CPF: " + CPF;
	};

	Fisica(String CPF) {
		super();
		this.setCPF(CPF);
	};

	Fisica(String nome, String telefone, String endereco, String CPF) {
		super(nome, telefone, endereco);
		this.setCPF(CPF);
	};
}
