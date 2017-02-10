package application;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;

public class TekenCanvas extends Canvas {
	private LintBox box;
	
	public TekenCanvas(LintBox hL, int breedte, int hoogte) {
		super(breedte, hoogte);
		box = hL;
		GraphicsContext gc = this.getGraphicsContext2D();
		gc.strokeRect(5, 5, breedte - 9, hoogte - 10);

		this.setOnMousePressed(event -> {
			if (event.getButton() == MouseButton.PRIMARY) {
				gc.beginPath();
				gc.setStroke(hL.getKleur());
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