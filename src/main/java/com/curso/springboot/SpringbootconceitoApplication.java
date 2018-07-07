package com.curso.springboot;

import com.curso.springboot.DAO.CategoriaDAO;
import com.curso.springboot.domain.Categoria;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootconceitoApplication implements CommandLineRunner{
    @Autowired
    private CategoriaDAO categoriaDAO;
    public static void main(String[] args) {
        SpringApplication.run(SpringbootconceitoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Categoria c1 = new Categoria(null, "Informática");
        Categoria c2 = new Categoria(null, "Escritório");
        Categoria c3 = new Categoria(null, "Educação");
        
        categoriaDAO.saveAll(Arrays.asList(c1,c2,c3));
    }
}
