package dbsc.repository;

        import dbsc.entity.PriceOfWorkEntity;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.Modifying;
        import org.springframework.data.jpa.repository.Query;

public interface PriceOfWorkRepository extends JpaRepository<PriceOfWorkEntity, Integer> {

   // @Modifying
    @Query(value = "select sum(pw.price) from price_work as pw where pw.order_id=?1", nativeQuery = true) //value-присвоение запроса для выполнения, nativeQuery-Родной SQLзапрос(false=HQL)?
    Double sum(Integer id);


}
