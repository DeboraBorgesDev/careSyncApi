package br.ufsm.csi.CareSync.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import br.ufsm.csi.CareSync.exceptions.GenericException;
import br.ufsm.csi.CareSync.exceptions.NotFoundException;
import br.ufsm.csi.CareSync.models.Permissao;
import br.ufsm.csi.CareSync.repository.PermissaoRepository;

@Service
public class PermissaoService {

    @Autowired
    private PermissaoRepository permissaoRepository;

    public ResponseEntity<List<Permissao>> listarPermissoes() {
        try {
            List<Permissao> permissoes = permissaoRepository.findAll();
            return ResponseEntity.ok(permissoes);
        } catch (Exception e) {
            throw new GenericException("Erro ao listar permissões");
        }
    }

    public ResponseEntity<Permissao> buscarPermissaoPorId(UUID id) {
        try {
            Optional<Permissao> permissaoOptional = permissaoRepository.findById(id);
            if (permissaoOptional.isEmpty()) {
                throw new NotFoundException("Permissão não encontrada");
            }
            Permissao permissao = permissaoOptional.get();
            return ResponseEntity.ok(permissao);
        } catch (Exception e) {
            throw new GenericException("Erro ao buscar permissão por ID");
        }
    }

    @Transactional
    public ResponseEntity<Permissao> criarPermissao(Permissao permissao, UriComponentsBuilder uriComponentsBuilder) {
        try {
            Permissao novaPermissao = permissaoRepository.save(permissao);
            URI uri = uriComponentsBuilder.path("/permissoes/{id}").buildAndExpand(novaPermissao.getId()).toUri();
            return ResponseEntity.created(uri).body(novaPermissao);
        } catch (Exception e) {
            throw new GenericException("Erro ao criar permissão");
        }
    }

    @Transactional
    public ResponseEntity<Permissao> editarPermissao(UUID id, String nome) {
        try {
            Optional<Permissao> permissaoOptional = permissaoRepository.findById(id);
            if (permissaoOptional.isEmpty()) {
                throw new NotFoundException("Permissão não encontrada");
            }
            Permissao permissao = permissaoOptional.get();
            permissao.setNome(nome);
            Permissao permissaoAtualizada = permissaoRepository.save(permissao);
            return ResponseEntity.ok(permissaoAtualizada);
        } catch (Exception e) {
            throw new GenericException("Erro ao editar permissão");
        }
    }

    @Transactional
    public ResponseEntity<Void> deletarPermissao(UUID id) {
        try {
            if (!permissaoRepository.existsById(id)) {
                throw new NotFoundException("Permissão não encontrada");
            }
            permissaoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new GenericException("Erro ao deletar permissão");
        }
    }
}
