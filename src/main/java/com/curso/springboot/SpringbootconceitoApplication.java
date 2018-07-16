package com.curso.springboot;

import com.curso.springboot.DAO.CategoriaDAO;
import com.curso.springboot.DAO.CidadeDAO;
import com.curso.springboot.DAO.EstadoDAO;
import com.curso.springboot.DAO.ProdutoDAO;
import com.curso.springboot.domain.Categoria;
import com.curso.springboot.domain.Cidade;
import com.curso.springboot.domain.Estado;
import com.curso.springboot.domain.Produto;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootconceitoApplication implements CommandLineRunner{
    @Autowired
    private CategoriaDAO categoriaDAO;
    @Autowired
    private ProdutoDAO produtoDAO;
    @Autowired
    private EstadoDAO estadoDAO;
    @Autowired
    private CidadeDAO cidadeDAO;
    
    public static void main(String[] args) {
        SpringApplication.run(SpringbootconceitoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        /*---------------------------------------------------------------------*/
        // Categoria produto
        Categoria categoria1 = new Categoria(null, "Informática");
        Categoria categoria2 = new Categoria(null, "Escritório");
        Produto produto1 = new Produto(null, "Computador", 2000.00);
        Produto produto2 = new Produto(null, "Impressora", 800.00);
        Produto produto3 = new Produto(null, "Mouse", 80.00);
        
        categoria1.getProdutos().addAll(Arrays.asList(produto1, produto2, produto3));
        categoria2.getProdutos().addAll(Arrays.asList(produto2));
        
        produto1.getCategorias().addAll(Arrays.asList(categoria1));
        produto2.getCategorias().addAll(Arrays.asList(categoria1, categoria2));
        produto3.getCategorias().addAll(Arrays.asList(categoria1));
        
        categoriaDAO.saveAll(Arrays.asList(categoria1, categoria2));
        produtoDAO.saveAll(Arrays.asList(produto1, produto2, produto3));
        /*---------------------------------------------------------------------*/
        // Estado Cidade
        Estado estado1 = new Estado(null, "Rio Grande do Sul");
        Estado estado2 = new Estado(null, "São Paulo");
        Cidade cidade1 = new Cidade(null, "Esteio", estado1);
        Cidade cidade2 = new Cidade(null, "Sapucaia", estado1);
        Cidade cidade3 = new Cidade(null, "São Paulo", estado2);
        
        estado1.getCidades().addAll(Arrays.asList(cidade1,cidade2));
        estado2.getCidades().addAll(Arrays.asList(cidade3));
        
        estadoDAO.saveAll(Arrays.asList(estado1,estado2));
        cidadeDAO.saveAll(Arrays.asList(cidade1,cidade2,cidade3));
    }
}
