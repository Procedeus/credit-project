package procedeus.credit.project.dto.CustomerView

import java.math.BigDecimal

class CustomerView(
    val firstAName: String,
    val lastName: String,
    val cpf: String,
    val income: BigDecimal,
    val email: String,
    val zipCode: String,
    val street: String

)