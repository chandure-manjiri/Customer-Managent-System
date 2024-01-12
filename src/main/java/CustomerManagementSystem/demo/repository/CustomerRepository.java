package CustomerManagementSystem.demo.repository;

import CustomerManagementSystem.demo.model.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface CustomerRepository extends JpaRepository<Customers, UUID> {


}
// this maps the instance into table entry using jPA repository, where only functions are declare in JPA repo interface
// internally spring boot uses hibernate which implements all method wrriten in JPA repo for use, so that
//interaction with database get done automattly by hibernate internally
//so in jpa repo we need to pass table and on which UUID wants to work
//in next class we are creating instance of it, which get executed by hibernate and data will stores on database
//can access database where in this project in Postgresql
//we downloads postgresql driver which communicate with postgresql which get used by out hibernate