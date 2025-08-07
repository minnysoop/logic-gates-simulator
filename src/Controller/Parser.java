package Controller;

import Views.LogicGatesSimulatorOutput;

import java.util.ArrayList;
import java.util.Set;
import java.util.Stack;
import java.util.Map;

public class Parser {
    private static final Set<String>operations = Set.of("+", "*", "~");
    private static final Map<String, Integer>precedence = Map.of(
            "+", 1,
            "*", 2,
            "~", 3
    );

    static class Token {
        String value = "";

        public void setValue(String value) {
            this.value = value;
        }

        public boolean isOperator() {
            return operations.contains(this.value);
        }

        public String getTokenAsString() {
            return this.value;
        }

        // Debug
        public void printToken() {
            System.out.print(this.value + " ");
        }
    }

    // Dependency injection through the constructor
    public Parser(String expression, LogicGatesSimulatorOutput output) {
        // This renders this expression on to the SimulatorOutput object
        ArrayList<Token>tokens = tokenizer(expression);
        try {
            String error = checkForSyntaxErrors(tokens);
            output.render(shuntingYard(tokens), error);
        } catch (Exception e) {
            output.render("", "Syntax error please retype your expression");
        }

    }

    private ArrayList<Token> tokenizer(String expression) {
        ArrayList<Token>tokens = new ArrayList<Token>();
        int n = expression.length();
        int i = 0;
        while (i < n) {
            String currentCharacter = String.valueOf(expression.charAt(i));
            if (currentCharacter.equals(" ") || currentCharacter.equals("\n") || currentCharacter.equals("\t")) {
                i++;
                continue;
            }
            Token t = new Token();
            t.setValue(currentCharacter);
            tokens.add(t);
            i++;
        }
        return tokens;
    }

    // The Shunting Yard Algorithm: https://brilliant.org/wiki/shunting-yard-algorithm/
    private String shuntingYard(ArrayList<Token> tokens) {
        StringBuilder result = new StringBuilder();
        Stack<Token> operator = new Stack<Token>();

        int n = tokens.size();
        for (int i = 0; i < n; i++) {
            Token t = tokens.get(i);

            if (!t.isOperator() &&
                    !t.getTokenAsString().equals("(") &&
                    !t.getTokenAsString().equals(")")) {
                result.append(t.getTokenAsString());
            } else if (t.getTokenAsString().equals("(")) {
                operator.push(t);
            } else if (t.getTokenAsString().equals(")")) {
                if (!operator.isEmpty()) {
                    while (!operator.peek().getTokenAsString().equals("(")) {
                        result.append(operator.pop().getTokenAsString());
                    }
                    operator.pop();
                }
            } else if (t.isOperator()) {
                while (!operator.isEmpty() &&
                        operator.peek().isOperator() &&
                        precedence.get(operator.peek().getTokenAsString()) > precedence.get(t.getTokenAsString())) {
                    result.append(operator.pop().getTokenAsString());
                }
                operator.push(t);
            }
        }

        while (!operator.isEmpty()) {
            result.append(operator.pop().getTokenAsString());
        }

        return result.toString();
    }

    private String checkForSyntaxErrors(ArrayList<Token> tokens) {
        Stack<String> openParentheses = new Stack<String>();

        int n = tokens.size();
        for (int i = 0; i < n; i++) {
            String prev = i > 0 ? tokens.get(i-1).getTokenAsString() : null;
            String current = tokens.get(i).getTokenAsString();
            String next = i + 1 < n ? tokens.get(i+1).getTokenAsString() : null;

            if (current.equals("(")) {
                openParentheses.push(current);
            }
            else if (current.equals(")")) {
                if (openParentheses.isEmpty()) { return "Unmatched closing parenthesis at position " + i; }
                openParentheses.pop();
            }
            else if (operations.contains(current)) {
                if (!current.equals("~")) {
                    if ((prev != null && (operations.contains(prev) || prev.equals("("))) ||
                            (next != null && !next.equals("~") && operations.contains(next)) ||
                            (next != null && next.equals(")"))
                    ){
                        return "Unexpected input for binary operation at position " + i;
                    }
                }

                if (next == null) {
                    return "Can't end expression with an operator";
                }
            } else if (current.matches("[A-Za-z]+")){
                if (next != null && (next.matches("[A-Za-z]+") || next.equals("(") || next.equals("~"))) { return "Unexpected extra token at position " + (i+1); }
            } else {
                return "Unexpected token at position " + i;
            }
        }

        if (!openParentheses.isEmpty()) {
            return "Unmatched opening parenthesis";
        }

        return null;
    }
}
