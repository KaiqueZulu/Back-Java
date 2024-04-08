package com.example.primeiroexemplo.services;

import com.example.primeiroexemplo.model.Produto;
import com.example.primeiroexemplo.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    /**
     * Metodo para retornar uma lista de produtos.
     * @return Lista de Produtos.
     */
    public List<Produto> obterTodos(){
        return produtoRepository.obterTodos();
    }

    /**
     * Metodo que retorna o produto encontrado pelo seu id.
     * @param id do produto que sera localizado.
     * @return Retorna um produto caso encontrado.
     */
    public Optional<Produto> obterPorId(Integer id) {
        return produtoRepository.obterPorId(id);
    }

    /**
     * Metodo para adicionar produto na lista.
     * @param produto que sera adicionado.
     * @return Retorna o produto que foi adicionado na lista.
     */
    public Produto adicionar(Produto produto) {
        return produtoRepository.adicionar(produto);
    }

    /**
     * Metodo para deletar o produto por id.
     * @param id do produto a ser deletado.
     */
    public void deletar(Integer id){
        produtoRepository.deletar(id);
    }

    /**
     * Metodo para atualizar o produto na lista
     * @param id do produto que sera atualizado.
     * @param produto que sera atualizado.
     * @return Retorna produto apos atualizar a lista
     * */
    public Produto atualizar(Integer id, Produto produto) {
        produto.setId(id);
        return produtoRepository.atualizar(produto);
    }
}
