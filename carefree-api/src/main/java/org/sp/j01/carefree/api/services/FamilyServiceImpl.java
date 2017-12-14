package org.sp.j01.carefree.api.services;

import org.sp.j01.carefree.FamilyService;
import org.sp.j01.carefree.api.db.FamilyRepository;
import org.sp.j01.carefree.api.domain.Family;

public class FamilyServiceImpl implements FamilyService {
    private FamilyRepository familyRepository;

    public FamilyServiceImpl(FamilyRepository familyRepository) {
        this.familyRepository = familyRepository;
    }

    @Override
    public Family findBy(Long familyId) {
        return null;
    }
}
