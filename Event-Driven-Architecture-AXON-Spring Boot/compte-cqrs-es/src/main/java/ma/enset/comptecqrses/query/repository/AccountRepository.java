package ma.enset.comptecqrses.query.repository;

import ma.enset.comptecqrses.query.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface AccountRepository extends JpaRepository<Account,String> {
}
