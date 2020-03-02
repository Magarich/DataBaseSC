package dbsc.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "price_work")
public class PriceOfWorkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "date_add")
    private Date dateAdd;

    @Column(name = "kind_of_work")
    private String kindOfWork;

    @Column(name = "price")
    private Double price;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private OrderEntity order;
}
