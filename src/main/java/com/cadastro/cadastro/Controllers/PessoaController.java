package com.cadastro.cadastro.Controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cadastro.cadastro.Models.Pessoa;
import com.cadastro.cadastro.Repository.PessoaRepository;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {
    @Autowired
    private PessoaRepository _pessoaRepository;
    
    @GetMapping("/listar")
    public List<Pessoa> getAllPersons() {
        return _pessoaRepository.findAll();
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<Pessoa> getPersonById(@PathVariable(value = "id") long id) {
        Optional<Pessoa> pessoa = _pessoaRepository.findById(id);
        if (pessoa.isPresent())
            return new ResponseEntity<Pessoa>(pessoa.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/criar")
    public Pessoa createPerson(@Valid @RequestBody Pessoa pessoa) {
        return _pessoaRepository.save(pessoa);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Pessoa> changePerson(@PathVariable(value = "id") long id, @Valid @RequestBody Pessoa newPessoa) {
        Optional<Pessoa> oldPessoa = _pessoaRepository.findById(id);
        if (oldPessoa.isPresent()) {
            Pessoa pessoa = oldPessoa.get();
            pessoa.setName(newPessoa.getName());
            pessoa.setAge(newPessoa.getAge());
            _pessoaRepository.save(pessoa);
            return new ResponseEntity<Pessoa>(pessoa, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Object> deletePerson(@PathVariable(value = "id") long id) {
        Optional<Pessoa> pessoa = _pessoaRepository.findById(id);

        if (pessoa.isPresent()) {
            _pessoaRepository.delete(pessoa.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }




}   
