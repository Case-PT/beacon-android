package li.brianv.domain

data class Form(val reporterName: String, val reporterNumber: String, val reporterEmail: String,
                val reportedName: String, val reportedNumber: String, val reportedEmail: String, val reportedAddress: String)