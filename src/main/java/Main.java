import javafx.application.Application;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    public static void initCanvas(GraphicsContext gc, int width, int height, int size) {
        final Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW};
        for (int i = 0; i < width; i += size) {
            for (int j = 0; j < height; j += size) {
                gc.setFill(colors[(i / size + j / size) % colors.length]);
                gc.fillRect(i, j, size, size);
            }
        }
    }

    public void start(Stage stage) throws Exception {
        new Drawer(stage);
    }
}
