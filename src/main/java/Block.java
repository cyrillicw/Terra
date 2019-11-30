public enum Block implements GameElement {
    FULL, EMPTY;

    @Override
    public int getHeight() {
        return 1;
    }

    @Override
    public int getWidth() {
        return 1;
    }
}