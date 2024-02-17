package procedeus.credit.project.service.impl

import org.springframework.stereotype.Service
import procedeus.credit.project.entity.Credit
import procedeus.credit.project.repository.CreditRepository
import procedeus.credit.project.service.ICreditService
import java.util.*

@Service
class CreditService(
    private val creditRepository: CreditRepository,
    private val customerService: CustomerService
): ICreditService {
    override fun save(credit: Credit): Credit {
        credit.apply{
            customer = customerService.findById(credit.customer?.id!!)
        }
        return this.creditRepository.save(credit)
    }

    override fun findAllByCustomer(customerId: Long): List<Credit> =
        this.creditRepository.findAllByCredits(customerId)

    override fun findByCreditCode(customerId: Long, creditCode: UUID): Credit {
        val credit: Credit = this.creditRepository
            .findByCreditCode(creditCode)
            ?: throw RuntimeException("Credit Code: '$creditCode' não encontrado")
        if (credit.customer?.id == customerId) return credit
        else throw RuntimeException("Contate Adm")
    }

}