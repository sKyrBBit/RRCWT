package ir;

public class LocalLabel implements Code {
    private final int index; // TODO String
    public LocalLabel(final int index) {
        this.index = index;
    }
    @Override
    public String toString() {
        return "$" + index + ":\n";
    }
    @Override
    public String build() {
        return ".L" + index + ":\n";
    }
    @Override
    public Code reduce() {
        return this;
    }
    @Override
    public int toWC() {
        return 0;
    }
    @Override
    public String toAssembly() {
        return "";
    }
    public String name() { // TODO remove
        return "$" + index;
    }
}
