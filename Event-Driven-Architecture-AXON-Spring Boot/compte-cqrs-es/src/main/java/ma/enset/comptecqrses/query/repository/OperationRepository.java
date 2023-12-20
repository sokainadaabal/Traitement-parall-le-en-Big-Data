package ma.enset.comptecqrses.query.repository;

import ma.enset.comptecqrses.query.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository  extends JpaRepository<Operation,Long> {

}
