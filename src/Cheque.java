import java.time.LocalDateTime;

public class Cheque {
	private double valor;
	private LocalDateTime data;

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

	Cheque(double valor, LocalDateTime data) {
		this.setValor(valor);
		this.setData(data);
	}

	@Override
	public String toString() {
		return "Valor: " + valor + " reais  Data: " + data;
	};

}
