package com.marcos.projetosts;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.marcos.projetosts.domain.Categoria;
import com.marcos.projetosts.domain.Cidade;
import com.marcos.projetosts.domain.Cliente;
import com.marcos.projetosts.domain.Endereco;
import com.marcos.projetosts.domain.Estado;
import com.marcos.projetosts.domain.Product;
import com.marcos.projetosts.domain.enums.TipoCliente;
import com.marcos.projetosts.domain.repositories.CategoriaRepository;
import com.marcos.projetosts.domain.repositories.CidadeRepository;
import com.marcos.projetosts.domain.repositories.ClienteRepository;
import com.marcos.projetosts.domain.repositories.EnderecoRepository;
import com.marcos.projetosts.domain.repositories.EstadoRepository;
import com.marcos.projetosts.domain.repositories.ProductRepository;

@SpringBootApplication
public class ProjetostsApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	
	

	public static void main(String[] args) {
		SpringApplication.run(ProjetostsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Product p1 = new Product(null, "Computador", 2000.00);
		Product p2 = new Product(null, "Impressora", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);
		
		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));		
				
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		productRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		
		Cliente cli1 = new Cliente(null, "Maria Silva" , "maria@gmail.com", "36378912377" , TipoCliente.PESSOAFISICA );
		
		cli1.getTelefone().addAll(Arrays.asList("27363323", "93838393 "));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim","38220834" , cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105","sala 800", " Centro", "3877012", cli1, c2);

		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
	}

}
