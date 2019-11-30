public class Boy implements GameElement{
    private int i;
    private int j;
    public Boy(int i, int j) {
        this.i = i;
        this.j = j;
    }

    @Override
    public int getHeight() {
        return 2;
    }

    @Override
    public int getWidth() {
        return 1;
    }
}
