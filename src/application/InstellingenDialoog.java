package application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class InstellingenDialoog extends Stage {
	private Stage refStage;
	TextField tf1 = new TextField();
	TextField tf2 = new TextField();
	Button annuleren = new Button("Annuleren");
	Button ok = new Button("Ok");
	
	public InstellingenDialoog(Stage owner, int b, int h) {
		super(StageStyle.UTILITY);
		refStage = owner;
		initOwner(owner);
		initModality(Modality.WINDOW_MODAL);
		this.setResizable(false);
		HBox hb = new HBox(5);
		
		Label hoogte = new Label("Hoogte:");
		hoogte.setPrefWidth(250);
		Label breedte = new Label("Breedte:");
		breedte.setPrefWidth(250);
		
		annuleren.setOnAction(e -> this.hide());
		
		ok.setOnAction(e -> { 
				this.hide();
		});
		ok.setPrefWidth(75);
		
		VBox controlBox = new VBox(10);
		hb.getChildren().addAll(annuleren, ok);
		hb.setAlignment(Pos.BOTTOM_RIGHT);
		controlBox.setAlignment(Pos.CENTER);
		controlBox.getChildren().addAll(hoogte, tf1, breedte, tf2, hb);
		controlBox.setPadding(new Insets(15));
		Scene scene = new Scene(controlBox);
		this.setScene(scene);

	}
	
	public void Melding() {
		InstellingenDialoog al = new InstellingenDialoog(refStage, 300, 300);
		al.showAndWait();
	}

	public int getBreedte() {
		return Integer.parseInt(tf1.getText());
	}
	
	public int getHoogte() {
		return Integer.parseInt(tf2.getText());
	}

}
