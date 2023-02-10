package br.edu.projeto.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.projeto.dao.ClienteDAO;
import br.edu.projeto.dao.UsuarioDAO;
import br.edu.projeto.model.Cliente;
import br.edu.projeto.model.TipoPermissao;

@ViewScoped
@Named
public class ClienteController implements Serializable{
	@Inject
	private FacesContext facesContext;
	
	@Inject
    private ClienteDAO clienteDAO;
	
	private List<Cliente>clientes;
	
	
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
	
}
