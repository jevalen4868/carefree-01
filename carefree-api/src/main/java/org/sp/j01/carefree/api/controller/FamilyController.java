package org.sp.j01.carefree.api.controller;

import org.sp.j01.carefree.api.db.FamilyRepository;
import org.sp.j01.carefree.api.domain.Family;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="/family")
public class FamilyController {
    private FamilyRepository familyRepository;

    public FamilyController(FamilyRepository familyRepository) {
        this.familyRepository = familyRepository;
    }

    @RequestMapping(path = "/helloWorld/{name}", method = RequestMethod.GET)
    public String helloWorld(@PathVariable(name = "name") String name) {
        return "Hello World, and " + name + ", if you're there.";
    }

    @RequestMapping(path = "/get/{familyId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional(readOnly = true)
    public Family get(@PathVariable(name = "familyId") Long familyId) {
        return familyRepository.getOne(familyId);
    }

    @RequestMapping(path = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional(readOnly = true)
    public List<Family> getAll() {
        return familyRepository.getAll();
    }
}
