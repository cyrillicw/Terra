public class GameField {
    private static final int maxHeight = 128;
    private final int n;
    private final int m;
    private GameElement[][] field;
    private Boy boy;
    private final int randomState = 17;
    private int left;
    private int up;
    private HeightsGenerator heightsGenerator;
    public GameField(int n, int m) {
        this.n = n;
        this.m = m;
        field = new GameElement[n][m];
        left = 0;
        up = 0;
        heightsGenerator = new HeightsGenerator(1 << 20);
        initField();
    }

    private void initField() {
//        Random random = new Random(randomState);
//        int boy_i = n / 2;
//        int boy_j = m / 2;
//        boy = new Boy(boy_i, boy_j);
//        for (int i = 0; i < boy.getHeight(); i++) {
//            for (int j = 0; j < boy.getWidth(); j++) {
//                field[boy_i + i][boy_j + j] = boy;
//            }
//        }
//        Block[] blocks = Block.values();
//        int blockN = blocks.length;
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                if (field[i][j] == null) {
//                    //int choose = ((new Random(i).nextInt(blockN)) * (new Random(j).nextInt(blockN)) * (new Random(randomState).nextInt(blockN))) % blockN;
//                    field[i][j] = Block.EMPTY;
//                }
//            }
//        }
        int max = -1;
        for (int j = 0; j < m; j++) {
            int height = (int)(heightsGenerator.getHeight(j + left) * maxHeight);
            max = Math.max(max, height);
            height += up;
            height = Math.max(0, Math.min(height, n));
            //System.out.println(height);
            for (int i = 0; i < Math.min(height, n); i++) {
                field[n - i - 1][j] = Block.FULL;
            }
            for (int i = height; i < n; i++) {
                field[n - i - 1][j] = Block.EMPTY;
            }
        }
        System.out.println(max);
    }

    public void goRight() {
        left++;
        initField();
    }

    public void goLeft() {
        if (left > 0) {
            left--;
            initField();
        }
    }

    public void goUp() {
        up--;
        initField();
    }

    public void goDown() {
        up++;
        initField();
    }

    public GameElement getGameElement(int i, int j) {
        if (i < 0 || i >= n || j < 0 || j >= m) {
            throw new IllegalArgumentException("i: " + n + ", j: " + j);
        }
        return field[i][j];
    }
}
