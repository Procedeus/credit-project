package procedeus.credit.project.dto

import jakarta.validation.constraints.Future
import jakarta.validation.constraints.NotNull
import procedeus.credit.project.entity.Credit
import procedeus.credit.project.entity.Customer
import java.math.BigDecimal
import java.time.LocalDate

data class CreditDto(
    @field:NotNull(message = "CustomerId cannot be null") val customerId: Long,
    @field:NotNull(message = "Value of Credit cannot be null")  val creditValue: BigDecimal,
    @field:Future(message = "Invalid Date") val dayFirstInstallment: LocalDate,
    @field:NotNull(message = "Number of Installments cannot be null") val numberOfInstallments: Int
) {
    fun toEntity(): Credit = Credit(
        customer = Customer(id = this.customerId),
        creditValue = this.creditValue,
        dayFirstInstallment = this.dayFirstInstallment,
        numberOfInstallments = this.numberOfInstallments
    )
}
