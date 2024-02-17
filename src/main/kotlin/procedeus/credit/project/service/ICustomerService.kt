package procedeus.credit.project.service

import procedeus.credit.project.entity.Customer

interface ICustomerService {
    fun save(customer: Customer): Customer
    fun  findById(id: Long): Customer
    fun delete(id: Long)
}