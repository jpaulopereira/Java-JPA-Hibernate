package com.jotape.hibernate.testes;

import com.jotape.hibernate.dao.CategoriaDAO;
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
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO produtoDao = new ProdutoDAO(em);

        Produto p = produtoDao.buscarPorId(1l);
        System.out.println(p.getPreco());

        List<Produto> todos = produtoDao.buscarPorNomeDaCategoria("CELULARES");
        todos.forEach(p2 -> System.out.println(p.getNome()));

        BigDecimal precoDoProduto = produtoDao.buscarPrecoDoProdutoComNome("Xiaomi Redmi");
        System.out.println("Preco do Produto: " + precoDoProduto);
    }

    private static void cadastrarProduto() {
        Categoria celulares = new Categoria("CELULARES");
        Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares);

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO produtoDao = new ProdutoDAO(em);
        CategoriaDAO categoriaDao = new CategoriaDAO(em);

        em.getTransaction().begin();

        categoriaDao.cadastrar(celulares);
        produtoDao.cadastrar(celular);

        em.getTransaction().commit();
        em.close();
    }
}


