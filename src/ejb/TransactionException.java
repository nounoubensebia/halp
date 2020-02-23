package ejb;

import javax.servlet.ServletException;

public class TransactionException extends ServletException {

    public TransactionException(String message) {
        super(message);
    }


}
