package com.curso.springboot;

import com.curso.springboot.DAO.CategoriaDAO;
import com.curso.springboot.DAO.ProdutoDAO;
import com.curso.springboot.domain.Categoria;
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
    
    public static void main(String[] args) {
        SpringApplication.run(SpringbootconceitoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Categoria c1 = new Categoria(null, "Informática");
        Categoria c2 = new Categoria(null, "Escritório");
        Produto p1 = new Produto(null, "Computador", 2000.00);
        Produto p2 = new Produto(null, "Impressora", 800.00);
        Produto p3 = new Produto(null, "Mouse", 80.00);
        
        c1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
        c2.getProdutos().addAll(Arrays.asList(p2));
        
        p1.getCategorias().addAll(Arrays.asList(c1));
        p2.getCategorias().addAll(Arrays.asList(c1, c2));
        p3.getCategorias().addAll(Arrays.asList(c1));
        
        categoriaDAO.saveAll(Arrays.asList(c1, c2));
        produtoDAO.saveAll(Arrays.asList(p1, p2, p3));
    }
}
