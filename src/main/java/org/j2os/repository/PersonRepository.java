package org.j2os.repository;

import org.j2os.domain.Person;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/*
    Bahador, Amirsam
 */
@Repository
public interface PersonRepository extends PagingAndSortingRepository<Person, String> {
}
