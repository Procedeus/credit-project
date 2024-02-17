package procedeus.credit.project.service.impl

import procedeus.credit.project.entity.Customer
import procedeus.credit.project.repository.CustomerRepository
import procedeus.credit.project.service.ICustomerService

class CustomerService(
    private val customerRepository: CustomerRepository
): ICustomerService {
    override fun save(customer: Customer): Customer =
        this.customerRepository.save(customer)

    override fun findById(id: Long): Customer =
        this.customerRepository.findById(id).orElseThrow{
            throw RuntimeException("ID: '$id' não encontrado")
        }

    override fun delete(id: Long) =
        this.customerRepository.deleteById(id)
}