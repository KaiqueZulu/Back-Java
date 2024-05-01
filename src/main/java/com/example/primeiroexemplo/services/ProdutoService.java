package com.example.primeiroexemplo.services;

import com.example.primeiroexemplo.model.Produto;
import com.example.primeiroexemplo.model.exception.ResourceNotFoundException;
import com.example.primeiroexemplo.repository.ProdutoRepository;
import com.example.primeiroexemplo.shared.ProdutoDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    /**
     * Metodo para retornar uma lista de produtos.
     * @return Lista de Produtos.
     */
    public List<ProdutoDTO> obterTodos(){

        List<Produto> produtos = produtoRepository.findAll();

        return produtos.stream()
                .map(produto -> new ModelMapper().map(produto, ProdutoDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Metodo que retorna o produto encontrado pelo seu id.
     * @param id do produto que sera localizado.
     * @return Retorna um produto caso encontrado.
     */
    public Optional<ProdutoDTO> obterPorId(Integer id) {
        //Obtendo optional de produto pelo id.
        Optional<Produto> produto = produtoRepository.findById(id);

        //Se não encontrar, lançar exception
        if(produto.isEmpty()){
            throw new ResourceNotFoundException("Produto com id: " + id + " não encontrado");
        }

        //Converter o meu optional de produto em um produtoDto
        ProdutoDTO dto = new ModelMapper().map(produto.get(), ProdutoDTO.class);

        //Criando e retornando um optional de produtoDTO
        return Optional.of(dto);
    }

    /**
     * Metodo para adicionar produto na lista.
     * @param produtoDto que sera adicionado.
     * @return Retorna o produto que foi adicionado na lista.
     */
    public ProdutoDTO adicionar(ProdutoDTO produtoDto) {
        //Remover o ID para conseguir fazer o cadastro.
        produtoDto.setId(null);

        //Criar um objeto mapeado.
        ModelMapper mapper = new ModelMapper();

        //Converter o nosso ProdutoDTO em um produto.
        Produto produto = mapper.map(produtoDto, Produto.class);

        //Salvar o Produto do banco
        produto = produtoRepository.save(produto);

        produtoDto.setId(produto.getId());

        //Retornar o ProdutoDTO atualizado.
        return produtoDto;
    }

    /**
     * Metodo para deletar o produto por id.
     * @param id do produto a ser deletado.
     */
    public void deletar(Integer id){
        //Verificar se o produto existe.
        Optional<Produto> produto = produtoRepository.findById(id);
        if(produto.isEmpty()){
            throw new ResourceNotFoundException("Não foi possível deletar o produto com o id: " + id + " - Produto não existe.");
        }

        produtoRepository.deleteById(id);
    }

    /**
     * Metodo para atualizar o produto na lista
     * @param id do produto que sera atualizado.
     * @param produtoDto que sera atualizado.
     * @return Retorna produto apos atualizar a lista
     * */
    public ProdutoDTO atualizar(Integer id, ProdutoDTO produtoDto) {
        //Passar o id para o produtoDto.
        produtoDto.setId(id);

        //Criar um objeto de mapeamento.
        ModelMapper mapper = new ModelMapper();

        //Converter o ProdutoDTO em um Produto.
        Produto produto = mapper.map(produtoDto, Produto.class);

        //Atualizar o produto no Banco de dados.
        produtoRepository.save(produto);

        //Retornar o produto atualizado
        return produtoDto;
    }
}
