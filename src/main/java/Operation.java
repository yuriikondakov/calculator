public enum Operation {
    ADD("+", 1),
    SUBTRACTION("-", 1),
    MULTIPLY("*",2),
    DIVISION("/",2);

    final String sign;
    final int priority;

    public static Operation valueOfSign(String sign) {
        for (Operation o : values()) {
            if (o.sign.equals(sign)) {
                return o;
            }
        }
        return null;
    }

    Operation(String sign, int priority) {
        this.sign = sign;
        this.priority = priority;
    }
}
