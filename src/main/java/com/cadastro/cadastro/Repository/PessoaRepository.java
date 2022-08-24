package com.cadastro.cadastro.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cadastro.cadastro.Models.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    
}
