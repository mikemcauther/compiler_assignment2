package syms;

import source.ErrorHandler;
import syms.Type.EnumerationType;

import java.util.ArrayList;
import java.util.List;
import syms.Type.FunctionType;
import syms.Type.ProductType;
import syms.Type.ScalarType;
import tree.Operator;

/**
 * class Predefined - handles the predefined types and symbols.
 */

public class Predefined {
    /**
     * Predefined integer type.
     */
    public static ScalarType INTEGER_TYPE;
    /**
     * Predefined boolean type.
     */
    public static EnumerationType BOOLEAN_TYPE;

    /**
     * Add the predefined constants, types and operators
     *
     * @param predefined scope to which entries are added
     */
    static void addPredefinedEntries(Scope predefined) {
        // Define types needed for predefined entries
        /* Predefined integer type. */
        INTEGER_TYPE = new ScalarType("int", Type.SIZE_OF_INT,
                Integer.MIN_VALUE, Integer.MAX_VALUE) {
        };
        /* Predefined boolean type. */
        List<Type.EnumerationElement> booleans =
                new ArrayList<>();
        booleans.add(new Type.EnumerationElement("false", ErrorHandler.NO_LOCATION));
        booleans.add(new Type.EnumerationElement("true", ErrorHandler.NO_LOCATION));
        BOOLEAN_TYPE = new EnumerationType(ErrorHandler.NO_LOCATION, booleans, predefined);
        /* As some operators on boolean return booleans, we need to set up the type
         * before adding the operators on boolean.
         */
        BOOLEAN_TYPE.addOperators();

        ProductType PAIR_INTEGER_TYPE = new ProductType(INTEGER_TYPE, INTEGER_TYPE);
        ProductType PAIR_BOOLEAN_TYPE = new ProductType(BOOLEAN_TYPE, BOOLEAN_TYPE);
        FunctionType ARITHMETIC_BINARY = new FunctionType(PAIR_INTEGER_TYPE, INTEGER_TYPE);
        FunctionType INT_RELATIONAL_TYPE = new FunctionType(PAIR_INTEGER_TYPE, BOOLEAN_TYPE);
        FunctionType LOGICAL_BINARY = new FunctionType(PAIR_BOOLEAN_TYPE, BOOLEAN_TYPE);
        FunctionType ARITHMETIC_UNARY = new FunctionType(INTEGER_TYPE, INTEGER_TYPE);
        FunctionType LOGICAL_UNARY = new FunctionType(BOOLEAN_TYPE, BOOLEAN_TYPE);
        // Add predefined symbols to predefined scope
        predefined.addType("int", ErrorHandler.NO_LOCATION, INTEGER_TYPE);
        predefined.addType("boolean", ErrorHandler.NO_LOCATION, BOOLEAN_TYPE);
        predefined.addOperator(Operator.NEG_OP, ErrorHandler.NO_LOCATION, ARITHMETIC_UNARY);
        predefined.addOperator(Operator.ADD_OP, ErrorHandler.NO_LOCATION, ARITHMETIC_BINARY);
        predefined.addOperator(Operator.SUB_OP, ErrorHandler.NO_LOCATION, ARITHMETIC_BINARY);
        predefined.addOperator(Operator.MUL_OP, ErrorHandler.NO_LOCATION, ARITHMETIC_BINARY);
        predefined.addOperator(Operator.DIV_OP, ErrorHandler.NO_LOCATION, ARITHMETIC_BINARY);
        predefined.addOperator(Operator.EQUALS_OP, ErrorHandler.NO_LOCATION,
                INT_RELATIONAL_TYPE);
        predefined.addOperator(Operator.NEQUALS_OP, ErrorHandler.NO_LOCATION,
                INT_RELATIONAL_TYPE);
        predefined.addOperator(Operator.GREATER_OP, ErrorHandler.NO_LOCATION,
                INT_RELATIONAL_TYPE);
        predefined.addOperator(Operator.LESS_OP, ErrorHandler.NO_LOCATION,
                INT_RELATIONAL_TYPE);
        predefined.addOperator(Operator.GEQUALS_OP, ErrorHandler.NO_LOCATION,
                INT_RELATIONAL_TYPE);
        predefined.addOperator(Operator.LEQUALS_OP, ErrorHandler.NO_LOCATION,
                INT_RELATIONAL_TYPE);
    }
}
