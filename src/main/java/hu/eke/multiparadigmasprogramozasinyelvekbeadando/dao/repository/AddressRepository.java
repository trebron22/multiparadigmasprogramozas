package hu.eke.multiparadigmasprogramozasinyelvekbeadando.dao.repository;

import hu.eke.multiparadigmasprogramozasinyelvekbeadando.dao.Address;
import hu.eke.multiparadigmasprogramozasinyelvekbeadando.dao.GeoLocationSearch;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AddressRepository extends CrudRepository<Address,Long> {

    List<Address> findByGeoLocationSearch(GeoLocationSearch geoLocationSearch);
}
