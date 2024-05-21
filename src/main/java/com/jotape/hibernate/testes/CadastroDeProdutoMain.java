package com.jotape.hibernate.testes;

import com.jotape.hibernate.dao.ProdutoDAO;
import com.jotape.hibernate.modelo.Categoria;
import com.jotape.hibernate.modelo.Produto;
import com.jotape.hibernate.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDeProdutoMain {

    public static void main(String[] args) {
        cadastrarProduto();
        Long id = 1L;
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(em);

        Produto p = produtoDAO.buscarPorId(1L);
        System.out.println(p.getPreco());

        List<Produto> todos = produtoDAO.buscarTodos();
        todos.forEach(product -> System.out.println(p.getNome()));

        List<Produto> buscarPorNomeDaCategoria = produtoDAO.buscarPorNomeDaCategoria("Celulares");
        todos.forEach(product -> System.out.println(p.getNome()));

        BigDecimal precoDoProduto = produtoDAO.buscarPrecoDoProdutoComNome("Xiaomi Redmi");
        System.out.println(precoDoProduto);
    }

    private static void cadastrarProduto() {
        Categoria celulares = new Categoria("Celulares");
        Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares);

        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();

        em.persist(celulares);
        celulares.setNome("XPTO");

        em.flush();
        em.clear();

        em.merge(celulares);
        celulares.setNome("1234");
        em.flush();
    }
}


