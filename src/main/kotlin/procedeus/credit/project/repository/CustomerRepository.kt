package procedeus.credit.project.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import procedeus.credit.project.entity.Credit
import procedeus.credit.project.entity.Customer
import java.util.*

@Repository
interface CustomerRepository: JpaRepository<Customer, Long>{
    fun findByCreditCode(creditCode: UUID): Credit

    @Query(value = "SELECT * FROM CREDITO WHERE CREDIT_ID = ?1", nativeQuery = true)
    fun findAllByCustomerId(customerId: Long): List<Credit>
}