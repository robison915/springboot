package com.curso.springboot;

import com.curso.springboot.DAO.CategoriaDAO;
import com.curso.springboot.DAO.CidadeDAO;
import com.curso.springboot.DAO.ClienteDAO;
import com.curso.springboot.DAO.EnderecoDAO;
import com.curso.springboot.DAO.EstadoDAO;
import com.curso.springboot.DAO.ProdutoDAO;
import com.curso.springboot.domain.Categoria;
import com.curso.springboot.domain.Cidade;
import com.curso.springboot.domain.Cliente;
import com.curso.springboot.domain.Endereco;
import com.curso.springboot.domain.Estado;
import com.curso.springboot.domain.Produto;
import com.curso.springboot.domain.enums.TipoCliente;
import com.sun.org.apache.bcel.internal.generic.AALOAD;
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
    @Autowired
    private EnderecoDAO enderecoDAO;
    @Autowired
    private ClienteDAO clienteDAO;
    
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
        /*---------------------------------------------------------------------*/
        // Cliente Endereco
        //Cliente cliente = new Cliente(null, "Fulano", "fulano@gmail.com", "01234567890", TipoCliente.PESSOA_FISICA);
        Cliente cliente1 = new Cliente(null, "Maria", "maria@gmail.com", "01234567891", TipoCliente.PESSOA_FISICA);
        cliente1.getTelefones().addAll(Arrays.asList("0123456789","0123456780"));
        
        Endereco endereco1 = new Endereco(null, "Rua Flores", "300", "apto 303", "Jardim", "01234567", cliente1, cidade1);
        Endereco endereco2 = new Endereco(null, "Avenida Matos", "105", "sala 800", "Centro", "01234568", cliente1, cidade2);
        
        cliente1.getEnderecos().addAll(Arrays.asList(endereco1,endereco2));
        
        clienteDAO.saveAll(Arrays.asList(cliente1));
        enderecoDAO.saveAll(Arrays.asList(endereco1,endereco2));
    }
}
