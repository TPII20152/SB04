package br.ufc.banco.dados;



import br.ufc.banco.conta.ContaAbstrata;
import br.ufc.banco.dados.excecoes.CEException;
import br.ufc.banco.dados.excecoes.CIException;

public interface IRepositorioContas {

	public void inserir(ContaAbstrata conta) throws CEException;

	public void apagar(String numero) throws CIException;

	public ContaAbstrata procurar(String numero);

	public ContaAbstrata[] listar();

	public int numeroContas();
	
	public void serializar() throws Exception;
	
	public void desserializar() throws Exception;
}
