package hu.eke.multiparadigmasprogramozasinyelvekbeadando.dao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Setter
@Getter
@ToString
@Table(name="Addresses")
public class Address {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String search;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name="geolocation_search_id",referencedColumnName = "id", nullable=false)
    private GeoLocationSearch geoLocationSearch;
}
