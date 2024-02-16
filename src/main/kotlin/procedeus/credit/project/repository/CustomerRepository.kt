package procedeus.credit.project.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import procedeus.credit.project.entity.Customer

@Repository
interface CustomerRepository: JpaRepository<Customer, Long>