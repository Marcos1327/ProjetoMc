package com.marcos.projetosts.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.marcos.projetosts.domain.Categoria;
import com.marcos.projetosts.domain.repositories.CategoriaRepository;
import com.marcos.projetosts.domain.services.exceptions.DataIntegrityException;
import com.marcos.projetosts.domain.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	// Método para Buscar no banco de dados / junto com o Resource
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));

	}
	
	// Método para Adicionar no banco de dados / junto com o Resource
	
	public Categoria insert(Categoria obj ) {
		obj.setId(null);
		return repo.save(obj);
	}


	// Método para Atualizar no banco de dados / junto com o Resource
	
	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	
	// Método para Delete no banco de dados / junto com o Resource
	
	public void delete(Integer id) {
		find(id);
		try {
				repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma categoria que possui produtos");
		}
		
	}
	
	public List<Categoria> findAll(){
		return repo.findAll();
	}
}
