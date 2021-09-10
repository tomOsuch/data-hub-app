package pl.tomaszosuch.datahubapp.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "order")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @OneToMany(
            targetEntity = Product.class,
            mappedBy = "order",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Product> products;

    public Order(Long id, String userName) {
        this.id = id;
        this.userName = userName;
    }
}
