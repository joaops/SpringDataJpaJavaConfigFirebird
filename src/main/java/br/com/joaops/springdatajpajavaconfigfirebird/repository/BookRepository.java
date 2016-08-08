/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.joaops.springdatajpajavaconfigfirebird.repository;

import br.com.joaops.springdatajpajavaconfigfirebird.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author João Paulo
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    
}