package com.cromero.controller;

import com.cromero.model.Card;
import com.cromero.service.CardService;
import com.cromero.service.HibernateSearchService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CardController {

    @Autowired
    private HibernateSearchService hibernateSearchService;

    @Autowired
    private CardService cardService;

    private ObjectMapper mapper = new ObjectMapper();

    @RequestMapping(value = "/", method = RequestMethod.GET, consumes="application/json", produces="application/json")
    @ResponseBody
    public String search(@RequestBody String q) {
        String searchResults = null;
        try {
            cardService.addCards();
            searchResults = mapper.writeValueAsString(hibernateSearchService.magicSearch(q));
        } catch (Exception ex) {

        }
        return searchResults;
    }

//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public List<Card> search(String q) {
//        List<Card> searchResults = null;
//        try {
//            cardService.addCards();
//            searchResults = hibernateSearchService.magicSearch(q);
//        } catch (Exception ex) {
//
//        }
//        return searchResults;
//    }
}
