package procedeus.credit.project.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import procedeus.credit.project.entity.Credit
import java.util.UUID

@Repository
interface CreditRepository: JpaRepository<Credit, Long>{
    fun findByCreditCode(creditCode: UUID): Credit

    @Query(value = "SELECT * FROM CREDITO WHERE CUSTOMER_ID = ?1", nativeQuery = true)
    fun findAllByCredits(customerId: Long): List<Credit>
}