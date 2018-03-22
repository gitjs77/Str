package csvCheck

/**
 * @Author Jack <e.kobets>
 */
class IncorrectDataException extends RuntimeException {
    IncorrectDataException(String s) {
        super(s)
    }
}
