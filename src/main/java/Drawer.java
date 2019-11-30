import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Drawer {
    private GraphicsContext gc;
    public Drawer(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
        final int w = (int)stage.getWidth();
        final int h = (int)stage.getHeight();
        int step = 40;
        Canvas canvas = new Canvas(w, h);
        root.getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();
        GameField gameField = new GameField(h / step, w / step);
        drawField(gameField, h / step, w / step, step);
        final Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW};
        scene.setOnKeyPressed((handler) -> {
            if (handler.getCode() == KeyCode.RIGHT) {
                gameField.goRight();
                drawField(gameField, h / step, w / step, step);
            }
            else if (handler.getCode() == KeyCode.LEFT) {
                gameField.goLeft();
                drawField(gameField, h / step, w / step, step);
            }
            else if (handler.getCode() == KeyCode.UP) {
                gameField.goUp();
                drawField(gameField, h / step, w / step, step);
            }
            else if (handler.getCode() == KeyCode.DOWN) {
                gameField.goDown();
                drawField(gameField, h / step, w / step, step);
            }
        });
    }

    private void drawField(GameField gameField, int n, int m, int step) {
        boolean[][] drew = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                drew[i][j] = false;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!drew[i][j]) {
                    GameElement gameElement = gameField.getGameElement(i, j);
                    for (int k = 0; k < gameElement.getHeight(); k++) {
                        for (int h = 0; h < gameElement.getWidth(); h++) {
                            drew[i + k][j + h] = true;
                        }
                    }
                    Image image;
                    Color color;
//                    if (gameElement instanceof Boy) {
//                        image = new Image("images/steve.png");
//                    }
//                    else {
//                        image = new Image("images/black.jpg");
//                    }

                    //gc.drawImage(image, j * step, i * step, step * gameElement.getWidth(), step * gameElement.getHeight());
                    if ((Block)(gameElement) == Block.EMPTY) {
                        color = Color.BLACK;
                    }
                    else {
                        color = Color.WHITE;
                    }
                    gc.setFill(color);
                    gc.fillRect(j * step, i * step, step * gameElement.getWidth(), step * gameElement.getHeight());
                }
            }
        }
    }
}
