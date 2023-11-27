package conta_bancaria.model;

import conta_bancaria.util.Cores;

public class ContaCorrente extends Conta {

	private float limite;

	public ContaCorrente(int numero, int agencia, int tipo, String titular, float saldo, float limite) {
		super(numero, agencia, tipo, titular, saldo);
		this.limite = limite;
	}

	public float getLimite() {
		return limite;
	}

	public void setLimite(float limite) {
		this.limite = limite;
	}
	
	@Override
	public boolean sacar(float valor) {
		if(this.getSaldo() + this.getLimite() < valor) {
			System.out.println("\n Saldo insuficiente");
			return false;
		}
		this.setSaldo(this.getSaldo()-valor);
		// alterando valor do limite em saques maiores que o saldo:
		if(this.getSaldo() < 0) {
			this.setLimite(this.getLimite()+this.getSaldo());
		}
		return true;
	}
	
	public void depositar(float valor) {
		if(this.getSaldo()<0 && (valor == Math.abs(this.getSaldo()) || valor < Math.abs(this.getSaldo()))) {
			this.setSaldo(this.getSaldo()+valor);
			this.setLimite(this.getLimite()+valor);
		}else if(this.getSaldo()<0 && valor > Math.abs(this.getSaldo())) {
		    this.setLimite(this.getLimite() + Math.abs(this.getSaldo()));
			this.setSaldo(valor-Math.abs(this.getSaldo()));
		}else {
			this.setSaldo(this.getSaldo()+valor);
		}
		
	}
	
	@Override
	public void visualizar() {
		super.visualizar();
		System.out.println("Limite da conta: " + this.limite);
		System.out.println(Cores.temaMenu2 + "                                                                ");
		System.out.println("****************************************************************");
		
	}
	
	
	
}
