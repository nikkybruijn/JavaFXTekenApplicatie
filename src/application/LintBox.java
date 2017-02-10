package application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class LintBox extends HBox {
	public enum Vorm {
		LIJN, RECHTHOEK, CIRKEL, PEN
	};

	ColorPicker cp = new ColorPicker();

	public LintBox() {
		cp.setPrefWidth(100);
		cp.setValue(Color.BLACK);

		FlowPane fp = new FlowPane();
		fp.setVgap(20);
		fp.setPrefWidth(150);
		fp.setPadding(new Insets(5));

		ToggleGroup group = new ToggleGroup();
		RadioButton rb1 = new RadioButton("Lijn");
		rb1.setToggleGroup(group);
		rb1.setSelected(true);
		rb1.setPrefSize(85, 0);
		RadioButton rb2 = new RadioButton("Cirkel");
		rb2.setToggleGroup(group);
		RadioButton rb3 = new RadioButton("Rechthoek");
		rb3.setToggleGroup(group);
		rb3.setPrefSize(85, 0);
		RadioButton rb4 = new RadioButton("Pen");
		rb4.setToggleGroup(group);
		fp.getChildren().addAll(rb1, rb2, rb3, rb4);

		// Rectangles
		int i = 0;
		TilePane tp = new TilePane();
		tp.setPadding(new Insets(5));
		tp.setVgap(4);
		tp.setHgap(4);

		Rectangle rl = new Rectangle();
		rl.setWidth(100);
		rl.setHeight(22);

		Rectangle[] r = new Rectangle[10];
		Color[] c = { Color.BLACK, Color.WHITE, Color.RED, Color.ORANGE,
				Color.YELLOW, Color.GRAY, Color.BLUE, Color.FIREBRICK,
				Color.PURPLE, Color.GREEN };
		for (i = 0; i < c.length && i < 10; i++) {
			r[i] = new Rectangle();
			tp.getChildren().add(r[i]);
			r[i].setWidth(25);
			r[i].setHeight(25);
			r[i].setFill(c[i]);

			int hulp = i;
			r[i].setOnMouseClicked(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent t) {
					cp.setValue(c[hulp]);
					rl.setFill(c[hulp]);
				}
			});
		}

		VBox vb = new VBox(2);
		vb.setPadding(new Insets(5));
		vb.getChildren().addAll(cp, rl);
		this.getChildren().addAll(vb, tp, fp);
		rl.setFill(cp.getValue());

		cp.setOnAction(e -> {
			rl.setFill(cp.getValue());
		});

	}

	public Paint getKleur() {
		return cp.getValue();

	}

}
