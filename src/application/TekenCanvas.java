package application;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;

public class TekenCanvas extends Canvas {
	private static final int XCOORDINATE = 5;
	private static final int YCOORDINATE = 5;
	
	public TekenCanvas(LintBox hL, int width, int height) {
		super(width, height);
		GraphicsContext gc = this.getGraphicsContext2D();
		gc.strokeRect(xCoordinate, YCOORDINATE, width - 9, height - 10);

		this.setOnMousePressed(event -> {
			if (event.getButton() == MouseButton.PRIMARY) {
				gc.beginPath();
				gc.setStroke(hL.getColor());
			}
		});

		this.setOnMouseDragged(event -> {
			if (event.getButton() == MouseButton.PRIMARY) {
				gc.lineTo(event.getX(), event.getY());
				gc.stroke();
			}
		});

		this.setOnMouseReleased(event -> {
			if (event.getButton() == MouseButton.PRIMARY) {
				gc.closePath();
			}
		});
	}
}
