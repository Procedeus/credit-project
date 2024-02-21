package procedeus.credit.project.service.impl

import org.springframework.stereotype.Service
import procedeus.credit.project.entity.Customer
import procedeus.credit.project.exception.BusinessException
import procedeus.credit.project.repository.CustomerRepository
import procedeus.credit.project.service.ICustomerService

@Service
class CustomerService(
    private val customerRepository: CustomerRepository
): ICustomerService {
    override fun save(customer: Customer): Customer =
        this.customerRepository.save(customer)

    override fun findById(id: Long): Customer =
        this.customerRepository.findById(id).orElseThrow{
            throw BusinessException("ID: '$id' não encontrado")
        }

    override fun delete(id: Long){
        val customer: Customer = this.findById(id)
        this.customerRepository.delete(customer)
    }
}