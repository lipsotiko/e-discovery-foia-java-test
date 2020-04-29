package com.gdit.ediscovery.foia.vango.quote;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "quote", path = "quote")
public interface QuoteRepository extends PagingAndSortingRepository<Quote, Long> {
}
