package org.sp.j01.carefree.api.db;

import org.sp.j01.carefree.api.domain.Family;

import java.util.List;


/**
 * Repository interface with operations for {@link Family} persistence.
 * @author j01
 */
public interface FamilyRepository {

  Long count();

  Family save(Family family);

  Family getOne(Long id);

  Family getBy(String familyName);

  List<Family> getAll();

}
