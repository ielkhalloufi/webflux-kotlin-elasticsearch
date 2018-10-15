package es.example.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

class ErrorResponse @JsonCreator constructor(@param:JsonProperty("error") val error: String?)
