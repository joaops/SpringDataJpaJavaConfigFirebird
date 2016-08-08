/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.joaops.springdatajpajavaconfigfirebird;

import br.com.joaops.springdatajpajavaconfigfirebird.configuration.DataConfiguration;
import br.com.joaops.springdatajpajavaconfigfirebird.model.Book;
import br.com.joaops.springdatajpajavaconfigfirebird.service.BookService;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author João Paulo
 */
public class Main {
    
    public static void main(String[] args) {
        try(AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataConfiguration.class)) {
            BookService service = context.getBean(BookService.class);
            Book book = new Book("First Book", new Date(), 33, new BigDecimal(26.50));
            service.save(book);
            List<Book> books = service.findAll();
            for (Book b : books) {
                System.out.println(b);
            }
        }
    }
    
}