package hu.eke.multiparadigmasprogramozasinyelvekbeadando.dao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="geolocation_search")
@Setter
@ToString
public class GeoLocationSearch {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy="geoLocationSearch",cascade=CascadeType.PERSIST)
    private List<Address> addresses;
}
