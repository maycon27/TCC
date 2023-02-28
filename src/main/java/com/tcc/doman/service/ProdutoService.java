package com.tcc.doman.service;

import com.tcc.api.dto.CaminhoImagemDTO;
import com.tcc.api.dto.NomeDTO;
import com.tcc.api.dto.ProdutoDTO;
import com.tcc.api.dto.ProdutoResumoDTO;
import com.tcc.api.mappers.ProdutoMapper;
import com.tcc.api.resources.swagger.model.PagedModel;
import com.tcc.doman.model.Produto;
import com.tcc.doman.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private static String caminhoImagem = "D:/Projetos/TCC/Imagens/";

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private ProdutoMapper mapper;

    public PagedModel<ProdutoResumoDTO> findAll(String search , Pageable pageable){
        return repository.findAll(search,pageable);
    }

    @Transactional
    public ProdutoDTO criar(ProdutoDTO dto){

        var produto = mapper.toDomainObject(dto);

        produto = repository.save(produto);

        dto.setId(produto.getId());

        return dto;
    }

    @Transactional
    public void atualizar(Integer id, ProdutoDTO dto){

        var produtoSalvo = findById(id);

        mapper.copyToDomainObject(dto, produtoSalvo);

        repository.save(produtoSalvo);
    }

    @Transactional
    public void excluir(Integer id){ repository.deleteById(id);}


    public List<NomeDTO> findByNome(String filter){
        return repository.findByNome("%" + filter.toLowerCase() + "%");
    }

    public Optional<ProdutoDTO> buscarPorIdDTO(Integer id){
        var produto = repository.findById(id);

        if(produto.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }

        var tipoEstabelecimentoOPT = Optional.of(mapper.toModel(produto.get()));
        return tipoEstabelecimentoOPT;
    }

    private Produto findById(Integer id){
        Optional<Produto> produtosalvo = repository.findById(id);
        if(produtosalvo.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }
        return produtosalvo.get();
    }

    public CaminhoImagemDTO uploadImagemProduto(MultipartFile arquivo)  {
        var caminhoImagemDto = new CaminhoImagemDTO();
        try {
            if(!arquivo.isEmpty()) {
                byte[] bytes = arquivo.getBytes();
                Path caminho = Paths.get(caminhoImagem+arquivo.getOriginalFilename());
                Files.write(caminho,bytes);
                caminhoImagemDto.setCaminhoImagem(caminhoImagem+arquivo.getOriginalFilename());
            }
        } catch (IOException e) {
                throw new RuntimeException(e);
        }

        return caminhoImagemDto;

    }

}
