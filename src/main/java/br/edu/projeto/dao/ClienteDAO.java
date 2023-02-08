package br.edu.projeto.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.edu.projeto.model.Cliente;
import br.edu.projeto.model.Usuario;

public class ClienteDAO implements Serializable{

	@Inject
	//Cria a conexão e controla a transação com o SGBD (usado pelo Hibernate)
    private EntityManager em;
	
	public Cliente encontrarId(String id) {
       // return em.find(Cliente.class, id);
		return em.find(Cliente.class, id);
        
    }
	
	//Query usando a API Criteria do Hibernate
	//Indicada para consultas complexas
	public Boolean ehUsuarioUnico(String u) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Cliente> criteria = cb.createQuery(Cliente.class);
        Root<Cliente> cliente = criteria.from(Cliente.class);
        criteria.select(cliente);
        criteria.where(cb.like(cliente.get("cliente"), u));
        if (em.createQuery(criteria).getResultList().isEmpty())
        	return true;
        return false;
    }
	
	//Query usando a linguagem HQL do Hibernate
	//Idnicada para consultas simples
	public List<Cliente> listarTodos() {
	    return em.createQuery("SELECT a FROM Cliente c ", Cliente.class).getResultList();      
	}
	
	public void salvar(Cliente c) {
		em.persist(c);
	}
	
	public void atualizar(Cliente c) {
		em.merge(c);
	}
	
	public void excluir(Cliente c) {
		em.remove(em.getReference(Cliente.class, c.getCnpj()));
	}
	
}
