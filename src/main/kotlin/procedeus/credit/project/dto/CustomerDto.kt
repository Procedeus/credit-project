package procedeus.credit.project.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.br.CPF
import procedeus.credit.project.entity.Address
import procedeus.credit.project.entity.Customer
import java.math.BigDecimal

data class CustomerDto(
    @field:NotEmpty(message = "First Name cannot be null") val firstName: String,
    @field:NotEmpty(message = "Last Name cannot be null") val lastName: String,
    @field:NotEmpty(message = "CPF cannot be null")
    @field:CPF(message = "Invalid CPF") val cpf: String,
    @field:NotNull(message = "Income cannot be null") val income: BigDecimal,
    @field:Email(message = "Invalid Email")
    @field:NotEmpty(message = "Email cannot be null") val email: String,
    @field:NotEmpty(message = "Password cannot be null") val password: String,
    @field:NotEmpty(message = "Zip-Code cannot be null") val zipCode: String,
    @field:NotEmpty(message = "Street cannot be null") val street: String
) {
    fun toEntity(): Customer = Customer(
        firstName = this.firstName,
        lastName = this.lastName,
        cpf = this.cpf,
        income = this.income,
        email = this.email,
        password = this.password,
        address = Address(
            zipCode = this.zipCode,
            street = this.street
        )

    )
}
