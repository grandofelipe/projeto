package br.edu.projeto.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import br.edu.projeto.dao.ClienteDAO;
import br.edu.projeto.dao.UsuarioDAO;
import br.edu.projeto.model.Cliente;
import br.edu.projeto.model.TipoPermissao;
import br.edu.projeto.model.Usuario;

@ViewScoped
@Named
public class ClienteController implements Serializable{
	@Inject
	private FacesContext facesContext;
	
	@Inject
    private ClienteDAO clienteDAO;
	
	private List<Cliente>clientes;
	
	private Cliente cliente;
	
	
	@PostConstruct
    public void init() {
    	//Verifica se usuário está autenticado e possui a permissão adequada
//    	if (!this.facesContext.getExternalContext().isUserInRole("ADMINISTRADOR")) {
//    		try {
//				this.facesContext.getExternalContext().redirect("login-error.xhtml");
//			} catch (IOException e) {e.printStackTrace();}
//    	}
    	//Inicializa elementos importantes
    	this.clientes = clienteDAO.listarTodos();
    }


	public List<Cliente> getClientes() {
		return clientes;
	}


	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	
	
	
	
	
	public ClienteDAO getClienteDAO() {
		return clienteDAO;
	}


	public void setClienteDAO(ClienteDAO clienteDAO) {
		this.clienteDAO = clienteDAO;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	//vai ser chamado no botao criar novo cliente
	public void novoCadastro() {
        this.setCliente(new Cliente());
    }
	
	public void salvar() {
      //  if (usuarioValido()) {
    	//	this.usuario.getPermissoes().clear();
    	//	for (Integer id: this.permissoesSelecionadas) {
    	//		TipoPermissao permissao = tipoPermissaoDAO.encontrarPermissao(id);
    	//		permissao.addUsuario(this.usuario);	
    	//	}
        	try {
        		
		        if (this.cliente.getCnpj() == null) {
		        	this.clienteDAO.salvar(this.cliente);
		        	this.clientes = clienteDAO.listarTodos();
		        	this.facesContext.addMessage(null, new FacesMessage("Cliente Criado"));
		        } else {
		        	this.clienteDAO.atualizar(this.cliente);
		        	this.facesContext.addMessage(null, new FacesMessage("Cliente Atualizado"));
		        }
		        this.clientes= clienteDAO.listarTodos();
		        //Atualiza e executa elementos Javascript na tela assincronamente
			    PrimeFaces.current().executeScript("PF('clienteDialog').hide()");
			    PrimeFaces.current().ajax().update("form:mensagens", "form:cliente");
	        } catch (Exception e) {
	            String errorMessage = getMensagemErro(e);
	            this.facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, null));
	        }
        }
	
	
	
	public void remover() {
		try {
			this.clienteDAO.excluir(this.cliente);
			this.clientes = clienteDAO.listarTodos();
	        this.cliente= null;
	        this.facesContext.addMessage(null, new FacesMessage("Cliente Removido"));
	        PrimeFaces.current().ajax().update("form:mensagens", "form:cliente");//table
        } catch (Exception e) {
            String errorMessage = getMensagemErro(e);
            this.facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, null));
        }
	}

	
private String getMensagemErro(Exception e) {
    String erro = "Falha no sistema!. Contacte o administrador do sistema.";
    if (e == null) 
        return erro;
    Throwable t = e;
    while (t != null) {
        erro = t.getLocalizedMessage();
        t = t.getCause();
    }
    return erro;
}

}	

