package procedeus.credit.project.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import procedeus.credit.project.entity.Customer
import java.math.BigDecimal

data class CustomerUpdateDto(
    @field:NotEmpty(message = "First Name cannot be null") val firstName: String,
    @field:NotEmpty(message = "Last Name cannot be null") val lastName: String,
    @field:NotNull(message = "Income cannot be null") val income: BigDecimal,
    @field:NotEmpty(message = "Zip-Code cannot be null") val zipCode: String,
    @field:NotEmpty(message = "Street cannot be null") val street: String
) {
    fun toEntity(customer: Customer): Customer {
        customer.firstName = this.firstName
        customer.lastName = this.lastName
        customer.income = this.income
        customer.address.zipCode = this.zipCode
        customer.address.street = this.street
        return customer
    }
}
