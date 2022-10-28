public enum Operation {
        ADD("+", 1), SUB("-", 1), MUL(""+'\u00D7', 2), DIV(""+'\u00F7', 2),
        CUBED("" +'\u00B3', 3), SQUARED("" +'\u00B2', 3), ROOT("" +'\u221A',3),
        EQUALS("=",0);
        private String name;
        private int value;

    Operation(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
