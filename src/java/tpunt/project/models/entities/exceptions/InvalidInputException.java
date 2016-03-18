package tpunt.project.models.entities.exceptions;

/**
 * This exception is thrown in the domain model objects upon validation errors.
 * 
 * @author tpunt
 */
public class InvalidInputException extends Exception {
    public InvalidInputException(String error) {
        super(error);
    }
}
