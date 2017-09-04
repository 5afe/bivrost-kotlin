package exceptions

class InvalidBitLengthException(message: String?) : Exception(message) {
    companion object {
        val NOT_MULTIPLE_OF_EIGHT = InvalidBitLengthException("The bit length of the value is not a multiple of 8")
        val BIG_VALUE = InvalidBitLengthException("The bit length of the value is too big")
    }
}