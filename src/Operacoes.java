import java.time.LocalDateTime;

public class Operacoes {
	private String descricao;
	private double valor;
	private LocalDateTime data;

	public String getDescricao() {
		return descricao;
	};

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	};

	public double getValor() {
		return valor;
	};

	public void setValor(double valor) {
		this.valor = valor;
	};

	public LocalDateTime getData() {
		return data;
	};

	public void setData(LocalDateTime data) {
		this.data = data;
	};

	Operacoes(String descricao, double valor, LocalDateTime data) {
		this.setDescricao(descricao);
		this.setValor(valor);
		this.setData(data);
	}

	Operacoes(String descricao, double valor) {
		this(descricao, valor, LocalDateTime.now());
	}

	@Override
	public String toString() {
		return "Operação: " + descricao + "  Valor: " + valor + "  Data: " + data;
	};
	
	

}
