package hu.eke.multiparadigmasprogramozasinyelvekbeadando.repository;

import hu.eke.multiparadigmasprogramozasinyelvekbeadando.dao.GeoLocationSearch;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeoLocationSearchRepository extends CrudRepository<GeoLocationSearch,Long> {
}
